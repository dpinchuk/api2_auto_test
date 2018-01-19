package api.utils;

import javax.xml.stream.StreamFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * interface with constants
 */

public interface Constants {

    String JSON_PATH                                                        = "./src/main/resources/api/json/counterparty/";
    String TEST_PROPERTIES_PATH                                             = "./src/main/resources/api/testproperties";

    String API_URL_ADDRESS                                                  = "https://api2.novaposhta.ua/v2.0/";

    String JSON                                                             = "json/";
    String XML                                                              = "xml/";

    String CHARSET                                                          = "UTF-8";
    String TEG_IGNORE                                                       = "@ignore";

    String FILE_COUNTER_PARTY_JSON                                          = "createCounterparty.json";

    String FILE_REF                                                         = "/ref/ref.txt";

    String COUNTERPARTY_CREATE_POSITIVE                                     = "/counterparty/excel/positive/create/counterpartyCreatePositive.xls";
    String COUNTERPARTY_CREATE_NEGATIVE                                     = "/counterparty/excel/negative/create/counterpartyCreateNegative.xls";
    String COUNTERPARTY_UPDATE_POSITIVE                                     = "/counterparty/excel/positive/update/counterpartyUpdatePositive.xls";
    String COUNTERPARTY_UPDATE_NEGATIVE                                     = "/counterparty/excel/negative/update/counterpartyUpdateNegative.xls";

    String CONTACT_PERSON_CREATE_POSITIVE                                   = "/contactPerson/excel/positive/create/contactPersonCreatePositive.xls";
    String CONTACT_PERSON_CREATE_NEGATIVE                                   = "/contactPerson/excel/negative/create/contactPersonCreateNegative.xls";
    String CONTACT_PERSON_UPDATE_POSITIVE                                   = "/contactPerson/excel/positive/update/contactPersonUpdatePositive.xls";
    String CONTACT_PERSON_UPDATE_NEGATIVE                                   = "/contactPerson/excel/negative/update/contactPersonUpdateNegative.xls";

    String API_KEY_COUNTERPARTY_USER                                        = "044f8693ad1f11a09ccc6142994b0761";
    String API_KEY_COUNTERPARTY_ADMIN                                       = "";

    // Models names
    String MODEL_NAME_COUNTERPARTY                                          = "Counterparty";
    String MODEL_NAME_COUNTACT_PERSON                                       = "ContactPerson";

    // Called methods
    String CALLED_METHOD_SAVE                                               = "save";
    String CALLED_METHOD_GET_COUNTERPARTIES                                 = "getCounterparties";
    String CALLED_METHOD_GET_COUNTERPARTY_CONTACT_PERSON                    = "getCounterpartyContactPersons";
    String CALLED_METHOD_GET_COUNTERPARTY_ADDRESSES                         = "getCounterpartyAddresses";
    String CALLED_METHOD_GET_COUNTERPARTY_OPTIONS                           = "getCounterpartyOptions";
    String CALLED_METHOD_DELETE_ALONE_CONTACT                               = "delete";
    String CALLED_METHOD_DELETE_PACK_CONTACTS                               = "deletePack";
    String CALLED_METHOD_UPDATE                                             = "update";

    String CITY_REF_DNEPR                                                   = "db5c88d7-391c-11dd-90d9-001a92567626";
    String CITY_REF_COUNTERPARTY_LEGAL                                      = "8d5a980d-391c-11dd-90d9-001a92567626";
    String CITY_REF_COUNTERPARTY_THIRD_PERSON                               = "8d5a980d-391c-11dd-90d9-001a92567626";
    String COUNTERPARTY_REF_CONTACT_PERSON                                  = "768da332-159d-11e5-ad08-005056801333";

    String REF_OWNER                                                        = "63aa8726-f146-11e7-becf-005056881c6b";

    String COUNTERPARTY_TYPE_PRIVATE_PERSON                                 = "PrivatePerson";
    String COUNTERPARTY_TYPE_ORGANIZATION                                   = "Organization";
    String COUNTERPARTY_THIRD_PERSON                                        = "Organization";
    String COUNTERPARTY_TYPE_FAKE                                           = "FakeType";

    String COUNTERPARTY_PROPERTY_RECIPIENT                                  = "Recipient";
    String COUNTERPARTY_PROPERTY_SENDER                                     = "Sender";
    String COUNTERPARTY_PROPERTY_THIRD_PERSON                               = "ThirdPerson";

    String REGEX_REPLACE_QUOTES                                             = "[\"]";
    String REGEX_REPLACE_QUOTES_BRACKETS                                    = "[\\[\\]\"]";
    String REGEX_SPLIT_COMMA                                                = ",";
    String REGEX_SPLIT_SPACE                                                = "\\s+";

    String OWNERSHIP_FORM_FAKE                                              = "00000000-0000-0000-0000-000000000000";
    String OWNERSHIP_FORM_EMPTY                                             = "";

    String PAGE                                                             = "1";

    List<String> OWNERSHIP_FORM_LIST = Arrays.asList(
            "7f0f3516-2519-11df-be9a-000c291af1b3", // 0.  ВК
            "7f0f3515-2519-11df-be9a-000c291af1b3", // 1.  ДП
            "7f0f3518-2519-11df-be9a-000c291af1b3", // 2.  КП
            "10d78dad-2352-11e2-83ab-d4ae52ab9fab", // 3.  КТ
            "361b83db-886e-11e1-a146-0026b97ed48a", // 4.  ПАТ
            "9252696e-2202-11e4-acce-0050568002cf", // 5.  ПІІ
            "7f0f3519-2519-11df-be9a-000c291af1b3", // 6.  ПП
            "b0b2c790-8920-11e1-8429-0026b97ed48a", // 7.  ПрАТ
            "7f0f3514-2519-11df-be9a-000c291af1b3", // 8.  ПТ
            "7f0f351a-2519-11df-be9a-000c291af1b3", // 9.  СП
            "7f0f351c-2519-11df-be9a-000c291af1b3", // 10. ТДВ
            "7f0f351d-2519-11df-be9a-000c291af1b3", // 11. ТОВ
            "7f0f3517-2519-11df-be9a-000c291af1b3", // 12. ФГ
            "7f0f351e-2519-11df-be9a-000c291af1b3"  // 13. ФОП (СПД)
    );

    List<String> NEW_COUNTERPARTY_CREATE_POSITIVE = Arrays.asList(
            API_KEY_COUNTERPARTY_USER,
            MODEL_NAME_COUNTERPARTY,
            CALLED_METHOD_SAVE,
            "",
            "db5c88d7-391c-11dd-90d9-001a92567626",
            "Тестовый",
            "Тестовый",
            "Тестовый",
            "+380971344443",
            "iTestPRO@test.com",
            COUNTERPARTY_TYPE_PRIVATE_PERSON,
            COUNTERPARTY_PROPERTY_RECIPIENT,
            "",
            "",
            "",
            ""
    );

    List<String> NEW_CONTACT_PERSON_CREATE_POSITIVE = Arrays.asList(
            API_KEY_COUNTERPARTY_USER,
            MODEL_NAME_COUNTACT_PERSON,
            CALLED_METHOD_SAVE,
            "",
            "",
            "Тестовый",
            "Тестовый",
            "Тестовый",
            "+380971344443",
            "iTestPRO@test.com",
            "",
            "",
            "",
            "",
            "",
            REF_OWNER
    );

    List<String> GET_COUNTERPARTY_CONTACT_PERSON = Arrays.asList(
            API_KEY_COUNTERPARTY_USER,
            MODEL_NAME_COUNTERPARTY,
            CALLED_METHOD_GET_COUNTERPARTY_CONTACT_PERSON,
            REF_OWNER,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            PAGE,
            ""
    );

    List<String> DELETE_COUNTERPARTY_CONTACT_PERSON = Arrays.asList(
            API_KEY_COUNTERPARTY_USER,
            MODEL_NAME_COUNTACT_PERSON,
            CALLED_METHOD_DELETE_ALONE_CONTACT,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    );


//    List<String[]> NEW_COUNTERPARTY_DATA_NEGATIVE = Arrays.asList(
//            new String[]{
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""},
//            new String[]{
//                    " ",
//                    " ",
//                    " ",
//                    " ",
//                    " "},
//            new String[]{
//                    "Имя",
//                    "Отчество",
//                    "Фамилия",
//                    "3 8 0 6 7 0 0 0 0 0 9 9 ",
//                    "t e s t @ t e s t . t s t"},
//            new String[]{},
//            new String[]{
//                    null,
//                    null,
//                    null,
//                    null,
//                    null}
//            );
//
//    List<String> NEW_COUNTERPARTY_LEGAL_DATA_POSITIVE = Arrays.asList(
//            " И м я ",
//            " 1 2 3 "
//    );
//
//    List<String> NEW_COUNTERPARTY_LEGAL_DATA_NEGATIVE = Arrays.asList(
//            "",
//            " ",
//            null
//    );
//
//    List<String> TEST_DATA_COUNTERPARTY_LEGAL = Arrays.asList(
//            "Юридическое Имя",
//            "Отчество",
//            "Фамилия",
//            "+380670000000",
//            "test@test.com",
//            OWNERSHIP_FORM_LIST.get(0)
//    );

    String FIELD_API_KEY                                                    = "apiKey";
    String FIELD_MODEL_NAME                                                 = "modelName";
    String FIELD_CALLED_METHOD                                              = "calledMethod";
    String FIELD_METHOD_PROPERTIES                                          = "methodProperties";
    String FIELD_CITY_REF                                                   = "CityRef";
    String FIELD_FIRST_NAME                                                 = "FirstName";
    String FIELD_MIDDLE_NAME                                                = "MiddleName";
    String FIELD_LAST_NAME                                                  = "LastName";
    String FIELD_PHONE                                                      = "Phone";
    String FIELD_PHONES                                                     = "Phones";
    String FIELD_EMAIL                                                      = "Email";
    String FIELD_COUNTERPARTY_TYPE                                          = "CounterpartyType";
    String FIELD_COUNTERPARTY_PROPERTY                                      = "CounterpartyProperty";
    String FIELD_REF                                                        = "Ref";
    String FIELD_DOCUMENT_REFS                                              = "DocumentRefs";
    String FIELD_PAGE                                                       = "Page";
    String FIELD_ERRORS                                                     = "errors";
    String FIELD_SUCCESS                                                    = "success";
    String FIELD_WARNINGS                                                   = "warnings";
    String FIELD_INFO                                                       = "info";
    String FIELD_TOTAL_COUNT                                                = "totalCount";
    String FIELD_MESSAGE_CODES                                              = "messageCodes";
    String FIELD_ERROR_CODES                                                = "errorCodes";
    String FIELD_WARNING_CODES                                              = "warningCodes";
    String FIELD_INFO_CODES                                                 = "infoCodes";
    String FIELD_DATA                                                       = "data";
    String FIELD_DESCRIPTION                                                = "Description";
    String FIELD_OWNERSHIP_FORM                                             = "OwnershipForm";
    String FIELD_OWNERSHIP_FORM_DESCRIPTION                                 = "OwnershipFormDescription";
    String FIELD_EDRPOU                                                     = "EDRPOU";
    String FIELD_EDRPOU_EMPTY                                               = "";

    String FIELD_SUCCESS_VALUE_TRUE                                         = "true";
    String FIELD_SUCCESS_VALUE_FALSE                                        = "false";

    String FIELD_DATA_EMPTY                                                 = "\"data\":[]";

    String WARNING_PERSON_ALREADY_EXISTS                                    = "Person already exists!";

    String ERROR_FIRST_NAME_HAS_INVALID_CHARACTERS                          = "FirstName has invalid characters";
    String ERROR_FIRST_NAME_IS_NOT_SPECIFIED                                = "FirstName is not specified";

    String ERROR_MIDDLE_NAME_HAS_INVALID_CHARACTERS                         = "MiddleName has invalid characters";
    String ERROR_MIDDLE_NAME_MUST_BE_EMPTY                                  = "MiddleName must be empty";
    String ERROR_MIDDLE_NAME_IS_NOT_SPECIFIED                               = "MiddleName is not specified";

    String ERROR_LAST_NAME_HAS_INVALID_CHARACTERS                           = "LastName has invalid characters";
    String ERROR_LAST_NAME_MUST_BE_EMPTY                                    = "LastName must be empty";
    String ERROR_LAST_NAME_IS_NOT_SPECIFIED                                 = "LastName is not specified";

    String ERROR_FULL_NAME_IS_LONGER_RHEN_50_CHARACTERS                     = "Full Name is longer then 50 characters";

    String ERROR_PHONE_INVALID_FORMAT                                       = "Phone invalid format";
    String ERROR_PHONE_MUST_BE_EMPTY                                        = "Phone must be empty";
    String ERROR_PHONE_IS_NOT_SPECIFIED                                     = "Phone is not specified";

    String ERROR_EMAIL_INVALID_FORMAT                                       = "Email invalid format";
    String ERROR_EMAIL_MUST_BE_EMPTY                                        = "Email must be empty";
    String ERROR_EMAIL_TOO_LONG                                             = "Email too long";

    String ERROR_COUNTERPARTY_TYPE_IS_INVALID                               = "CounterpartyType is invalid";

    String ERROR_OWNERSHIP_FORM_IS_NOT_SPECIFIED                            = "OwnershipForm is not specified";
    String ERROR_OWNERSHIP_FORM_IS_INCORRECT                                = "OwnershipForm is incorrect";

    String ERROR_STRING_TOO_LONG                                            = "String too long";

    String ERROR_COMPANY_NAME_IS_NOT_SPECIFIED                              = "CompanyName is not specified";
    String ERROR_COMPANY_NAME_IS_INVALID                                    = "CompanyName is invalid";
    String ERROR_COMPANY_NAME_TOO_LONG                                      = "CompanyName too long";
    String ERROR_COMPANY_NAME_HAS_INVALID_CHARACTERS                        = "CompanyName has invalid characters";

    String ERROR_YOU_CAN_ONLY_CREATE_COUNTERPARTY_RECIPIENT                 = "You can only create counterparties Recipient";

    String NONE                                                             = "NONE";
    String NULL                                                             = "null";
    String SPACE                                                            = " ";

    List<String> CITY_REF = Arrays.asList(
            "db5c8892-391c-11dd-90d9-001a92567626"
    );

}