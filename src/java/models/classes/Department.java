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
public class Department {
    private int departamento_id;
    private String departamento_nombre;
    private String campus_nombre;

    public Department(int departamento_id, String departamento_nombre, String campus_nombre) {
        this.departamento_id = departamento_id;
        this.departamento_nombre = departamento_nombre;
        this.campus_nombre = campus_nombre;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public String getDepartamento_nombre() {
        return departamento_nombre;
    }

    public String getCampus_nombre() {
        return campus_nombre;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public void setDepartamento_nombre(String departamento_nombre) {
        this.departamento_nombre = departamento_nombre;
    }

    public void setCampus_nombre(String campus_nombre) {
        this.campus_nombre = campus_nombre;
    }
    
    
}
