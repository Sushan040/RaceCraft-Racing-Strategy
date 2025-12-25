/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.model;

public class Track {
    private String trackName;
    private String country;

    public Track(String trackName, String country) {
        this.trackName = trackName;
        this.country = country;
    }

    public String getTrackName() {
        return trackName;
    }
}
