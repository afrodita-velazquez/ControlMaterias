/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Login_Model;
import models.classes.Menu;
import models.dbconnection;

/**
 *
 * 
 */
public class SessionHelper {

    Connection con;
    boolean connectionStatus;

    public SessionHelper() {
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

    public ArrayList<Menu> getSessionMenus(String grupo_usuario_id) {
        ArrayList<Menu> menus = new ArrayList<Menu>();
        try {
            String query = ""
                    + "select \n"
                    + "     menu.*\n"
                    + "from \n"
                    + "     menus_x_grupos\n"
                    + "     left join\n"
                    + "     menu\n"
                    + "         on menus_x_grupos.menu_id = menu.menu_id\n"
                    + "where\n"
                    + "     menus_x_grupos.grupo_usuario_id = " + grupo_usuario_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                menus.add(new Menu(rs.getString("menu_nombre"), rs.getString("menu_url"), rs.getString("menu_icono")));
            }
        } catch (SQLException ex) {

        }
        return menus;
    }
    
    public void Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(true).invalidate();
        response.sendRedirect("index");
    }
}
