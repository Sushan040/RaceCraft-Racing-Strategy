/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.utils;

import java.util.ArrayList;
import racecraft.model.Strategy;

public class DataValidator {

    public static boolean isValidYear(int year) {
        return year >= 1950 && year <= 2025;
    }

    public static boolean isPositive(int value) {
        return value >= 0;
    }

    public static boolean isDuplicate(ArrayList<Strategy> list, String name) {
        for (Strategy s : list) {
            if (s.getStrategyName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
