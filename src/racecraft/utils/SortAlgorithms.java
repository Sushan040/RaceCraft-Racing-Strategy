/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.utils;

import java.util.ArrayList;
import racecraft.model.Strategy;

public class SortAlgorithms {

    public static void sortYearAsc(ArrayList<Strategy> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size()-1; j++)
                if (list.get(j).getRaceYear() > list.get(j+1).getRaceYear()) {
                    Strategy temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
    }

    public static void sortYearDesc(ArrayList<Strategy> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size()-1; j++)
                if (list.get(j).getRaceYear() < list.get(j+1).getRaceYear()) {
                    Strategy temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
    }

    public static void sortNameAsc(ArrayList<Strategy> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size()-1; j++)
                if (list.get(j).getStrategyName().compareToIgnoreCase(list.get(j+1).getStrategyName()) > 0) {
                    Strategy temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
    }

    public static void sortNameDesc(ArrayList<Strategy> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size()-1; j++)
                if (list.get(j).getStrategyName().compareToIgnoreCase(list.get(j+1).getStrategyName()) < 0) {
                    Strategy temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
    }
}
