/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Nadoura kaaboura
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nadoura kaaboura
 */
public class Type {

    
    private float id;
    private String description;
    private float price;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Description=" + description + ",  price=" + price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Type() {
    }

    public Type(float id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

  

    public String get(String price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  

}

