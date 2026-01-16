/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import racecraft.model.Driver;
import racecraft.model.Strategy;
import racecraft.model.Track;
import racecraft.utils.MemoryManager;
import racecraft.utils.SearchAlgorithms;
import racecraft.utils.SortAlgorithms;
import racecraft.utils.DataValidator;

import javax.swing.*;
import java.util.List;

public class RaceController {

    // Driver CRUD
    public static void addDriver(String name, String ageStr, String expStr, String winsStr) {
        if (DataValidator.isEmpty(name) || !DataValidator.isPositiveInt(ageStr) || !DataValidator.isPositiveInt(expStr) || !DataValidator.isPositiveInt(winsStr)) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }
        if (DataValidator.isDuplicateDriver(name)) {
            JOptionPane.showMessageDialog(null, "Duplicate driver");
            return;
        }
        int id = MemoryManager.getDrivers().size() + 1;
        int age = Integer.parseInt(ageStr);
        int exp = Integer.parseInt(expStr);
        int wins = Integer.parseInt(winsStr);
        MemoryManager.getDrivers().add(new Driver(id, name, age, exp, wins));
        MemoryManager.addActivity("Added driver: " + name);
    }

    public static void updateDriver(int id, String name, int age, int exp, int wins) {
        for (Driver driver : MemoryManager.getDrivers()) {
            if (driver.getId() == id) {
                driver.setName(name);
                driver.setAge(age);
                driver.setExperience(exp);
                driver.setWins(wins);
                MemoryManager.addActivity("Updated driver ID: " + id);
                return;
            }
        }
    }

public static void deleteDriver(int id) {
    boolean wasRemoved = false;
    
    Iterator<Driver> iterator = MemoryManager.getDrivers().iterator();
    while (iterator.hasNext()) {
        Driver d = iterator.next();
        if (d.getId() == id) {
            iterator.remove();
            wasRemoved = true;
        }
    }
    
    if (wasRemoved) {
        MemoryManager.addActivity("Deleted driver ID: " + id);
    }
}

    // Strategy CRUD
    public static void addStrategy(Driver driver, Track track, String tyreType, String yearStr) {
        if (driver == null || track == null || DataValidator.isEmpty(tyreType) || !DataValidator.isPositiveInt(yearStr)) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }
        int id = MemoryManager.getStrategies().size() + 1;
        int year = Integer.parseInt(yearStr);
        Strategy strategy = new Strategy(id, driver, track, tyreType, year);
        MemoryManager.getStrategies().add(strategy);
        MemoryManager.getRecentStrategies().enqueue(strategy);
        
        String message = "Added strategy: " + 
                     (driver != null ? driver.getName() : "Unknown") + 
                     " @ " + 
                     (track != null ? track.getName() : "Unknown") + 
                     " (" + year + ")";

        MemoryManager.addActivity(message);
    }

    public static void updateStrategy(int id, Driver driver, Track track, String tyreType, int year) {
        for (Strategy strategy : MemoryManager.getStrategies()) {
            if (strategy.getId() == id) {
                strategy.setDriver(driver);
                strategy.setTrack(track);
                strategy.setTyreType(tyreType);
                strategy.setYear(year);
                MemoryManager.addActivity("Updated strategy ID: " + id);
                return;
            }
        }
    }

    public static void deleteStrategy(int id) {
        Strategy toDelete = null;
        for (Strategy strategy : MemoryManager.getStrategies()) {
            if (strategy.getId() == id) {
                toDelete = strategy;
                MemoryManager.addActivity("Deleted strategy ID: " + id);
                break;
            }
        }
        if (toDelete != null) {
            MemoryManager.getStrategies().remove(toDelete);
            MemoryManager.getUndoStack().push(toDelete);
        }
    }
    

    public static void undoDeleteStrategy() {
        Strategy undone = MemoryManager.getUndoStack().pop();
        if (undone != null) {
            MemoryManager.getStrategies().add(undone);
            MemoryManager.addActivity("Restored strategy ID: " + undone.getId());
        }
    }
    


    public static void addTrack(String name, String lengthStr, String difficulty) {
        if (DataValidator.isEmpty(name) || DataValidator.isEmpty(lengthStr)) {
            JOptionPane.showMessageDialog(null, "Name and Length are required!");
            return;
        }

        try {
            double length = Double.parseDouble(lengthStr);
            if (length <= 0) {
                JOptionPane.showMessageDialog(null, "Length must be positive!");
                return;
            }

            // Optional duplicate check
            for (Track t : MemoryManager.getTracks()) {
                if (t.getName().equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "Track name already exists!");
                    return;
                }
            }

            int id = MemoryManager.getTracks().size() + 1;
            Track newTrack = new Track(id, name, length, difficulty);
            MemoryManager.getTracks().add(newTrack);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid length format!");
        }
         MemoryManager.addActivity("Added track: " + name);
    }

    public static void updateTrack(int id, String name, double length, String difficulty) {
        for (Track t : MemoryManager.getTracks()) {
            if (t.getId() == id) {
                t.setName(name);
                t.setLength(length);
                t.setDifficulty(difficulty);
                MemoryManager.addActivity("Updated track ID: " + id);
                return;
            }
        }
    }

    public static void deleteTrack(int id) {
    boolean removed = false;
    
    java.util.Iterator<Track> iterator = MemoryManager.getTracks().iterator();
    while (iterator.hasNext()) {
        Track t = iterator.next();
        if (t.getId() == id) {
            iterator.remove();
            removed = true;
        }
    }
    
    if (removed) {
        MemoryManager.addActivity("Deleted track ID: " + id);
    }
}
    

public static List<Strategy> searchStrategies(String searchType, String query) {
    String q = query.trim().toLowerCase();
    List<Strategy> results = new ArrayList<>();

    if (q.isEmpty()) {
        return new ArrayList<>(MemoryManager.getStrategies());
    }

    for (Strategy s : MemoryManager.getStrategies()) {
        switch (searchType) {
            case "Driver Name :":
                if (s.getDriver() != null && s.getDriver().getName().toLowerCase().contains(q))
                    results.add(s);
                break;
            case "Track Name :":
                if (s.getTrack() != null && s.getTrack().getName().toLowerCase().contains(q))
                    results.add(s);
                break;
            case "Year :":
                try {
                    int year = Integer.parseInt(q);
                    if (s.getYear() == year)
                        results.add(s);
                } catch (NumberFormatException ignored) {}
                break;
            case "Tyre :":
                if (s.getTyreType() != null && s.getTyreType().toLowerCase().contains(q))
                    results.add(s);
                break;
        }
    }
    return results;
}

public static List<Strategy> sortStrategies(List<Strategy> list, String sortBy, String order) {
    List<Strategy> sorted = new ArrayList<>(list);

    Comparator<Strategy> comp = null;

switch (sortBy) {

    case "Driver Name :":
        comp = new Comparator<Strategy>() {
            @Override
            public int compare(Strategy s1, Strategy s2) {
                String name1 = (s1.getDriver() != null) ? s1.getDriver().getName() : "";
                String name2 = (s2.getDriver() != null) ? s2.getDriver().getName() : "";
                return name1.compareTo(name2);
            }
        };
        break;

        case "Track Name :":
            comp = new Comparator<Strategy>() {
                @Override
                public int compare(Strategy s1, Strategy s2) {
                    String name1 = (s1.getTrack() != null) ? s1.getTrack().getName() : "";
                    String name2 = (s2.getTrack() != null) ? s2.getTrack().getName() : "";
                    return name1.compareTo(name2);
                }
            };
            break;

        case "Year :":
            comp = new Comparator<Strategy>() {
                @Override
                public int compare(Strategy s1, Strategy s2) {
                    return Integer.compare(s1.getYear(), s2.getYear());
                }
            };
            break;

        case "Tyre :":
            comp = new Comparator<Strategy>() {
                @Override
                public int compare(Strategy s1, Strategy s2) {
                    String tyre1 = (s1.getTyreType() != null) ? s1.getTyreType() : "";
                    String tyre2 = (s2.getTyreType() != null) ? s2.getTyreType() : "";
                    return tyre1.compareTo(tyre2);
                }
            };
            break;

        default:
            comp = null;
            break;
    };

    if (comp != null) {
        if ("Descending".equals(order)) {
            comp = comp.reversed();
        }
        sorted.sort(comp);
    }

    return sorted;
}


    // Search and Sort calls
    public static List<Driver> searchDrivers(String query) {
        return SearchAlgorithms.linearSearchDrivers(query);
    }

    public static List<Track> searchTracks(String query) {
        return SearchAlgorithms.linearSearchTracks(query);
    }

    public static Strategy searchStrategyByYear(int year) {
        return SearchAlgorithms.binarySearchStrategyByYear(year);
    }

    public static void sortDriversByExperience() {
        SortAlgorithms.bubbleSortDriversByExperience(MemoryManager.getDrivers());
    }

    public static void sortStrategiesByYear() {
        SortAlgorithms.quickSortStrategiesByYear(MemoryManager.getStrategies());
    }
}