/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.classes;

/**
 *
 * @author victo
 */
public class Classes {
    private int clase_id;
    private String materia_nombre;
    private String dias_grupo_grupo;
    private String hora_clase_inicio;
    private String hora_clase_fin;
    private String acciones;

    public Classes(int clase_id, String materia_nombre, String dias_grupo_grupo, String hora_clase_inicio, String hora_clase_fin, String acciones) {
        this.clase_id = clase_id;
        this.materia_nombre = materia_nombre;
        this.dias_grupo_grupo = dias_grupo_grupo;
        this.hora_clase_inicio = hora_clase_inicio;
        this.hora_clase_fin = hora_clase_fin;
        this.acciones = acciones;
    }

    public int getClase_id() {
        return clase_id;
    }

    public String getMateria_nombre() {
        return materia_nombre;
    }

    public String getDias_grupo_grupo() {
        return dias_grupo_grupo;
    }

    public String getHora_clase_inicio() {
        return hora_clase_inicio;
    }

    public String getHora_clase_fin() {
        return hora_clase_fin;
    }

    public String getAcciones() {
        return acciones;
    }
    

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public void setMateria_nombre(String materia_nombre) {
        this.materia_nombre = materia_nombre;
    }

    public void setDias_grupo_grupo(String dias_grupo_grupo) {
        this.dias_grupo_grupo = dias_grupo_grupo;
    }

    public void setHora_clase_inicio(String hora_clase_inicio) {
        this.hora_clase_inicio = hora_clase_inicio;
    }

    public void setHora_clase_fin(String hora_clase_fin) {
        this.hora_clase_fin = hora_clase_fin;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
    
    
}
