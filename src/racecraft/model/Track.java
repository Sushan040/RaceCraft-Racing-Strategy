/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/model/Track.java
package racecraft.model;

public class Track extends RacingItem {
    private String country;
    private String city;
    private double length; // in km
    private int corners;
    private double lapRecord; // in seconds
    private String recordHolder;
    private int laps;
    
    public Track(String id, String name, int year, String country) {
        super(id, name, year);
        setCountry(country);
        this.length = 5.0;
        this.corners = 10;
        this.laps = 50;
        this.city = "Unknown";
    }
    
    // Getters and Setters
    public String getCountry() { return country; }
    
    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be empty");
        }
        this.country = country.trim();
    }
    
    public String getCity() { return city; }
    
    public void setCity(String city) {
        this.city = city != null ? city.trim() : "Unknown";
    }
    
    public double getLength() { return length; }
    
    public void setLength(double length) {
        if (length <= 0 || length > 20) {
            throw new IllegalArgumentException("Length must be 0-20 km");
        }
        this.length = length;
    }
    
    public int getCorners() { return corners; }
    
    public void setCorners(int corners) {
        if (corners < 1) {
            throw new IllegalArgumentException("Track must have at least 1 corner");
        }
        this.corners = corners;
    }
    
    public double getLapRecord() { return lapRecord; }
    
    public void setLapRecord(double lapRecord) {
        if (lapRecord < 0) {
            throw new IllegalArgumentException("Lap record cannot be negative");
        }
        this.lapRecord = lapRecord;
    }
    
    public String getRecordHolder() { return recordHolder; }
    
    public void setRecordHolder(String recordHolder) {
        this.recordHolder = recordHolder != null ? recordHolder.trim() : "Unknown";
    }
    
    public int getLaps() { return laps; }
    
    public void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException("Laps must be at least 1");
        }
        this.laps = laps;
    }
    
    // Calculate race distance
    public double getRaceDistance() {
        return length * laps;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s, %s (%.3f km)", 
            name, city, country, length);
    }
    
    @Override
    public String getType() {
        return "TRACK";
    }
    
    // For table display
    public Object[] toTableRow() {
        return new Object[]{
            id,
            name,
            country,
            city,
            String.format("%.3f", length),
            corners,
            laps,
            lapRecord > 0 ? String.format("%.3f", lapRecord) : "N/A",
            recordHolder != null ? recordHolder : "N/A",
            year
        };
    }
}
