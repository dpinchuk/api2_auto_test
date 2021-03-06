//package api.contactPerson;
//
//import api.models.contactPerson.ContactPersonCreateModel;
//import api.models.counterparty.CounterpartyGetCounterpartyContactPersonModel;
//import api.service.Sender;
//import api.tools.ToolsJSON;
//import com.google.gson.JsonObject;
//import org.junit.*;
//import org.junit.runner.Description;
//import org.junit.runners.MethodSorters;
//import org.junit.runners.model.Statement;
//
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
//import static api.utils.Constants.*;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class CreateNewContactPersonTest {
//
//    private static int testCount = 0;
//    private Sender sender;
//    private JsonObject serverResponse;
//    private String jsonStringForSend;
//
//    private final List<String> TEST_POSITIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_CONTACT_PERSON_TEST_PROPERTIES_POSITIVE, TEG_IGNORE);
//    private final List<String> TEST_NEGATIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_CONTACT_PERSON_TEST_PROPERTIES_NEGATIVE, TEG_IGNORE);
//    private static Set<String> refList = new HashSet<>();
//
//    public CreateNewContactPersonTest() throws IOException {
//    }
//
//    public Statement apply(final Statement base, Description description) {
//        return new Statement() {
//            @Override
//            public void evaluate() throws Throwable {
//                System.out.println("before");
//                base.evaluate();
//                System.out.println("after");
//            }
//        };
//    }
//
//    @Before
//    public void setUp() {
//        System.out.print("#" + this.testCount++ + " Run test");
//    }
//
//    @After
//    public void tearDown() throws IOException {
//        System.out.println(" - completed");
//        try {
//            String s = this.serverResponse.toString();
//            this.refList.add(ToolsJSON.getParamValueFromList(s, FIELD_REF, 0));
//            ToolsJSON.getParamsValuesAsList(this.serverResponse.toString(), Arrays.asList(
//                    FIELD_SUCCESS,
//                    FIELD_REF,
//                    FIELD_FIRST_NAME,
//                    FIELD_MIDDLE_NAME,
//                    FIELD_LAST_NAME,
//                    FIELD_ERRORS,
//                    FIELD_WARNINGS
//            )).forEach(System.out::println);
//        } catch (NullPointerException e) {
//            //System.out.println("Reference to a 'null'");
//        } catch (IndexOutOfBoundsException e) {
//            ToolsJSON.getParamsValuesAsList(this.serverResponse.toString(), Arrays.asList(
//                    FIELD_SUCCESS,
//                    FIELD_REF,
//                    FIELD_FIRST_NAME,
//                    FIELD_MIDDLE_NAME,
//                    FIELD_LAST_NAME,
//                    FIELD_ERRORS,
//                    FIELD_WARNINGS
//            )).forEach(System.out::println);
//        }
//        System.out.println();
//    }
//
//
//    /**
//     * Init test environment
//     */
//
//    @BeforeClass
//    public static void testBefore_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        ToolsJSON.deleteContact(MODEL_NAME_COUNTACT_PERSON, ToolsJSON.getFieldValue(CounterpartyGetCounterpartyContactPersonModel.getInstance(), FIELD_REF));
//    }
//
//    @AfterClass
//    public static void testAfter_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        ToolsJSON.deleteContact(MODEL_NAME_COUNTACT_PERSON, ToolsJSON.getFieldValue(CounterpartyGetCounterpartyContactPersonModel.getInstance(), FIELD_REF));
//    }
//
//
///**
// * Create new ContactPerson - Positive cases
// */
//
//    /**
//     * @Positive #0000
//     * Creation new ContactPerson with valid parameters
//     *
//     * Check for success='true' in response from Server after api-request
//     */
//
////    @Test
////    public void test0000_CreateNewContactPerson_L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_POSITIVE_WITH_SPACES.get(0)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0001
////     * Creation of an already existing ContactPerson with valid parameters
////     *
////     * Check for success='Person already exists!' response from Server after api-request
////     */
////
////    @Test
////    public void test0001_CreateExistContactPerson_L1_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_POSITIVE_WITH_SPACES.get(1)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest(); //First request to create new ContactPerson
////        this.serverResponse = this.sender.sendApiRequest(); //Second request to create an already created ContactPerson
////        Assert.assertEquals(true, this.serverResponse.toString().contains(WARNING_PERSON_ALREADY_EXISTS));
////    }
////
////    /**
////     * @Positive #0002
////     * Creation new ContactPerson with valid parameters
////     * <p>
////     * Spaces in Fullname
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0002_CreateNewContactPerson_L2_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_POSITIVE_WITH_SPACES.get(2)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0003P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * Check for creating new ContactPerson with simple parameters
////     * <p>
////     * firstName = 'Тест' <-
////     * middleName = 'Тест' <-
////     * lastName = 'Тест' <-
////     * phone = '380970000000'
////     * email = 'test0@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0003_CreateNewContactPerson_0P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0004P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * Check for MIN length of fullname with uppercase chars
////     * <p>
////     * firstName = 'Т' <-
////     * middleName = 'Т' <-
////     * lastName = 'Т' <-
////     * phone = '380970000001'
////     * email = 'test1@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0004_CreateNewContactPerson_1P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(1))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0005P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * Check for MIN length of fullname with lowcase chars
////     * <p>
////     * firstName = 'т' <-
////     * middleName = 'т' <-
////     * lastName = 'т' <-
////     * phone = '380970000002'
////     * email = 'test2@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0005_CreateNewContactPerson_2P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(2))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0006P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * Check for special symbol in fullname
////     * <p>
////     * firstName = '.,-`*()+'"/<>№' <-
////     * middleName = '.,-`*()+'"/<>№' <-
////     * lastName = '.,-`*()+'"/<>№' <-
////     * phone = '380970000003'
////     * email = 'test3@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0006_CreateNewContactPerson_3P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(3))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0007P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * Check MAX length of fullname
////     * <p>
////     * firstName = 'ёыъэїєґі' <-
////     * middleName = 'ёыъэїєґі' <-
////     * lastName = 'ёыъэїєґі' <-
////     * phone = '380970000004'
////     * email = 'test4@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0007_CreateNewContactPerson_4P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(4))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0008P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'ЁЫЪЭЇЄҐІ' <-
////     * middleName = 'ЁЫЪЭЇЄҐІ' <-
////     * lastName = 'ЁЫЪЭЇЄҐІ' <-
////     * phone = '380970000005'
////     * email = 'test5@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0008_CreateNewContactPerson_5P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(5))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0009P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'ЙЦУКЕНГШЩЗХЪФЫВ123' <-
////     * middleName = 'АПРОЛДЖЭЯЧСМИТЬ12345' <-
////     * lastName = 'БЮЁЇІЄҐ67890' <-
////     * phone = '380970000006'
////     * email = 'test6@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0010_CreateNewContactPerson_6P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(6))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0010P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
////     * middleName = 'ыыыыыыыыыыыы' <-
////     * lastName = 'эээээээээээээ' <-
////     * phone = '380970000007'
////     * email = 'test7@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0010_CreateNewContactPerson_7P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(7))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0011P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'эээээээээээээ' <-
////     * middleName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
////     * lastName = 'ыыыыыыыыыыыы' <-
////     * phone = '380970000008'
////     * email = 'test8@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0011_CreateNewContactPerson_8P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(8))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0012P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'ыыыыыыыыыыыы' <-
////     * middleName = 'эээээээээээээ' <-
////     * lastName = 'йййййёёёёёїїїїїіііііґґґґґ' <-
////     * phone = '380970000009'
////     * email = 'test9@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0012_CreateNewContactPerson_9P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(9))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0013P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = '1234567890' <-
////     * middleName = '1234567890' <-
////     * lastName = '1234567890' <-
////     * phone = '380970000010'
////     * email = 'test10@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0013_CreateNewContactPerson_10P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(10))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0014P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = '1234567890098765432112345' <-
////     * middleName = '123456789012' <-
////     * lastName = '1234567890123' <-
////     * phone = '380970000011'
////     * email = 'test11@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0014_CreateNewContactPerson_11P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(11))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0015P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = '123456789012' <-
////     * middleName = '1234567890098765432112345' <-
////     * lastName = '1234567890123' <-
////     * phone = '380970000012'
////     * email = 'test12@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0015_CreateNewContactPerson_12P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(12))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0016P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = '1234567890123' <-
////     * middleName = '123456789012' <-
////     * lastName = '1234567890098765432112345' <-
////     * phone = '380970000013'
////     * email = 'test13@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0016_CreateNewContactPerson_13P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(13))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0017P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест1234567890' <-
////     * middleName = 'Тест1234567890' <-
////     * lastName = 'Тест1234567890' <-
////     * phone = '380970000014'
////     * email = 'test14@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0017_CreateNewContactPerson_14P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(14))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0018P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '+380970000015' <-
////     * email = 'test15@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0018_CreateNewContactPerson_15P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(15))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #19P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '0970000016' <-
////     * email = 'test16@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0019_CreateNewContactPerson_16P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(16))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0020P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380670000017' <-
////     * email = 'test17@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0020_CreateNewContactPerson_17P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(17))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0021P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380680000018' <-
////     * email = 'test18@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0021_CreateNewContactPerson_18P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(18))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0022P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380660000019' <-
////     * email = 'test19@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0022_CreateNewContactPerson_19P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(19))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0023P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380500000020' <-
////     * email = 'test20@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0023_CreateNewContactPerson_20P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(20))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0024P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380630000021' <-
////     * email = 'test21@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0024_CreateNewContactPerson_21P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(21))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0025P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380730000022' <-
////     * email = 'test22@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0025_CreateNewContactPerson_22P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(22))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0026P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380910000023' <-
////     * email = 'test23@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0026_CreateNewContactPerson_23P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(23))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0027P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380920000024' <-
////     * email = 'test24@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0027_CreateNewContactPerson_24P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(24))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0028P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380930000025' <-
////     * email = 'test25@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0028_CreateNewContactPerson_25P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(25))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0029P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380940000026' <-
////     * email = 'test26@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0029_CreateNewContactPerson_26P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(26))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0030P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380950000027' <-
////     * email = 'test27@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0030_CreateNewContactPerson_27P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(27))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0031P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380960000028' <-
////     * email = 'test28@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0031_CreateNewContactPerson_28P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(28))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0032P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380980000029' <-
////     * email = 'test29@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0032_CreateNewContactPerson_29P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(29))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0033P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380990000030' <-
////     * email = 'test30@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0033_CreateNewContactPerson_30P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(30))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0034P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380390000031' <-
////     * email = 'test31@test.test'
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0034_CreateNewContactPerson_31P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(31))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0035P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000032'
////     * email = 't@t.t' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0035_CreateNewContactPerson_32P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(32))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0036P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000033'
////     * email = 't@t.testttttttttttttttttttttttttttt' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0036_CreateNewContactPerson_33P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(33))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0037P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000034'
////     * email = 't@testttttttttttttttttttttttttttt.t' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0037_CreateNewContactPerson_34P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(34))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0038P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000035'
////     * email = 'emailtesttt@emailtesttt.emailtesttt' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0038_CreateNewContactPerson_35P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(35))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0039P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000036'
////     * email = 'email123@test123.test123' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0039_CreateNewContactPerson_36P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(36))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0040P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000037'
////     * email = 'test_test@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0040_CreateNewContactPerson_37P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(37))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0041P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000038'
////     * email = 'test.test@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0041_CreateNewContactPerson_38P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(38))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0042P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000039'
////     * email = 'test.test@test.test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0042_CreateNewContactPerson_39P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(39))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0043P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000040'
////     * email = 'test.test.test@test.test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0043_CreateNewContactPerson_40P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(40))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0044P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000041'
////     * email = '`~!#$%^&*_+|-={}?./@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0044_CreateNewContactPerson_41P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(41))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #45P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000042'
////     * email = 'test@test-test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0045_CreateNewContactPerson_42P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(42))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0046P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000043'
////     * email = 'test@test--test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0046_CreateNewContactPerson_43P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(43))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0047P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000044'
////     * email = '1234567890@1234567890.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0047_CreateNewContactPerson_44P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(44))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0048P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000045'
////     * email = 'TEST@TEST.TEST' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0048_CreateNewContactPerson_45P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(45))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0049P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000046'
////     * email = 'TESTtest@TESTtest.TESTtes' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0049_CreateNewContactPerson_46P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(46))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0050P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000047'
////     * email = '_test@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0050_CreateNewContactPerson_47P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(47))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0051P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380971344443'
////     * email = 'test_@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0051_CreateNewContactPerson_48P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(48))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0052P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000049'
////     * email = 'test@test.t-t' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0052_CreateNewContactPerson_49P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(49))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0053P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000050'
////     * email = 'test@test.t--t' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0053_CreateNewContactPerson_50P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(50))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0054P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000051'
////     * email = 'test@[127.0.0.1]' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0054_CreateNewContactPerson_51P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(51))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0055P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000052'
////     * email = 'test@[IPv6:::]' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0055_CreateNewContactPerson_52P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(52))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0056P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000053'
////     * email = 'Miles.O'Brian@example.com' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0056_CreateNewContactPerson_53P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(53))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0057P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000054'
////     * email = 'TEST@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0057_CreateNewContactPerson_54P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(54))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0058P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000055'
////     * email = 'Test@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0058_CreateNewContactPerson_55P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(55))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0059P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000056'
////     * email = 'e.m.a.i.l.t.e.s.t@test.test' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0059_CreateNewContactPerson_56P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(56))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////    /**
////     * @Positive #0060P
////     * Creation new ContactPerson with valid parameters:
////     * <p>
////     * firstName = 'Тест'
////     * middleName = 'Тест'
////     * lastName = 'Тест'
////     * phone = '380970000057'
////     * email = 'e.m.a.i.l.t.e.s.t@e.m.a.i.l.t.e.s.t' <-
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0060_CreateNewContactPerson_57P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(57))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
////    }
////
////
////
/////**
//// * Create new ContactPerson - Negative cases
//// */
////
////    /**
////     * @Negative #0500N
////     * Creation new ContactPerson with invalid parameters (fullname contains '' empty data)
////     * <p>
////     * firstName = '' <-
////     * middleName = '' <-
////     * lastName = '' <-
////     * phone = '' <-
////     * email = '' <-
////     * <p>
////     * Check for success='false' in response from Server after api-request
////     */
////
////    @Test
////    public void test0500_CreateNewContactPerson_L0_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_NEGATIVE.get(0)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_FALSE));
////    }
////
////    /**
////     * @Negative #0501N
////     * Creation new ContactPerson with invalid parameters (fullname contains " " space data)
////     * <p>
////     * firstName = ' ' <-
////     * middleName = ' ' <-
////     * lastName = ' ' <-
////     * phone = ' ' <-
////     * email = ' ' <-
////     * <p>
////     * Check for success='false' in response from Server after api-request
////     */
////
////    @Test
////    public void test0501_CreateNewContactPerson_L1_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_NEGATIVE.get(1)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_FALSE));
////    }
////
////    /**
////     * @Negative #0502N
////     * Creation new ContactPerson with invalid parameters (phone and email contains " ")
////     * <p>
////     * firstName = 'Имя'
////     * middleName = 'Отчество'
////     * lastName = 'Фамилия'
////     * phone = ' 3 8 0 6 7 0 0 0 0 0 9 9'
////     * email = 't e s t @ t e s t . t s t' <-
////     * <p>
////     * NN generated randomly as int number
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test
////    public void test0502_CreateNewContactPerson_L2_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_NEGATIVE.get(2)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT,
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0503N
////     * Creation new ContactPerson with invalid parameters (Object = null)
////     * <p>
////     * null <-
////     * <p>
////     * NN generated randomly as int number
////     * <p>
////     * Check for success='true' in response from Server after api-request
////     */
////
////    @Test(expected = ArrayIndexOutOfBoundsException.class)
////    public void test0503_CreateNewContactPerson_3L_N() throws IOException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_NEGATIVE.get(3)));
////    }
////
////    /**
////     * @Negative #0504N
////     * Creation new ContactPerson with invalid parameters (fullname contains NULL data)
////     * <p>
////     * firstName = null <-
////     * middleName = null <-
////     * lastName = null <-
////     * phone = null <-
////     * email = null <-
////     * <p>
////     * Check for success='false' in response from Server after api-request
////     */
////
////    @Test
////    public void test0504_CreateNewContactPerson_2L_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(NEW_COUNTERPARTY_DATA_NEGATIVE.get(2)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_FALSE));
////    }
////
////    /**
////     * @Negative #0505N
////     * Creation new ContactPerson with invalid parameters (fullname contains all Latin symbols)
////     * <p>
////     * firstName = Test <-
////     * middleName = Test <-
////     * lastName = Test <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0505_CreateNewContactPerson_0N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(0))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0506N
////     * Creation new ContactPerson with invalid parameters (fullname contains Cyrillic and Latin symbols)
////     * <p>
////     * firstName = Польzователь <-
////     * middleName = Польzователь <-
////     * lastName = Польzователь <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0506_CreateNewContactPerson_1N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(1))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0507N
////     * Creation new ContactPerson with invalid parameters (too long Fulname with cyrillic)
////     * <p>
////     * firstName = Тестттттттттттттт <-
////     * middleName = Тестттттттттттттт <-
////     * lastName = Тестттттттттттттт <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Full Name is longer then 50 characters",
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0507_CreateNewContactPerson_2N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(2))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FULL_NAME_IS_LONGER_RHEN_50_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0508N
////     * Creation new ContactPerson with invalid parameters (too long Fulname with numbers)
////     * <p>
////     * firstName = 12345678901234567 <-
////     * middleName = 12345678901234567 <-
////     * lastName = 12345678901234567 <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Full Name is longer then 50 characters",
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0508_CreateNewContactPerson_3N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(3))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FULL_NAME_IS_LONGER_RHEN_50_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0509N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol ~)
////     * <p>
////     * firstName = Т~ест <-
////     * middleName = Т~ест <-
////     * lastName = Т~ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0509_CreateNewContactPerson_4N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(4))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0510N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol !)
////     * <p>
////     * firstName = Т!ест <-
////     * middleName = Т!ест <-
////     * lastName = Т!ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0510_CreateNewContactPerson_5N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(5))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0511N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol @)
////     * <p>
////     * firstName = Т@ест <-
////     * middleName = Т@ест <-
////     * lastName = Т@ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0511_CreateNewContactPerson_6N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(6))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0512N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol #)
////     * <p>
////     * firstName = Т#ест <-
////     * middleName = Т#ест <-
////     * lastName = Т#ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0512_CreateNewContactPerson_7N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(7))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0513N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol $)
////     * <p>
////     * firstName = Т$ест <-
////     * middleName = Т$ест <-
////     * lastName = Т$ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0513_CreateNewContactPerson_8N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(8))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0514N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol %)
////     * <p>
////     * firstName = Т%ест <-
////     * middleName = Т%ест <-
////     * lastName = Т%ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0514_CreateNewContactPerson_9N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(9))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0515N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol ^)
////     * <p>
////     * firstName = Т^ест <-
////     * middleName = Т^ест <-
////     * lastName = Т^ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0515_CreateNewContactPerson_10N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(10))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0516N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol &)
////     * <p>
////     * firstName = Т&ест <-
////     * middleName = Т&ест <-
////     * lastName = Т&ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0516_CreateNewContactPerson_11N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(11))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0517N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol =)
////     * <p>
////     * firstName = Т=ест <-
////     * middleName = Т=ест <-
////     * lastName = Т=ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0517_CreateNewContactPerson_12N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(12))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0518N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol {)
////     * <p>
////     * firstName = Т{ест <-
////     * middleName = Т{ест <-
////     * lastName = Т{ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0518_CreateNewContactPerson_13N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(13))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0519N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol })
////     * <p>
////     * firstName = Т}ест <-
////     * middleName = Т}ест <-
////     * lastName = Т}ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0519_CreateNewContactPerson_14N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(14))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0520N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol [)
////     * <p>
////     * firstName = Т[ест <-
////     * middleName = Т[ест <-
////     * lastName = Т[ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0520_CreateNewContactPerson_15N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(15))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0521N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol ])
////     * <p>
////     * firstName = Т]ест <-
////     * middleName = Т]ест <-
////     * lastName = Т]ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0521_CreateNewContactPerson_16N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(16))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0522N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol ;)
////     * <p>
////     * firstName = Т;ест <-
////     * middleName = Т;ест <-
////     * lastName = Т;ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0522_CreateNewContactPerson_17N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(17))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0523N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol :)
////     * <p>
////     * firstName = Т:ест <-
////     * middleName = Т:ест <-
////     * lastName = Т:ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0523_CreateNewContactPerson_18N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(18))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0524N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol |)
////     * <p>
////     * firstName = Т|ест <-
////     * middleName = Т|ест <-
////     * lastName = Т|ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0524_CreateNewContactPerson_19N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(19))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0525N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol \)
////     * <p>
////     * firstName = Т\ест <-
////     * middleName = Т\ест <-
////     * lastName = Т\ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0525_CreateNewContactPerson_20N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(20))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0526N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol ?)
////     * <p>
////     * firstName = Т?ест <-
////     * middleName = Т?ест <-
////     * lastName = Т?ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0526_CreateNewContactPerson_21N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(21))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0527N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol 🚀)
////     * <p>
////     * firstName = Т🚀ест <-
////     * middleName = Т🚀ест <-
////     * lastName = Т🚀ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0527_CreateNewContactPerson_22N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(22))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0528N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol 😀)
////     * <p>
////     * firstName = Т😀ест <-
////     * middleName = Т😀ест <-
////     * lastName = Т😀ест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0528_CreateNewContactPerson_23N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(23))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0529N
////     * Creation new ContactPerson with invalid parameters (fullname contains symbol Δ)
////     * <p>
////     * firstName = ТΔест <-
////     * middleName = ТΔест <-
////     * lastName = ТΔест <-
////     * phone = 380971344443
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "FirstName has invalid characters",
////     * "MiddleName has invalid characters",
////     * "LastName has invalid characters"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0529_CreateNewContactPerson_24N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(24))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS,
////                ERROR_LAST_NAME_HAS_INVALID_CHARACTERS)));
////    }
////
////    /**
////     * @Negative #0530N
////     * Creation new ContactPerson with invalid parameters (phone number contains symbols ++)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = ++380971344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0530_CreateNewContactPerson_25N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(25))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0531N
////     * Creation new ContactPerson with invalid parameters (phone number contains symbols + in the end of a number)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = +380971344443+ <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * <p>
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0531_CreateNewContactPerson_26N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(26))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0532N
////     * Creation new ContactPerson with invalid parameters (phone number contains space ' ' as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38 0971344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0532_CreateNewContactPerson_27N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(27))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0533N
////     * Creation new ContactPerson with invalid parameters (phone number contains [] as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38[097]1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0533_CreateNewContactPerson_28N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(28))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0534N
////     * Creation new ContactPerson with invalid parameters (phone number contains {} as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38{097}1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0534_CreateNewContactPerson_29N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(29))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0535N
////     * Creation new ContactPerson with invalid parameters (phone number contains // as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38/097/1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0535_CreateNewContactPerson_30N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(30))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0536N
////     * Creation new ContactPerson with invalid parameters (phone number contains \\ as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38\097\1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0536_CreateNewContactPerson_31N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(31))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0537N
////     * Creation new ContactPerson with invalid parameters (phone number contains () as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38(097)1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0537_CreateNewContactPerson_32N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(32))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////        ;
////    }
////
////    /**
////     * @Negative #0538N
////     * Creation new ContactPerson with invalid parameters (phone number contains <> as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38<097>1344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0538_CreateNewContactPerson_33N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(33))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0539N
////     * Creation new ContactPerson with invalid parameters (phone number contains - - - as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38-097-134-44-43 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0539_CreateNewContactPerson_34N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(34))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0540N
////     * Creation new ContactPerson with invalid parameters (phone number contains . . . as separators)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38.097.134.44.43 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0540_CreateNewContactPerson_35N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(35))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0541N
////     * Creation new ContactPerson with invalid parameters (phone number without +380)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 971344443 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0541_CreateNewContactPerson_36N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(36))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0542N
////     * Creation new ContactPerson with invalid parameters (phone number starts with 8)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 80670000000 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0542_CreateNewContactPerson_37N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(37))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0543N
////     * Creation new ContactPerson with invalid parameters (phone number of another country)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 79000000000 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0543_CreateNewContactPerson_38N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(38))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0544N
////     * Creation new ContactPerson with invalid parameters (phone number consists of zero)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 00000000000 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0544_CreateNewContactPerson_39N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(39))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0545N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 1 symbol)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = + <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0545_CreateNewContactPerson_40N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(40))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0546N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 1 digit)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 3 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0546_CreateNewContactPerson_41N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(41))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0547N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 2 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0547_CreateNewContactPerson_42N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(42))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////
////    /**
////     * @Negative #0548N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 3 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0548_CreateNewContactPerson_43N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(43))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0549N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 4 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 3809 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0549_CreateNewContactPerson_44N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(44))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0550N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 5 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38097 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0550_CreateNewContactPerson_45N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(45))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0551N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 6 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0551_CreateNewContactPerson_46N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(46))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0552N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 7 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 3809713 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0552_CreateNewContactPerson_47N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(47))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0553N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 8 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38097134 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0553_CreateNewContactPerson_48N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(48))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0554N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 9 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0554_CreateNewContactPerson_49N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(49))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0555N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 10 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 3809713444 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0555_CreateNewContactPerson_50N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(50))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0556N
////     * Creation new ContactPerson with invalid parameters (phone number consists of 11 digits)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 38097134444 <-
////     * email = test@test.test
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Phone invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0556_CreateNewContactPerson_51N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(51))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_PHONE_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0557N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0557_CreateNewContactPerson_52N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(52))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0558N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0558_CreateNewContactPerson_53N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(53))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0559N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = @ <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0559_CreateNewContactPerson_54N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(54))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0560N
////     * Creation new ContactPerson with invalid parameters (email too long)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = t@t.testtttttttttttttttttttttttttttt <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0560_CreateNewContactPerson_55N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(55))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0561N
////     * Creation new ContactPerson with invalid parameters (email too long)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = t@testtttttttttttttttttttttttttttt.t <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email too long"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0561_CreateNewContactPerson_56N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(56))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0562N
////     * Creation new ContactPerson with invalid parameters (email too long)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email =  testtttttttttttttttttttttttttttt@t.t <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email too long"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0562_CreateNewContactPerson_57N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(57))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0563N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test@test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0563_CreateNewContactPerson_58N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(58))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0564N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = _@_._ <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0564_CreateNewContactPerson_59N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(59))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0565N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = @test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0565_CreateNewContactPerson_60N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(60))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0566N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@ <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0566_CreateNewContactPerson_61N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(61))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0567N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = @test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0567_CreateNewContactPerson_62N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(62))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0568N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test_te-st.com <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0568_CreateNewContactPerson_63N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(63))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0569N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test..test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0569_CreateNewContactPerson_64N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(64))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0570N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test_.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0570_CreateNewContactPerson_65N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(65))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0571N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@_test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0571_CreateNewContactPerson_66N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(66))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0572N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = _test_@_test_._test_ <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0572_CreateNewContactPerson_67N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(67))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0573N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.test_ <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0573_CreateNewContactPerson_68N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(68))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0574N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test._test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0574_CreateNewContactPerson_69N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(69))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0575N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@-test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0575_CreateNewContactPerson_70N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(70))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0576N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test-.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0576_CreateNewContactPerson_71N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(71))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0577N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test.@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0577_CreateNewContactPerson_72N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(72))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0578N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = .test@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid formatt"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0578_CreateNewContactPerson_73N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(73))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0579N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0579_CreateNewContactPerson_74N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(74))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0580N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = тест@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0580_CreateNewContactPerson_75N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(75))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0581N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@тест.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0581_CreateNewContactPerson_76N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(76))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0582N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.тест <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0582_CreateNewContactPerson_77N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(77))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0583N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = t🚀est@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0583_CreateNewContactPerson_78N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(78))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0584N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@t🚀est.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0584_CreateNewContactPerson_79N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(79))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0585N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.t🚀est <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0585_CreateNewContactPerson_80N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(80))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0586N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.t™est <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0586_CreateNewContactPerson_81N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(81))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0587N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = "test"@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Ignore
////    @Test
////    public void test0587_CreateNewContactPerson_82N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(82))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0588N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = "test@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0588_CreateNewContactPerson_83N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(83))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0589N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test"@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0589_CreateNewContactPerson_84N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(84))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0590N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.t™est <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0590_CreateNewContactPerson_85N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(85))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0591N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@te"st.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0591_CreateNewContactPerson_86N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(86))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0592N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = "\t"@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Ignore
////    @Test
////    public void test0592_CreateNewContactPerson_87N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(87))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0593N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = "\"@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0593_CreateNewContactPerson_88N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(88))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0594N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = "\\"@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Ignore
////    @Test
////    public void test0594_CreateNewContactPerson_89N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(89))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0595N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = ␠@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0595_CreateNewContactPerson_90N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(90))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0596N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@t␠.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0596_CreateNewContactPerson_91N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(91))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0597N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = tes<t@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0597_CreateNewContactPerson_93N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(93))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0598N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@t<est.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0598_CreateNewContactPerson_94N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(94))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0599N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.t<est <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0599_CreateNewContactPerson_95N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(95))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0600N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = tes>t@test.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0600_CreateNewContactPerson_96N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(96))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0601N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@t>est.test <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0601_CreateNewContactPerson_97N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(97))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0602N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.t>est <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0602_CreateNewContactPerson_98N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(98))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////    /**
////     * @Negative #0603N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = 12345678901234567@1234567890123456.t <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0603_tCreateNewContactPerson_99N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(99))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0604N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = 12345678901234567@1234567890123456.t <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0604_CreateNewContactPerson_100N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(100))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0605N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = 1234567890123456@12345678901234567.t <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0605_CreateNewContactPerson_101N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(101))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_TOO_LONG)));
////    }
////
////    /**
////     * @Negative #0606N
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     * <p>
////     * firstName = Тест
////     * middleName = Тест
////     * lastName = Тест
////     * phone = 380971344443
////     * email = test@test.1 <-
////     * <p>
////     * Check for true if errors =
////     * [
////     * "Email invalid format"
////     * ]
////     * <p>
////     * in response from Server after api-request
////     */
////
////    @Test
////    public void test0606_CreateNewContactPerson_102N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = ToolsJSON.parseObjectToJson(ContactPersonCreateModel.getInstance(ToolsJSON.getPropertiesFromListLine(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(102))));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
////        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
////                ERROR_EMAIL_INVALID_FORMAT)));
////    }
////
////
/////**
//// * Checking the existence of previously created counterparties - Positive cases
//// */
////
////    /**
////     * @Positive #0999EC
////     * Creation new ContactPerson with invalid parameters (invalid email)
////     *
////     * Run only after running all tests!!!
////     * Check for true if refs exist
////     *
////     */
////
////    @Test
////    public void test0999_CreateNewContactPerson_00EC() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        Assert.assertTrue(this.refList.containsAll(ToolsJSON.getFieldValue(CounterpartyGetCounterpartyContactPersonModel.getInstance(), FIELD_REF)));
////    }
//
//}
