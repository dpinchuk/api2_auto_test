package api.common.counterparty.negative.update;

import api.models.common.CommonCounterpartyModel;
import api.models.counterparty.CounterpartyGetCounterparties;
import api.service.Sender;
import api.tools.ToolsExcel;
import api.tools.ToolsJSON;
import com.google.gson.JsonObject;
import org.junit.*;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static api.utils.Constants.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonCounterpartyUpdateNegativeTest {

    private static int testCount = 0;
    private Sender sender;
    private JsonObject serverResponse;
    private String jsonStringForSend;
    private CommonCounterpartyModel commonCounterpartyModel;

    private final List<List<String>> TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE = ToolsExcel.getDataFromExcelSheet(TEST_PROPERTIES_PATH + COUNTERPARTY_UPDATE_NEGATIVE);

    private static Set<String> refList = new HashSet<>();

    public CommonCounterpartyUpdateNegativeTest() throws IOException {}

    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("before");
                base.evaluate();
                System.out.println("after");
            }
        };
    }

    @Before
    public void setUp() {
        System.out.print("#" + this.testCount++ + " Run test");
    }

    @After
    public void tearDown() throws IOException {
        System.out.println(" - completed");
        try {
            //this.refList.add(ToolsJSON.getParamValueFromList(this.serverResponse.toString(), FIELD_REF, 0));
            ToolsJSON.getParamsValuesAsList(this.serverResponse.toString(), Arrays.asList(
                    FIELD_SUCCESS,
                    FIELD_REF,
                    FIELD_FIRST_NAME,
                    FIELD_ERRORS,
                    FIELD_WARNINGS
            )).forEach(System.out::println);
        } catch (NullPointerException e) {
            //System.out.println("Reference to a 'null'");
        } catch (IndexOutOfBoundsException e) {
            ToolsJSON.getParamsValuesAsList(this.serverResponse.toString(), Arrays.asList(
                    FIELD_SUCCESS,
                    FIELD_REF,
                    FIELD_FIRST_NAME,
                    FIELD_ERRORS,
                    FIELD_WARNINGS
            )).forEach(System.out::println);
        }
        System.out.println();
    }

    /**
     * * * * * * * * * * * *
     * Редактировать Контрагента *
     * * * * * * * * * * * *
     *
     * Метод "update", работает в модели "Counterparty", этот метод используется при создании Контрагента с типом (юридическое лицо) или организация
     *
     * {
     *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
     *   "modelName": "Counterparty",
     *   "calledMethod": "save",
     *   "methodProperties":
     *   {
     *     "CityRef": "db5c88d7-391c-11dd-90d9-001a92567626",
     *     "CounterpartyRef": "",
     *     "FirstName": "+",
     *     "MiddleName": "+",
     *     "LastName": "+",
     *     "Phone": "+",
     *     "Email": "+",
     *     "CounterpartyType": "PrivatePerson",
     *     "CounterpartyProperty": "Recipient",
     *     "EDRPOU": "",
     *     "OwnershipForm": ""
     *   }
     * }
     *
     * /

     /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Update Counterparty - Negative cases                                                                          *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * @Negative #0000N
     * Update new counterparty with invalid parameters:
     * <p>
     * Check for uncreating new counterparty with simple parameters
     * <p>
     * firstName = '' <-
     * middleName = '' <-
     * lastName = '' <-
     * phone = '' <-
     * email = '' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0000_UpdateNewCounterparty_0N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(0)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_IS_NOT_SPECIFIED,
                ERROR_LAST_NAME_IS_NOT_SPECIFIED,
                ERROR_PHONE_IS_NOT_SPECIFIED)));
    }

    /**
     * @Negative #0001N
     * Update new counterparty with invalid parameters:
     * <p>
     * Check for uncreating new counterparty with simple parameters
     * <p>
     * firstName = 'null' <-
     * middleName = 'null' <-
     * lastName = 'null' <-
     * phone = 'null' <-
     * email = 'null' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0001_UpdateNewCounterparty_1N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(1)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_IS_NOT_SPECIFIED,
                ERROR_LAST_NAME_IS_NOT_SPECIFIED,
                ERROR_PHONE_IS_NOT_SPECIFIED)));
    }

    /**
     * @Negative #0002N
     * Update new counterparty with invalid parameters:
     * <p>
     * Check for uncreating new counterparty with simple parameters
     * <p>
     * firstName = ' ' <-
     * middleName = ' ' <-
     * lastName = ' ' <-
     * phone = ' ' <-
     * email = ' ' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0002_UpdateNewCounterparty_2N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(2)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_IS_NOT_SPECIFIED,
                ERROR_LAST_NAME_IS_NOT_SPECIFIED,
                ERROR_PHONE_INVALID_FORMAT,
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0003N
     * Update new counterparty with invalid parameters:
     * <p>
     * Check for special symbol in fullname
     * <p>
     * firstName = 'Name' <-
     * middleName = 'Middlename' <-
     * lastName = 'Lastname' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0003_UpdateNewCounterparty_3N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(3)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0004N
     * Update new counterparty with invalid parameters:
     * <p>
     * Check MAX length of fullname
     * <p>
     * firstName = 'Польzователь' <-
     * middleName = 'Польzователь' <-
     * lastName = 'Польzователь' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0004_UpdateNewCounterparty_4N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(4)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0005N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тестттттттттттттт' <-
     * middleName = 'Тестттттттттттттт' <-
     * lastName = 'Тестттттттттттттт' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0005_UpdateNewCounterparty_5N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(5)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_STRING_TOO_LONG,
                ERROR_STRING_TOO_LONG,
                ERROR_STRING_TOO_LONG)));
    }

    /**
     * @Negative #0006N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = '12345678901234567' <-
     * middleName = '12345678901234567' <-
     * lastName = '12345678901234567' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test006_UpdateNewCounterparty_6N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(6)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_STRING_TOO_LONG,
                ERROR_STRING_TOO_LONG,
                ERROR_STRING_TOO_LONG)));
    }

    /**
     * @Negative #0007N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т~ест' <-
     * middleName = 'Т~ест' <-
     * lastName = 'Т~ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0007_UpdateNewCounterparty_7N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(7)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0008N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т!ест' <-
     * middleName = 'Т!ест' <-
     * lastName = 'Т!ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0008_UpdateNewCounterparty_8N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(8)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0009N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т@ест' <-
     * middleName = 'Т@ест' <-
     * lastName = 'Т@ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0009_UpdateNewCounterparty_9N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(9)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0010N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т#ест' <-
     * middleName = 'Т#ест' <-
     * lastName = 'Т#ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0010_UpdateNewCounterparty_10N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(10)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0011N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т$ест' <-
     * middleName = 'Т$ест' <-
     * lastName = 'Т$ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0011_UpdateNewCounterparty_11N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(11)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0012N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т%ест' <-
     * middleName = 'Т%ест' <-
     * lastName = 'Т%ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0012_UpdateNewCounterparty_12N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(12)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0013N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т^ест' <-
     * middleName = 'Т^ест' <-
     * lastName = 'Т^ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0013_UpdateNewCounterparty_13N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(13)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0014N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т&ест' <-
     * middleName = 'Т&ест' <-
     * lastName = 'Т&ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0014_UpdateNewCounterparty_14N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(14)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0015N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т=ест' <-
     * middleName = 'Т=ест' <-
     * lastName = 'Т=ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0015_UpdateNewCounterparty_15N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(15)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0016N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т{ест' <-
     * middleName = 'Т{ест' <-
     * lastName = 'Т{ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0016_UpdateNewCounterparty_16N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(16)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0017N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т}ест' <-
     * middleName = 'Т}ест' <-
     * lastName = 'Т}ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0017_UpdateNewCounterparty_17N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(17)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0018N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т[ест' <-
     * middleName = 'Т[ест' <-
     * lastName = 'Т[ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0018_UpdateNewCounterparty_18N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(18)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0019N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т]ест' <-
     * middleName = 'Т]ест' <-
     * lastName = 'Т]ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0019_UpdateNewCounterparty_19N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(19)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0020N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т;ест' <-
     * middleName = 'Т;ест' <-
     * lastName = 'Т;ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0020_UpdateNewCounterparty_20N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(20)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0021N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т:ест' <-
     * middleName = 'Т:ест' <-
     * lastName = 'Т:ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0021_UpdateNewCounterparty_21N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(21)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0022N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т|ест' <-
     * middleName = 'Т|ест' <-
     * lastName = 'Т|ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0022_UpdateNewCounterparty_22N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(22)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0023N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т\ест' <-
     * middleName = 'Т\ест' <-
     * lastName = 'Т\ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0023_UpdateNewCounterparty_23N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(23)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0024N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т?ест' <-
     * middleName = 'Т?ест' <-
     * lastName = 'Т?ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0024_UpdateNewCounterparty_24N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(24)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0025N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т🚀ест' <-
     * middleName = 'Т🚀ест' <-
     * lastName = 'Т🚀ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0025_UpdateNewCounterparty_25N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(25)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0026N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Т😀ест' <-
     * middleName = 'Т😀ест' <-
     * lastName = 'Т😀ест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0026_UpdateNewCounterparty_26N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(26)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0027N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'ТΔест' <-
     * middleName = 'ТΔест' <-
     * lastName = 'ТΔест' <-
     * phone = '380971344443'
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0027_UpdateNewCounterparty_27N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(27)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
    }

    /**
     * @Negative #0028N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '++380971344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0028_UpdateNewCounterparty_28P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(28)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0029N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+380971344443+' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0029_UpdateNewCounterparty_29N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(29)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0030N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '38 097 134 44 43' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0030_UpdateNewCounterparty_30N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(30)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0031N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38[097]1344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0031_UpdateNewCounterparty_31N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(31)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0032N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38{097}1344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0032_UpdateNewCounterparty_32N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(32)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0033N
     * Update new counterparty with invalid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38/097/134/44/43' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0033_UpdateNewCounterparty_33N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(33)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0034N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38\097\134\44\43' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0034_UpdateNewCounterparty_34N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(34)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0035N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38(097)1344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0035_UpdateNewCounterparty_35N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(35)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0036N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38<097>1344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0036_UpdateNewCounterparty_36N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(36)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0037N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38-097-134-44-43' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0037_UpdateNewCounterparty_37N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(37)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0038N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+38.097.134.44.43' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0038_UpdateNewCounterparty_38N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(38)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0039N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '971344443' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0039_UpdateNewCounterparty_39N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(39)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0040N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '80670000000' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0040_UpdateNewCounterparty_40N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(40)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0041N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '79000000000' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0041_UpdateNewCounterparty_41N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(41)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0042N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '00000000000' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0042_UpdateNewCounterparty_42N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(42)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0043N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0043_UpdateNewCounterparty_43N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(43)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0044N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '3' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0044_UpdateNewCounterparty_44N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(44)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0045N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '38' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0045_UpdateNewCounterparty_45N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(45)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0046N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0046_UpdateNewCounterparty_46N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(46)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0047N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '3809' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0047_UpdateNewCounterparty_47N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(47)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0048N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '38097' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0048_UpdateNewCounterparty_48N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(48)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0049N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0049_UpdateNewCounterparty_49N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(49)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0050N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '3809713' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0050_UpdateNewCounterparty_50N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(50)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0051N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '38097134' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0051_UpdateNewCounterparty_51N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(51)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0052N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0052_UpdateNewCounterparty_52N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(52)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0053N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '3809713444' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0053_UpdateNewCounterparty_53N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(53)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0054N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '38097134444' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0054_UpdateNewCounterparty_54N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(54)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0055N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0055_UpdateNewCounterparty_55N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(55)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0056N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0056_UpdateNewCounterparty_56N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(56)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0057N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '@' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0057_UpdateNewCounterparty_57N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(57)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0058N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 't@t.testtttttttttttttttttttttttttttt' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0058_UpdateNewCounterparty_58N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(58)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0059N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = ' И м я ' <-
     * middleName = ' О т ч е с т в о ' <-
     * lastName = ' Ф а м и л и я ' <-
     * phone = '380971344443'
     * email = 't@testtttttttttttttttttttttttttttt.t' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0059_UpdateNewCounterparty_59N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(59)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0060N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'testtttttttttttttttttttttttttttt@t.t' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0060_UpdateNewCounterparty_60N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(60)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0061N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test@test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0061_UpdateNewCounterparty_61N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(61)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0062N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '_@_._' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0062_UpdateNewCounterparty_62N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(62)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0063N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0063_UpdateNewCounterparty_63N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(63)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0064N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '@test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0064_UpdateNewCounterparty_64N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(64)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0065N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test@test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0065_UpdateNewCounterparty_65N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(65)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0066N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test_te-st.com' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0066_UpdateNewCounterparty_66N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(66)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0067N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test..test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0067_UpdateNewCounterparty_67N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(67)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0068N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test_.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0068_UpdateNewCounterparty_68N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(68)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0069N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@_test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0069_UpdateNewCounterparty_69N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(69)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0070N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '_test_@_test_._test_' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0070_UpdateNewCounterparty_70N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(70)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0071N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.test_' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0071_UpdateNewCounterparty_71N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(71)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0072N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test._test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0072_UpdateNewCounterparty_72N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(72)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0073N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@-test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0073_UpdateNewCounterparty_73N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(73)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0074N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test-.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0074_UpdateNewCounterparty_74N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(74)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0075N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test.@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0075_UpdateNewCounterparty_75N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(75)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0076N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '.test@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0076_UpdateNewCounterparty_76N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(76)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0077N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0077_UpdateNewCounterparty_77N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(77)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0078N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'тест@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0078_UpdateNewCounterparty_78N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(78)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0079N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@тест.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0079_UpdateNewCounterparty_79N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(79)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0080N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.тест' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0080_UpdateNewCounterparty_80N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(80)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0081N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 't🚀est@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0081_UpdateNewCounterparty_81N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(81)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0082N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@t🚀est.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0082_UpdateNewCounterparty_82N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(82)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0083N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t🚀est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0083_UpdateNewCounterparty_83N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(83)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0084N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t™est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0084_UpdateNewCounterparty_84N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(84)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0085N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '"test"@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Ignore
    @Test
    public void test0085_UpdateNewCounterparty_85N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(85)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0086N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '"test@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0086_UpdateNewCounterparty_86N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(86)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0087N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test"@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0087_UpdateNewCounterparty_87N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(87)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0088N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t™est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0088_UpdateNewCounterparty_88N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(88)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0089N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@te"st.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0089_UpdateNewCounterparty_89N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(89)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0090N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '"\t"@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Ignore
    @Test
    public void test0090_UpdateNewCounterparty_90N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(90)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0091N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '"\"@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0091_UpdateNewCounterparty_91N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(91)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0092N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '"\\"@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Ignore
    @Test
    public void test0092_UpdateNewCounterparty_92N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(92)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0093N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '␠@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0093_UpdateNewCounterparty_93N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(93)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0094N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@t␠.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0094_UpdateNewCounterparty_94N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(94)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0095N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t␠est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0095_UpdateNewCounterparty_95N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(95)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0096N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'tes<t@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0096_UpdateNewCounterparty_96N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(96)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0097N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@t<est.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0097_UpdateNewCounterparty_97N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(97)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0098N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t<est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0098_UpdateNewCounterparty_98N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(98)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0099N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'tes>t@test.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0099_UpdateNewCounterparty_99N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(99)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0100N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@t>est.test' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0100_UpdateNewCounterparty_100N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(100)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0101N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.t>est' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0101_UpdateNewCounterparty_101N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(101)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0102N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '12345678901234567890123456789012@1.t' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0102_UpdateNewCounterparty_102N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(102)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0103N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '1@12345678901234567890123456789012.t' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0103_UpdateNewCounterparty_103N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(103)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0104N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = '1@1.t1234567890123456789012345678901' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0104_UpdateNewCounterparty_104N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(104)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0105N
     * Update new counterparty with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test@test.1' <-
     * <p>
     * Check for success='false' in response from Server after api-request
     */

    @Test
    public void test0105_UpdateNewCounterparty_105N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstance(
                this.TEST_DATA_UPDATE_NEW_COUNTERPARTY_NEGATIVE.get(105)
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * Checking the existence of previously created Counterparties Legal - Negative cases
     */

    /**
     * @Negative #0100
     * Update new CounterpartyLegal with invalid parameters (invalid email)
     *
     * Run only after running all tests!
     * Check for 'true' if refs exist
     *
     */

    @Test
    public void test0163_UpdateNewCounterpartyLegal_00EC() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Assert.assertTrue(this.refList.containsAll(ToolsJSON.getFieldValue(CounterpartyGetCounterparties.getInstance(COUNTERPARTY_PROPERTY_RECIPIENT), FIELD_REF)));
    }

}