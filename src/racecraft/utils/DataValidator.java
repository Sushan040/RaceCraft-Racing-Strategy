/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// src/racecraft/utils/ValidationUtil.java
package racecraft.utils;

import racecraft.model.Driver;
import racecraft.model.Strategy;
import racecraft.model.Track;

public class DataValidator {

    public static boolean isEmpty(String str) { 
        return str == null || str.trim().isEmpty(); 
    }

    public static boolean isPositiveInt(String str) { 
        try { 
            int num = Integer.parseInt(str);
            return num > 0; 
        } catch (NumberFormatException e) { 
            return false; 
        } 
    }

    public static boolean isPositiveDouble(String str) { 
        try { 
            double num = Double.parseDouble(str);
            return num > 0; 
        } catch (NumberFormatException e) { 
            return false; 
        } 
    }

    public static boolean isDuplicateDriver(String name) { 
        for (Driver driver : MemoryManager.getDrivers()) { 
            if (driver.getName().equalsIgnoreCase(name)) { 
                return true; 
            } 
        }
        return false; 
    }

    public static boolean isDuplicateTrack(String name) { 
        for (Track track : MemoryManager.getTracks()) { 
            if (track.getName().equalsIgnoreCase(name)) { 
                return true; 
            } 
        }
        return false; 
    }

    public static boolean isDuplicateStrategy(int id) { 
        for (Strategy strategy : MemoryManager.getStrategies()) { 
            if (strategy.getId() == id) { 
                return true; 
            } 
        }
        return false; 
    }
}

