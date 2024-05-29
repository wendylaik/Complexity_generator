
package Main;

import Classes.Read_File;

/**
 * The main class to run the application. It reads a Java file and analyzes the
 * complexity of methods within it.
 *
 * @author wendy Parra
 * @author francini Vindas
 */
public class ComplexityMain {

    /**
     * The main method that serves as the entry point for the application. It
     * creates an instance of Read_File and calls its read method with the path
     * to the Java file to be analyzed.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Read_File re = new Read_File();
        re.read("Example.java");
    }
}
