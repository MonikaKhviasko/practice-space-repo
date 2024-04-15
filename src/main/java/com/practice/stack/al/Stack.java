package main.java.com.practice.stack.al;

import java.util.ArrayList;

public class Stack<T> {

    private ArrayList<T> stackList = new ArrayList<>();

    public ArrayList<T> getStackList() {
        return stackList;
    }

    public void printStack() {
        for (int i = stackList.size() - 1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }

    public void push(T value) {
        stackList.add(value);
    }

    public T pop(){
        if (stackList.size() == 0){
            return null;
        }
        T tmp = stackList.get(stackList.size()-1);
        stackList.remove(tmp);
        return tmp;
    }

    public int size() {
        return stackList.size();
    }
}
