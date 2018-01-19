package api.models.counterparty;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterpartyContactPerson of (@link resources.api)
 */

@Getter
@Setter
public final class CounterpartyGetCounterpartyContactPersonModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CounterpartyGetCounterpartyContactPersonModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTERPARTY;
        this.calledMethod = CALLED_METHOD_GET_COUNTERPARTY_CONTACT_PERSON;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String Ref;
        private String Page;

        private MethodProperties() {
            this.Ref = REF_OWNER;
            this.Page = "1";
        }

        @JsonProperty("Ref")
        public String getCityRef() {
            return Ref;
        }

        @JsonProperty("Page")
        public String getFirstName() {
            return Page;
        }

    }

    public static CounterpartyGetCounterpartyContactPersonModel getInstance() {
        return new CounterpartyGetCounterpartyContactPersonModel(new MethodProperties());
    }

}
