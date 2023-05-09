/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author omare
 */
public class Matche {
    private double id;
    private String name;
    private double Jmax;
    private String date;
    private String time;
    private String etat;
    private List<String> team1;
    private List<String> team2;
    private String[] team1t;
    private String[] team2t;
    private Terrain terrain;
    
    public Matche(){
    }

    public Matche(String name, int Jmax, String date, String time, String etat) {
        this.name = name;
        this.Jmax = Jmax;
        this.date = date;
        this.time = time;
        this.etat = etat;
    }

    public Matche(double id, String name, double Jmax, String date, String time, String etat, List<String> team1, List<String> team2) {
        this.id = id;
        this.name = name;
        this.Jmax = Jmax;
        this.date = date;
        this.time = time;
        this.etat = etat;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Matche(String name, double Jmax, String date, String time, String etat, Terrain terrain) {
        this.name = name;
        this.Jmax = Jmax;
        this.date = date;
        this.time = time;
        this.etat = etat;
        this.terrain = terrain;
    }
    
    
    

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getJmax() {
        return Jmax;
    }

    public void setJmax(double Jmax) {
        this.Jmax = Jmax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<String> getTeam1() {
        return team1;
    }

    public void setTeam1(List<String> team1) {
        this.team1 = team1;
    }

    public List<String> getTeam2() {
        return team2;
    }

    public void setTeam2(List<String> team2) {
        this.team2 = team2;
    }

    public String[] getTeam1t() {
        return team1t;
    }

    public void setTeam1t(String[] team1t) {
        this.team1t = team1t;
    }

    public String[] getTeam2t() {
        return team2t;
    }

    public void setTeam2t(String[] team2t) {
        this.team2t = team2t;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    
    

    
    

    @Override
    public String toString() {
        return "Matche{" + "id=" + id + ", name=" + name + ", Jmax=" + Jmax + ", date=" + date + ", time=" + time + ", etat=" + etat + ", team1=" + team1 + ", team2=" + team2 + ", team1t=" + team1t + ", team2t=" + team2t + '}';
    }
    

    
    
    
}