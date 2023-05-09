/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nadoura kaaboura
 */
public class Coupon {
    private int id;
    private String code;
    private int pourcentageReductin;

    public Coupon() {
    }

    public Coupon(String code, int pourcentageReductin) {
        this.code = code;
        this.pourcentageReductin = pourcentageReductin;
    }

    public Coupon(int id, String code, int pourcentageReductin) {
        this.id = id;
        this.code = code;
        this.pourcentageReductin = pourcentageReductin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPourcentageReductin() {
        return pourcentageReductin;
    }

    public void setPourcentageReductin(int pourcentageReductin) {
        this.pourcentageReductin = pourcentageReductin;
    }
    
}
