/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.classes;

import java.io.Serializable;

/**
 *
 * 
 */
public class Menu implements Serializable {
    private String menu_nombre;
    private String menu_url;
    private String menu_icono;

    public Menu(String menu_nombre, String menu_url, String menu_icono) {
        this.menu_nombre = menu_nombre;
        this.menu_url = menu_url;
        this.menu_icono = menu_icono;
    }

    public String getMenu_nombre() {
        return menu_nombre;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public String getMenu_icono() {
        return menu_icono;
    }

    public void setMenu_nombre(String menu_nombre) {
        this.menu_nombre = menu_nombre;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public void setMenu_icono(String menu_icono) {
        this.menu_icono = menu_icono;
    }
    
    
}
