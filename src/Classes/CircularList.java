package Classes;

/**
 * Hosts all processes related to the circular linked list. Provides methods to
 * insert nodes, check if the list is empty, print the list, and retrieve nodes
 * by index.
 *
 * @see Node
 *
 * @author wendy Parra
 * @author francini Vindas
 */
public class CircularList {

    /**
     * The last node in the circular linked list.
     */
    Node last;

    /**
     * Constructor that initializes an empty circular linked list.
     */
    public CircularList() {
        last = null;
    }

    /**
     * Checks if the circular linked list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return last == null;
    }

    /**
     * Inserts a new node into the circular linked list.
     *
     * @param name The data for the new node.
     * @param content The content for the new node.
     * @param complexity The complexity associated with the new node.
     * @param tn The termination number associated with the new node.
     * @return The current instance of {@code CircularList} to allow method
     * chaining.
     */
    public CircularList insert(String name, String content, int complexity, int tn) {
        Node newNode = new Node(name, content, complexity, tn);
        if (last != null) {
            newNode.next = last.next;
            last.next = newNode;
        }
        last = newNode;
        return this;
    }

    /**
     * Prints the circular linked list to the standard output. Each node's
     * index, data, complexity, and termination number are printed.
     */
    public void show() {
        if (last == null) {
            return;
        }
        Node aux = last.next;
        int index = 1;
        do {
            System.out.println(index + ": " + aux.data + "  OE" + aux.complexity + " + N" + aux.termi_N);
            aux = aux.next;
            index++;
        } while (aux != last.next);
    }

    /**
     * Retrieves the node at the specified index in the circular linked list.
     *
     * @param index The index of the node to retrieve.
     * @return The node at the given index, or {@code null} if the index is out
     * of bounds.
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
