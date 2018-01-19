package api.models.common;


import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterpartyContactPerson of (@link resources.api)
 */

@Getter
@Setter
public final class CommonContactDeleteModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private CommonContactDeleteModel(String modelName, MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = modelName;
        this.calledMethod = CALLED_METHOD_DELETE_ALONE_CONTACT;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String Ref;

        private MethodProperties(String Ref) {
            this.Ref = Ref;
        }

        @JsonProperty("Ref")
        public String getCityRef() {
            return Ref;
        }

    }

    public static CommonContactDeleteModel getInstance(String modelName, String ref) {
        return new CommonContactDeleteModel(modelName, new MethodProperties(ref));
    }

}