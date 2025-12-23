/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/racecraft/model/Driver.java
package racecraft.model;

public class Driver extends RacingItem {
    private String nationality;
    private String team;
    private int driverNumber;
    private double skillRating; // 1-100
    private int wins;
    private int podiums;
    
    public Driver(String id, String name, int year, String nationality) {
        super(id, name, year);
        setNationality(nationality);
        this.skillRating = 75.0;
        this.driverNumber = 1;
        this.team = "Independent";
    }
    
    // Getters and Setters
    public String getNationality() { return nationality; }
    
    public void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be empty");
        }
        this.nationality = nationality.trim();
    }
    
    public String getTeam() { return team; }
    
    public void setTeam(String team) {
        this.team = team != null ? team.trim() : "Independent";
    }
    
    public int getDriverNumber() { return driverNumber; }
    
    public void setDriverNumber(int driverNumber) {
        if (driverNumber < 1 || driverNumber > 99) {
            throw new IllegalArgumentException("Driver number must be 1-99");
        }
        this.driverNumber = driverNumber;
    }
    
    public double getSkillRating() { return skillRating; }
    
    public void setSkillRating(double skillRating) {
        if (skillRating < 0 || skillRating > 100) {
            throw new IllegalArgumentException("Skill rating must be 0-100");
        }
        this.skillRating = skillRating;
    }
    
    public int getWins() { return wins; }
    
    public void setWins(int wins) {
        if (wins < 0) {
            throw new IllegalArgumentException("Wins cannot be negative");
        }
        this.wins = wins;
    }
    
    public int getPodiums() { return podiums; }
    
    public void setPodiums(int podiums) {
        if (podiums < 0) {
            throw new IllegalArgumentException("Podiums cannot be negative");
        }
        this.podiums = podiums;
    }
    
    // Statistics
    public double getWinRate(int racesEntered) {
        if (racesEntered <= 0) return 0;
        return (wins * 100.0) / racesEntered;
    }
    
    @Override
    public String toString() {
        return String.format("#%02d %s (%s) - %s", 
            driverNumber, name, nationality, team);
    }
    
    @Override
    public String getType() {
        return "DRIVER";
    }
    
    // For table display
    public Object[] toTableRow() {
        return new Object[]{
            id,
            name,
            nationality,
            team,
            driverNumber,
            String.format("%.1f", skillRating),
            wins,
            podiums,
            year
        };
    }
}