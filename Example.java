public class Example {

    public static void main(String[] args) {
        // Llamadas a los métodos de ejemplo
        method1();
        method2();
        int result = method3(5, 3);
        System.out.println("The result is: " + result);
        String message = method4("Hello", "world!");
        System.out.println(message);
        double squareRoot = method5(16);
        System.out.println("The square root of 16 is: " + squareRoot);
    }

    public static void method1() {
        System.out.println("This is method 1.");
    }

    public static void method2() {
        System.out.println("This is method 2.");
    }

    public static int method3(int a, int b) {
        return a + b;
    }

    public static String method4(String str1, String str2) {
        return str1 + " " + str2;
    }

    public static double method5(double number) {
        return Math.sqrt(number);
    }
}
