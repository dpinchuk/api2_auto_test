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
public final class CounterpartyGetAddressesModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CounterpartyGetAddressesModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_GET_COUNTERPARTY_ADDRESSES;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String cityRef;
        private String counterpartyProperty;

        private MethodProperties(String counterpartyProperty) {
            this.cityRef = REF_OWNER;
            this.counterpartyProperty = counterpartyProperty;
        }

        @JsonProperty("CityRef")
        public String getCityRef() {
            return this.cityRef;
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return this.counterpartyProperty;
        }
    }

    // properties[0] == "Recipient" || "Sender"
    public static CounterpartyGetAddressesModel getInstance(String[] properties) {
        return new CounterpartyGetAddressesModel(
                new MethodProperties(
                        properties[0]
                ));
    }

}