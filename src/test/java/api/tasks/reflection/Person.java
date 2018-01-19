package api.tasks.reflection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private String apiKey;
    private String modelName;
    private String calledMethod;

    public Person(String apiKey, String modelName, String calledMethod) {
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.calledMethod = calledMethod;
    }

}