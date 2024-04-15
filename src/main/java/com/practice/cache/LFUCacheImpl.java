package main.java.com.practice.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheImpl {
    private Map<Integer, Integer> cache; // Key-Value store
    private Map<Integer, Integer> counts; // Key-Access Count store
    private Map<Integer, LinkedHashSet<Integer>> frequencyMap; // Access Count-Frequency List store
    private int capacity;
    private int minFrequency;

    public LFUCacheImpl(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        cache = new HashMap<>();
        counts = new HashMap<>();
        frequencyMap = new HashMap<>();
        frequencyMap.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        int count = counts.get(key);
        counts.put(key, count + 1);

        frequencyMap.get(count).remove(key);
        if (count == minFrequency && frequencyMap.get(count).isEmpty())
            minFrequency++;

        if (!frequencyMap.containsKey(count + 1))
            frequencyMap.put(count + 1, new LinkedHashSet<>());
        frequencyMap.get(count + 1).add(key);

        return cache.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0)
            return;

        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() >= capacity) {
            int evicted = frequencyMap.get(minFrequency).iterator().next();
            frequencyMap.get(minFrequency).remove(evicted);
            cache.remove(evicted);
            counts.remove(evicted);
        }

        cache.put(key, value);
        counts.put(key, 1);
        minFrequency = 1;
        frequencyMap.get(1).add(key);
    }
}
