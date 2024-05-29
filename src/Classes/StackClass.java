
package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class provides methods to process and analyze symbols in a given file or
 * string. It includes methods to check the balance of symbols, find the
 * position of balanced symbols, and calculate the total balance of symbols.
 *
 *
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
     * Constructor with parameters.
     *
     * @param k The integer parameter.
     * @param filePath The path to the file to be processed.
     */
    public StackClass(int k, String filePath) {
        this.filePath = filePath;
        this.k = k;
    }

    /**
     * Default constructor.
     */
    public StackClass() {
    }

    /**
     * Calculates the balance of symbols in a given string.
     *
     * @param str The input string to be analyzed.
     * @return The count of balanced symbols in the string.
     */
    public int calculateSymbolBalance(String str) {
        int sum = 0;
        char[] array = str.toCharArray();
        for (char c : array) {
            if (c == '=' || c == '+' || c == '-' || c == '<' || c == '>' || c == '*' || c == '/' || c == '%') {
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
     * Finds the position where the balance of braces occurs in the given file.
     *
     * @param t The starting position to begin the search.
     * @param filePath The path to the file to be processed.
     * @return The position where the braces are balanced.
     */
    public int findBalancePosition(int t, String filePath) {
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
     * Calculates the total balance of symbols and braces in the given file.
     *
     * @param countSymbols A boolean indicating whether to count symbols.
     * @param startPos The starting position to begin the analysis.
     * @param filePath The path to the file to be processed.
     * @return The count of balanced symbols and braces in the file.
     */
    public int calculateTotalBalance(boolean countSymbols, int startPos, String filePath) {
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

        for (int p = startPos; p < lines.size() - 1; p++) {
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
                if (countSymbols) {
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
     * Provides a library of common programming symbols.
     *
     * @param index The index of the symbol to retrieve.
     * @return The symbol at the given index.
     */
    public String getSymbolFromLibrary(int index) {
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
        return symbols[index];
    }
}
