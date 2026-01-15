/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.model;

public class Track {
    private int id;
    private String name;
    private double length;
    private String difficulty;

    public Track(int id, String name, double length, String difficulty) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.difficulty = difficulty;
    }

    // Getters and Setters
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public double getLength() { 
        return length; 
    }

    public void setLength(double length) { 
        this.length = length; 
    }

    public String getDifficulty() { 
        return difficulty; 
    }

    public void setDifficulty(String difficulty) { 
        this.difficulty = difficulty; 
    }
}
