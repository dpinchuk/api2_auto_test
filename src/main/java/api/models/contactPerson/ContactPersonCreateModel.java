package api.models.contactPerson;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects ContactPerson of (@link resources.api)
 */

@Getter
@Setter
public final class ContactPersonCreateModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private ContactPersonCreateModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTACT_PERSON;
        this.calledMethod = CALLED_METHOD_SAVE;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String counterpartyRef;
        private String firstName;
        private String middleName;
        private String lastName;
        private String phone;
        private String email;

        private MethodProperties(String firstName, String middleName, String lastName, String phone, String email) {
            this.counterpartyRef = REF_OWNER;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
        }

        @JsonProperty("CounterpartyRef")
        public String getCounterpartyRef() {
            return this.counterpartyRef;
        }

        @JsonProperty("FirstName")
        public String getFirstName() {
            return this.firstName;
        }

        @JsonProperty("MiddleName")
        public String getMiddleName() {
            return this.middleName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return this.lastName;
        }

        @JsonProperty("Phone")
        public String getPhone() {
            return this.phone;
        }

        @JsonProperty("Email")
        public String getEmail() {
            return this.email;
        }

    }

    public static ContactPersonCreateModel getInstance(String[] properties) {
        return new ContactPersonCreateModel(
                new MethodProperties(
                        properties[0],
                        properties[1],
                        properties[2],
                        properties[3],
                        properties[4]
                ));

    }

}