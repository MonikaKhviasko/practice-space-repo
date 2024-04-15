package main.java.com.practice.linkedList;

import java.util.HashSet;
import java.util.Stack;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    class Node {
        private int value;
        private Node next;

        Node(int value){
            this.value = value;
        }

        Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    public LinkedList(int value){
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void append(int value){
        Node newNode = new Node(value);
        if(length == 0){
            this.head = newNode;
            this.tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public void preAppend(int value){
        Node newNode = new Node(value);
        if(length == 0){
            this.head = newNode;
            this.tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeLast(){
        if(length == 0){
            return null;
        }else{
            Node tmp = head;
            Node pre = head;
            while(tmp.next != null){
                pre = tmp;
                tmp = tmp.next;
            }
            tail = pre;
            tail.next = null;
            length--;
            if(length == 0){
                head = null;
                tail = null;
            }
            return tmp;
        }
    }

    public Node removeFirst(){
        if(length == 0){
            return null;
        }else{
            Node tmp = head;
            head = head.next;
            tmp.next = null;
            length--;
            if(length == 0){
                tail = null;
            }
            return tmp;
        }
    }

    public Node get(int index){
        if(index<0 || index>=length){
            return null;
        }
        Node tmp = head;
        for(int i =0; i< index; i++){
            tmp = tmp.next;
        }
        return tmp;
    }

    public boolean set(int value, int index){
        Node tmp = get(index);
        if(tmp != null){
            tmp.value = value;
            return true;
        }else {
            return false;
        }
    }

    public boolean insert(int value, int index){
        if(index<0 || index>= length){
            return false;
        }
        if(index == 0){
            preAppend(value);
            return true;
        }
        if(index == length){
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node tmp = get(index-1);
        newNode.next = tmp.next;
        tmp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index){
        if(index<0 || index> length){
            return null;
        }
        if(index == 0){
            return removeFirst();
        }
        if(index == length){
            return removeLast();
        }
        Node prev = get(index - 1);
        Node tmp = prev.next;
        prev.next = tmp.next;
        tmp.next = null;
        length--;
        return tmp;
    }

    public void print(){
        Node tmp = head;
        while(tmp!=null){
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    public Node findMiddleNode(){
        if(head == null){
            return null;
        }

        Node slow = head;
        Node fast = head;

        while(fast!= null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean hasLoop(){
        if(head == null){
            return false;
        }

        Node slow = head;
        Node fast = head;

        while(fast!= null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    public Node findFthFromEnd(int k){
        if(head == null){
            return null;
        }

        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }

        while (fast!= null && fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public void removeDuplicates(){

        if(head == null){
            return;
        }

        Node prev = null;
        Node current = head;
        HashSet<Integer> seen = new HashSet<>();

        while(current!=null){
            if(!seen.contains(current.value)){
                seen.add(current.value);
                prev = current;
            }else{
                prev.next = current.next;
            }
            current = current.next;
        }

    }

    public int binaryToDecimal(){
        int num = 0;
        Node current = head;
        while (current != null) {
            num = num * 2 + current.value;
            current = current.next;
        }
        return num;
    }

    public void partitionList(int x){
        if (head == null) return;

        //new linked lists
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        //pointers for new linked lists
        Node tmp1 = dummy1;
        Node tmp2 = dummy2;

        Node current = head;

        while (current != null) {
            if (current.value < x) {
                tmp1.next = current;
                tmp1 = current;
            } else {
                tmp2.next = current;
                tmp2 = current;
            }
            current = current.next;
        }

        tmp2.next = null;
        tmp1.next = dummy2.next;

        head = dummy1.next;
    }

    public void reverse(){
        Node tmp = head;
        head = tail;
        tail = tmp;

        Node after = tmp.next;
        Node before = null;

        for(int i = 0; i<length; i++){
            after = tmp.next;

            // flip to before! flip the arrow <-
            tmp.next = before;
            //for next iteration
            before = tmp;
            tmp = after;
        }
    }

    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null) return;

        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;

        // Move previousNode to the node before the starting index
        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        Node currentNode = previousNode.next;
        Node after = currentNode.next;
        Node before = null;

        // Reverse the sublist between startIndex and endIndex
        for (int i = startIndex; i < endIndex; i++) {
            after = currentNode.next;

            // Reverse arrow direction
            currentNode.next = before;

            // For next iteration
            before = currentNode;
            currentNode = after;
        }

        // Connect the reversed sublist back to the main list
        previousNode.next.next = currentNode;
        previousNode.next = before;

        head = dummyNode.next;
    }

    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true; // Empty or single-node list is a palindrome
        }

        // Step 1: Traverse the list and store node values in a stack
        Stack<Integer> stack = new Stack<>();
        Node current = head;
        while (current != null) {
            stack.push(current.value);
            current = current.next;
        }

        // Step 2: Compare stored values with original list to check palindrome
        current = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != current.value) {
                return false; // If values don't match, it's not a palindrome
            }
            current = current.next;
        }

        return true; // If all values match, it's a palindrome
    }

}
