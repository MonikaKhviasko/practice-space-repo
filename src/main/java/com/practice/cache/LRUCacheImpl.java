package main.java.com.practice.cache;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUCacheImpl {
    HashMap<Integer, String> data = new HashMap<Integer, String>();
    LinkedList<Integer> order = new LinkedList<Integer>();
    int capacity;

    LRUCacheImpl(int capacity){
        this.capacity = capacity;
    }

    void put(int key, String value){
        if(order.size() >= capacity){
            int keyRemoved = order.removeLast();
            data.remove(keyRemoved);
        }
        order.addFirst(key);
        data.put(key, value);
    }

    String get(int key){
        String res = data.get(key);
        if(res!=null){
            order.remove(Integer.valueOf(key));
            order.addFirst(key);
        }
        return res;
    }
}
