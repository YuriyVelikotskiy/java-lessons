package dataStructures;

public class MyHashSet {
    private static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node[] nodes;
    private int size;
    private static final int SIZE = 16;
    private static final float LOAD_FACTOR = 0.7f;

    public MyHashSet() {
        nodes = new Node[SIZE];
        size = 0;
    }

    private int getIndex(Object value) {
        return Math.abs(value.hashCode() % (nodes.length-1));
    }

    public void add(Object value) {
        int index = getIndex(value);
        Node head = nodes[index];
        Node curr = head;

        while (curr != null) {
            if (curr.value.equals(value))
                return;
            curr = curr.next;
        }
        Node newNode = new Node(value);
        newNode.next = head;
        nodes[index] = newNode;
        size++;
        if (size > nodes.length * LOAD_FACTOR) {
            rehash();
        }
    }

    private void rehash() {
        Node[] oldNodes = nodes;
        nodes = new Node[nodes.length*2];
        size = 0;
        for (Node head : oldNodes){
            while (head != null){
                add(head.value);
                head = head.next;
            }
        }
    }

    public void remove(Object value) {
        int index = getIndex(value);
        Node head = nodes[index];
        Node pre = null;
        while (head != null) {
            if (head.value.equals(value)) {
                if (pre == null) {
                    nodes[index] = head.next;
                } else {
                    pre.next = head.next;
                }
                size -= 1;
                return;
            }
            pre = head;
            head = head.next;
        }
    }
}
