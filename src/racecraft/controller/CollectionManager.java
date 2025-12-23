/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/controller/CollectionManager.java
package racecraft.controller;

import racecraft.model.*;
import racecraft.utils.DataValidator;
import racecraft.utils.FileManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CollectionManager {
    // Data storage
    private ArrayList<Driver> drivers;
    private ArrayList<Track> tracks;
    private ArrayList<Strategy> strategies;
    
    // For quick lookups
    private HashMap<String, Driver> driverMap;
    private HashMap<String, Track> trackMap;
    private HashMap<String, Strategy> strategyMap;
    
    // Data structures for specific features
    private Queue<Strategy> recentStrategies; // For recent items display
    private Stack<String> actionHistory; // For undo functionality
    
    // Statistics
    private int totalItems;
    
    public CollectionManager() {
        drivers = new ArrayList<>();
        tracks = new ArrayList<>();
        strategies = new ArrayList<>();
        
        driverMap = new HashMap<>();
        trackMap = new HashMap<>();
        strategyMap = new HashMap<>();
        
        recentStrategies = new LinkedList<>();
        actionHistory = new Stack<>();
        
        loadSampleData();
    }
    
    private void loadSampleData() {
        // Sample Drivers
        addDriver(new Driver("DRV001", "Lewis Hamilton", 1985, "British"));
        addDriver(new Driver("DRV002", "Max Verstappen", 1997, "Dutch"));
        addDriver(new Driver("DRV003", "Charles Leclerc", 1997, "Monegasque"));
        addDriver(new Driver("DRV004", "Lando Norris", 1999, "British"));
        addDriver(new Driver("DRV005", "Carlos Sainz", 1994, "Spanish"));
        
        // Sample Tracks
        addTrack(new Track("TRK001", "Monza", 1922, "Italy"));
        addTrack(new Track("TRK002", "Silverstone", 1948, "UK"));
        addTrack(new Track("TRK003", "Spa-Francorchamps", 1921, "Belgium"));
        addTrack(new Track("TRK004", "Monaco", 1929, "Monaco"));
        addTrack(new Track("TRK005", "Suzuka", 1962, "Japan"));
        
        // Sample Strategies
        addStrategy(new Strategy("STR001", "Monza One-Stop", 2024, "DRV001", "TRK001"));
        addStrategy(new Strategy("STR002", "Spa Two-Stop", 2024, "DRV002", "TRK003"));
        
        updateStatistics();
    }
    
    // ========== CRUD OPERATIONS ==========
    
    // CREATE
    public boolean addDriver(Driver driver) {
        try {
            // Check for duplicate ID
            if (driverMap.containsKey(driver.getId())) {
                throw new IllegalArgumentException("Driver ID already exists: " + driver.getId());
            }
            
            // Validate data
            DataValidator.validateDriver(driver);
            
            drivers.add(driver);
            driverMap.put(driver.getId(), driver);
            actionHistory.push("ADD_DRIVER:" + driver.getId());
            updateStatistics();
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e; // Re-throw for UI to display
        }
    }
    
    public boolean addTrack(Track track) {
        try {
            if (trackMap.containsKey(track.getId())) {
                throw new IllegalArgumentException("Track ID already exists: " + track.getId());
            }
            
            DataValidator.validateTrack(track);
            
            tracks.add(track);
            trackMap.put(track.getId(), track);
            actionHistory.push("ADD_TRACK:" + track.getId());
            updateStatistics();
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    public boolean addStrategy(Strategy strategy) {
        try {
            if (strategyMap.containsKey(strategy.getId())) {
                throw new IllegalArgumentException("Strategy ID already exists: " + strategy.getId());
            }
            
            // Check if driver and track exist
            if (!driverMap.containsKey(strategy.getDriverId())) {
                throw new IllegalArgumentException("Driver not found: " + strategy.getDriverId());
            }
            if (!trackMap.containsKey(strategy.getTrackId())) {
                throw new IllegalArgumentException("Track not found: " + strategy.getTrackId());
            }
            
            DataValidator.validateStrategy(strategy);
            
            strategies.add(strategy);
            strategyMap.put(strategy.getId(), strategy);
            
            // Add to recent strategies queue (limit to 5)
            recentStrategies.offer(strategy);
            if (recentStrategies.size() > 5) {
                recentStrategies.poll();
            }
            
            actionHistory.push("ADD_STRATEGY:" + strategy.getId());
            updateStatistics();
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    // READ
    public ArrayList<Driver> getAllDrivers() {
        return new ArrayList<>(drivers);
    }
    
    public ArrayList<Track> getAllTracks() {
        return new ArrayList<>(tracks);
    }
    
    public ArrayList<Strategy> getAllStrategies() {
        return new ArrayList<>(strategies);
    }
    
    public Driver getDriverById(String id) {
        return driverMap.get(id);
    }
    
    public Track getTrackById(String id) {
        return trackMap.get(id);
    }
    
    public Strategy getStrategyById(String id) {
        return strategyMap.get(id);
    }
    
    // UPDATE
    public boolean updateDriver(String id, Driver updatedDriver) {
        try {
            Driver existing = driverMap.get(id);
            if (existing == null) {
                throw new IllegalArgumentException("Driver not found: " + id);
            }
            
            // If ID changed, check for duplicates
            if (!id.equals(updatedDriver.getId()) && driverMap.containsKey(updatedDriver.getId())) {
                throw new IllegalArgumentException("New ID already exists: " + updatedDriver.getId());
            }
            
            DataValidator.validateDriver(updatedDriver);
            
            // Remove old, add new
            drivers.remove(existing);
            driverMap.remove(id);
            
            drivers.add(updatedDriver);
            driverMap.put(updatedDriver.getId(), updatedDriver);
            
            actionHistory.push("UPDATE_DRIVER:" + id + "->" + updatedDriver.getId());
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    public boolean updateTrack(String id, Track updatedTrack) {
        try {
            Track existing = trackMap.get(id);
            if (existing == null) {
                throw new IllegalArgumentException("Track not found: " + id);
            }
            
            if (!id.equals(updatedTrack.getId()) && trackMap.containsKey(updatedTrack.getId())) {
                throw new IllegalArgumentException("New ID already exists: " + updatedTrack.getId());
            }
            
            DataValidator.validateTrack(updatedTrack);
            
            tracks.remove(existing);
            trackMap.remove(id);
            
            tracks.add(updatedTrack);
            trackMap.put(updatedTrack.getId(), updatedTrack);
            
            actionHistory.push("UPDATE_TRACK:" + id + "->" + updatedTrack.getId());
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    public boolean updateStrategy(String id, Strategy updatedStrategy) {
        try {
            Strategy existing = strategyMap.get(id);
            if (existing == null) {
                throw new IllegalArgumentException("Strategy not found: " + id);
            }
            
            if (!id.equals(updatedStrategy.getId()) && strategyMap.containsKey(updatedStrategy.getId())) {
                throw new IllegalArgumentException("New ID already exists: " + updatedStrategy.getId());
            }
            
            // Check if driver and track exist
            if (!driverMap.containsKey(updatedStrategy.getDriverId())) {
                throw new IllegalArgumentException("Driver not found: " + updatedStrategy.getDriverId());
            }
            if (!trackMap.containsKey(updatedStrategy.getTrackId())) {
                throw new IllegalArgumentException("Track not found: " + updatedStrategy.getTrackId());
            }
            
            DataValidator.validateStrategy(updatedStrategy);
            
            strategies.remove(existing);
            strategyMap.remove(id);
            
            strategies.add(updatedStrategy);
            strategyMap.put(updatedStrategy.getId(), updatedStrategy);
            
            actionHistory.push("UPDATE_STRATEGY:" + id + "->" + updatedStrategy.getId());
            return true;
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    // DELETE
    public boolean deleteDriver(String id) {
        Driver driver = driverMap.get(id);
        if (driver != null) {
            // Check if any strategy uses this driver
            for (Strategy strategy : strategies) {
                if (strategy.getDriverId().equals(id)) {
                    throw new IllegalArgumentException("Cannot delete driver: Used in strategy " + strategy.getId());
                }
            }
            
            drivers.remove(driver);
            driverMap.remove(id);
            actionHistory.push("DELETE_DRIVER:" + id);
            updateStatistics();
            return true;
        }
        return false;
    }
    
    public boolean deleteTrack(String id) {
        Track track = trackMap.get(id);
        if (track != null) {
            // Check if any strategy uses this track
            for (Strategy strategy : strategies) {
                if (strategy.getTrackId().equals(id)) {
                    throw new IllegalArgumentException("Cannot delete track: Used in strategy " + strategy.getId());
                }
            }
            
            tracks.remove(track);
            trackMap.remove(id);
            actionHistory.push("DELETE_TRACK:" + id);
            updateStatistics();
            return true;
        }
        return false;
    }
    
    public boolean deleteStrategy(String id) {
        Strategy strategy = strategyMap.get(id);
        if (strategy != null) {
            strategies.remove(strategy);
            strategyMap.remove(id);
            recentStrategies.remove(strategy);
            actionHistory.push("DELETE_STRATEGY:" + id);
            updateStatistics();
            return true;
        }
        return false;
    }
    
    // ========== SEARCH OPERATIONS ==========
    
    // Linear Search for partial matches
    public ArrayList<Driver> searchDrivers(String query) {
        ArrayList<Driver> results = new ArrayList<>();
        query = query.toLowerCase();
        
        for (Driver driver : drivers) {
            if (driver.getName().toLowerCase().contains(query) ||
                driver.getNationality().toLowerCase().contains(query) ||
                driver.getTeam().toLowerCase().contains(query) ||
                String.valueOf(driver.getDriverNumber()).contains(query)) {
                results.add(driver);
            }
        }
        return results;
    }
    
    public ArrayList<Track> searchTracks(String query) {
        ArrayList<Track> results = new ArrayList<>();
        query = query.toLowerCase();
        
        for (Track track : tracks) {
            if (track.getName().toLowerCase().contains(query) ||
                track.getCountry().toLowerCase().contains(query) ||
                track.getCity().toLowerCase().contains(query)) {
                results.add(track);
            }
        }
        return results;
    }
    
    // Binary Search for IDs (lists must be sorted by ID first)
    public Driver binarySearchDriverById(String id) {
        ArrayList<Driver> sorted = new ArrayList<>(drivers);
        Collections.sort(sorted, (d1, d2) -> d1.getId().compareTo(d2.getId()));
        
        int left = 0;
        int right = sorted.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sorted.get(mid).getId().compareTo(id);
            
            if (comparison == 0) {
                return sorted.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    
    // ========== SORT OPERATIONS ==========
    
    public void sortDriversByName(boolean ascending) {
        if (ascending) {
            Collections.sort(drivers);
        } else {
            Collections.sort(drivers, Collections.reverseOrder());
        }
    }
    
    public void sortDriversByYear(boolean ascending) {
        drivers.sort((d1, d2) -> {
            int comparison = Integer.compare(d1.getYear(), d2.getYear());
            return ascending ? comparison : -comparison;
        });
    }
    
    public void sortTracksByName(boolean ascending) {
        if (ascending) {
            Collections.sort(tracks);
        } else {
            Collections.sort(tracks, Collections.reverseOrder());
        }
    }
    
    public void sortTracksByLength(boolean ascending) {
        tracks.sort((t1, t2) -> {
            int comparison = Double.compare(t1.getLength(), t2.getLength());
            return ascending ? comparison : -comparison;
        });
    }
    
    public void sortStrategiesByRisk(boolean ascending) {
        strategies.sort((s1, s2) -> {
            int comparison = Integer.compare(s1.getRiskLevel(), s2.getRiskLevel());
            return ascending ? comparison : -comparison;
        });
    }
    
    // ========== STATISTICS ==========
    
    private void updateStatistics() {
        totalItems = drivers.size() + tracks.size() + strategies.size();
    }
    
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== RACECRAFT STATISTICS ===\n\n");
        stats.append(String.format("Total Items: %d\n", totalItems));
        stats.append(String.format("Drivers: %d\n", drivers.size()));
        stats.append(String.format("Tracks: %d\n", tracks.size()));
        stats.append(String.format("Strategies: %d\n", strategies.size()));
        stats.append(String.format("Recent Strategies: %d\n", recentStrategies.size()));
        stats.append(String.format("Total Actions: %d\n", actionHistory.size()));
        
        // Driver statistics
        if (!drivers.isEmpty()) {
            double avgRating = drivers.stream()
                .mapToDouble(Driver::getSkillRating)
                .average()
                .orElse(0);
            stats.append(String.format("\nAverage Driver Rating: %.1f/100\n", avgRating));
        }
        
        return stats.toString();
    }
    
    public Queue<Strategy> getRecentStrategies() {
        return new LinkedList<>(recentStrategies);
    }
    
    // ========== FILE OPERATIONS ==========
    
    public void saveAllData() {
        try {
            FileManager.saveDrivers(drivers);
            FileManager.saveTracks(tracks);
            FileManager.saveStrategies(strategies);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save data: " + e.getMessage(), e);
        }
    }
    
    public void loadAllData() {
        try {
            drivers = FileManager.loadDrivers();
            tracks = FileManager.loadTracks();
            strategies = FileManager.loadStrategies();
            
            // Rebuild maps
            rebuildMaps();
            updateStatistics();
        } catch (Exception e) {
            System.err.println("Failed to load data: " + e.getMessage());
            loadSampleData(); // Load defaults if file read fails
        }
    }
    
    private void rebuildMaps() {
        driverMap.clear();
        trackMap.clear();
        strategyMap.clear();
        recentStrategies.clear();
        
        for (Driver driver : drivers) {
            driverMap.put(driver.getId(), driver);
        }
        for (Track track : tracks) {
            trackMap.put(track.getId(), track);
        }
        for (Strategy strategy : strategies) {
            strategyMap.put(strategy.getId(), strategy);
            recentStrategies.offer(strategy);
            if (recentStrategies.size() > 5) {
                recentStrategies.poll();
            }
        }
    }
    
    // ========== VALIDATION ==========
    
    public boolean isDriverIdUnique(String id) {
        return !driverMap.containsKey(id);
    }
    
    public boolean isTrackIdUnique(String id) {
        return !trackMap.containsKey(id);
    }
    
    public boolean isStrategyIdUnique(String id) {
        return !strategyMap.containsKey(id);
    }
}
