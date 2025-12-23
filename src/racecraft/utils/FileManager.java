/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/utils/FileManager.java
package racecraft.utils;

import racecraft.model.*;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String DRIVERS_FILE = DATA_DIR + "/drivers.dat";
    private static final String TRACKS_FILE = DATA_DIR + "/tracks.dat";
    private static final String STRATEGIES_FILE = DATA_DIR + "/strategies.dat";
    
    static {
        // Create data directory if it doesn't exist
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
    
    // Save methods
    public static void saveDrivers(ArrayList<Driver> drivers) throws IOException {
        saveToFile(drivers, DRIVERS_FILE);
    }
    
    public static void saveTracks(ArrayList<Track> tracks) throws IOException {
        saveToFile(tracks, TRACKS_FILE);
    }
    
    public static void saveStrategies(ArrayList<Strategy> strategies) throws IOException {
        saveToFile(strategies, STRATEGIES_FILE);
    }
    
    private static void saveToFile(Object obj, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(obj);
        }
    }
    
    // Load methods
    @SuppressWarnings("unchecked")
    public static ArrayList<Driver> loadDrivers() throws IOException, ClassNotFoundException {
        return (ArrayList<Driver>) loadFromFile(DRIVERS_FILE);
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Track> loadTracks() throws IOException, ClassNotFoundException {
        return (ArrayList<Track>) loadFromFile(TRACKS_FILE);
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Strategy> loadStrategies() throws IOException, ClassNotFoundException {
        return (ArrayList<Strategy>) loadFromFile(STRATEGIES_FILE);
    }
    
    private static Object loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
    
    // Export to CSV for reports
    public static void exportToCSV(ArrayList<?> items, String filename) throws IOException {
        File file = new File(filename);
        try (PrintWriter writer = new PrintWriter(file)) {
            if (!items.isEmpty()) {
                // Write header based on first item type
                Object first = items.get(0);
                if (first instanceof Driver) {
                    writer.println("ID,Name,Nationality,Team,Driver Number,Skill Rating,Wins,Podiums,Year");
                    for (Object item : items) {
                        Driver d = (Driver) item;
                        writer.println(String.format("%s,%s,%s,%s,%d,%.1f,%d,%d,%d",
                            d.getId(), d.getName(), d.getNationality(), d.getTeam(),
                            d.getDriverNumber(), d.getSkillRating(), d.getWins(),
                            d.getPodiums(), d.getYear()));
                    }
                } else if (first instanceof Track) {
                    writer.println("ID,Name,Country,City,Length,Corners,Laps,Lap Record,Record Holder,Year");
                    for (Object item : items) {
                        Track t = (Track) item;
                        writer.println(String.format("%s,%s,%s,%s,%.3f,%d,%d,%.3f,%s,%d",
                            t.getId(), t.getName(), t.getCountry(), t.getCity(),
                            t.getLength(), t.getCorners(), t.getLaps(),
                            t.getLapRecord(), t.getRecordHolder(), t.getYear()));
                    }
                } else if (first instanceof Strategy) {
                    writer.println("ID,Name,Driver ID,Track ID,Pit Stops,Tire Compound,Risk Level,Estimated Time,Status,Year");
                    for (Object item : items) {
                        Strategy s = (Strategy) item;
                        writer.println(String.format("%s,%s,%s,%s,%d,%s,%d,%.1f,%s,%d",
                            s.getId(), s.getName(), s.getDriverId(), s.getTrackId(),
                            s.getPitStops(), s.getTireCompound(), s.getRiskLevel(),
                            s.getEstimatedTime(), s.getStatus(), s.getYear()));
                    }
                }
            }
        }
    }
}
