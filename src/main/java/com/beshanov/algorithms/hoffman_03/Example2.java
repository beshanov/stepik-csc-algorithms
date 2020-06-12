package com.beshanov.algorithms.hoffman_03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Восстановите строку по её коду и беспрефиксному коду символов.
 * В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв, встречающихся в строке,
 * и размер получившейся закодированной строки, соответственно. В следующих kk строках записаны коды букв в формате "letter: code".
 * Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь
 * строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке
 * записана закодированная строка. Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет
 * минимальный возможный размер.
 * В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита. Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
 */
public class Example2 {
    public static void main(String[] args) {
        new Example2().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split(" ");
        int numberOfLetters = Integer.valueOf(firstLine[0]);
        Map<String, String> codeMap = new HashMap<>();

        for (int i = 0; i < numberOfLetters; i++) {
            String line = sc.nextLine();
            String[] letterAndCode = line.split(": ");
            codeMap.put(letterAndCode[0], letterAndCode[1]);
        }

        String encodedString = sc.nextLine();

        StringBuilder decodedString = new StringBuilder();
        while (!encodedString.isEmpty()) {
            for (Map.Entry<String, String> entry : codeMap.entrySet()) {
                if (encodedString.startsWith(entry.getValue())) {
                    decodedString.append(entry.getKey());
                    encodedString = encodedString.substring(entry.getValue().length());
                }
            }
        }

        System.out.println(decodedString.toString());
    }
}
