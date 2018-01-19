package api.models.counterparty;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterParty of (@link resources.api)
 */

@Getter
@Setter
public final class CounterpartyCreateModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CounterpartyCreateModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_SAVE;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String CityRef;
        private String FirstName;
        private String MiddleName;
        private String LastName;
        private String Phone;
        private String Email;
        private String CounterpartyType;
        private String CounterpartyProperty;

        private MethodProperties(String firstName, String middleName, String lastName, String phone, String email) {
            this.CityRef = CITY_REF_DNEPR;
            this.FirstName = firstName;
            this.MiddleName = middleName;
            this.LastName = lastName;
            this.Phone = phone;
            this.Email = email;
            this.CounterpartyType = COUNTERPARTY_TYPE_PRIVATE_PERSON;
            this.CounterpartyProperty = COUNTERPARTY_PROPERTY_RECIPIENT;
        }

        @JsonProperty("CityRef")
        public String getCityRef() {
            return this.CityRef;
        }

        @JsonProperty("FirstName")
        public String getFirstName() {
            return this.FirstName;
        }

        @JsonProperty("MiddleName")
        public String getMiddleName() {
            return this.MiddleName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return this.LastName;
        }

        @JsonProperty("Phone")
        public String getPhone() {
            return this.Phone;
        }

        @JsonProperty("Email")
        public String getEmail() {
            return this.Email;
        }

        @JsonProperty("CounterpartyType")
        public String getCounterpartyType() {
            return this.CounterpartyType;
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return this.CounterpartyProperty;
        }
    }

    public static CounterpartyCreateModel getInstance(String[] properties) {
        return new CounterpartyCreateModel(
                new MethodProperties(
                        properties[0],
                        properties[1],
                        properties[2],
                        properties[3],
                        properties[4]
                ));
    }

}