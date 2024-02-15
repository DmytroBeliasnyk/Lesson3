package task2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Saver {
}

@SaveTo(path = "D:\\java_study\\JavaPro\\Homework\\Lesson3HW\\src\\main\\java\\task2\\text.txt")
class Container {
    private String text;

    public Container(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Saver
    public void saveTextToFile(String text, String path) {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SaveTo st = Container.class.getAnnotation(SaveTo.class);
        Method[] methods = Container.class.getDeclaredMethods();
        Container container = new Container("Annotation");
        for (Method method : methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(container, container.getText(), st.path());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
