package api.models.counterparty;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterParty of (@link resources.api)
 * одержувач
 */

@Getter
@Setter
public final class CounterpartyUpdateModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CounterpartyUpdateModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_SAVE;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String ref;
        private String cityRef;
        private String firstName;
        private String middleName;
        private String lastName;
        private String phone;
        private String email;
        private String counterpartyType;
        private String counterpartyProperty;

        private MethodProperties(String firstName, String middleName, String lastName, String phone, String email) {
            this.ref = REF_OWNER;
            this.cityRef = CITY_REF_DNEPR;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.counterpartyType = COUNTERPARTY_TYPE_PRIVATE_PERSON;
            this.counterpartyProperty = COUNTERPARTY_PROPERTY_RECIPIENT;
        }

        @JsonProperty("Ref")
        public String getRef() {
            return this.ref;
        }

        @JsonProperty("CityRef")
        public String getCityRef() {
            return this.cityRef;
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

        @JsonProperty("CounterpartyType")
        public String getCounterpartyType() {
            return this.counterpartyType;
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return this.counterpartyProperty;
        }
    }

    public static CounterpartyUpdateModel getInstance(String[] properties) {
        return new CounterpartyUpdateModel(
                new MethodProperties(
                        properties[0],
                        properties[1],
                        properties[2],
                        properties[3],
                        properties[4]
                ));
    }

}