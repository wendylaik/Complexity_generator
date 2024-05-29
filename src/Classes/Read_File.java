package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class provides methods to read and analyze a .java file. It detects
 * method names, calculates the complexity of methods and loops, and stores
 * information in a circular list.
 *
 *
 *
 * @author wendy Parra
 * @author francini Vindas
 */
public class Read_File {

    private String[] variables = new String[15];
    private String[] symbols = new String[17];
    private String[] factors = new String[5];
    int counter = 0;
    ArrayList<String> auxList = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> ps = new ArrayList<>();

    /**
     * Returns the list of lines read from the file.
     *
     * @return The list of lines.
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * Sets the list of lines read from the file.
     *
     * @param list The list of lines.
     */
    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    /**
     * Returns the current line counter.
     *
     * @return The line counter.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets the current line counter.
     *
     * @param counter The line counter.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Returns the array of detected variables.
     *
     * @return The array of variables.
     */
    public String[] getVariables() {
        return variables;
    }

    /**
     * Sets the array of detected variables.
     *
     * @param variables The array of variables.
     */
    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    /**
     * Reads a .java file from the specified path, detects method names and
     * their complexity, and stores the information in a circular list.
     *
     * @param path The path of the .java file to be analyzed.
     */
    public void read(String path) {
        CircularList lines = new CircularList();
        ArrayList<Integer> worstCase = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                counter++;
                String methodName = detectName(bfRead, counter);
                if (!methodName.isEmpty()) {
                    auxList.add(methodName);
                }
                list.add(bfRead.trim());
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        for (int i = 0; i < ps.size(); i++) {
            int methodStartLine = ps.get(i);
            int methodComplexity = complexity(path, methodStartLine);
            String methodContent = getMethodContent(methodStartLine);
            int methodOE = methods(methodStartLine);
            lines.insert(auxList.get(i), methodContent, methodOE, methodComplexity);
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Detected Methods:");
            lines.show();
            System.out.print("Select a method to view (or type 0 to exit): ");
            int selection = scanner.nextInt();
            if (selection == 0) {
                break;
            }
            Node selectedNode = lines.getNode(selection);
            if (selectedNode != null) {
                System.out.println("Method Content:\n" + selectedNode.content);
                System.out.println("Complexity: OE" + selectedNode.complexity
                        + " + " + selectedNode.termi_N + "N");
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Detects the name of a method in a given line of code.
     *
     * @param data The line of code to analyze.
     * @param line The line number of the code.
     * @return The name of the method if detected, otherwise an empty string.
     */
    public String detectName(String data, int line) {
        String[] split = data.split("\\s+");
        String method = "";
        String aux = "";

        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("(")) {
                boolean isMethod = false;
                for (int j = 0; j < 15; j++) {
                    if (i > 0 && (split[i - 1].equals(library(j)) || split[i
                            - 1].contains("List"))) {
                        isMethod = true;
                        break;
                    }
                }

                if (!isMethod && i > 1 && containsAccessModifier(split, i)) {
                    isMethod = true;
                }

                if (isMethod) {
                    method = split[i];
                    break;
                }
            }
        }

        if (method.contains("(")) {
            if (!method.contains("get") && !method.contains("set")) {
                for (int i = 0; i < method.length(); i++) {
                    if (method.charAt(i) != '(') {
                        aux = aux + method.charAt(i);
                    } else {
                        break;
                    }
                }
                ps.add(line);
            }
        } else {
            aux = "";
        }

        return aux;
    }

    /**
     * Checks if the given array of strings contains an access modifier at the
     * specified index.
     *
     * @param split The array of strings to check.
     * @param index The index to check for an access modifier.
     * @return True if an access modifier is found, otherwise false.
     */
    private boolean containsAccessModifier(String[] split, int index) {
        if (index >= 3 && (split[index - 2].equals("public") || split[index
                - 2].equals("private") || split[index - 2].equals("protected"))) {
            return true;
        }
        return false;
    }

    /**
     * Determines the algorithmic complexity of loops in the specified method.
     *
     * @param path The path of the .java file.
     * @param d The line number where the method starts.
     * @return The algorithmic complexity of the method.
     */
    public int complexity(String path, int d) {
        int limit = 0;
        int endLoop = 0;
        int comp = 0;
        StackClass p = new StackClass();
        for (int i = d; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (list.get(i).contains(library(j))) {
                    i = list.size() - 1;
                    limit = i;
                }
            }
            if (i != limit) {
                for (int h = 0; h < 4; h++) {
                    if (list.get(i).contains(loopLibrary(h)) && list.get(i).
                            contains("(")) {
                        comp = p.calculateTotalBalance(true, i, path);
                        endLoop = p.findBalancePosition(i, path) + 1;
                        i = endLoop;
                    }
                }
            }
        }
        return comp;
    }

    /**
     * Determines the overall complexity of the entire method, not just the
     * loops.
     *
     * @param k The line number where the method starts.
     * @return The overall complexity of the method.
     */
    public int methods(int k) {
        String chain = "";
        int limit = 0;
        int n = 0;
        int OE = 0;
        for (int i = k; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (list.get(i).contains(library(j)) && list.get(i)
                        .contains("(")) {
                    i = list.size() - 1;
                    limit = i;
                }
            }
            if (i != limit) {
                chain += list.get(i);
            }
        }
        StackClass p = new StackClass();
        OE = p.calculateSymbolBalance(chain);
        return OE;
    }

    /**
     * Retrieves the content of a method starting from the specified line
     * number.
     *
     * @param startLine The line number where the method starts.
     * @return The content of the method.
     */
    public String getMethodContent(int startLine) {
        StringBuilder content = new StringBuilder();
        boolean methodStarted = false;
        int braceCount = 0;

        for (int i = startLine - 1; i < list.size(); i++) {
            String line = list.get(i);
            if (line.contains("{")) {
                if (!methodStarted) {
                    methodStarted = true;
                }
                braceCount++;
            }
            if (methodStarted) {
                content.append(line).append("\n");
            }
            if (line.contains("}")) {
                braceCount--;
                if (braceCount == 0) {
                    break;
                }
            }
        }

        return content.toString();
    }

    /**
     * Provides a library of common Java keywords used to detect methods.
     *
     * @param i The index of the keyword in the library.
     * @return The keyword at the specified index.
     */
    public String library(int i) {
        this.variables[0] = "public";
        this.variables[1] = "private";
        this.variables[2] = "static";
        this.variables[3] = "void";
        this.variables[4] = "int";
        this.variables[5] = "short";
        this.variables[6] = "byte";
        this.variables[7] = "long";
        this.variables[8] = "char";
        this.variables[9] = "boolean";
        this.variables[10] = "String";
        this.variables[11] = "float";
        this.variables[12] = "double";
        this.variables[13] = "String[]";
        this.variables[14] = "class";

        return variables[i];
    }

    /**
     * Provides a library of common Java keywords used to detect loops.
     *
     * @param i The index of the keyword in the library.
     * @return The keyword at the specified index.
     */
    public String loopLibrary(int i) {
        this.factors[0] = "for";
        this.factors[1] = "while";
        this.factors[2] = "switch";
        this.factors[3] = "if";
        this.factors[4] = "do";

        return factors[i];
    }
}
