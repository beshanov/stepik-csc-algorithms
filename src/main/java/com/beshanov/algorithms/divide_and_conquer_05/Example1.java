package com.beshanov.algorithms.divide_and_conquer_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
 * в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных чисел b b1,…,bk, не превышающих 10^9.
 * Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого A[j]=bi, или −1, если такого j нет.
 */
public class Example1 {
    public static void main(String[] args) {
        new Example1().run();
    }

    private void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] givenSortedArray = readArrayFromStandardInput(scanner);
        int[] numbersForSearchingArray = readArrayFromStandardInput(scanner);

        for (int numberToSearch : numbersForSearchingArray) {
            int numberIndex = binarySearch(givenSortedArray, numberToSearch);
            System.out.print(numberIndex + " ");
        }
    }

    private int binarySearch(int[] givenSortedArray, int numberToSearch) {
        int leftElementIndex = 0;
        int rightElementIndex = givenSortedArray.length - 1;
        while (rightElementIndex >= leftElementIndex) {
            int middleElementIndex = leftElementIndex + (rightElementIndex - leftElementIndex) / 2;
            if (givenSortedArray[middleElementIndex] == numberToSearch) {
                return middleElementIndex + 1; // если совпадение найдено, то возвращаем со смещением в единицу, т.к. массив нумеруется с 0
            }
            if(leftElementIndex == rightElementIndex) {
                return -1; // если интервал поиска состоит из одного элемента и при этом этот элемент не является искомым, то возвращаем -1
            }
            if (givenSortedArray[middleElementIndex] < numberToSearch) {
                leftElementIndex = middleElementIndex + 1;
            }
            if (givenSortedArray[middleElementIndex] > numberToSearch) {
                rightElementIndex = middleElementIndex - 1;
            }
        }
        return -1;
    }


    private int[] readArrayFromStandardInput(Scanner scanner) {
        int arraySize = scanner.nextInt();
        int[] resultArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            resultArray[i] = scanner.nextInt();
        }
        return resultArray;
    }
}
