/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.utils;

import racecraft.model.Driver;
import racecraft.model.Track;
import racecraft.model.Strategy;
import racecraft.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemoryManager {
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Track> tracks = new ArrayList<>();
    private static LinkedList<Strategy> strategies = new LinkedList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static Queue<Strategy> recentStrategies = new Queue<>(5); // Queue for last 5 added
    private static Stack<Strategy> undoStack = new Stack<>(10); // Stack for undo delete

    public static void initializeData() {
        // Preload 10 Drivers
        drivers.add(new Driver(1, "Lewis Hamilton", 38, 15, 103));
        drivers.add(new Driver(2, "Max Verstappen", 25, 8, 50));
        drivers.add(new Driver(3, "Charles Leclerc", 25, 6, 5));
        drivers.add(new Driver(4, "Sergio Perez", 33, 13, 6));
        drivers.add(new Driver(5, "Fernando Alonso", 42, 20, 32));
        drivers.add(new Driver(6, "Lando Norris", 23, 5, 4));
        drivers.add(new Driver(7, "George Russell", 26, 6, 2));
        drivers.add(new Driver(8, "Carlos Sainz", 29, 9, 2));
        drivers.add(new Driver(9, "Sebastian Vettel", 36, 16, 53));
        drivers.add(new Driver(10, "Oscar Piastri", 22, 2, 3));

        // Preload 10 Tracks
        tracks.add(new Track(1, "Monza", 5.793, "Medium"));
        tracks.add(new Track(2, "Silverstone", 5.891, "High"));
        tracks.add(new Track(3, "Monaco", 3.337, "High"));
        tracks.add(new Track(4, "Spa", 7.004, "High"));
        tracks.add(new Track(5, "Suzuka", 5.807, "High"));
        tracks.add(new Track(6, "Interlagos", 4.309, "Medium"));
        tracks.add(new Track(7, "Bahrain", 5.412, "Medium"));
        tracks.add(new Track(8, "Imola", 4.909, "Medium"));
        tracks.add(new Track(9, "Austin", 5.513, "High"));
        tracks.add(new Track(10, "Singapore", 5.063, "High"));

        // Preload 10 Strategies
        strategies.add(new Strategy(1, drivers.get(0), tracks.get(0), "Soft", 2023));
        strategies.add(new Strategy(2, drivers.get(1), tracks.get(1), "Medium", 2022));
        strategies.add(new Strategy(3, drivers.get(2), tracks.get(2), "Hard", 2021));
        strategies.add(new Strategy(4, drivers.get(3), tracks.get(3), "Soft", 2020));
        strategies.add(new Strategy(5, drivers.get(4), tracks.get(4), "Medium", 2019));
        strategies.add(new Strategy(6, drivers.get(5), tracks.get(5), "Hard", 2018));
        strategies.add(new Strategy(7, drivers.get(6), tracks.get(6), "Soft", 2023));
        strategies.add(new Strategy(8, drivers.get(7), tracks.get(7), "Hard", 2022));
        strategies.add(new Strategy(9, drivers.get(8), tracks.get(8), "Medium", 2021));
        strategies.add(new Strategy(10, drivers.get(9), tracks.get(9), "Soft", 2024));


        // Preload 2 Users
        users.add(new User("admin", "admin123", "ADMIN"));
        users.add(new User("Sushan", "123", "ADMIN"));
        users.add(new User("user", "123", "USER"));
    }

    // Getters
    public static ArrayList<Driver> getDrivers() { 
        return drivers; 
    }

    public static ArrayList<Track> getTracks() { 
        return tracks; 
    }

    public static LinkedList<Strategy> getStrategies() { 
        return strategies; 
    }

    public static ArrayList<User> getUsers() { 
        return users; 
    }

    public static Queue<Strategy> getRecentStrategies() { 
        return recentStrategies; 
    }

    public static Stack<Strategy> getUndoStack() { 
        return undoStack; 
    }
    
    private static final List<String> recentActivities = new ArrayList<>();
    private static final int MAX_ACTIVITIES = 5;

    public static void addActivity(String action) {
        recentActivities.add(0, action);  // Add to front → newest first
        if (recentActivities.size() > MAX_ACTIVITIES) {
            recentActivities.remove(recentActivities.size() - 1); // Remove oldest
        }
    }

    public static List<String> getRecentActivities() {
        return new ArrayList<>(recentActivities); // Return copy
    }
}
