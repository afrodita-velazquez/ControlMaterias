/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.classes;

/**
 *
 * 
 */
public class Campus {
    private int campus_id;
    private String campus_nombre;
    private String campus_direccion;
    private String campus_telefono;

    public Campus(int campus_id, String campus_nombre, String campus_direccion, String campus_telefono) {
        this.campus_id = campus_id;
        this.campus_nombre = campus_nombre;
        this.campus_direccion = campus_direccion;
        this.campus_telefono = campus_telefono;
    }

    public int getCampus_id() {
        return campus_id;
    }

    public String getCampus_nombre() {
        return campus_nombre;
    }

    public String getCampus_direccion() {
        return campus_direccion;
    }

    public String getCampus_telefono() {
        return campus_telefono;
    }

    public void setCampus_id(int campus_id) {
        this.campus_id = campus_id;
    }

    public void setCampus_nombre(String campus_nombre) {
        this.campus_nombre = campus_nombre;
    }

    public void setCampus_direccion(String campus_direccion) {
        this.campus_direccion = campus_direccion;
    }

    public void setCampus_telefono(String campus_telefono) {
        this.campus_telefono = campus_telefono;
    }
    
    
}
