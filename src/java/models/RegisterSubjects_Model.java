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
import models.classes.Carreers;
import models.classes.Subject;

/**
 *
 *
 */
public class RegisterSubjects_Model {

    Connection con;
    boolean connectionStatus;

    public RegisterSubjects_Model() {
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

    public ArrayList<Carreers> getCarreers(int departamento_id) {
        ArrayList<Carreers> CarreerList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	   carreras.carreras_id,\n"
                    + "    carreras.carreras_clave,\n"
                    + "    carreras.carreras_nombre,\n"
                    + "    departamento.departamento_nombre\n"
                    + "FROM \n"
                    + "	carreras\n"
                    + "    left join\n"
                    + "    departamento\n"
                    + "		on carreras.departamento_id = departamento.departamento_id\n"
                    + "WHERE \n"
                    + "    carreras.departamento_id = " + departamento_id;
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

    public ArrayList<Subject> getInsertedSubjects() {
        ArrayList<Subject> SubjectList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	   materia.materia_id,\n"
                    + "    materia.materia_clave,\n"
                    + "    materia.materia_nombre,\n"
                    + "    materia.materia_creditos,\n"
                    + "    carreras.carreras_nombre\n"
                    + "FROM \n"
                    + "	materia\n"
                    + "    left join\n"
                    + "    carreras\n"
                    + "		on materia.carreras_id = carreras.carreras_id;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                SubjectList.add(new Subject(
                        rs.getInt("materia_id"),
                        rs.getString("materia_clave"),
                        rs.getString("materia_nombre"),
                        rs.getInt("materia_creditos"),
                        rs.getString("carreras_nombre"),
                        "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Editar\" onclick=\"openModal(" + rs.getInt("materia_id") + ")\"><i class=\"fas fa-pen-square\"></i></button>"
                ));
            }

        } catch (SQLException ex) {

        }
        return SubjectList;
    }
    
    public ArrayList<Subject> getInsertedSubjectsByCarreerId(String carrera_id) {
        ArrayList<Subject> SubjectList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	   materia.materia_id,\n"
                    + "    materia.materia_clave,\n"
                    + "    materia.materia_nombre,\n"
                    + "    materia.materia_creditos,\n"
                    + "    carreras.carreras_nombre\n"
                    + "FROM \n"
                    + "	materia\n"
                    + "    left join\n"
                    + "    carreras\n"
                    + "		on materia.carreras_id = carreras.carreras_id\n"
                    + "where\n"
                    + "     materia.carreras_id = " + carrera_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                SubjectList.add(new Subject(
                        rs.getInt("materia_id"),
                        rs.getString("materia_clave"),
                        rs.getString("materia_nombre"),
                        rs.getInt("materia_creditos"),
                        rs.getString("carreras_nombre"),
                        "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Editar\" onclick=\"openModal(" + rs.getInt("materia_id") + ")\"><i class=\"fas fa-pen-square\"></i></button>"
                ));
            }

        } catch (SQLException ex) {

        }
        return SubjectList;
    }

    public ArrayList<Subject> getInsertedSubjectsById(int materia_id) {
        ArrayList<Subject> SubjectList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	   materia.materia_id,\n"
                    + "    materia.materia_clave,\n"
                    + "    materia.materia_nombre,\n"
                    + "    materia.materia_creditos,\n"
                    + "    carreras.carreras_nombre\n"
                    + "FROM \n"
                    + "	materia\n"
                    + "    left join\n"
                    + "    carreras\n"
                    + "		on materia.carreras_id = carreras.carreras_id\n"
                    + "where\n"
                    + "    materia.materia_id = " + materia_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                SubjectList.add(new Subject(
                        rs.getInt("materia_id"),
                        rs.getString("materia_clave"),
                        rs.getString("materia_nombre"),
                        rs.getInt("materia_creditos"),
                        rs.getString("carreras_nombre"),
                        "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Editar\" onclick=\"openModal(" + rs.getInt("materia_id") + ")\"><i class=\"fas fa-pen-square\"></i></button>"
                ));
            }

        } catch (SQLException ex) {

        }
        return SubjectList;
    }

    public int insertSubject(String materia_clave, String materia_nombre, int materia_creditos, int carreras_id) {
        int rows_number = 0;
        try {
            String query = ""
                    + "insert into \n"
                    + "	materia(materia.materia_clave, materia.materia_nombre, materia.materia_creditos, materia.carreras_id)\n"
                    + "values\n"
                    + "	('" + materia_clave + "', '" + materia_nombre + "', " + materia_creditos + ", " + carreras_id + ")";
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);

        } catch (SQLException ex) {

        }
        return rows_number;
    }

    public int updateSubject(int materia_id, String materia_clave, String materia_nombre, int materia_creditos, int carreras_id) {
        int rows_number = 0;
        try {
            String query = ""
                    + "update\n"
                    + "    materia\n"
                    + "set\n"
                    + "	materia.materia_clave = '" + materia_clave + "',\n"
                    + "    materia.materia_nombre = '" + materia_nombre + "',\n"
                    + "    materia.materia_creditos = " + materia_creditos + ",\n"
                    + "    materia.carreras_id = " + carreras_id + "\n"
                    + "where\n"
                    + "	materia.materia_id = " + materia_id;
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);

        } catch (SQLException ex) {

        }
        return rows_number;
    }
}
