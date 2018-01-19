package api.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import api.models.common.CommonContactDeleteModel;
import api.models.common.CommonCounterpartyModel;
import api.models.counterparty.CounterpartyLoadCounterpartiesListModel;
import api.service.Sender;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import static api.utils.Constants.*;

/**
 * @Author QA-departament of NP
 * tool class with static methods for (@link resources)
 * Project: Test API for "New Post"
 */

public final class ToolsJSON {

    private ToolsJSON() {};

    // The method converts Object to JSON as string
    public static String parseObjectToJson(Object object) throws IOException {
        return new ObjectMapper().writeValueAsString(object);
    }

    // The method reads properties as strings from file to collection List<String> with filter
    public static List<String> getPropertiesFromFileWithFilter(String filePath, String filter) throws IOException {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            list = stream
                    .filter(line -> !line.startsWith(filter))
                    .map(line -> line.trim())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // The method reads properties as strings from file to collection List<String>
    public static List<String> readPropertiesFromFile(String filePath) throws IOException {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            list = stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // The method writes properties to file as strings. Used split(regex)
    public static void writePropertiesToFile(String filePath, String string) throws IOException {
        Files.write(Paths.get(filePath), Arrays.asList(string), StandardCharsets.UTF_8);
    }

    // The method returns String array from string
    public static String[] getPropertiesFromListLine(String stringFromList) {
        return stringFromList.trim().split(REGEX_SPLIT_SPACE);
    }

    // The method takes a JSON as string and string param as params and returns collection List<String>
    public static List<String> getParamsValueAsList(String jsonString, String param) throws IOException {
        return Arrays.asList(new ObjectMapper().readTree(jsonString).findValues(param).toString().replaceAll(REGEX_REPLACE_QUOTES_BRACKETS, "").split(REGEX_SPLIT_COMMA));
    }

    //The method deletes a symbols in the any string of collection List<J>
    public static List<String> deleteSymbols(List<String> list, String regex) {
        return list.stream().map(s -> s.replaceAll(REGEX_REPLACE_QUOTES, "")).collect(Collectors.toList());
    }

    // The method takes a JSON as string and collection List<String> as params and returns collection List<List<JsonNode>>. Used replaceAll(regex)
    public static List<List<String>> getParamsValuesAsListArray(String jsonString, List<String> paramList) throws IOException {
        List<List<String>> listOfListFieldsValues = new ArrayList<>();
        for (String param : paramList) {
            listOfListFieldsValues.add(Arrays.asList(new ObjectMapper().readTree(jsonString).findValues(param).toString().replaceAll(REGEX_REPLACE_QUOTES_BRACKETS, "").split(REGEX_SPLIT_COMMA)));
        }
        return listOfListFieldsValues;
    }

    // The method takes a JSON as string and collection List<String> as params and returns collection List<List<JsonNode>>. Used replaceAll(regex)
    public static List<String> getParamsValuesAsList(String jsonString, List<String> paramList) throws IOException {
        List<String> listFieldsValues = new ArrayList<>();
        String string;
        for (String param : paramList) {
            string = new ObjectMapper().readTree(jsonString).findValues(param).toString().replaceAll(REGEX_REPLACE_QUOTES_BRACKETS, "");
            if (string.equals(", ") || string.equals("")) {
                string = param + ": []";
            } else {
                string = param + ": " + string;
            }
            listFieldsValues.add(string);
        }
        return listFieldsValues;
    }

    // The method takes a JSON as string and collection List<String> as params and returns string. Used replaceAll(regex)
    public static String getParamValueFromList(String jsonString, String param, int i) throws IOException {
        return new ObjectMapper().readTree(jsonString).findValues(param).get(i).toString().replaceAll(REGEX_REPLACE_QUOTES, "");
    }

    // The method contains List<JsonNode> with List<Strings>. If the collection equal then returns TRUE else returns FALSE. Used split(regex)
    public static boolean checkValuesInJsonNodeList(List<String> paramsValueActualList, List<String> paramsValueExpectedList) throws IOException {
        return paramsValueActualList.containsAll(paramsValueExpectedList);
    }

    // The method takes collection List<String> as param and returns JSON as string
    public static String getJsonRef(List<String> jsonList) {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode content = factory.objectNode();
        ObjectNode modelProperties = factory.objectNode();
        ArrayNode documentRefs = factory.arrayNode();
        for (String s: jsonList) {
            documentRefs.add(s);
        }
        modelProperties.put("DocumentRefs", documentRefs);
        content.put("modelName", "ContactPerson");
        content.put("calledMethod", "deletePack");
        content.put("modelProperties", modelProperties);
        return content.toString();
    }

    // The void method deletes all Contact Persons
    public static void deleteContact(String modelName, List<String> refList) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Sender sender;
        System.out.print("Deleting all Counterparty's ContactPersons: ");
        for (String ref : refList) {
            sender = new Sender(API_URL_ADDRESS + JSON, ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(DELETE_COUNTERPARTY_CONTACT_PERSON, ref)));
            sender.sendApiRequest();
        }
        System.out.println("Deleting completed!");
    }

    // The method clear a file. Used string param as file path
    public static void clearFileContent(String filePath) throws IOException {
        FileUtils.write(new File(filePath), "");
    }

    // The method returns Ref-list of all Refs of objects
    public static List<String> getFieldValue(Object object, String field) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String jsonString = ToolsJSON.parseObjectToJson(object);
        Sender sender = new Sender(API_URL_ADDRESS + JSON, jsonString);
        return ToolsJSON.getParamsValueAsList(sender.sendApiRequest().toString(), field)
                .stream()
                .filter(s -> !s.equals(REF_OWNER))
                .map(s -> s.replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    // The method returns Ref-list of all Senders, Recipients or Third persons
    public static List<String> getCounterpartiesListOfSenderOrRecipientRef(String param, String filter) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        CounterpartyLoadCounterpartiesListModel counterpartyGetContactPersonModel = CounterpartyLoadCounterpartiesListModel.getInstance(new String[]{param});
        String jsonString = ToolsJSON.parseObjectToJson(counterpartyGetContactPersonModel);
        Sender sender = new Sender(API_URL_ADDRESS + JSON, jsonString);
        return ToolsJSON.getParamsValueAsList(sender.sendApiRequest().toString(), FIELD_REF)
                .stream()
                .filter(o -> !o.equals(filter))
                .map(s -> s.replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

}