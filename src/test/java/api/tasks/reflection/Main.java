package api.tasks.reflection;

class Other{}

public class Main {

    class MyClass{}

    public static void main(String[] args) {
        final Integer i = 777;

        Class c1 = Integer.class;
        Class c2 = i.getClass();
        Class c3 = MyClass.class;
        Class<?> c4 = Other.class;
        Class<?> c5 = Main.class;

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);

    }

}
