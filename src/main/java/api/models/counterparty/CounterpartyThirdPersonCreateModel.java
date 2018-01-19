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
public class CounterpartyThirdPersonCreateModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CounterpartyThirdPersonCreateModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_SAVE;
        this.methodProperties = methodProperties;
    }

    private static class MethodProperties {

        private String cityRef;
        private String firstName;
        private String middleName;
        private String lastName;
        private String phone;
        private String email;
        private String counterpartyType;
        private String counterpartyProperty;
        private String edrpou;
        private String ownershipForm;

        private MethodProperties(String firstName, String middleName, String lastName, String phone, String email, String edrpou, String ownershipForm) {
            this.cityRef = CITY_REF_COUNTERPARTY_THIRD_PERSON;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.counterpartyType = COUNTERPARTY_THIRD_PERSON;
            this.counterpartyProperty = COUNTERPARTY_PROPERTY_THIRD_PERSON;
            this.edrpou = edrpou;
            this.ownershipForm = ownershipForm;
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

        @JsonProperty("EDRPOU")
        public String getEDRPOU() {
            return this.edrpou;
        }

        @JsonProperty("OwnershipForm")
        public String getOwnershipForm() {
            return this.ownershipForm;
        }
    }

    public static CounterpartyThirdPersonCreateModel getInstance(String[] properties) {
        return new CounterpartyThirdPersonCreateModel(
                new MethodProperties(
                        properties[0],
                        properties[1],
                        properties[2],
                        properties[3],
                        properties[4],
                        properties[5],
                        properties[6]
                ));
    }

}