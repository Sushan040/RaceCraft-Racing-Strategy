/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.controller;

import java.io.*;
import java.util.*;
import racecraft.model.Strategy;
import racecraft.utils.SortAlgorithms;

public class RaceController {
    private ArrayList<Strategy> strategies = new ArrayList<>();

    public RaceController() {
        loadFromFile();
    }

    // Load strategies from file
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/strategies.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                Strategy st = new Strategy(
                        s[0], // strategyName
                        s[1], // driverName
                        Integer.parseInt(s[2]), // raceYear
                        Integer.parseInt(s[3])  // pitStops
                );
                strategies.add(st);
            }
        } catch (Exception e) {
            System.out.println("File not found or empty.");
        }
    }

    
    // Save all strategies
    public void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/strategies.txt"))) {
            for (Strategy s : strategies) {
                bw.write(s.getStrategyName() + "," +
                         s.getDriverName() + "," +
                         s.getRaceYear() + "," +
                         s.getPitStops());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Save failed");
        }
    }

    // CRUD Operations
    public boolean addStrategy(Strategy s) {
        for (Strategy st : strategies) {
            if (st.getStrategyName().equalsIgnoreCase(s.getStrategyName())) return false;
        }
        strategies.add(s);
        saveAll();
        return true;
    }

    public void deleteStrategy(String name) {
        strategies.removeIf(s -> s.getStrategyName().equalsIgnoreCase(name));
        saveAll();
    }

    public ArrayList<Strategy> getAllStrategies() {
        return strategies;
    }

    // Sorting
    public void sortByYear(boolean ascending) {
        if (ascending) SortAlgorithms.sortYearAsc(strategies);
        else SortAlgorithms.sortYearDesc(strategies);
    }

    public void sortByName(boolean ascending) {
        if (ascending) SortAlgorithms.sortNameAsc(strategies);
        else SortAlgorithms.sortNameDesc(strategies);
    }
    
    // Search strategies by keyword and field (name, driver, year)
public ArrayList<Strategy> searchStrategies(String keyword, String by) {
    ArrayList<Strategy> results = new ArrayList<>();
    for (Strategy s : strategies) {
        switch (by.toLowerCase()) {
            case "name":
                if (s.getStrategyName().toLowerCase().contains(keyword.toLowerCase()))
                    results.add(s);
                break;
            case "driver":
                if (s.getDriverName().toLowerCase().contains(keyword.toLowerCase()))
                    results.add(s);
                break;
            case "year":
                if (String.valueOf(s.getRaceYear()).equals(keyword))
                    results.add(s);
                break;
            default:
                break;
        }
    }
    return results;
}

}
