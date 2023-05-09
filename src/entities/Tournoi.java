/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author omare
 */
public class Tournoi {
    private double id;
    private String name;
    private double nbr_equipe;
    private double nbr_Jequipe;
    private double nbr_Terrain;
    private String date;
    private String etat;
    private List<Matche> matches;

    public Tournoi() {
        this.matches = new ArrayList<>();
    }

    public Tournoi(String name, double nbr_equipe, double nbr_Jequipe, double nbr_Terrain, String date, String etat, List<Matche> matches) {
        this.name = name;
        this.nbr_equipe = nbr_equipe;
        this.nbr_Jequipe = nbr_Jequipe;
        this.nbr_Terrain = nbr_Terrain;
        this.date = date;
        this.etat = etat;
        this.matches = matches;
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

    public double getNbr_equipe() {
        return nbr_equipe;
    }

    public void setNbr_equipe(double nbr_equipe) {
        this.nbr_equipe = nbr_equipe;
    }

    public double getNbr_Jequipe() {
        return nbr_Jequipe;
    }

    public void setNbr_Jequipe(double nbr_Jequipe) {
        this.nbr_Jequipe = nbr_Jequipe;
    }

    public double getNbr_Terrain() {
        return nbr_Terrain;
    }

    public void setNbr_Terrain(double nbr_Terrain) {
        this.nbr_Terrain = nbr_Terrain;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<Matche> getMatches() {
        return matches;
    }

    public void setMatches(List<Matche> matches) {
        this.matches = matches;
    }

    


    
    
    
    
}
