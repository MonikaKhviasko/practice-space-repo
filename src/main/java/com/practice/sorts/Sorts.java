package main.java.com.practice.sorts;

import java.util.Arrays;

public class Sorts {

    /////////////////////////////////////////////
    // O(n^2) time complexity
    // O(1) space complexity
    public static void bubbleSort(int[] array) {
        for (int i = array.length; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }


    // minimum index
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
    }


    // always start with the second item and compare with prev
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            while (j > -1 && tmp < array[j]) {
                array[j + 1] = array[j];
                array[j] = tmp;
                j--;
            }
        }
    }

    /////////////////////////////////////////////
    // Fork Join - recursive (merge - helper function)
    // O(n log(n)) time complexity
    // O(n) space complexity
    private int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int index = 0;
        int i = 0;
        int j = 0;

        //merge 2 arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[index] = arr1[1];
                index++;
                i++;
            } else {
                result[index] = arr2[j];
                index++;
                j++;
            }
        }

        //leftover1
        while (i < arr1.length) {
            result[index] = arr1[i];
            index++;
            i++;
        }

        //leftover2
        while (j < arr2.length) {
            result[index] = arr2[j];
            index++;
            j++;
        }
        return result;
    }

    public int[] mergeSort(int[] arr) {
        if(arr.length == 1){
            return arr;
        }

        int middle = arr.length / 2;

        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, middle));
        int[] right = mergeSort(Arrays.copyOfRange(arr, middle, arr.length));

        return merge(left, right);
    }

    /////////////////////////////////////////////
    // Pivot - recursive (pivot - helper function + swap helper function)
    // O(n^2) time complexity
    // O(log(n)) space complexity

    public void quickSort(int[] arr, int left, int right){
        if(left < right) {
            int pivotIndex = pivot(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private int pivot(int[] arr, int pivotIndex, int endIndex){
        int swapIndex = pivotIndex;

        for (int i = pivotIndex+1; i <= endIndex; i++) {
            if(arr[i] < arr[pivotIndex]){
                swapIndex+=1;
                swap(arr, i, swapIndex);
            }
        }
        swap(arr, swapIndex, pivotIndex);
        return swapIndex;
    }

    private void swap(int[] arr, int firstIndex, int secondIndex){
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }
}
