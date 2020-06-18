package com.beshanov.algorithms.heap_04;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Первая строка входа содержит число операций 1≤n≤10^5. Каждая из последующих n строк задают операцию одного из следующих двух типов:
 * Insert x, где 0≤x≤10^9 — целое число;
 * ExtractMax
 * Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 */
public class Example1 {
    public static void main(String[] args) {
        new Example1().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        long operationsNumber = sc.nextLong();
        ArrayList<String> operationList = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < operationsNumber; i++) {
            String operation = sc.nextLine();
            operationList.add(operation);
        }
        PriorityQueue queue = new PriorityQueue();
        for (String operation : operationList) {
            String[] operationDetails = operation.split(" ");
            if (operationDetails.length == 1)
            {
                System.out.println(queue.extractMax());
            }
            if (operationDetails.length == 2) {
                queue.insert(Integer.valueOf(operationDetails[1]));
            }
        }
    }
}

class PriorityQueue {
    private ArrayList<Integer> heap = new ArrayList<>();

    public void insert(Integer node) {
        int indexToInsert = heap.size();
        heap.add(indexToInsert, node);
        int parentIndex = (indexToInsert - 1) / 2;
        siftUp(indexToInsert, parentIndex);
    }

    private void siftUp(int childIndex, Integer parentIndex) {
        Integer childNode = heap.get(childIndex);
        Integer parentNode = heap.get(parentIndex);
        if (childNode < parentNode) {
            return;
        }
        heap.set(parentIndex, childNode);
        heap.set(childIndex, parentNode);
        childIndex = parentIndex;
        parentIndex = (childIndex - 1) / 2;
        if (childIndex != parentIndex || childIndex == 1) {
            siftUp(childIndex, parentIndex);
        }
    }

    public Integer extractMax() {
        Integer maxNode = heap.get(0);
        Integer nodeToSiftDown = heap.get(heap.size() - 1);
        heap.set(0, nodeToSiftDown);
        int nodeToSiftDownIndex = 0;
        heap.remove(heap.size() - 1);
        siftDown(nodeToSiftDown, nodeToSiftDownIndex);
        return maxNode;
    }

    private void siftDown(Integer nodeToSiftDown, int nodeToSiftDownIndex) {
        int firstChildIndex = 2 * nodeToSiftDownIndex + 1;
        int secondChildIndex = firstChildIndex + 1;
        int maxIndex = heap.size() - 1;
        if (maxIndex > firstChildIndex && maxIndex >= secondChildIndex) {
            Integer firstChild = heap.get(firstChildIndex);
            Integer secondChild = heap.get(secondChildIndex);
            Integer maxNode = maxOfIntegers(nodeToSiftDown, firstChild, secondChild);
            if (maxNode.equals(nodeToSiftDown)) {
                return;
            }
            if (maxNode.equals(firstChild)) {
                heap.set(nodeToSiftDownIndex, firstChild);
                heap.set(firstChildIndex, nodeToSiftDown);
                siftDown(nodeToSiftDown, firstChildIndex);
                return;
            }
            if (maxNode.equals(secondChild)) {
                heap.set(nodeToSiftDownIndex, secondChild);
                heap.set(secondChildIndex, nodeToSiftDown);
                siftDown(nodeToSiftDown, secondChildIndex);
                return;
            }
        }
        if (maxIndex >= firstChildIndex && maxIndex < secondChildIndex) {
            Integer onlyChild = heap.get(firstChildIndex);
            Integer maxNode = maxOfIntegers(onlyChild, nodeToSiftDown);
            if (maxNode.equals(nodeToSiftDown)) {
                return;
            }
            heap.set(firstChildIndex, nodeToSiftDown);
            heap.set(nodeToSiftDownIndex, onlyChild);
        }
        if (maxIndex < firstChildIndex || maxIndex < secondChildIndex) {
            return;
        }

    }

    private Integer maxOfIntegers(Integer... integers) {
        Integer max = integers[0];
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] > max) {
                max = integers[i];
            }
        }
        return max;
    }
}
