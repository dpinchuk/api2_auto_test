package api.tasks.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Person person = new Person("apiKey", "modelName", "calledMethod");

        String className = "api.reflection.Person";

        Class<?> clazz = Class.forName(className);

        Constructor<?> ctor = clazz.getConstructor(String.class, String.class, String.class);
        Object object = ctor.newInstance(new Object[] {"apiKey2", "modelName2", "calledMethod2"});

        Person p = (Person) object;


        System.out.println(p.equals(object));


    }

}