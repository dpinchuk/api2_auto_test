package api.models.common;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Getter
@Setter
public final class CommonCounterpartyModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CommonCounterpartyModel(String apiKey, String modelName, String calledMethod, MethodProperties methodProperties) {
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.calledMethod = calledMethod;
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
        private String edrpou;
        private String ownershipForm;
        private String page;
        private String counterpartyRef;

        private MethodProperties(String ref, String cityRef, String firstName, String middleName, String lastName,
                                 String phone, String email, String counterpartyType, String counterpartyProperty,
                                 String edrpou, String ownershipForm, String page, String counterpartyRef) {
            this.ref = ref;
            this.cityRef = cityRef;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.counterpartyType = counterpartyType;
            this.counterpartyProperty = counterpartyProperty;
            this.edrpou = edrpou;
            this.ownershipForm = ownershipForm;
            this.page = page;
            this.counterpartyRef = counterpartyRef;
        }

        @JsonProperty("Ref")
        public String getRef() {
            return ref;
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

        @JsonProperty("EDRPOU")
        public String getEdrpou() {
            return edrpou;
        }

        @JsonProperty("OwnershipForm")
        public String getOwnershipForm() {
            return ownershipForm;
        }

        @JsonProperty("Page")
        public String getPage() {
            return page;
        }

        @JsonProperty("CounterpartyRef")
        public String getCounterpartyRef() {
            return counterpartyRef;
        }
    }

    public static CommonCounterpartyModel getInstance(
            List<String> list) {

        return new CommonCounterpartyModel(
                list.get(0), // apiKey
                list.get(1), // modelName
                list.get(2), // calledMethod
                new MethodProperties(
                        list.get(3),  // Ref
                        list.get(4),  // CityRef
                        list.get(5),  // Firstname
                        list.get(6),  // MiddleName
                        list.get(7),  // LastName
                        list.get(8),  // Phone
                        list.get(9),  // Email
                        list.get(10), // CounterpartyType
                        list.get(11), // CounterpartyProperty
                        list.get(12), // EDRPOU
                        list.get(13), // OwnershipForm
                        list.get(14), // Page
                        list.get(15)  // CounterpartyRef
                ));
    }

    public static CommonCounterpartyModel getInstanceRef(
            List<String> list, String ref) {

        return new CommonCounterpartyModel(
                list.get(0), // apiKey
                list.get(1), // modelName
                list.get(2), // calledMethod
                new MethodProperties(
                        ref,  // Ref
                        "",  // CityRef
                        list.get(5),  // Firstname
                        list.get(6),  // MiddleName
                        list.get(7),  // LastName
                        list.get(8),  // Phone
                        list.get(9),  // Email
                        "", // CounterpartyType
                        "", // CounterpartyProperty
                        "", // EDRPOU
                        "", // OwnershipForm
                        "",  // Page
                        list.get(15)  // CounterpartyRef
                ));
    }

}