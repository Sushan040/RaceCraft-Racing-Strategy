/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package racecraft.model;

public class Driver {
    private int id;
    private String name;
    private int age;
    private int experience;
    private int wins;

    public Driver(int id, String name, int age, int experience, int wins) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.wins = wins;
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

    public int getAge() { 
        return age; 
    }

    public void setAge(int age) { 
        this.age = age; 
    }

    public int getExperience() { 
        return experience; 
    }

    public void setExperience(int experience) { 
        this.experience = experience; 
    }

    public int getWins() { 
        return wins; 
    }

    public void setWins(int wins) { 
        this.wins = wins; 
    }

}

