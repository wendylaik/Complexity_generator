package Classes;

/**
 * Hosts all processes related to the node
 *
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
     * @param name
     * @param complexity
     * @param tn
     * @param content
     * @return this
     */
    
    public CircularList insert(String name, String content, int complexity
            , int tn) {
        Node newNode = new Node(name, content, complexity, tn);
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
        if (last == null) {
            return;
        }
        Node aux = last.next;
        int index = 1;
        do {
            System.out.println(index + ": " + aux.data + "  OE" + aux.complexity
                    + " + N" + aux.termi_N);
            aux = aux.next;
            index++;
        } while (aux != last.next);
    }

    /**
     * Method to get a node by index
     *
     * @param index
     * @return the node at the given index or null if not found
     */
    public Node getNode(int index) {
        if (last == null) {
            return null;
        }
        Node aux = last.next;
        int currentIndex = 1;
        do {
            if (currentIndex == index) {
                return aux;
            }
            aux = aux.next;
            currentIndex++;
        } while (aux != last.next);
        return null;
    }
}
