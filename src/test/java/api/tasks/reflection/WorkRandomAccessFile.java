package api.tasks.reflection;

import java.io.RandomAccessFile;
import java.lang.reflect.Modifier;

public class WorkRandomAccessFile {

    public static void main(String[] args) {

        final Class<?> cls = RandomAccessFile.class;

        int countModifires = cls.getModifiers();
        System.out.println(countModifires);

        if (Modifier.isPrivate(countModifires)) {

        }

    }

}
