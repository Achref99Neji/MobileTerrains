package entities;

import java.util.Date;

public class Abonnement {
    private int id;
    private double player;
    private String terrain;
    private float prixTot;

    public Abonnement(double player, String terrain, float prixTot, String startDate, String endDate) {
        this.player = player;
        this.terrain = terrain;
        this.prixTot = prixTot;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }
    private String startDate;
    private String endDate;

    public Abonnement() {
    }

    public Abonnement( double player, String terrain, String startDate, String endDate) {
        this.player = player;
        this.terrain = terrain;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public double getPlayer() {
        return player;
    }

    public void setPlayer(double player) {
        this.player = player;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
