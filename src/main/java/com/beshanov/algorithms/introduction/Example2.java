package com.beshanov.algorithms.introduction;

import java.util.Scanner;

public class Example2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(fibonachiLastDigit(input));
    }

    private static int fibonachiLastDigit(int n) {
      int[] array = new int[n + 1];
      array[0] = 0;
      array[1] = 1;

      for (int i = 2; i <= n; i++) {
          array[i] = (array[i - 1] + array[i - 2]) % 10;
      }
      return array[n-1];
    }
}
