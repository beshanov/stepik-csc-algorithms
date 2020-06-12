package com.beshanov.algorithms.hoffman_03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * То же, что и hoffman.Example2, но решение построено на основе обхода дерева, а не на словаре,
 * что даёт лучшую ассимптотику
 */
public class Example3 {
    public static void main(String[] args) {
        new Example3().run();
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

        Element result = new Element();
        Element tree = buildTree(result, codeMap);

        String decodeString = decode(tree, encodedString);
        System.out.println(decodeString);
    }

    private String decode(Element root, String encodedString) {
        StringBuilder result = new StringBuilder();
        Element currentElement = root;
        for (String currentDigitCode : encodedString.split("")) {
            if (currentDigitCode.equals("0")) {
                if (currentElement.leftChild != null) {
                    currentElement = currentElement.leftChild;
                }
                if (elementHasNoChild(currentElement)) {
                    result.append(currentElement.value);
                    currentElement = root;
                    continue;
                }
            }
            if (currentDigitCode.equals("1")) {
                if (currentElement.rightChild != null) {
                    currentElement = currentElement.rightChild;
                }
                if (elementHasNoChild(currentElement)) {
                    result.append(currentElement.value);
                    currentElement = root;
                    continue;
                }
            }
        }
        return result.toString();
    }

    private boolean elementHasNoChild(Element element) {
        return element.leftChild == null && element.rightChild == null;
    }

    private Element buildTree(Element result, Map<String, String> codeMap) {
        for (Map.Entry<String, String> entry : codeMap.entrySet()) {
            String letter = entry.getKey();
            String code = entry.getValue();
            String[] codeArray = code.split("");
            Element currentElement = result;
            for (int i = 0; i < codeArray.length; i++) {
                if (codeArray[i].equals("0")) {
                    if (currentElement.leftChild == null) {
                        currentElement.leftChild = new Element();
                        if (i == codeArray.length - 1) {
                            currentElement.leftChild.value = letter;
                        }
                    }
                    currentElement = currentElement.leftChild;
                }
                if (codeArray[i].equals("1")) {
                    if (currentElement.rightChild == null) {
                        currentElement.rightChild = new Element();
                        if (i == codeArray.length - 1) {
                            currentElement.rightChild.value = letter;
                        }
                    }
                    currentElement = currentElement.rightChild;
                }
            }
        }
        return result;
    }

    public class Element {
        int priority;
        String value;
        String code = "";
        Element leftChild;
        Element rightChild;
    }
}

