package api.common.contactPerson.positive.update;

import api.models.common.CommonCounterpartyModel;
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
public class CommonContactPersonUpdatePositiveTest {

    private static int testCount = 0;
    private static String ref;
    private Sender sender;
    private JsonObject serverResponse;
    private String jsonStringForSend;

    private final List<List<String>> TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE = ToolsExcel.getDataFromExcelSheet(TEST_PROPERTIES_PATH + CONTACT_PERSON_UPDATE_POSITIVE);

    private static Set<String> refList = new HashSet<>();

    public CommonContactPersonUpdatePositiveTest() throws IOException {}

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
     * * * * * * * * * * * * * * * * * * * * * * *
     * Редактировать Контактное лицо контрагента *
     * * * * * * * * * * * * * * * * * * * * * * *
     *
     * Метод "update", работает в модели "Counterparty", этот метод используется при создании Контрагента с типом (юридическое лицо) или организация
     *
     * {
     *   "apiKey": "+",
     *   "modelName": "ContactPerson",
     *   "calledMethod": "save",
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
     * Create new ContactPerson - Positive cases                                                                          *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * @Positive #0000P
     * Update new contactPerson with valid parameters:
     * <p>
     * Check for creating new contactPerson with simple parameters
     * <p>
     * firstName = 'Тест' <-
     * middleName = 'Тест' <-
     * lastName = 'Тест' <-
     * phone = '380970000000'
     * email = 'test0@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0000_CreateNewContactPerson_0P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(0),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0001P
     * Update new contactPerson with valid parameters:
     * <p>
     * Check for MIN length of fullname with uppercase chars
     * <p>
     * firstName = 'Т' <-
     * middleName = 'Т' <-
     * lastName = 'Т' <-
     * phone = '380970000001'
     * email = 'test1@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0001_CreateNewContactPerson_1P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(1),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0002P
     * Update new contactPerson with valid parameters:
     * <p>
     * Check for MIN length of fullname with lowcase chars
     * <p>
     * firstName = 'т' <-
     * middleName = 'т' <-
     * lastName = 'т' <-
     * phone = '380970000002'
     * email = 'test2@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0002_CreateNewContactPerson_2P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(2),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0003P
     * Update new contactPerson with valid parameters:
     * <p>
     * Check for special symbol in fullname
     * <p>
     * firstName = '.,-`*()+'"/<>№' <-
     * middleName = '.,-`*()+'"/<>№' <-
     * lastName = '.,-`*()+'"/<>№' <-
     * phone = '380970000003'
     * email = 'test3@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0003_CreateNewContactPerson_3P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(3),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0004P
     * Update new contactPerson with valid parameters:
     * <p>
     * Check MAX length of fullname
     * <p>
     * firstName = 'ёыъэїєґі' <-
     * middleName = 'ёыъэїєґі' <-
     * lastName = 'ёыъэїєґі' <-
     * phone = '380970000004'
     * email = 'test4@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0004_CreateNewContactPerson_4P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(4),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0005P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'ЁЫЪЭЇЄҐІ' <-
     * middleName = 'ЁЫЪЭЇЄҐІ' <-
     * lastName = 'ЁЫЪЭЇЄҐІ' <-
     * phone = '380970000005'
     * email = 'test5@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0005_CreateNewContactPerson_5P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(5),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0006P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'ЙЦУКЕНГШЩЗХЪФЫВ123' <-
     * middleName = 'АПРОЛДЖЭЯЧСМИТЬ12345' <-
     * lastName = 'БЮЁЇІЄҐ67890' <-
     * phone = '380970000006'
     * email = 'test6@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0006_CreateNewContactPerson_6P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(6),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0007P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
     * middleName = 'ыыыыыыыыыыыы' <-
     * lastName = 'эээээээээээээ' <-
     * phone = '380970000007'
     * email = 'test7@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0007_CreateNewContactPerson_7P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(7),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0008P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'эээээээээээээ' <-
     * middleName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
     * lastName = 'ыыыыыыыыыыыы' <-
     * phone = '380970000008'
     * email = 'test8@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0008_CreateNewContactPerson_8P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(8),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0009P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'ыыыыыыыыыыыы' <-
     * middleName = 'эээээээээээээ' <-
     * lastName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
     * phone = '380970000009'
     * email = 'test9@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0009_CreateNewContactPerson_9P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(9),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0010P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = '1234567890' <-
     * middleName = '1234567890' <-
     * lastName = '1234567890' <-
     * phone = '380970000010'
     * email = 'test10@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0010_CreateNewContactPerson_10P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(10),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0011P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = '1234567890098765432112345' <-
     * middleName = '123456789012' <-
     * lastName = '1234567890123' <-
     * phone = '380970000011'
     * email = 'test11@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0011_CreateNewContactPerson_11P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(11),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0012P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = '123456789012' <-
     * middleName = '1234567890098765432112345' <-
     * lastName = '1234567890123' <-
     * phone = '380970000012'
     * email = 'test12@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0012_CreateNewContactPerson_12P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(12),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0013P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = '1234567890123' <-
     * middleName = '123456789012' <-
     * lastName = '1234567890098765432112345' <-
     * phone = '380970000013'
     * email = 'test13@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0013_CreateNewContactPerson_13P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(13),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0014P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест1234567890' <-
     * middleName = 'Тест1234567890' <-
     * lastName = 'Тест1234567890' <-
     * phone = '380970000014'
     * email = 'test14@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0014_CreateNewContactPerson_14P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(14),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0015P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '+380970000015' <-
     * email = 'test15@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0015_CreateNewContactPerson_15P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(15),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #16P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '0970000016' <-
     * email = 'test16@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0016_CreateNewContactPerson_16P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(16),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0017P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380670000017' <-
     * email = 'test17@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0017_CreateNewContactPerson_17P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(17),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0018P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380680000018' <-
     * email = 'test18@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0018_CreateNewContactPerson_18P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(18),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0019P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380660000019' <-
     * email = 'test19@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0019_CreateNewContactPerson_19P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(19),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0020P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380500000020' <-
     * email = 'test20@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0020_CreateNewContactPerson_20P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(20),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0021P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380630000021' <-
     * email = 'test21@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0021_CreateNewContactPerson_21P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(21),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0022P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380730000022' <-
     * email = 'test22@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0022_CreateNewContactPerson_22P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(22),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0023P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380910000023' <-
     * email = 'test23@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0023_CreateNewContactPerson_23P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(23),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0024P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380920000024' <-
     * email = 'test24@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0024_CreateNewContactPerson_24P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(24),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0025P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380930000025' <-
     * email = 'test25@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0025_CreateNewContactPerson_25P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(25),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0026P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380940000026' <-
     * email = 'test26@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0026_CreateNewContactPerson_26P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(26),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0027P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380950000027' <-
     * email = 'test27@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0027_CreateNewContactPerson_27P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(27),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0028P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380960000028' <-
     * email = 'test28@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0028_CreateNewContactPerson_28P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(28),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0029P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380980000029' <-
     * email = 'test29@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0029_CreateNewContactPerson_29P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(29),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0030P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380990000030' <-
     * email = 'test30@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0030_CreateNewContactPerson_30P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(30),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0031P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380390000031' <-
     * email = 'test31@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0031_CreateNewContactPerson_31P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(31),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0032P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000032'
     * email = 't@t.t' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0032_CreateNewContactPerson_32P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(32),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0033P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000033'
     * email = 't@t.testttttttttttttttttttttttttttt' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0033_CreateNewContactPerson_33P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(33),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0034P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000034'
     * email = 't@testttttttttttttttttttttttttttt.t' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0034_CreateNewContactPerson_34P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(34),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0035P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000035'
     * email = 'emailtesttt@emailtesttt.emailtesttt' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0035_CreateNewContactPerson_35P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(35),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0036P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000036'
     * email = 'email123@test123.test123' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0036_CreateNewContactPerson_36P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(36),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0037P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000037'
     * email = 'test_test@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0037_CreateNewContactPerson_37P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(37),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0038P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000038'
     * email = 'test.test@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0038_CreateNewContactPerson_38P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(38),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0039P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000039'
     * email = 'test.test@test.test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0039_CreateNewContactPerson_39P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(39),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0040P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000040'
     * email = 'test.test.test@test.test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0040_CreateNewContactPerson_40P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(40),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0041P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000041'
     * email = '`~!#$%^&*_+|-={}?./@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0041_CreateNewContactPerson_41P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(41),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0042P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000042'
     * email = 'test@test-test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0042_CreateNewContactPerson_42P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(42),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0043P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000043'
     * email = 'test@test--test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0043_CreateNewContactPerson_43P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(43),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0044P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000044'
     * email = '1234567890@1234567890.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0044_CreateNewContactPerson_44P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(44),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0045P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000045'
     * email = 'TEST@TEST.TEST' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0045_CreateNewContactPerson_45P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(45),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0046P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000046'
     * email = 'TESTtest@TESTtest.TESTtes' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0046_CreateNewContactPerson_46P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(46),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0047P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000047'
     * email = '_test@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0047_CreateNewContactPerson_47P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(47),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0048P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380971344443'
     * email = 'test_@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0048_CreateNewContactPerson_48P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(48),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0049P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000049'
     * email = 'test@test.t-t' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0049_CreateNewContactPerson_49P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(49),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0050P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000050'
     * email = 'test@test.t--t' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0050_CreateNewContactPerson_50P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(50),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0051P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000051'
     * email = 'test@[127.0.0.1]' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0051_CreateNewContactPerson_51P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(51),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0052P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000052'
     * email = 'test@[IPv6:::]' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0052_CreateNewContactPerson_52P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(52),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0053P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000053'
     * email = 'Miles.O'Brian@example.com' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0053_CreateNewContactPerson_53P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(53),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0054P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000054'
     * email = 'TEST@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0054_CreateNewContactPerson_54P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(54),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0055P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000055'
     * email = 'Test@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0055_CreateNewContactPerson_55P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(55),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0056P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000056'
     * email = 'e.m.a.i.l.t.e.s.t@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0056_CreateNewContactPerson_56P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(56),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0057P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000057'
     * email = 'e.m.a.i.l.t.e.s.t@e.m.a.i.l.t.e.s.t' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0057_CreateNewContactPerson_57P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(57),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0058P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000058'
     * email = '""@test.test' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0058_CreateNewContactPerson_58P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(58),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0059P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = ' И м я ' <-
     * middleName = ' О т ч е с т в о ' <-
     * lastName = ' Ф а м и л и я ' <-
     * phone = '380970000059'
     * email = 'test@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0059_CreateNewContactPerson_59P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(59),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0060P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = ' 380970000060 ' <-
     * email = 'test@test.test'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0060_CreateNewContactPerson_60P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(60),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0061P
     * Update new contactPerson with valid parameters:
     * <p>
     * firstName = 'Тест'
     * middleName = 'Тест'
     * lastName = 'Тест'
     * phone = '380970000061' <-
     * email = ' test@test.test ' <-
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0061_CreateNewContactPerson_61P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(61),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

    /**
     * @Positive #0062P
     * Return all account data:
     * <p>
     * firstName = 'Дмитрий'
     * middleName = 'Анатольевич'
     * lastName = 'Пинчук'
     * phone = '380971344443'
     * email = 'pinchuk.dap@gmail.com'
     * <p>
     * Check for success='true' in response from Server after api-request
     */

    @Test
    public void test0062_CreateNewContactPerson_62P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CommonCounterpartyModel.getInstanceRef(
                this.TEST_DATA_UPDATE_NEW_CONTACT_PERSON_POSITIVE.get(62),
                ref
        ));
        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
        this.serverResponse = this.sender.sendApiRequest();
        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
    }

}