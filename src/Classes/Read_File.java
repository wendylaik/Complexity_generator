
package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wendy Parra
 * @author francini Vindas
 */
public class Read_File {
   private String[] variables = new String[15];
    private String[] symbols = new String[17];
    private String[] factors = new String[4];
    int counter = 0;
    ArrayList<String> auxList = new ArrayList();
    ArrayList<String> list = new ArrayList();
    ArrayList<Integer> ps = new ArrayList();

    /**
     *
     * @return list
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    /**
     *
     * @return counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     *
     * @return variables
     */
    public String[] getVariables() {
        return variables;
    }

    /**
     *
     * @param variables
     */
    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    /**
     * Receives as a parameter the path of the .java file to be analyzed.
     * Additionally, methods are implemented to capture the name, complexity,
     * and store it in the circular list.
     *
     * @param path
     */
    public void read(String path) {
        CircularList lines = new CircularList();
        ArrayList<Integer> worstCase = new ArrayList();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String bfRead;
            String temp = "";
            while ((bfRead = bf.readLine()) != null) {
                counter++;
                temp = temp + bfRead;
                if (!detectName(temp, counter).equals("")) {
                    auxList.add(detectName(temp, counter));
                }
                list.add(temp.trim());
                temp = "";
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        
        for (int i = 0; i < ps.size(); i += 2) {
            worstCase.add(methods(ps.get(i)) - complexity(path, ps.get(i)));
        }
        for (int i = 0; i < auxList.size(); i++) {
            String methodContent = getMethodContent(ps.get(i));
            lines.insert(auxList.get(i), methodContent, worstCase.get(i), complexity(path, ps.get(i)));
        }
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Detected Methods:");
            lines.show();
            System.out.print("Select a method to view (or type 0 to exit): ");
            int selection = scanner.nextInt();
            if (selection == 0) break;
            Node selectedNode = lines.getNode(selection);
            if (selectedNode != null) {
                System.out.println("Method Content:\n" + selectedNode.content);
                System.out.println("Complexity: OE" + selectedNode.complexity + " + N" + selectedNode.termi_N);
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     *
     * Receives as a parameter the lines of the file and also the line number
     * where it is located, and returns the names of the methods in the file.
     *
     * @param data
     * @param line
     * @return aux
     */
    public String detectName(String data, int line) {
        String[] split = data.split(" ");
        String method = "";
        String aux = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("(")) {
                break;
            }
            for (int j = 0; j < 15; j++) {
                if (split[i].equals(library(j))) {
                    method = split[i + 1];
                    break;
                }
            }
        }
        if (method.contains("(")) {
            if (method.contains("get") || method.contains("set")) {
                aux = "";
            } else {
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
     * This method specializes in determining the algorithmic complexity of
     * loops in order to determine the algorithmic cost.
     *
     * @param path
     * @param d
     * @return comp
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
                    if (list.get(i).contains(loopLibrary(h)) && list.get(i).contains("(")) {
                        comp = p.totalBalance(true, i, path);
                        endLoop = p.balancePosition(i, path) + 1;
                        i = endLoop;
                    }
                }
            }
        }
        return comp;
    }

    /**
     *
     * Unlike the complexity method, this method determines the complexity of
     * the entire method, not just the loops.
     *
     * @param k
     * @return OE
     */
    public int methods(int k) {
        String chain = "";
        int limit = 0;
        int n = 0;
        int OE = 0;
        for (int i = k; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (list.get(i).contains(library(j)) && list.get(i).contains("(")) {
                    i = list.size() - 1;
                    limit = i;
                }
            }
            if (i != limit) {
                chain += list.get(i);
            }
        }
        StackClass p = new StackClass();
        OE = p.balance(chain);
        return OE;
    }
    
    public String getMethodContent(int startLine) {
        StringBuilder content = new StringBuilder();
        for (int i = startLine; i < list.size(); i++) {
            content.append(list.get(i)).append("\n");
            if (list.get(i).contains("}")) {
                break;
            }
        }
        return content.toString();
    }

    /**
     *
     * The library contains all the parameters to detect a method.
     *
     * @param i
     * @return variables
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
     * This library detects the loops that are in a line to analyze them.
     *
     * @param i
     * @return factors
     */
    public String loopLibrary(int i) {
        this.factors[0] = "for";
        this.factors[1] = "while";
        this.factors[2] = "switch";
        this.factors[3] = "if";

        return factors[i];
    } 
}
