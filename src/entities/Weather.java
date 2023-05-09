/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author omare
 */
public class Weather {
    private String location;
    private String description;
    private double temperature;
    private double windSpeed;
    private int humidity;

    public Weather() {
    }

    public Weather(String location, String description, double temperature, double windSpeed, int humidity) {
        this.location = location;
        this.description = description;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Weather{" + "location=" + location + ", description=" + description + ", temperature=" + temperature + ", windSpeed=" + windSpeed + ", humidity=" + humidity + '}';
    }
    
    
}


