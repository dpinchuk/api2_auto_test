package api.tasks.reflection;

import java.lang.reflect.Field;

public class B {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Class<?> cls = A.class;
        A a = new A();
        Field field = cls.getDeclaredField("a");
        field.setAccessible(true);

        System.out.println("Private value is: " + field.get(a));

        field.set(a, 100);

        System.out.println("New private value is: " + field.get(a));
    }
}
