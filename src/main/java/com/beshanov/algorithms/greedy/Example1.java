package com.beshanov.algorithms.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * По данным n отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержит хотя бы одну из точек.
 * В первой строке дано число 1≤n≤100 отрезков.
 * Каждая из последующих nn строк содержит по два числа 0≤l≤r≤10^9,
 * задающих начало и конец отрезка. Выведите оптимальное число m точек и сами mm точек.
 * Если таких множеств точек несколько, выведите любое из них.
 */
public class Example1 {
    public static void main(String[] args) {
        new Example1().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int segmentNumber = sc.nextInt();
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < segmentNumber; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            segments.add(new Segment(left, right));
        }

        List<Integer> resultPoints = processOptimalDecision(segments);

        System.out.println(resultPoints.size());
        for (Integer point : resultPoints) {
            if(resultPoints.indexOf(point) < resultPoints.size() - 1) {
                System.out.print(point + " ");
            } else {
                System.out.print(point);
            }
        }
    }

    private List<Integer> processOptimalDecision(List<Segment> segments) {
        ArrayList<Integer> resultPoints = new ArrayList<>();
        segments.sort(Comparator.comparingInt(x -> x.right));
        resultPoints.add(segments.get(0).right);
        for (int i = 1; i < segments.size(); i++) {
            if (!containsPoint(segments.get(i), resultPoints.get(resultPoints.size()-1))) {
                resultPoints.add(segments.get(i).right);
            }

        }
        return resultPoints;
    }

    private boolean containsPoint(Segment segment, Integer lastPoint) {
        return segment.left <= lastPoint;
    }

    class Segment {
        Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }

        private int left;
        private int right;
    }
}
