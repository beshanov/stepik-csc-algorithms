package com.beshanov.algorithms.introduction_01;

import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        System.out.println(euclidGCD(a, b));
    }

    private static long euclidGCD(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a >= b) return euclidGCD(a % b , b);
        else return euclidGCD(a, b % a);
    }
}
