package api.common.contactPerson.negative.update;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static api.utils.Constants.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonContactPersonUpdateNegativeTest {

    private static int testCount = 0;
    private static String ref;
    private Sender sender;
    private JsonObject serverResponse;
    private String jsonStringForSend;
    private CommonCounterpartyModel commonCounterpartyModel;

    private final List<List<String>> TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE = ToolsExcel.getDataFromExcelSheet(TEST_PROPERTIES_PATH + CONTACT_PERSON_UPDATE_NEGATIVE);

    private static Set<String> refList = new HashSet<>();

    public CommonContactPersonUpdateNegativeTest() throws IOException {}

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
    
    /*

     */


    /**
     * Delete all test data before starting tests
     */

    @BeforeClass
    public static void testBefore_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {

        ToolsJSON.deleteContact(MODEL_NAME_COUNTERPARTY, ToolsJSON.getFieldValue(CommonCounterpartyModel.getInstance(GET_COUNTERPARTY_CONTACT_PERSON), FIELD_REF));
        ref = ToolsJSON.getFieldValue(CommonCounterpartyModel.getInstance(NEW_CONTACT_PERSON_CREATE_POSITIVE), FIELD_REF).get(0);
    }

    /**
     * Delete all test data after the tests are completed
     */

    @AfterClass
    public static void testAfter_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        ToolsJSON.deleteContact(MODEL_NAME_COUNTERPARTY, ToolsJSON.getFieldValue(CommonCounterpartyModel.getInstance(GET_COUNTERPARTY_CONTACT_PERSON), FIELD_REF));
    }

    /**
     * * * * * * * * * * * *
     * Редактировать Контактное лицо *
     * * * * * * * * * * * *
     *
     * Метод "update", работает в модели "Counterparty", этот метод используется при создании Контрагента с типом (юридическое лицо) или организация
     *
     * {
     *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
     *   "modelName": "ContactPerson",
     *   "calledMethod": "update",
     *   "methodProperties":
     *   {
     *     "CityRef": "+",
     *     "CounterpartyRef": "+",
     *     "FirstName": "+",
     *     "MiddleName": "+",
     *     "LastName": "+",
     *     "Phone": "+",
     *     "Email": "+",
     *     "CounterpartyType": "",
     *     "CounterpartyProperty": "",
     *     "EDRPOU": "",
     *     "OwnershipForm": ""
     *   }
     * }
     *
     * /

     /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Update ContactPerson - Negative cases                                                                          *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * @Negative #0000N
     * Update new ContactPerson with invalid parameters:
     * <p>
     * Check for uncreating new ContactPerson with simple parameters
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
    public void test0000_UpdateContactPerson_0N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(0),
                ref
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
     * Update new ContactPerson with invalid parameters:
     * <p>
     * Check for uncreating new ContactPerson with simple parameters
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
    public void test0001_UpdateContactPerson_1N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(1),
                ref
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
     * Update new ContactPerson with invalid parameters:
     * <p>
     * Check for uncreating new ContactPerson with simple parameters
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
    public void test0002_UpdateContactPerson_2N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(2),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0003_UpdateContactPerson_3N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(3),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0004_UpdateContactPerson_4N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(4),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0005_UpdateContactPerson_5N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(5),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test006_UpdateContactPerson_6N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(6),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0007_UpdateContactPerson_7N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(7),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0008_UpdateContactPerson_8N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(8),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0009_UpdateContactPerson_9N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(9),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0010_UpdateContactPerson_10N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(10),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0011_UpdateContactPerson_11N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(11),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0012_UpdateContactPerson_12N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(12),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0013_UpdateContactPerson_13N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(13),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0014_UpdateContactPerson_14N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(14),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0015_UpdateContactPerson_15N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(15),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0016_UpdateContactPerson_16N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(16),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0017_UpdateContactPerson_17N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(17),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0018_UpdateContactPerson_18N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(18),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0019_UpdateContactPerson_19N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(19),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0020_UpdateContactPerson_20N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(20),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0021_UpdateContactPerson_21N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(21),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0022_UpdateContactPerson_22N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(22),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0023_UpdateContactPerson_23N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(23),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0024_UpdateContactPerson_24N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(24),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0025_UpdateContactPerson_25N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(25),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0026_UpdateContactPerson_26N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(26),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0027_UpdateContactPerson_27N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(27),
                ref
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
     * Update new ContactPerson with invalid parameters:
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
    public void test0028_UpdateContactPerson_28P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(28),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0029N
     * Update new ContactPerson with invalid parameters:
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
    public void test0029_UpdateContactPerson_29N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(29),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0030N
     * Update new ContactPerson with invalid parameters:
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
    public void test0030_UpdateContactPerson_30N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(30),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0031N
     * Update new ContactPerson with invalid parameters:
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
    public void test0031_UpdateContactPerson_31N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(31),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0032N
     * Update new ContactPerson with invalid parameters:
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
    public void test0032_UpdateContactPerson_32N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(32),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0033N
     * Update new ContactPerson with invalid parameters:
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
    public void test0033_UpdateContactPerson_33N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(33),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0034N
     * Update new ContactPerson with valid parameters:
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
    public void test0034_UpdateContactPerson_34N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(34),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0035N
     * Update new ContactPerson with valid parameters:
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
    public void test0035_UpdateContactPerson_35N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(35),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0036N
     * Update new ContactPerson with valid parameters:
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
    public void test0036_UpdateContactPerson_36N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(36),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0037N
     * Update new ContactPerson with valid parameters:
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
    public void test0037_UpdateContactPerson_37N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(37),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0038N
     * Update new ContactPerson with valid parameters:
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
    public void test0038_UpdateContactPerson_38N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(38),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0039N
     * Update new ContactPerson with valid parameters:
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
    public void test0039_UpdateContactPerson_39N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(39),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0040N
     * Update new ContactPerson with valid parameters:
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
    public void test0040_UpdateContactPerson_40N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(40),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0041N
     * Update new ContactPerson with valid parameters:
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
    public void test0041_UpdateContactPerson_41N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(41),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0042N
     * Update new ContactPerson with valid parameters:
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
    public void test0042_UpdateContactPerson_42N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(42),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0043N
     * Update new ContactPerson with valid parameters:
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
    public void test0043_UpdateContactPerson_43N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(43),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0044N
     * Update new ContactPerson with valid parameters:
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
    public void test0044_UpdateContactPerson_44N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(44),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0045N
     * Update new ContactPerson with valid parameters:
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
    public void test0045_UpdateContactPerson_45N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(45),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0046N
     * Update new ContactPerson with valid parameters:
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
    public void test0046_UpdateContactPerson_46N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(46),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0047N
     * Update new ContactPerson with valid parameters:
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
    public void test0047_UpdateContactPerson_47N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(47),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0048N
     * Update new ContactPerson with valid parameters:
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
    public void test0048_UpdateContactPerson_48N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(48),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0049N
     * Update new ContactPerson with valid parameters:
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
    public void test0049_UpdateContactPerson_49N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(49),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0050N
     * Update new ContactPerson with valid parameters:
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
    public void test0050_UpdateContactPerson_50N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(50),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0051N
     * Update new ContactPerson with valid parameters:
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
    public void test0051_UpdateContactPerson_51N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(51),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0052N
     * Update new ContactPerson with valid parameters:
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
    public void test0052_UpdateContactPerson_52N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(52),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0053N
     * Update new ContactPerson with valid parameters:
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
    public void test0053_UpdateContactPerson_53N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(53),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0054N
     * Update new ContactPerson with valid parameters:
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
    public void test0054_UpdateContactPerson_54N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(54),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_PHONE_INVALID_FORMAT)));
    }

    /**
     * @Negative #0055N
     * Update new ContactPerson with valid parameters:
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
    public void test0055_UpdateContactPerson_55N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(55),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0056N
     * Update new ContactPerson with valid parameters:
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
    public void test0056_UpdateContactPerson_56N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(56),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0057N
     * Update new ContactPerson with valid parameters:
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
    public void test0057_UpdateContactPerson_57N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(57),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0058N
     * Update new ContactPerson with valid parameters:
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
    public void test0058_UpdateContactPerson_58N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(58),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0059N
     * Update new ContactPerson with valid parameters:
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
    public void test0059_UpdateContactPerson_59N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(59),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0060N
     * Update new ContactPerson with valid parameters:
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
    public void test0060_UpdateContactPerson_60N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(60),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0061N
     * Update new ContactPerson with valid parameters:
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
    public void test0061_UpdateContactPerson_61N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(61),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0062N
     * Update new ContactPerson with valid parameters:
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
    public void test0062_UpdateContactPerson_62N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(62),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0063N
     * Update new ContactPerson with valid parameters:
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
    public void test0063_UpdateContactPerson_63N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(63),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0064N
     * Update new ContactPerson with valid parameters:
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
    public void test0064_UpdateContactPerson_64N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(64),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0065N
     * Update new ContactPerson with valid parameters:
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
    public void test0065_UpdateContactPerson_65N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(65),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0066N
     * Update new ContactPerson with valid parameters:
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
    public void test0066_UpdateContactPerson_66N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(66),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0067N
     * Update new ContactPerson with valid parameters:
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
    public void test0067_UpdateContactPerson_67N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(67),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0068N
     * Update new ContactPerson with valid parameters:
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
    public void test0068_UpdateContactPerson_68N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(68),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0069N
     * Update new ContactPerson with valid parameters:
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
    public void test0069_UpdateContactPerson_69N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(69),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0070N
     * Update new ContactPerson with valid parameters:
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
    public void test0070_UpdateContactPerson_70N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(70),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0071N
     * Update new ContactPerson with valid parameters:
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
    public void test0071_UpdateContactPerson_71N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(71),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0072N
     * Update new ContactPerson with valid parameters:
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
    public void test0072_UpdateContactPerson_72N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(72),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0073N
     * Update new ContactPerson with valid parameters:
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
    public void test0073_UpdateContactPerson_73N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(73),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0074N
     * Update new ContactPerson with valid parameters:
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
    public void test0074_UpdateContactPerson_74N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(74),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0075N
     * Update new ContactPerson with valid parameters:
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
    public void test0075_UpdateContactPerson_75N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(75),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0076N
     * Update new ContactPerson with valid parameters:
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
    public void test0076_UpdateContactPerson_76N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(76),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0077N
     * Update new ContactPerson with valid parameters:
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
    public void test0077_UpdateContactPerson_77N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(77),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0078N
     * Update new ContactPerson with valid parameters:
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
    public void test0078_UpdateContactPerson_78N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(78),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0079N
     * Update new ContactPerson with valid parameters:
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
    public void test0079_UpdateContactPerson_79N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(79),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0080N
     * Update new ContactPerson with valid parameters:
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
    public void test0080_UpdateContactPerson_80N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(80),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0081N
     * Update new ContactPerson with valid parameters:
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
    public void test0081_UpdateContactPerson_81N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(81),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0082N
     * Update new ContactPerson with valid parameters:
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
    public void test0082_UpdateContactPerson_82N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(82),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0083N
     * Update new ContactPerson with valid parameters:
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
    public void test0083_UpdateContactPerson_83N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(83),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0084N
     * Update new ContactPerson with valid parameters:
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
    public void test0084_UpdateContactPerson_84N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(84),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0085N
     * Update new ContactPerson with valid parameters:
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
    public void test0085_UpdateContactPerson_85N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(85),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0086N
     * Update new ContactPerson with valid parameters:
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
    public void test0086_UpdateContactPerson_86N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(86),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0087N
     * Update new ContactPerson with valid parameters:
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
    public void test0087_UpdateContactPerson_87N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(87),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0088N
     * Update new ContactPerson with valid parameters:
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
    public void test0088_UpdateContactPerson_88N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(88),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0089N
     * Update new ContactPerson with valid parameters:
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
    public void test0089_UpdateContactPerson_89N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(89),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0090N
     * Update new ContactPerson with valid parameters:
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
    public void test0090_UpdateContactPerson_90N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(90),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0091N
     * Update new ContactPerson with valid parameters:
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
    public void test0091_UpdateContactPerson_91N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(91),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0092N
     * Update new ContactPerson with valid parameters:
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
    public void test0092_UpdateContactPerson_92N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(92),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0093N
     * Update new ContactPerson with valid parameters:
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
    public void test0093_UpdateContactPerson_93N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(93),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0094N
     * Update new ContactPerson with valid parameters:
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
    public void test0094_UpdateContactPerson_94N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(94),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0095N
     * Update new ContactPerson with valid parameters:
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
    public void test0095_UpdateContactPerson_95N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(95),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0096N
     * Update new ContactPerson with valid parameters:
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
    public void test0096_UpdateContactPerson_96N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(96),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0097N
     * Update new ContactPerson with valid parameters:
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
    public void test0097_UpdateContactPerson_97N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(97),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0098N
     * Update new ContactPerson with valid parameters:
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
    public void test0098_UpdateContactPerson_98N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(98),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0099N
     * Update new ContactPerson with valid parameters:
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
    public void test0099_UpdateContactPerson_99N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(99),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0100N
     * Update new ContactPerson with valid parameters:
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
    public void test0100_UpdateContactPerson_100N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(100),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0101N
     * Update new ContactPerson with valid parameters:
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
    public void test0101_UpdateContactPerson_101N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(101),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_INVALID_FORMAT)));
    }

    /**
     * @Negative #0102N
     * Update new ContactPerson with valid parameters:
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
    public void test0102_UpdateContactPerson_102N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(102),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0103N
     * Update new ContactPerson with valid parameters:
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
    public void test0103_UpdateContactPerson_103N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(103),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0104N
     * Update new ContactPerson with valid parameters:
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
    public void test0104_UpdateContactPerson_104N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(104),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
                ERROR_EMAIL_TOO_LONG)));
    }

    /**
     * @Negative #0105N
     * Update new ContactPerson with valid parameters:
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
    public void test0105_UpdateContactPerson_105N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_NEGATIVE.get(105),
                ref
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
    public void test0163_UpdateContactPersonLegal_00EC() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Assert.assertTrue(this.refList.containsAll(ToolsJSON.getFieldValue(CounterpartyGetCounterparties.getInstance(COUNTERPARTY_PROPERTY_RECIPIENT), FIELD_REF)));
    }

}