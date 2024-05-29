package Classes;

/**
 * This class represents a node to store the required information. It includes
 * data fields for various attributes and methods to get and set these
 * attributes.
 *
 * @see java.lang.String
 * @see java.lang.Integer
 *
 * @autor wendy Parra
 * @autor francini Vindas
 */
public class Node {

    /**
     * The data stored in this node.
     */
    public String data;

    /**
     * The complexity associated with this node.
     */
    public int complexity;

    /**
     * The termination number associated with this node.
     */
    public int termi_N;

    /**
     * The reference to the next node.
     */
    public Node next;

    /**
     * The content stored in this node.
     */
    public String content;

    /**
     * Constructs a new node with the specified data, content, complexity, and
     * termination number.
     *
     * @param elem The data to be stored in this node.
     * @param cont The content to be stored in this node.
     * @param comp The complexity to be associated with this node.
     * @param term The termination number to be associated with this node.
     */
    public Node(String elem, String cont, int comp, int term) {
        this.data = elem;
        this.complexity = comp;
        this.termi_N = term;
        this.next = this;
        this.content = cont;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return The data stored in this node.
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data to be stored in this node.
     *
     * @param data The data to be stored in this node.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the complexity associated with this node.
     *
     * @return The complexity associated with this node.
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * Sets the complexity to be associated with this node.
     *
     * @param complexity The complexity to be associated with this node.
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    /**
     * Returns the termination number associated with this node.
     *
     * @return The termination number associated with this node.
     */
    public int getTermi_N() {
        return termi_N;
    }

    /**
     * Sets the termination number to be associated with this node.
     *
     * @param termi_N The termination number to be associated with this node.
     */
    public void setTermi_N(int termi_N) {
        this.termi_N = termi_N;
    }

    /**
     * Returns the next node.
     *
     * @return The next node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param next The next node.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Returns the content stored in this node.
     *
     * @return The content stored in this node.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content to be stored in this node.
     *
     * @param content The content to be stored in this node.
     */
    public void setContent(String content) {
        this.content = content;
    }

}
