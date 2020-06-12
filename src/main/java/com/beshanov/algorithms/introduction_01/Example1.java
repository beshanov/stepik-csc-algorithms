package com.beshanov.algorithms.introduction_01;

import java.util.Scanner;

public class Example1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(fibonachi(input));
    }

    private static long fibonachi(int n) {
        long[] array = new long[n+1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }
}
