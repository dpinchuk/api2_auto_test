package api.models.common;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * simple model that represents JSON-objects for: Counterparty Model
 *
 * uses for next methods:
 *
 *
 *
 * Метод "getCounterpartyAddresses", работает в модели "Counterparty",
 * этот метод загружает список контрагентов отправителей/получателей.
 *
 * {
 *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
 *   "modelName": "Counterparty",
 *   "calledMethod": "getCounterpartyAddresses",
 *   "methodProperties":
 *   {
 *     "Ref": "63aa8726-f146-11e7-becf-005056881c6b",
 *     "CounterpartyProperty": "Sender",
 *     "Page": ""
 *   }
 * }
 *
 * {
 *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
 *   "modelName": "Counterparty",
 *   "calledMethod": "getCounterpartyAddresses",
 *   "methodProperties":
 *   {
 *     "Ref": "63aa8726-f146-11e7-becf-005056881c6b",
 *     "CounterpartyProperty": "Recipient",
 *     "Page": ""
 *   }
 * }
 *
 *
 *
 * Метод "getCounterpartyOptions", работает в модели "Counterparty",
 * этот метод используется для получения параметров контрагента отправителя
 * в разрезе возможности заказа дополнительных услуг
 *
 * {
 *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
 *   "modelName": "Counterparty",
 *   "calledMethod": "getCounterpartyOptions",
 *   "methodProperties":
 *   {
 *     "Ref": "63aa8726-f146-11e7-becf-005056881c6b"
 *     "CounterpartyProperty": "",
 *     "Page": ""
 *   }
 * }
 *
 *
 *
 * Метод "getCounterpartyContactPerson", работает в модели "Counterparty",
 * этот метод загружает список контактных лиц контрагентов
 *
 * {
 *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
 *   "modelName": "Counterparty",
 *   "calledMethod": "getCounterpartyContactPersons",
 *   "methodProperties":
 *   {
 *     "Ref": "63aa8726-f146-11e7-becf-005056881c6b",
 *     "CounterpartyProperty": "",
 *     "Page": "1"
 *   }
 * }
 *
 *
 *
 *  Метод "getCounterparties", работает в модели "Counterparty",
 *  этот метод загружает список контрагентов отправителей, получателей или третьих лиц.
 *
 * {
 *   "apiKey": "044f8693ad1f11a09ccc6142994b0761",
 *   "modelName": "Counterparty",
 *   "calledMethod": "getCounterparties",
 *   "methodProperties":
 *   {
 *     "Ref": "",
 *     "CounterpartyProperty": "Sender",
 *     "Page": "1"
 *   }
 * }
 *
 *
 *
 * (@link resources.api)
 */

@Getter
@Setter
public final class CommonGetCounterpartyModel {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    private CommonGetCounterpartyModel(String apiKey, String modelName, String calledMethod, MethodProperties methodProperties) {
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.calledMethod = calledMethod;
        this.methodProperties = methodProperties;
    }

    private static final class MethodProperties {

        private String ref;
        private String counterpartyProperty;
        private String page;

        private MethodProperties(String ref, String counterpartyProperty, String page) {
            this.ref = ref;
            this.counterpartyProperty = counterpartyProperty;
            this.page = page;
        }

        @JsonProperty("Ref")
        public String getRef() {
            return ref;
        }

        @JsonProperty("CounterpartyProperty")
        public String getCounterpartyProperty() {
            return counterpartyProperty;
        }

        @JsonProperty("Page")
        public String getPage() {
            return page;
        }
    }

    public static CommonGetCounterpartyModel getInstance(
            String apiKey,
            String modelName,
            String calledMethod,
            String ref,
            String counterpartyProperty,
            String page) {

        return new CommonGetCounterpartyModel(
                apiKey,
                modelName,
                calledMethod,
                new MethodProperties(
                        ref,
                        counterpartyProperty,
                        page
                ));
    }

}