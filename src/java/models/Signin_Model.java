/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.classes.User;

/**
 *
 *
 */
public class Signin_Model {

    Connection con;
    boolean connectionStatus;

    public Signin_Model() {
        try {
            con = dbconnection.initializeDatabase();
            connectionStatus = true;
        } catch (SQLException ex) {
            connectionStatus = false;
            Logger.getLogger(Login_Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            connectionStatus = false;
            Logger.getLogger(Login_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertUser(String persona_nombre, String persona_appat, String persona_apmat, String persona_tel, String persona_email, String usuario_login, String usuario_pwd, int grupo_usuario_id, int departamento_id) {
        int rows_number = 0;
        try {
            con.setAutoCommit(false);
            String query = ""
                    + "insert into \n"
                    + "	persona(persona_nombre, persona_appat, persona_apmat, persona_tel, persona_email)\n"
                    + "values\n"
                    + "	('" + persona_nombre + "', '" + persona_appat + "', '" + persona_apmat + "', '" + persona_tel + "', '" + persona_email + "')";

            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            Statement st = con.createStatement();
            query = ""
                    + "insert into \n"
                    + "	usuario(usuario_login, usuario_pwd, persona_id, grupo_usuario_id, departamento_id)\n"
                    + "values\n"
                    + "	('" + usuario_login + "', MD5('" + usuario_pwd + "'), " + generatedKey + ", " + grupo_usuario_id + ", " + departamento_id + ")";
            rows_number = st.executeUpdate(query);
            con.commit();

        } catch (SQLException e) {
            try {
                // in case of exception, rollback the transaction
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Signin_Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows_number;
    }

    public ArrayList<User> getInsetedUsers(int grupo_usuario_id, int departamento_id) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "    usuario_id,\n"
                    + "    persona_nombre,\n"
                    + "    persona_appat,\n"
                    + "    persona_apmat,\n"
                    + "    persona_tel,\n"
                    + "    persona_email,\n"
                    + "    grupo_usuario_descripcion,\n"
                    + "    departamento_nombre\n"
                    + "FROM \n"
                    + "	usuario\n"
                    + "    left join\n"
                    + "    persona\n"
                    + "		on usuario.persona_id = persona.persona_id\n"
                    + "	left join\n"
                    + "    grupo_usuario\n"
                    + "		on usuario.grupo_usuario_id = grupo_usuario.grupo_usuario_id\n"
                    + "	left join\n"
                    + "    departamento\n"
                    + "		on usuario.departamento_id = departamento.departamento_id";
            if (grupo_usuario_id == 2) {
                query += "\n where usuario.grupo_usuario_id = 3";
                query += "\n and usuario.departamento_id = " + departamento_id;
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("usuario_id"),
                        rs.getString("persona_nombre"),
                        rs.getString("persona_appat"),
                        rs.getString("persona_apmat"),
                        rs.getString("persona_tel"),
                        rs.getString("persona_email"),
                        rs.getString("grupo_usuario_descripcion"),
                        rs.getString("departamento_nombre"),
                        "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Editar\" onclick=\"openModal(" + rs.getInt("usuario_id") + ")\"><i class=\"fas fa-pen-square\"></i></button>"
                ));
            }

        } catch (SQLException ex) {

        }
        return userList;
    }
}
