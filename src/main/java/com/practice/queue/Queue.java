package main.java.com.practice.queue;

public class Queue {
    private Node head;
    private Node tail;
    private int lenght;

    class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public Queue(int value) {
        Node node = new Node(value);
        head = node;
        tail = node;
        lenght++;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (lenght == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        lenght++;
    }

    public Node dequeue() {
        if (lenght == 0) {
            return null;
        }
        Node tmp = head;
        if (lenght == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tmp.next = null;
        }
        lenght--;
        return tmp;
    }
}
