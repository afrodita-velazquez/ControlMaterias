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
public class Subject {
    private int materia_id;
    private String materia_clave;
    private String materia_nombre;
    private int materia_creditos;
    private String carrera_nombre;
    private String acciones;

    public Subject(int materia_id, String materia_clave, String materia_nombre, int materia_creditos, String carrera_nombre, String acciones) {
        this.materia_id = materia_id;
        this.materia_clave = materia_clave;
        this.materia_nombre = materia_nombre;
        this.materia_creditos = materia_creditos;
        this.carrera_nombre = carrera_nombre;
        this.acciones = acciones;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public String getMateria_clave() {
        return materia_clave;
    }

    public String getMateria_nombre() {
        return materia_nombre;
    }

    public int getMateria_creditos() {
        return materia_creditos;
    }

    public String getCarrera_nombre() {
        return carrera_nombre;
    }

    public String getAcciones() {
        return acciones;
    }
    
   
    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }

    public void setMateria_clave(String materia_clave) {
        this.materia_clave = materia_clave;
    }

    public void setMateria_nombre(String materia_nombre) {
        this.materia_nombre = materia_nombre;
    }

    public void setMateria_creditos(int materia_creditos) {
        this.materia_creditos = materia_creditos;
    }

    public void setCarrera_nombre(String carrera_nombre) {
        this.carrera_nombre = carrera_nombre;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
}
