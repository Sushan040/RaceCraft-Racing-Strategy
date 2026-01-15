/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// src/racecraft/utils/SearchAlgorithm.java
package racecraft.utils;

import racecraft.model.Driver;
import racecraft.model.Strategy;
import racecraft.model.Track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchAlgorithms {

    // Linear Search for partial match (e.g., driver name)
    public static List<Driver> linearSearchDrivers(String query) { 
        List<Driver> results = new ArrayList<>();
        for (Driver driver : MemoryManager.getDrivers()) { 
            if (driver.getName().toLowerCase().contains(query.toLowerCase())) { 
                results.add(driver); 
            } 
        }
        return results; 
    }

    // Linear Search for tracks
    public static List<Track> linearSearchTracks(String query) { 
        List<Track> results = new ArrayList<>();
        for (Track track : MemoryManager.getTracks()) { 
            if (track.getName().toLowerCase().contains(query.toLowerCase())) { 
                results.add(track); 
            } 
        }
        return results; 
    }

    // Binary Search for strategy year (assumes sorted by year)
    public static Strategy binarySearchStrategyByYear(int year) { 
        LinkedList<Strategy> strategies = MemoryManager.getStrategies();
        // Assume sorted; if not, sort first
        SortAlgorithms.quickSortStrategiesByYear(strategies);

        int low = 0;
        int high = strategies.size() - 1;

        while (low <= high) { 
            int mid = low + (high - low) / 2;
            Strategy midStrat = strategies.get(mid);

            if (midStrat.getYear() == year) { 
                return midStrat; 
            } else if (midStrat.getYear() < year) { 
                low = mid + 1; 
            } else { 
                high = mid - 1; 
            } 
        }

        return null; 
    }
}
