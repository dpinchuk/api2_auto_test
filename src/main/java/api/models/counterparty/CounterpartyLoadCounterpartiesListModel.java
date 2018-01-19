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
public final class CounterpartyLoadCounterpartiesListModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CounterpartyLoadCounterpartiesListModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_GET_COUNTERPARTIES;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String counterpartyProperty;
        private String page;

        private MethodProperties(String counterpartyProperty) {
            this.counterpartyProperty = counterpartyProperty;
            this.page = "1";
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return this.counterpartyProperty;
        }

        @JsonProperty("Page")
        public String getPage() {
            return this.page;
        }

    }

    public static CounterpartyLoadCounterpartiesListModel getInstance(String[] properties) {
        return new CounterpartyLoadCounterpartiesListModel(
                new MethodProperties(
                        properties[0]
                ));
    }

}