/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.classes.Carreers;
import models.classes.Department;

/**
 *
 * 
 */
public class RegisterCarreers_Model {

    Connection con;
    boolean connectionStatus;

    public RegisterCarreers_Model() {
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

    public ArrayList<Department> getDepartments() {
        ArrayList<Department> DepartmentList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	 departamento.departamento_id,\n"
                    + "    departamento.departamento_nombre,\n"
                    + "    campus.campus_nombre\n"
                    + "FROM \n"
                    + "	 departamento\n"
                    + "    left join\n"
                    + "    campus\n"
                    + "		on departamento.campus_id = campus.campus_id;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DepartmentList.add(new Department(
                        rs.getInt("departamento_id"),
                        rs.getString("departamento_nombre"),
                        rs.getString("campus_nombre")
                ));
            }

        } catch (SQLException ex) {

        }
        return DepartmentList;
    }
    
    public int insertCarrees(String carreras_clave, String carreras_nombre, int departamento_id, PrintWriter out) {
        int rows_number = 0;
        try {
            String query = ""
                    +   "insert into \n" +
                        "	carreras(carreras.carreras_clave, carreras.carreras_nombre, carreras.departamento_id)\n" +
                        "values\n" +
                        "	('" + carreras_clave + "', '" + carreras_nombre + "', " + departamento_id + ")";
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);

        } catch (SQLException ex) {

        }
        return rows_number;
    }

    public ArrayList<Carreers> getInsertedCarreers() {
        ArrayList<Carreers> CarreerList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n" +
                    "	 carreras.carreras_id,\n" +
                    "    carreras_clave,\n" +
                    "    carreras.carreras_nombre,\n" +
                    "    departamento.departamento_nombre\n" +
                    "FROM \n" +
                    "	 carreras\n" +
                    "    left join\n" +
                    "    departamento\n" +
                    "		on carreras.departamento_id = departamento.departamento_id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CarreerList.add(new Carreers(
                        rs.getInt("carreras_id"),
                        rs.getString("carreras_clave"),
                        rs.getString("carreras_nombre"),
                        rs.getString("departamento_nombre")
                ));
            }

        } catch (SQLException ex) {

        }
        return CarreerList;
    }
}
