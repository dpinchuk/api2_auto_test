package api.models.counterparty;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterpartyContactPerson of (@link resources.api)
 */

@Getter
public final class CounterpartyGetCounterparties {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CounterpartyGetCounterparties(MethodProperties methodProperties) {
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
        public String getCityRef() {
            return counterpartyProperty;
        }

        @JsonProperty("Page")
        public String getFirstName() {
            return page;
        }

    }

    public static CounterpartyGetCounterparties getInstance(String counterpartyProperty) {
        return new CounterpartyGetCounterparties(new MethodProperties(counterpartyProperty));
    }

}