package com.beshanov.algorithms.hoffman;


import java.util.*;

/**
 * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита, постройте оптимальный
 * беспрефиксный код. В первой строке выведите количество различных букв k, встречающихся в строке, и размер получившейся
 * закодированной строки. В следующих k строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.
 */
public class Example1 {

    public static void main(String[] args) {
        new Example1().run();
    }

    private void run() {
        //Создаём мапу частотности из введенного текста
        Map<String, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] lettersArray = inputString.split("");
        for (String letter : lettersArray) {
            Integer priority = map.get(letter);
            if (priority != null) {
                map.put(letter, ++priority);
            } else {
                map.put(letter, 1);
            }
        }

        //Наполняем очередь с приоритетами значениями из мапы
        PriorityQueue priorityQueue = new PriorityQueue();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Element element = new Element();
            element.value = entry.getKey();
            element.priority = entry.getValue();
            priorityQueue.insert(element);
        }

        int n = map.size();
        System.out.print(n + " ");

        //строим двоичное дерево: в конечном итоге единственный элемент в очереди - корень дерева
        for (int i = n + 1; i <= 2 * n - 1; i++) {
            Element firstMin = priorityQueue.extractMin();
            Element secondMin = priorityQueue.extractMin();
            Element resultElement = new Element();
            resultElement.priority = firstMin.priority + secondMin.priority;
            resultElement.childLeft = firstMin;
            resultElement.childRight = secondMin;
            priorityQueue.insert(resultElement);
        }

        Element currentElement = priorityQueue.extractMin();
        //Проходимся по дереву и формируем мапу с кодами Хоффмана для листьев
        Map<String, String> codeMap = processBinaryTree(currentElement, new HashMap<>(), "");

        //Вывод результатов
        StringBuilder encodedString = new StringBuilder();
        for (String letter : lettersArray) {
            encodedString.append(codeMap.get(letter));
        }
        System.out.println(encodedString.length());
        for (Map.Entry<String, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(encodedString);
    }


    private Map<String, String> processBinaryTree(Element element, Map<String, String> resultMap, String parentCode) {
        if (element.childLeft == null && element.childRight == null) {
            HashMap<String, String> map = new HashMap<>();
            if(parentCode.equals("")){
                element.code = "0";
            }
            map.put(element.value, parentCode + element.code);
            return map;
        }
        if (element.childLeft != null) {
            String currentCode = parentCode + "1";
            resultMap.putAll(processBinaryTree(element.childLeft, resultMap, currentCode));
        }
        if (element.childRight != null) {
            String currentCode = parentCode + "0";
            resultMap.putAll(processBinaryTree(element.childRight, resultMap, currentCode));
        }

        return resultMap;
    }
}


class PriorityQueue {
    private List<Element> list = new ArrayList<>();

    public void insert(Element element) {
        list.add(element);
    }

    public Element extractMin() {
        Element minElement = list.stream().min(Comparator.comparingInt(o -> o.priority)).get();
        list.remove(minElement);
        return minElement;
    }
}

class Element {
    public String value;
    public Integer priority;
    public String code = "";

    public Element childLeft;
    public Element childRight;

    @Override
    public String toString() {
        return value + " : " + priority;
    }
}
