package com.beshanov.algorithms.greedy_02;

import java.util.Scanner;

/**
 * По данному числу 1≤n≤10^9 найдите максимальное число k, для которого nn можно представить как сумму k различных
 * натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
 */
public class Example3 {
    public static void main(String[] args) {
        new Example3().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        long inputNumber = sc.nextLong();
        printTermsList(inputNumber);
    }

    private void printTermsList(long inputNumber) {
        //добавление единицы в решение в любом случае является жадным шагом для n > 2
        StringBuilder stringBuilder = new StringBuilder();
        long rest = inputNumber;
        int quantity = 0;
        for (long i = 1; i <= rest; i++) {
            if(shouldNumberBeAdded(i, rest)) {
                stringBuilder.append(i + " ");
                rest = rest - i;
            }
        }
        quantity = stringBuilder.toString().split(" ").length;
        stringBuilder.insert(0, quantity + "\n");
        System.out.println(stringBuilder);
    }

    private boolean shouldNumberBeAdded(long i, long rest) {
        if (i == rest) {
            return true;
        }
        return rest - i > i;
    }
}
