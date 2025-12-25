/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.utils;

import java.util.ArrayList;
import racecraft.model.Strategy;

public class SearchAlgorithms {

    // Linear Search (partial match)
    public static ArrayList<Strategy> linearSearch(ArrayList<Strategy> list, String keyword) {
        ArrayList<Strategy> result = new ArrayList<>();

        for (Strategy s : list) {
            if (s.getStrategyName().toLowerCase().contains(keyword.toLowerCase())
             || s.getDriverName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Binary Search (exact year)
    public static Strategy binarySearchByYear(ArrayList<Strategy> list, int year) {
        int low = 0, high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midYear = list.get(mid).getRaceYear();

            if (midYear == year) return list.get(mid);
            if (midYear < year) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}
