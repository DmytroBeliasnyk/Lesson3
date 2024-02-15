package task3;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        File file = new File("D:\\java_study\\JavaPro\\Homework\\Lesson3HW\\src\\main\\java\\task3\\person.txt");
        try {
            serializationObject(person, file);
        } catch (FileNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(deserializationObject(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void serializationObject(Object obj, File file) throws FileNotFoundException, IllegalAccessException {
        JsonObject jo = new JsonObject();
        Field[] fields = Person.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Save.class)) {
                f.setAccessible(true);
                jo.addProperty(f.getName(), String.valueOf(f.get(obj)));
            }
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(new Gson().toJson(jo));
        }
    }

    public static Person deserializationObject(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        try (Scanner sc = new Scanner(file)) {
            return gson.fromJson(sc.nextLine(), Person.class);
        }
    }

}
