package main.java.com.practice.doubleLinkedList;

import main.java.com.practice.linkedList.LinkedList;

public class DoubleLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        private int value;
        private Node next;

        private Node prev;

        Node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }

    public DoubleLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) {
            return null;
        }
        Node tmp = tail;
        if (length == 1) {
            head = null;
            tail = null;
            return tmp;
        }
        tail = tmp.prev;
        tail.next = null;
        tmp.prev = null;
        return tmp;
    }

    public Node removeFirst() {
        if (length == 0) {
            return null;
        }
        Node tmp = head;
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return tmp;
        }
        head = tmp.next;
        head.prev = null;
        tmp.next = null;
        length--;
        return tmp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }

        Node tmp = head;
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        } else {
            tmp = tail;
            for (int i = length - 1; i > index; i--) {
                tmp = tmp.prev;
            }
        }
        return tmp;
    }

    public boolean set(int value, int index){
        Node tmp = get(index);
        if(tmp!=null) {
            tmp.value = value;
            return true;
        }
        return false;
    }

    public void insert(int value, int index){
        if(index< 0 || index>length){
            return;
        }
        if(index == length){
            append(value);
        }
        if(index == 0){
            prepend(value);
        }
        Node newNode = new Node(value);
        //1st option
        Node before = get(index-1);
        Node after = before.next;
        newNode.prev = before;
        newNode.next = after;
        before.next = newNode;
        after.prev = newNode;

        //2nd option
//        Node before = get(index-1);
//        before.next.prev = newNode;
//        before.next = newNode;
//        newNode.next =before.next;
//        newNode.prev = before;
        length++;
    }

    public Node remove(int index){
        if(index< 0 || index>=length){
            return null;
        }
        if(index == length-1){
            removeLast();
        }
        if(index == 0){
            removeFirst();
        }
        Node tmp = get(index);
        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
        tmp.prev = null;
        tmp.next = null;
        length--;
        return tmp;
    }

    public void swapFirstLast(){
        if(length< 2){
            return;
        }
        int tmp = head.value;
        head.value = tail.value;
        tail.value = tmp;
    }

    public void reverse(){
        Node current = head;
        Node tmp = null;

        while(current!=null){
            tmp = current.prev;
            current.prev = current.next;
            current.next = tmp;
            current = current.prev;
        }

        tmp = head;
        head = tail;
        tail = tmp;
    }

    public boolean isPalindrome(){
        if(length<=1){
            return true;
        }

        Node forwardNode = head;
        Node backwardNode = tail;

        for (int i = 0; i < length/2; i++) {
            if(forwardNode.value!=backwardNode.value){
                return false;
            }
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
    }

     public void swapPairs() {
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;

        while (head != null && head.next != null){
            Node firstNode = head;
            Node secondNode = head.next;

            previousNode.next = secondNode;

            firstNode.next = secondNode.next;
            firstNode.prev = secondNode;

            secondNode.next = firstNode;
            secondNode.prev = previousNode;

            if(firstNode.next!= null){
                firstNode.next.prev = firstNode;
            }

            head = firstNode.next;
            previousNode = firstNode;
        }

        head = dummyNode.next;
        if(head!=null){
            head.prev = null;
        }
    }
}
