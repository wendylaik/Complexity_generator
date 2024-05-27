package Classes;

/**Hosts all processes related to the node
 * @author wendy Parra
 * @author francini Vindas
 */
public class CircularList {

    Node last;

    /**
     * Constructor for the Node last
     */
    public CircularList() {
        last = null;
    }

    /**
     *
     * @return last
     */
    public boolean isEmpty() {
        return last == null;
    }

    /**
     * Method to insert nodes
     *
     * @param element
     * @param complexity
     * @param tn
     * @return this
     */
    public CircularList insert(String element, int complexity, int tn) {
        Node newNode = new Node(element, complexity, tn);
        if (last != null) {
            newNode.next = last.next;
            last.next = newNode;
        }
        last = newNode;
        return this;
    }

    /**
     * Method to print the circular list
     */
    public void show() {
        Node aux;
        aux = last.next;
        do {
            System.out.println(aux.data + "  OE" + aux.complexity + 
                    " + N" + aux.termi_N);
            aux = aux.next;
        } while (aux != last.next);
    }
}
