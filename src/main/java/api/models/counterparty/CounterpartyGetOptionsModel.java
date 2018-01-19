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
public final class CounterpartyGetOptionsModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CounterpartyGetOptionsModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_GET_COUNTERPARTY_ADDRESSES;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String ref;

        private MethodProperties() {
            this.ref = REF_OWNER;
        }

        @JsonProperty("Ref")
        public String getRef() {
            return this.ref;
        }

    }

    public static CounterpartyGetOptionsModel getInstance(String[] properties) {
        return new CounterpartyGetOptionsModel(new MethodProperties());
    }

}