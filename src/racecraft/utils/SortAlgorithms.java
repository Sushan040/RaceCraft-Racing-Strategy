/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// src/racecraft/utils/SortAlgorithm.java
package racecraft.utils;

import racecraft.model.Driver;
import racecraft.model.Strategy;

import java.util.ArrayList;
import java.util.LinkedList;

public class SortAlgorithms {

    // Bubble Sort for drivers by experience
    public static void bubbleSortDriversByExperience(ArrayList<Driver> drivers) { 
        int n = drivers.size();
        for (int i = 0; i < n - 1; i++) { 
            for (int j = 0; j < n - i - 1; j++) { 
                if (drivers.get(j).getExperience() > drivers.get(j + 1).getExperience()) { 
                    Driver temp = drivers.get(j);
                    drivers.set(j, drivers.get(j + 1));
                    drivers.set(j + 1, temp);
                } 
            } 
        } 
    }

    // Quick Sort for strategies by year
    public static void quickSortStrategiesByYear(LinkedList<Strategy> strategies) { 
        quickSortHelper(strategies, 0, strategies.size() - 1); 
    }

    private static void quickSortHelper(LinkedList<Strategy> strategies, int low, int high) { 
        if (low < high) { 
            int pi = partition(strategies, low, high);
            quickSortHelper(strategies, low, pi - 1);
            quickSortHelper(strategies, pi + 1, high);
        } 
    }

    private static int partition(LinkedList<Strategy> strategies, int low, int high) { 
        int pivot = strategies.get(high).getYear();
        int i = low - 1;

        for (int j = low; j < high; j++) { 
            if (strategies.get(j).getYear() < pivot) { 
                i++;
                Strategy temp = strategies.get(i);
                strategies.set(i, strategies.get(j));
                strategies.set(j, temp);
            } 
        }

        Strategy temp = strategies.get(i + 1);
        strategies.set(i + 1, strategies.get(high));
        strategies.set(high, temp);

        return i + 1; 
    }
}
