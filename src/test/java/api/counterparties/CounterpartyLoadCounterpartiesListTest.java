//package api.counterparties;
//
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
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static api.utils.Constants.*;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class CounterpartyLoadCounterpartiesListTest {
//
//    private static int testCount = 0;
//    private Sender sender;
//    private JsonObject serverResponse;
//    private String jsonStringForSend;
//
//    private final List<String> TEST_POSITIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_COUNTERPARTY_TEST_PROPERTIES_POSITIVE, TEG_IGNORE);
//    private final List<String> TEST_NEGATIVE_PROPERTIES_FROM_FILE = ToolsJSON.getPropertiesFromFileWithFilter(TEST_PROPERTIES_PATH + FILE_COUNTERPARTY_TEST_PROPERTIES_NEGATIVE, TEG_IGNORE);
//    private static Set<String> refList = new HashSet<>();
//
//    public CounterpartyLoadCounterpartiesListTest() throws IOException {
//    }
//
//    public Statement apply(final Statement base, Description description) {
//        return new Statement() {
//            @Override
//            public void evaluate() throws Throwable {
//                System.out.println("before");
//                //base.evaluate();
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
//        System.out.println();
//    }
//
//
//    /**
//     * Init test environment
//     */
//
//    @BeforeClass
//    public static void test0000_InitTestEnvitonment_0TE() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        //Init.init();
//    }
//
//    @AfterClass
//    public static void test0001_InitTestEnvitonment_0TE() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        //Init.init();
//    }
//
//
///**
// * Create new Counterparty - Positive cases
// */
//
//    /**
//     * @Positive #0P
//     * Creation new counterparty with valid parameters
//     * Check for success='true' in response from Server after api-request
//     */
//
//    @Test
//    public void test0002_CreateNewCounterparty_0P() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
////        this.jsonStringForSend = Tools.parseObjectToJson(CounterpartyCreateModel.getInstance(NEW_COUNTERPARTY_DATA_POSITIVE_WITH_SPACES.get(0)));
////        this.sender = new Sender(API_URL_ADDRESS + JSON, this.jsonStringForSend);
////        this.serverResponse = this.sender.sendApiRequest();
//
//        List<String> stringList = ToolsJSON.getCounterpartiesListOfSenderOrRecipientRef("Recipient", REF_OWNER);
//
//        Assert.assertTrue(!stringList.isEmpty());
//    }
//
//
//
//}