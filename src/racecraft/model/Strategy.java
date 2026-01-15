/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.model;

public class Strategy {
    private int id;
    private Driver driver;
    private Track track;
    private String tyreType;
    private int year;

    public Strategy(int id, Driver driver, Track track, String tyreType, int year) {
        this.id = id;
        this.driver = driver;
        this.track = track;
        this.tyreType = tyreType;
        this.year = year;
    }

    // Getters and Setters
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public Driver getDriver() { 
        return driver; 
    }

    public void setDriver(Driver driver) { 
        this.driver = driver; 
    }

    public Track getTrack() { 
        return track; 
    }

    public void setTrack(Track track) { 
        this.track = track; 
    }

    public String getTyreType() { 
        return tyreType; 
    }

    public void setTyreType(String tyreType) { 
        this.tyreType = tyreType; 
    }

    public int getYear() { 
        return year; 
    }

    public void setYear(int year) { 
        this.year = year; 
    }
}
