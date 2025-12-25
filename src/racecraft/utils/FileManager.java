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
    
    static {
        // Create data directory if it doesn't exist
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
    
    // Save methods
    public static void saveDrivers(ArrayList<Driver> drivers) throws IOException {
        saveToFile(drivers, DATA_DIR + "/drivers.dat");
    }
    
    public static void saveTracks(ArrayList<Track> tracks) throws IOException {
        saveToFile(tracks, DATA_DIR + "/tracks.dat");
    }
    
    public static void saveStrategies(ArrayList<Strategy> strategies) throws IOException {
        saveToFile(strategies, DATA_DIR + "/strategies.dat");
    }
    
    public static void saveUsers(ArrayList<User> users) throws IOException {
        saveToFile(users, DATA_DIR + "/users.dat");
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
        return (ArrayList<Driver>) loadFromFile(DATA_DIR + "/drivers.dat");
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Track> loadTracks() throws IOException, ClassNotFoundException {
        return (ArrayList<Track>) loadFromFile(DATA_DIR + "/tracks.dat");
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Strategy> loadStrategies() throws IOException, ClassNotFoundException {
        return (ArrayList<Strategy>) loadFromFile(DATA_DIR + "/strategies.dat");
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<User> loadUsers() throws IOException, ClassNotFoundException {
        return (ArrayList<User>) loadFromFile(DATA_DIR + "/users.dat");
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
}