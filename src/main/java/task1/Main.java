package task1;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    int param1();

    int param2();

}

class Test {
    @TestAnnotation(param1 = 7, param2 = 3)
    public int calculateSum(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

public class Main {
    public static void main(String[] args) {
        Method[] methods = Test.class.getDeclaredMethods();
        int res = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation ta = method.getAnnotation(TestAnnotation.class);
                try {
                    res = (int) method.invoke(new Test(), ta.param1(), ta.param2());
                } catch (Exception e) {
                    e.printStackTrace();
                    res = -1;
                }
            }
        }
        System.out.println(res);
    }

}
