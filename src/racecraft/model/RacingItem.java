/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/model/RacingItem.java
package racecraft.model;

import java.io.Serializable;

public abstract class RacingItem implements Serializable, Comparable<RacingItem> {
    protected String id;
    protected String name;
    protected int year;
    protected String description;
    
    public RacingItem(String id, String name, int year) {
        setId(id);
        setName(name);
        setYear(year);
        this.description = "";
    }
    
    // Getters and Setters with validation
    public String getId() { return id; }
    
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.id = id.trim().toUpperCase();
    }
    
    public String getName() { return name; }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }
    
    public int getYear() { return year; }
    
    public void setYear(int year) {
        if (year < 1950 || year > 2024) {
            throw new IllegalArgumentException("Year must be between 1950-2024");
        }
        this.year = year;
    }
    
    public String getDescription() { return description; }
    
    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }
    
    @Override
    public abstract String toString();
    
    @Override
    public int compareTo(RacingItem other) {
        return this.name.compareToIgnoreCase(other.name);
    }
    
    public abstract String getType();
}
