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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.classes.Campus;
import models.classes.Department;

/**
 *
 * 
 */
public class RegisterDepartment_Model {

    Connection con;
    boolean connectionStatus;

    public RegisterDepartment_Model() {
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

    public ArrayList<Campus> getCampus() {
        ArrayList<Campus> CampusList = new ArrayList<>();
        try {
            String query = "SELECT * FROM materias.campus;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CampusList.add(new Campus(
                        rs.getInt("campus_id"),
                        rs.getString("campus_nombre"),
                        rs.getString("campus_direccion"),
                        rs.getString("campus_telefono")
                ));
            }

        } catch (SQLException ex) {

        }
        return CampusList;
    }

    public int insertDepartment(String departamento_nombre, int campus_id) {
        int rows_number = 0;
        try {
            String query = "insert into departamento(departamento_nombre, campus_id) values ('" + departamento_nombre + "', " + campus_id + ")";
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);

        } catch (SQLException ex) {

        }
        return rows_number;
    }

    public ArrayList<Department> getInsertedDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        try {
            String query = ""
                    +
                    "SELECT \n" +
                    "	 departamento.departamento_id,\n" +
                    "    departamento.departamento_nombre,\n" +
                    "    campus.campus_nombre\n" +
                    "FROM \n" +
                    "	 departamento\n" +
                    "    left join\n" +
                    "    campus\n" +
                    "		on departamento.campus_id = campus.campus_id;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("departamento_id"),
                        rs.getString("departamento_nombre"),
                        rs.getString("campus_nombre")
                ));
            }

        } catch (SQLException ex) {

        }
        return departments;
    }
}
