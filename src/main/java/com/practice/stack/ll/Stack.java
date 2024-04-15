package main.java.com.practice.stack.ll;

public class Stack {
    private Node top;
    private int height;

    class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public Stack(int value) {
        Node node = new Node(value);
        top = node;
        height++;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        height++;
    }

    public Node pop() {
        if (height == 0) {
            return null;
        }
        Node tmp = top;
        top = top.next;
        tmp.next = null;
        height--;
        return tmp;
    }
}
