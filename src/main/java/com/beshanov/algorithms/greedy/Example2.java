package com.beshanov.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Первая строка содержит количество предметов 1≤n≤10^3 и вместимость рюкзака 0≤W≤2⋅10^6
 * Каждая из следующих n строк задаёт стоимость 0≤c≤2⋅10^6 и объём 0<w≤2⋅10^6 предмета
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть,
 * стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков
 * после запятой.
 */
public class Example2 {
    public static void main(String[] args) {
        new Example2().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int itemsNumber = sc.nextInt();
        int backpackVolume = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < itemsNumber; i++) {
            int cost = sc.nextInt();
            int volume = sc.nextInt();
            Item item = new Item(cost, volume);
            items.add(item);
        }

        double maxCost = calculateMaxCost(backpackVolume, items);
        System.out.println(maxCost);
    }

    private double calculateMaxCost(int backpackVolume, List<Item> items) {
        items.sort((y, x) -> {
            double unitCostX =( (double) x.getCost()) / ((double) x.getVolume());
            double unitCostY =( (double) y.getCost()) / ((double) y.getVolume());
            return Double.compare(unitCostX, unitCostY);
        });
        int freeSpace = backpackVolume;
        int totalCost = 0;

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int itemVolume = item.getVolume();
            int itemCost = item.getCost();
            if (itemVolume >= freeSpace) {
                return (double) freeSpace / itemVolume * itemCost + totalCost;
            }

            totalCost += itemCost;
            freeSpace -= itemVolume;
        }

        return totalCost;
    }

    private class Item {
        private int cost;
        private int volume;

        public Item(int cost, int volume) {
            this.cost = cost;
            this.volume = volume;
        }

        public int getCost() {
            return cost;
        }

        public int getVolume() {
            return volume;
        }
    }
}
