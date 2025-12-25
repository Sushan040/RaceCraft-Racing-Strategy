/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.model;

public class Strategy {
    private String strategyName;
    private String driverName;
    private int raceYear;
    private int pitStops;

    public Strategy(String strategyName, String driverName, int raceYear, int pitStops) {
        this.strategyName = strategyName;
        this.driverName = driverName;
        this.raceYear = raceYear;
        this.pitStops = pitStops;
    }

    // Getters
    public String getStrategyName() { return strategyName; }
    public String getDriverName() { return driverName; }
    public int getRaceYear() { return raceYear; }
    public int getPitStops() { return pitStops; }

    // Setters (optional, for Edit functionality)
    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setRaceYear(int raceYear) { this.raceYear = raceYear; }
    public void setPitStops(int pitStops) { this.pitStops = pitStops; }
}
