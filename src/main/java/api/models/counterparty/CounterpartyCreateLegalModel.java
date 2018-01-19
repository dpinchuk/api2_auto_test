package api.models.counterparty;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterParty of (@link resources.api)
 */

@Getter
@Setter
public final class CounterpartyCreateLegalModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CounterpartyCreateLegalModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_SAVE;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String cityRef;
        private String firstName;
        private String middleName;
        private String lastName;
        private String phone;
        private String email;
        private String counterpartyType;
        private String counterpartyProperty;
        private String ownershipForm;

        private MethodProperties(String firstName, String ownershipForm) {
            this.cityRef = CITY_REF_COUNTERPARTY_LEGAL;
            this.firstName = firstName;
            this.middleName = "";
            this.lastName = "";
            this.phone = "";
            this.email = "";
            this.counterpartyType = COUNTERPARTY_TYPE_ORGANIZATION;
            this.counterpartyProperty = COUNTERPARTY_PROPERTY_RECIPIENT;
            this.ownershipForm = ownershipForm;
        }

        private MethodProperties(String firstName, String middleName, String lastName, String phone, String email, String ownershipForm) {
            this.cityRef = CITY_REF_COUNTERPARTY_LEGAL;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.counterpartyType = COUNTERPARTY_TYPE_ORGANIZATION;
            this.counterpartyProperty = COUNTERPARTY_PROPERTY_RECIPIENT;
            this.ownershipForm = ownershipForm;
        }

        private MethodProperties(String cityRef, String firstName, String counterpartyType, String counterpartyProperty, String ownershipForm) {
            this.cityRef = cityRef;
            this.firstName = firstName;
            this.middleName = "";
            this.lastName = "";
            this.phone = "";
            this.email = "";
            this.counterpartyType = counterpartyType;
            this.counterpartyProperty = counterpartyProperty;
            this.ownershipForm = ownershipForm;
        }

        @JsonProperty("CityRef")
        public String getCityRef() {
            return cityRef;
        }

        @JsonProperty("FirstName")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("MiddleName")
        public String getMiddleName() {
            return middleName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return lastName;
        }

        @JsonProperty("Phone")
        public String getPhone() {
            return phone;
        }

        @JsonProperty("Email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("CounterpartyType")
        public String getCounterpartyType() {
            return counterpartyType;
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return counterpartyProperty;
        }

        @JsonProperty("OwnershipForm")
        public String getOwnershipForm() {
            return ownershipForm;
        }
    }

    public static CounterpartyCreateLegalModel getInstance(String firstName, String ownershipForm) {
        return new CounterpartyCreateLegalModel(new MethodProperties(firstName, ownershipForm));
    }

    public static CounterpartyCreateLegalModel getInstance(List<String> params) {
        return new CounterpartyCreateLegalModel(new MethodProperties(
                params.get(0),
                params.get(1),
                params.get(2),
                params.get(3),
                params.get(4),
                params.get(5)));
    }

    public static CounterpartyCreateLegalModel getInstance4(String[] params) {
        return new CounterpartyCreateLegalModel(new MethodProperties(
                params[0],
                params[1],
                params[2],
                params[3],
                params[4]));
    }

}