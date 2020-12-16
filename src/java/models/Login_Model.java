/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Login_Model {

    Connection con;
    boolean connectionStatus;

    public Login_Model() {
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

    public boolean getConnectionStatus() {
        return connectionStatus;
    }

    public String[] Login(String Usuario, String Contrasena) {
        String[] response = new String[8];
        try {
            String query = ""
                    + "SELECT\n"
                    + "	 usuario.usuario_id,\n"
                    + "    usuario.grupo_usuario_id,\n"
                    + "    persona.persona_nombre,\n"
                    + "    persona.persona_appat,\n"
                    + "    persona.persona_apmat,\n"
                    + "    usuario.departamento_id\n"
                    + "FROM \n"
                    + "	 usuario \n"
                    + "	 left join\n"
                    + "		persona\n"
                    + "			on usuario.persona_id = persona.persona_id\n"
                    + "WHERE \n"
                    + "	 usuario_login = '" + Usuario + "' \n"
                    + "AND \n"
                    + "	 usuario_pwd = MD5('" + Contrasena + "')";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                response[0] = "true";
                response[1] = "Login Correcto";
                response[2] = rs.getString("usuario_id");
                response[3] = rs.getString("grupo_usuario_id");
                response[4] = rs.getString("persona_nombre");
                response[5] = rs.getString("persona_appat");
                response[6] = rs.getString("persona_apmat");
                response[7] = rs.getString("departamento_id");
            } else {
                response[0] = "false";
                response[1] = "El usuario o contraseña son incorrectos";
            }

        } catch (SQLException ex) {
            response[0] = "false";
            response[1] = "Error de base de datos, inténtelo más tarde";
        }
        return response;
    }
}
