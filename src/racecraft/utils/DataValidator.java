/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/utils/DataValidator.java
package racecraft.utils;

import racecraft.model.*;

public class DataValidator {
    
    public static void validateDriver(Driver driver) throws IllegalArgumentException {
        validateId(driver.getId());
        validateName(driver.getName());
        validateYear(driver.getYear());
        
        if (driver.getNationality() == null || driver.getNationality().trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be empty");
        }
        
        if (driver.getDriverNumber() < 1 || driver.getDriverNumber() > 99) {
            throw new IllegalArgumentException("Driver number must be between 1-99");
        }
        
        if (driver.getSkillRating() < 0 || driver.getSkillRating() > 100) {
            throw new IllegalArgumentException("Skill rating must be between 0-100");
        }
        
        if (driver.getWins() < 0) {
            throw new IllegalArgumentException("Wins cannot be negative");
        }
        
        if (driver.getPodiums() < 0) {
            throw new IllegalArgumentException("Podiums cannot be negative");
        }
    }
    
    public static void validateTrack(Track track) throws IllegalArgumentException {
        validateId(track.getId());
        validateName(track.getName());
        validateYear(track.getYear());
        
        if (track.getCountry() == null || track.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be empty");
        }
        
        if (track.getLength() <= 0 || track.getLength() > 20) {
            throw new IllegalArgumentException("Track length must be between 0-20 km");
        }
        
        if (track.getCorners() < 1) {
            throw new IllegalArgumentException("Track must have at least 1 corner");
        }
        
        if (track.getLaps() < 1) {
            throw new IllegalArgumentException("Laps must be at least 1");
        }
        
        if (track.getLapRecord() < 0) {
            throw new IllegalArgumentException("Lap record cannot be negative");
        }
    }
    
    public static void validateStrategy(Strategy strategy) throws IllegalArgumentException {
        validateId(strategy.getId());
        validateName(strategy.getName());
        validateYear(strategy.getYear());
        
        if (strategy.getDriverId() == null || strategy.getDriverId().trim().isEmpty()) {
            throw new IllegalArgumentException("Driver ID cannot be empty");
        }
        
        if (strategy.getTrackId() == null || strategy.getTrackId().trim().isEmpty()) {
            throw new IllegalArgumentException("Track ID cannot be empty");
        }
        
        if (strategy.getPitStops() < 0 || strategy.getPitStops() > 5) {
            throw new IllegalArgumentException("Pit stops must be between 0-5");
        }
        
        if (strategy.getRiskLevel() < 1 || strategy.getRiskLevel() > 10) {
            throw new IllegalArgumentException("Risk level must be between 1-10");
        }
        
        if (strategy.getEstimatedTime() < 0) {
            throw new IllegalArgumentException("Estimated time cannot be negative");
        }
        
        // Validate tire compound
        String[] validCompounds = {"Soft", "Medium", "Hard", "Intermediate", "Wet"};
        boolean validCompound = false;
        for (String comp : validCompounds) {
            if (comp.equalsIgnoreCase(strategy.getTireCompound())) {
                validCompound = true;
                break;
            }
        }
        if (!validCompound) {
            throw new IllegalArgumentException("Invalid tire compound. Must be: Soft, Medium, Hard, Intermediate, or Wet");
        }
        
        // Validate status
        String[] validStatuses = {"Draft", "Approved", "Rejected", "Archived"};
        boolean validStatus = false;
        for (String status : validStatuses) {
            if (status.equalsIgnoreCase(strategy.getStatus())) {
                validStatus = true;
                break;
            }
        }
        if (!validStatus) {
            throw new IllegalArgumentException("Invalid status. Must be: Draft, Approved, Rejected, or Archived");
        }
    }
    
    private static void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (!id.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("ID must contain only letters and numbers");
        }
    }
    
    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name cannot exceed 100 characters");
        }
    }
    
    private static void validateYear(int year) {
        if (year < 1950 || year > 2024) {
            throw new IllegalArgumentException("Year must be between 1950-2024");
        }
    }
    
    public static void validateNumericInput(String input, String fieldName) 
            throws NumberFormatException {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number");
        }
    }
    
    public static void validateIntegerInput(String input, String fieldName) 
            throws NumberFormatException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid integer");
        }
    }
}