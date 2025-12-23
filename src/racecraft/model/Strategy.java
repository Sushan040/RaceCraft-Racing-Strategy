/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/model/Strategy.java
package racecraft.model;

public class Strategy extends RacingItem {
    private String driverId;
    private String trackId;
    private int pitStops;
    private String tireCompound;
    private int riskLevel; // 1-10
    private double estimatedTime; // seconds
    private String status; // Draft, Approved, Rejected
    
    public Strategy(String id, String name, int year, String driverId, String trackId) {
        super(id, name, year);
        setDriverId(driverId);
        setTrackId(trackId);
        this.pitStops = 1;
        this.tireCompound = "Medium";
        this.riskLevel = 5;
        this.status = "Draft";
    }
    
    // Getters and Setters
    public String getDriverId() { return driverId; }
    
    public void setDriverId(String driverId) {
        if (driverId == null || driverId.trim().isEmpty()) {
            throw new IllegalArgumentException("Driver ID cannot be empty");
        }
        this.driverId = driverId.trim();
    }
    
    public String getTrackId() { return trackId; }
    
    public void setTrackId(String trackId) {
        if (trackId == null || trackId.trim().isEmpty()) {
            throw new IllegalArgumentException("Track ID cannot be empty");
        }
        this.trackId = trackId.trim();
    }
    
    public int getPitStops() { return pitStops; }
    
    public void setPitStops(int pitStops) {
        if (pitStops < 0 || pitStops > 5) {
            throw new IllegalArgumentException("Pit stops must be 0-5");
        }
        this.pitStops = pitStops;
    }
    
    public String getTireCompound() { return tireCompound; }
    
    public void setTireCompound(String tireCompound) {
        String[] valid = {"Soft", "Medium", "Hard", "Intermediate", "Wet"};
        boolean isValid = false;
        for (String comp : valid) {
            if (comp.equalsIgnoreCase(tireCompound)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException("Invalid tire compound");
        }
        this.tireCompound = tireCompound;
    }
    
    public int getRiskLevel() { return riskLevel; }
    
    public void setRiskLevel(int riskLevel) {
        if (riskLevel < 1 || riskLevel > 10) {
            throw new IllegalArgumentException("Risk level must be 1-10");
        }
        this.riskLevel = riskLevel;
    }
    
    public double getEstimatedTime() { return estimatedTime; }
    
    public void setEstimatedTime(double estimatedTime) {
        if (estimatedTime < 0) {
            throw new IllegalArgumentException("Estimated time cannot be negative");
        }
        this.estimatedTime = estimatedTime;
    }
    
    public String getStatus() { return status; }
    
    public void setStatus(String status) {
        String[] valid = {"Draft", "Approved", "Rejected", "Archived"};
        boolean isValid = false;
        for (String s : valid) {
            if (s.equalsIgnoreCase(status)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }
    
    // Calculate estimated time
    public void calculateEstimatedTime(double baseLapTime, int laps) {
        double pitLoss = pitStops * 25; // 25 seconds per pit stop
        double raceTime = baseLapTime * laps;
        double riskBonus = (5 - riskLevel) * 0.5; // Higher risk = potentially faster
        
        this.estimatedTime = raceTime + pitLoss - riskBonus;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s at %s (%d stops, Risk: %d)", 
            name, driverId, trackId, pitStops, riskLevel);
    }
    
    @Override
    public String getType() {
        return "STRATEGY";
    }
    
    // For table display
    public Object[] toTableRow() {
        return new Object[]{
            id,
            name,
            driverId,
            trackId,
            pitStops,
            tireCompound,
            riskLevel,
            String.format("%.1f", estimatedTime),
            status,
            year
        };
    }
}
