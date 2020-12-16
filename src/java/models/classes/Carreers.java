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
public class Carreers {

    private int carreras_id;
    private String carreras_clave;
    private String carreras_nombre;
    private String departamento_nombre;

    public Carreers(int carreras_id, String carreras_clave, String carreras_nombre, String departamento_nombre) {
        this.carreras_id = carreras_id;
        this.carreras_clave = carreras_clave;
        this.carreras_nombre = carreras_nombre;
        this.departamento_nombre = departamento_nombre;
    }

    public int getCarreras_id() {
        return carreras_id;
    }
    
    public String getCarreras_clave() {
        return carreras_clave;
    }

    public String getCarreras_nombre() {
        return carreras_nombre;
    }

    public String getDepartamento_nombre() {
        return departamento_nombre;
    }

    public void setCarreras_id(int carreras_id) {
        this.carreras_id = carreras_id;
    }

    public void setCarreras_clave(String carreras_clave) {
        this.carreras_clave = carreras_clave;
    }

    public void setCarreras_nombre(String carreras_nombre) {
        this.carreras_nombre = carreras_nombre;
    }

    public void setDepartamento_nombre(String departamento_nombre) {
        this.departamento_nombre = departamento_nombre;
    }

}
