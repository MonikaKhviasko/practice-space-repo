package main.java.com.practice.array;

import java.util.Arrays;

public class Array {
    public static int removeElement(int[] nums, int val){
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static int[] findMaxMin(int[] myList){
        int[] newArr = new int[2];
        int min = myList[0];
        int max = myList[0];
        for (int i = 1; i < myList.length; i++) {
            if(myList[i] < min){
                min = myList[i];
            }
            if(myList[i] > max){
                max = myList[i];
            }
        }
        newArr[0] = max;
        newArr[1] = min;
        return newArr;
    }

    public static String findLongestString(String[] stringList){
        if(stringList.length == 0){
            return "";
        }
        int maxLength = 0;
        int index = 0;
        for (int i = 0; i < stringList.length; i++) {
            if(stringList[i].toCharArray().length > maxLength){
                maxLength = stringList[i].toCharArray().length;
                index = i;
            }else if(stringList[i].toCharArray().length == maxLength){
                break;
            }
        }
        return stringList[index];
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int writePointer = 1;

        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != nums[readPointer - 1]) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }

        return writePointer;
    }

    public static int maxProfit(int[] prices){
        if(prices.length == 0){
            return 0;
        }

        int minIndex = 0;
        int min = prices[minIndex];
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < min){
                minIndex = i;
                min = prices[i];
            }
        }

        int max = min;
        for (int i = minIndex + 1; i < prices.length; i++) {
            if(prices[i] > max){
                max = prices[i];
            }
        }
        return max-min;
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return; // No rotation needed
        }

        int n = nums.length;
        k = k % n; // Adjust k if it's greater than the array length

        reverse(nums, 0, n - 1); // Reverse the entire array
        reverse(nums, 0, k - 1); // Reverse the first k elements
        reverse(nums, k, n - 1); // Reverse the remaining elements
    }

    // Helper method to reverse a portion of the array from start index to end index
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static int maxSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
