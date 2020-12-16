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
public class Days_Group {
    private int dias_grupo_id;
    private String dias_grupo_grupo;

    public Days_Group(int dias_grupo_id, String dias_grupo_grupo) {
        this.dias_grupo_id = dias_grupo_id;
        this.dias_grupo_grupo = dias_grupo_grupo;
    }

    public int getDias_grupo_id() {
        return dias_grupo_id;
    }

    public String getDias_grupo_grupo() {
        return dias_grupo_grupo;
    }

    public void setDias_grupo_id(int dias_grupo_id) {
        this.dias_grupo_id = dias_grupo_id;
    }

    public void setDias_grupo_grupo(String dias_grupo_grupo) {
        this.dias_grupo_grupo = dias_grupo_grupo;
    }
    
    
}
