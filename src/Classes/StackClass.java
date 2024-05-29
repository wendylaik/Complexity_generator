
package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author wendy Parra
 * @author francini Vindas
 */
public class StackClass {
   
    private int k = 0;
    private String filePath;
    private String[] symbols = new String[17];
    ArrayList<String> lines = new ArrayList<>();
    private Stack<Character> data = new Stack<>();

    /**
     * Constructor with parameters
     * @param k
     * @param filePath 
     */
    public StackClass(int k, String filePath) {
        this.filePath = filePath;
        this.k = k;
    }

    /**
     * Default constructor
     */
    public StackClass() {
    }

    /**
     * Method to calculate the balance of symbols in a string
     * @param str
     * @return sum
     */
    public int balance(String str) {
        int sum = 0;
        char[] array = str.toCharArray();
        for (char c : array) {
            if (c == '=' || c == '+' || c == '-' || c == '<' || c == '>' ||
                    c == '*' || c == '/' || c == '%') {
                data.push(c);
                sum++;
            }
        }        
   
    boolean insideComment = false;
    boolean insideString = false;
    for (int i = 0; i < array.length; i++) {
        char currentChar = array[i];
        if (currentChar == '/') {
            if (i + 1 < array.length && array[i + 1] == '/') {
                insideComment = true;
            } else if (i + 1 < array.length && array[i + 1] == '*') {
                insideComment = true;
                i++;
            }
        } else if (currentChar == '\n') {
            insideComment = false;
        } else if (currentChar == '*' && i + 1 < array.length && array[i + 1] == '/') {
            insideComment = false;
            i++;
        } else if (currentChar == '"') {
            insideString = !insideString;
        } else if (!insideComment && !insideString) {
            if (currentChar == '=' || currentChar == '+' || currentChar == '-' || currentChar == '<' || currentChar == '>' || currentChar == '*' || currentChar == '/' || currentChar == '%') {
                data.push(currentChar);
                sum++;
            } else if (currentChar == 'r' && i + 6 < array.length && str.substring(i, i + 6).equals("return") && !Character.isLetter(array[i + 6])) {
                sum++;
            }
        }
    }
    return sum;


    }

    /**
     * Method to find the position balance of symbols
     * @param t
     * @param filePath
     * @return i
     */
    public int balancePosition(int t, String filePath) {
        int openBraces = 0;
        int closeBraces = 0;
        int i = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String bfRead;
            String temp = "";
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
                lines.add(temp.trim());
                temp = "";
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        for (int p = t; p < lines.size() - 1; p++) {
            char[] array = lines.get(p).toCharArray();
            for (char c : array) {
                if (c == '{') {
                    data.push('{');
                    openBraces++;
                } else if (c == '}') {
                    if (!data.empty()) {
                        data.pop();
                        closeBraces++;
                    }
                }
            }
            if (openBraces != 0 || closeBraces != 0) {
                if (openBraces <= closeBraces) {
                    i = p;
                    break;
                }
            }
        }
        return i;
    }

    /**
     * Method to calculate the total balance of symbols
     * @param d
     * @param t
     * @param filePath
     * @return i
     */
    public int totalBalance(boolean d, int t, String filePath) {
        int openBraces = 0;
        int closeBraces = 0;
        int sum = 0;
        int i = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String bfRead;
            String temp = "";
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
                lines.add(temp.trim());
                temp = "";
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        for (int p = t; p < lines.size() - 1; p++) {
            char[] array = lines.get(p).toCharArray();
            for (char c : array) {
                if (c == '{') {
                    data.push('{');
                    openBraces++;
                } else if (c == '}') {
                    if (!data.empty()) {
                        data.pop();
                        closeBraces++;
                    }
                }
                if (d) {
                    if (c == '=' || c == '+' || c == '-' || c == '<' || c == '>' || c == '*' || c == '/' || c == '%') {
                        data.push(c);
                        sum++;
                        i = sum;
                    }
                }
            }
            if (openBraces != 0 || closeBraces != 0) {
                if (openBraces <= closeBraces) {
                    break;
                }
            }
        }
        return i;
    }

    /**
     * General library to detect symbols in a method
     * @param i
     * @return symbols
     */
    public String symbolLibrary(int i) {
        this.symbols[0] = "=";
        this.symbols[1] = "==";
        this.symbols[2] = "!=";
        this.symbols[3] = "+";
        this.symbols[4] = "++";
        this.symbols[5] = "-";
        this.symbols[6] = "--";
        this.symbols[7] = "<";
        this.symbols[8] = ">";
        this.symbols[9] = "<=";
        this.symbols[10] = ">=";
        this.symbols[11] = ".length";
        this.symbols[12] = "*";
        this.symbols[13] = "/";
        this.symbols[14] = "%";
        this.symbols[15] = "-=";
        this.symbols[16] = "+=";
        return symbols[i];
    } 
}
