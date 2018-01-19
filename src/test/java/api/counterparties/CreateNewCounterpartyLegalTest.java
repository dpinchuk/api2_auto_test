//package api.counterparties;
//
//import api.models.common.CommonCounterpartyModel;
//import api.models.counterparty.CounterpartyCreateLegalModel;
//import api.models.counterparty.CounterpartyGetCounterparties;
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
//public class CreateNewCounterpartyLegalTest {
//
//    private static int testCount = 0;
//    private Sender sender;
//    private JsonObject serverResponse;
//    private String jsonStringForSend;
//    CommonCounterpartyModel commonCounterpartyModel;
//
//    private final List<String> TEST_POSITIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_COUNTERPARTY_LEGAL_TEST_PROPERTIES_POSITIVE, TEG_IGNORE);
//    private final List<String> TEST_NEGATIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_COUNTERPARTY_LEGAL_TEST_PROPERTIES_NEGATIVE, TEG_IGNORE);
//    private static Set<String> refList = new HashSet<>();
//
//    public CreateNewCounterpartyLegalTest() throws IOException {
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
//            this.refList.add(ToolsJSON.getParamValueFromList(this.serverResponse.toString(), FIELD_REF, 0));
//            ToolsJSON.getParamsValuesAsList(this.serverResponse.toString(), Arrays.asList(
//                    FIELD_SUCCESS,
//                    FIELD_REF,
//                    FIELD_FIRST_NAME,
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
//                    FIELD_ERRORS,
//                    FIELD_WARNINGS
//            )).forEach(System.out::println);
//        }
//        System.out.println();
//    }
//
//
//    /**
//     * Delete all test data before starting tests
//     */
//
//    @BeforeClass
//    public static void testBefore_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        ToolsJSON.deleteContact(MODEL_NAME_COUNTERPARTY, ToolsJSON.getFieldValue(CounterpartyGetCounterparties.getInstance(COUNTERPARTY_PROPERTY_RECIPIENT), FIELD_REF));
//    }
//
//    /**
//     * Delete all test data after the tests are completed
//     */
//
//    @AfterClass
//    public static void testAfter_InitTestEnvitonment() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        ToolsJSON.deleteContact(MODEL_NAME_COUNTERPARTY, ToolsJSON.getFieldValue(CounterpartyGetCounterparties.getInstance(COUNTERPARTY_PROPERTY_RECIPIENT), FIELD_REF));
//    }
//
//
//    /**
//     * @Positive #0000P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ВК' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0000_CreateNewCounterpartyLegal_L0L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0001P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ДП' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0001_CreateNewCounterpartyLegal_L0L1_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(1)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0002P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'КП' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0002_CreateNewCounterpartyLegal_L0L2_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(2)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0003P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'КТ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0003_CreateNewCounterpartyLegal_L0L3_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(3)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0004P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ПАТ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0004_CreateNewCounterpartyLegal_L0L4_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(4)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0005P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ПІІ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0005_CreateNewCounterpartyLegal_L0L5_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(5)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0006P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ПП' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0006_CreateNewCounterpartyLegal_L0L6_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(6)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0007P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ПрАТ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0007_CreateNewCounterpartyLegal_L0L7_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(7)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0008P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ПТ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0008_CreateNewCounterpartyLegal_L0L8_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(8)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0009P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'СП' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0009_CreateNewCounterpartyLegal_L0L9_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(9)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0010P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ТДВ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0010_CreateNewCounterpartyLegal_L0L10_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(10)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0011P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ТОВ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0011_CreateNewCounterpartyLegal_L0L11_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(11)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0012P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ФГ' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0012_CreateNewCounterpartyLegal_L0L12_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(12)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0013P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Тест777'
//     * OwnershipForm = 'ФОП (СПД)' <-
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0013_CreateNewCounterpartyLegal_L0L13_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(13)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0014P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Т' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0014_CreateNewCounterpartyLegal_L1L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(1), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0015P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'т' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0015_CreateNewCounterpartyLegal_L2L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(2), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0016P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0016_CreateNewCounterpartyLegal_L3L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(3), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0017P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'тесттесттесттесттесттесттесттесттесттесттесттесттесттесттест' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0017_CreateNewCounterpartyLegal_L4L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(4), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0018P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '1' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0018_CreateNewCounterpartyLegal_L5L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(5), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0019P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '123456789012345678901234567890123456789012345678901234567890' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0019_CreateNewCounterpartyLegal_L6L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(6), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0020P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '.,-`*()+'"/<>№' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0020_CreateNewCounterpartyLegal_L7L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(7), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0021P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'йцукенгшщзхъфывапролджэячсмитьбюёыъэїєґі' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0021_CreateNewCounterpartyLegal_L8L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(8), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0022P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁЫЪЭЇЄҐІ' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0022_CreateNewCounterpartyLegal_L9L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_POSITIVE_PROPERTIES_FROM_FILE.get(9), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0023P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ' И м я ' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0023_CreateNewCounterpartyLegal_L0L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(NEW_COUNTERPARTY_LEGAL_DATA_POSITIVE.get(0), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//    /**
//     * @Positive #0024P
//     * Creation new Counterparty Legal with valid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ' 1 2 3 ' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0024_CreateNewCounterpartyLegal_L1L0_P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(NEW_COUNTERPARTY_LEGAL_DATA_POSITIVE.get(1), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(FIELD_SUCCESS_VALUE_TRUE));
//    }
//
//
//
///**
// * Create new Counterparty - Negative cases
// */
//
//    /**
//     * @Negative #0500N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is not specified' in response from Server after api-request
//     */
//
//    @Test
//    public void test0500_CreateNewCounterpartyLegal_L0L0_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(NEW_COUNTERPARTY_LEGAL_DATA_NEGATIVE.get(0), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_NOT_SPECIFIED));
//    }
//
//    /**
//     * @Negative #0501N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ' ' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is not specified' in response from Server after api-request
//     */
//
//    @Test
//    public void test0501_CreateNewCounterpartyLegal_L1L0_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(NEW_COUNTERPARTY_LEGAL_DATA_NEGATIVE.get(1), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_NOT_SPECIFIED));
//    }
//
//    /**
//     * @Negative #0502N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'null' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is not specified' in response from Server after api-request
//     */
//
//    @Test
//    public void test0502_CreateNewCounterpartyLegal_L2L0_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(NEW_COUNTERPARTY_LEGAL_DATA_NEGATIVE.get(2), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_NOT_SPECIFIED));
//    }
//
//    /**
//     * @Negative #0503N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'тесттестестестестестестестестестестестестестестестестестестес' <- length > 60 symbols
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is invalid' in response from Server after api-request
//     */
//
//    @Test
//    public void test0504_CreateNewCounterpartyLegal_0N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(0), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_TOO_LONG));
//    }
//
//    /**
//     * @Negative #0504N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'ТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТЕСТ' <- length > 60 symbols
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is invalid' in response from Server after api-request
//     */
//
//    @Test
//    public void test0504_CreateNewCounterpartyLegal_1N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(1), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_TOO_LONG));
//    }
//
//    /**
//     * @Negative #0505N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '1234567890123456789012345678901234567890123456789012345678901' <- length > 60 symbols
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is invalid' in response from Server after api-request
//     */
//
//    @Test
//    public void test0505_CreateNewCounterpartyLegal_2N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(2), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_TOO_LONG));
//    }
//
//    /**
//     * @Negative #0506N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'Test' <- latin symbols
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is invalid' in response from Server after api-request
//     */
//
//    @Test
//    public void test0506_CreateNewCounterpartyLegal_3N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(3), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_INVALID));
//    }
//
//    /**
//     * @Negative #0507N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = 'null' <- as text
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is invalid' in response from Server after api-request
//     */
//
//    @Test
//    public void test0507_CreateNewCounterpartyLegal_4N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(4), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_INVALID));
//    }
//
//    /**
//     * @Negative #0508N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '~' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0508_CreateNewCounterpartyLegal_5N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(5), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0509N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '!' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0509_CreateNewCounterpartyLegal_6N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(6), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0510N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '@' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0510_CreateNewCounterpartyLegal_7N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(7), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0511N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '#' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0511_CreateNewCounterpartyLegal_8N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(8), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0512N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '$' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0512_CreateNewCounterpartyLegal_9N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(9), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0513N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '%' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0513_CreateNewCounterpartyLegal_10N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(10), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0514N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '^' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0514_CreateNewCounterpartyLegal_11N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(11), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0515N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '&' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0515_CreateNewCounterpartyLegal_12N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(12), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0516N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '_' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0516_CreateNewCounterpartyLegal_13N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(13), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0517N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '=' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0517_CreateNewCounterpartyLegal_14N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(14), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0519N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '{' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0519_CreateNewCounterpartyLegal_15N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(15), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0520N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '}' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0520_CreateNewCounterpartyLegal_16N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(16), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #05218N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '[' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0521_CreateNewCounterpartyLegal_17N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(17), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0522N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ']' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0522_CreateNewCounterpartyLegal_18N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(18), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0523N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ':' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0523_CreateNewCounterpartyLegal_19N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(19), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0524N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = ';' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0525_CreateNewCounterpartyLegal_20N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(20), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0526N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '|' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0526_CreateNewCounterpartyLegal_21N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(21), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0527N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '\' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0527_CreateNewCounterpartyLegal_22N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(22), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0528N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '<' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName is not specified' in response from Server after api-request
//     */
//
//    @Test
//    public void test0528_CreateNewCounterpartyLegal_23N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(23), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_IS_NOT_SPECIFIED));
//    }
//
//    /**
//     * @Negative #0529N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '>' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0529_CreateNewCounterpartyLegal_24N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(24), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0530N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * FirstName = '?' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='CompanyName has invalid characters' in response from Server after api-request
//     */
//
//    @Test
//    public void test0530_CreateNewCounterpartyLegal_25N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(this.TEST_NEGATIVE_PROPERTIES_FROM_FILE.get(25), OWNERSHIP_FORM_LIST.get(0)));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS));
//    }
//
//    /**
//     * @Negative #0531N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * АirstName = 'Имя'
//     * MiddleName = 'Отчество' <-
//     * LastName = 'Фамилия' <-
//     * Phone = '380670000000' <-
//     * Email = 'test@test.com' <-
//     * <p>
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for true if errors =
//     * [
//     * "MiddleName must be empty",
//     * "LastName must be empty",
//     * "Phone must be empty",
//     * "Email must be empty"
//     * ]
//     * <p>
//     * in response from Server after api-request
//     */
//
//    @Test
//    public void test0531_CreateNewCounterpartyLegal_A0L0_N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance(TEST_DATA_COUNTERPARTY_LEGAL));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertTrue(ToolsJSON.checkValuesInJsonNodeList(ToolsJSON.getParamsValueAsList(this.serverResponse.toString(), FIELD_ERRORS), Arrays.asList(
//                ERROR_MIDDLE_NAME_MUST_BE_EMPTY,
//                ERROR_LAST_NAME_MUST_BE_EMPTY,
//                ERROR_PHONE_MUST_BE_EMPTY,
//                ERROR_EMAIL_MUST_BE_EMPTY)));
//    }
//
//    /**
//     * @Negative #0532N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * CounterpartyProperty = 'Sender' <-
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='You can only create counterparties Recipient' in response from Server after api-request
//     */
//
//    @Test
//    public void test0532_CreateNewCounterpartyLegal_001N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance4(new String[]{
//                CITY_REF.get(0),
//                TEST_DATA_COUNTERPARTY_LEGAL.get(0),
//                COUNTERPARTY_TYPE_ORGANIZATION,
//                COUNTERPARTY_PROPERTY_SENDER,
//                OWNERSHIP_FORM_LIST.get(0)}));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_YOU_CAN_ONLY_CREATE_COUNTERPARTY_RECIPIENT));
//    }
//
//    /**
//     * @Negative #0533N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * CounterpartyType = 'FakeType' <-
//     * CounterpartyProperty = 'Sender'
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='You can only create counterparties Recipient' in response from Server after api-request
//     */
//
//    @Test
//    public void test0533_CreateNewCounterpartyLegal_002N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance4(new String[]{
//                CITY_REF.get(0),
//                TEST_DATA_COUNTERPARTY_LEGAL.get(0),
//                COUNTERPARTY_TYPE_FAKE,
//                COUNTERPARTY_PROPERTY_SENDER,
//                OWNERSHIP_FORM_LIST.get(0)}));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COUNTERPARTY_TYPE_IS_INVALID));
//    }
//
//    /**
//     * @Negative #0533N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * CounterpartyType = 'FakeType' <-
//     * CounterpartyProperty = 'Recipient'
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='You can only create counterparties Recipient' in response from Server after api-request
//     */
//
//    @Test
//    public void test0534_CreateNewCounterpartyLegal_003N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance4(new String[]{
//                CITY_REF.get(0),
//                TEST_DATA_COUNTERPARTY_LEGAL.get(0),
//                COUNTERPARTY_TYPE_FAKE,
//                COUNTERPARTY_PROPERTY_RECIPIENT,
//                OWNERSHIP_FORM_LIST.get(0)}));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_COUNTERPARTY_TYPE_IS_INVALID));
//    }
//
//    /**
//     * @Negative #0534N
//     * Creation new Counterparty Legal with invalid parameters:
//     * <p>
//     * Check for creating with simple parameters
//     * <p>
//     * OwnershipForm = '00000000-0000-0000-0000-000000000000' <-
//     * CounterpartyProperty = 'Recipient'
//     * OwnershipForm = 'ВК'
//     * <p>
//     * Check for error='You can only create counterparties Recipient' in response from Server after api-request
//     */
//
//    @Test
//    public void test0535_CreateNewCounterpartyLegal_004N() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.jsonStringForSend = ToolsJSON.parseObjectToJson(CounterpartyCreateLegalModel.getInstance4(new String[]{
//                CITY_REF.get(0),
//                TEST_DATA_COUNTERPARTY_LEGAL.get(0),
//                COUNTERPARTY_TYPE_ORGANIZATION,
//                COUNTERPARTY_PROPERTY_RECIPIENT,
//                OWNERSHIP_FORM_FAKE}));
//        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
//        this.serverResponse = this.sender.sendApiRequest();
//        Assert.assertEquals(true, this.serverResponse.toString().contains(ERROR_OWNERSHIP_FORM_IS_INCORRECT));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
///**
// * Checking the existence of previously created Counterparties Legal - Positive cases
// */
//
//    /**
//     * @Positive #00EC
//     * Creation new CounterpartyLegal with invalid parameters (invalid email)
//     *
//     * Run only after running all tests!!!
//     * Check for true if refs exist
//     *
//     */
//
//    @Test
//    public void test0163_CreateNewCounterpartyLegal_00EC() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        Assert.assertTrue(this.refList.containsAll(ToolsJSON.getFieldValue(CounterpartyGetCounterparties.getInstance(COUNTERPARTY_PROPERTY_RECIPIENT), FIELD_REF)));
//    }
//
//}