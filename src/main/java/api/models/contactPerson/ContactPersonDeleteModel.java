package api.models.contactPerson;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import static api.utils.Constants.*;

/**
 * simple model that represents JSON-objects CounterpartyContactPerson of (@link resources.api)
 */

@Getter
@Setter
public final class ContactPersonDeleteModel {

    private MethodProperties methodProperties;
    private String apiKey;
    private String modelName;
    private String calledMethod;

    private ContactPersonDeleteModel(MethodProperties methodProperties) {
        this.apiKey = API_KEY_COUNTERPARTY_USER;
        this.modelName = MODEL_NAME_COUNTACT_PERSON;
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

    public static ContactPersonDeleteModel getInstance(String ref) {
        return new ContactPersonDeleteModel(new MethodProperties(ref));
    }

}
