package com.beshanov.algorithms.divide_and_conquer_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа, не превосходящие 10^9.
 * Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j]. (Такая пара элементов называется инверсией массива.
 * Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности: например, в упорядоченном по неубыванию
 * массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию, инверсию образуют каждые два элемента.)
 */
public class Example2 {
    private static int inversion;
    public static void main(String[] args) {
        new Example2().run();
    }
    private void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int originArraySize = scanner.nextInt();
        int[] originArray = new int[originArraySize];
        for (int i = 0; i < originArray.length; i++) {
            originArray[i] = scanner.nextInt();
        }

        int[] sortedArray = mergeSort(originArray, 0, originArraySize - 1);
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.println(sortedArray[i]);
        }
        System.out.println(inversion);
    }

    private int[] mergeSort(int[] array, int left, int right) {
        if(right <= left) {
            return new int[] {array[left]};
        }
        int middle = left + (right - left) / 2;
        return merge(mergeSort(array, left, middle), mergeSort(array, middle + 1, right));
    }

    private int[] merge(int[] leftArray, int[] rightArray) {
        int[] sortedResult = new int[leftArray.length + rightArray.length];
        int leftPointer = 0;
        int rightPointer = 0;
        while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
            if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                sortedResult[leftPointer + rightPointer] = leftArray[leftPointer];
                leftPointer++;
            } else {
                sortedResult[leftPointer + rightPointer] = rightArray[rightPointer];
                inversion = inversion + leftArray.length - leftPointer;
                rightPointer++;
            }
        }
        if (leftPointer >= leftArray.length) {
            while(rightPointer < rightArray.length) {
                sortedResult[leftPointer + rightPointer] = rightArray[rightPointer];
                rightPointer++;
            }
        }
        if (rightPointer >= rightArray.length) {
            while(leftPointer < leftArray.length) {
                sortedResult[leftPointer + rightPointer] = leftArray[leftPointer];
                leftPointer++;
            }
        }
        return sortedResult;
    }

}
