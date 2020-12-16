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
public class User {
    private int usuario_id;
    private String persona_nombre;
    private String persona_appat;
    private String persona_apmat;
    private String persona_tel;
    private String persona_email;
    private String grupo_usuario_descripcion;
    private String departamento_nombre;
    private String acciones;

    public User(int usuario_id, String persona_nombre, String persona_appat, String persona_apmat, String persona_tel, String persona_email, String grupo_usuario_descripcion, String departamento_nombre, String acciones) {
        this.usuario_id = usuario_id;
        this.persona_nombre = persona_nombre;
        this.persona_appat = persona_appat;
        this.persona_apmat = persona_apmat;
        this.persona_tel = persona_tel;
        this.persona_email = persona_email;
        this.grupo_usuario_descripcion = grupo_usuario_descripcion;
        this.departamento_nombre = departamento_nombre;
        this.acciones = acciones;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public String getPersona_nombre() {
        return persona_nombre;
    }

    public String getPersona_appat() {
        return persona_appat;
    }

    public String getPersona_apmat() {
        return persona_apmat;
    }

    public String getPersona_tel() {
        return persona_tel;
    }

    public String getPersona_email() {
        return persona_email;
    }

    public String getGrupo_usuario_descripcion() {
        return grupo_usuario_descripcion;
    }

    public String getDepartamento_nombre() {
        return departamento_nombre;
    }

    public String getAcciones() {
        return acciones;
    }
    
    

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setPersona_nombre(String persona_nombre) {
        this.persona_nombre = persona_nombre;
    }

    public void setPersona_appat(String persona_appat) {
        this.persona_appat = persona_appat;
    }

    public void setPersona_apmat(String persona_apmat) {
        this.persona_apmat = persona_apmat;
    }

    public void setPersona_tel(String persona_tel) {
        this.persona_tel = persona_tel;
    }

    public void setPersona_email(String persona_email) {
        this.persona_email = persona_email;
    }

    public void setGrupo_usuario_descripcion(String grupo_usuario_descripcion) {
        this.grupo_usuario_descripcion = grupo_usuario_descripcion;
    }

    public void setDepartamento_nombre(String departamento_nombre) {
        this.departamento_nombre = departamento_nombre;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
    
    
}
