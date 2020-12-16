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
public class Hour {
    private int hora_clase_id;
    private String hora_clase_inicio;
    private String hora_clase_fin;

    public Hour(int hora_clase_id, String hora_clase_inicio, String hora_clase_fin) {
        this.hora_clase_id = hora_clase_id;
        this.hora_clase_inicio = hora_clase_inicio;
        this.hora_clase_fin = hora_clase_fin;
    }

    public int getHora_clase_id() {
        return hora_clase_id;
    }

    public String getHora_clase_inicio() {
        return hora_clase_inicio;
    }

    public String getHora_clase_fin() {
        return hora_clase_fin;
    }

    public void setHora_clase_id(int hora_clase_id) {
        this.hora_clase_id = hora_clase_id;
    }

    public void setHora_clase_inicio(String hora_clase_inicio) {
        this.hora_clase_inicio = hora_clase_inicio;
    }

    public void setHora_clase_fin(String hora_clase_fin) {
        this.hora_clase_fin = hora_clase_fin;
    }
    
    
}
