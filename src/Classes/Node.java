
package Classes;

/**
 * This class formulates the node to store the required information.
 *@author wendy Parra
 * @author francini Vindas
 */
public class Node {

    public String data;
    public int complexity;
    public int termi_N;
    public Node next;

    /**
     *
     * @param elem
     * @param comp
     * @param term
     */
    public Node(String elem, int comp, int term) {
        termi_N = term;
        complexity = comp;
        data = elem;
        next = this;
    }

    /**
     * 
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * 
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 
     * @return complexity
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * 
     * @param complexity 
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    /**
     * 
     * @return termi_N
     */
    public int getTermi_N() {
        return termi_N;
    }

    /**
     * 
     * @param termi_N 
     */
    public void setTermi_N(int termi_N) {
        this.termi_N = termi_N;
    }

    /**
     * 
     * @return next
     */
    public Node getNext() {
        return next;
    }

    /**
     * 
     * @param next 
     */
    public void setNext(Node next) {
        this.next = next;
    }

}
