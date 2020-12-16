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
import models.classes.Classes;
import models.classes.Days_Group;
import models.classes.Hour;
import models.classes.Subject;

/**
 *
 * @author victo
 */
public class RegisterClasses_Model {

    Connection con;
    boolean connectionStatus;

    public RegisterClasses_Model() {
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

    public ArrayList<Subject> getMaterias() {
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
                        ""
                ));
            }

        } catch (SQLException ex) {

        }
        return SubjectList;
    }

    public ArrayList<Days_Group> getDias() {
        ArrayList<Days_Group> DaysList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT * FROM materias.dias_grupo;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DaysList.add(
                        new Days_Group(
                                rs.getInt("dias_grupo_id"),
                                rs.getString("dias_grupo_grupo")
                        ));
            }

        } catch (SQLException ex) {

        }
        return DaysList;
    }

    public ArrayList<Hour> getHoras() {
        ArrayList<Hour> HourList = new ArrayList<>();
        try {
            String query = "SELECT * FROM materias.hora_clase;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                HourList.add(
                        new Hour(
                                rs.getInt("hora_clase_id"),
                                rs.getString("hora_clase_inicio"),
                                rs.getString("hora_clase_fin")
                        ));
            }

        } catch (SQLException ex) {

        }
        return HourList;
    }

    public int insertCarrees(int materia_id, int dias_grupo_id, int hora_clase_id) {
        int rows_number = 0;
        try {
            String query = "insert into clase(clase.materia_id, clase.dias_grupo_id, clase.hora_clase_id) values (" + materia_id + ", " + dias_grupo_id + ", " + hora_clase_id + ")";
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);
        } catch (SQLException ex) {

        }
        return rows_number;
    }

    public int insertSchedule(int clase_id, int usuario_id) {
        int rows_number = 0;
        try {
            String query = "insert into horario_profesor (horario_profesor.clase_id, horario_profesor.usuario_id) values (" + clase_id + ", " + usuario_id + ");";
            Statement st = con.createStatement();
            rows_number = st.executeUpdate(query);
        } catch (SQLException ex) {

        }
        return rows_number;
    }

    public ArrayList<Classes> getInsertedClasses(int departamento_id) {
        ArrayList<Classes> ClassesList = new ArrayList<>();
        try {
            String query = ""
                    + "select \n"
                    + "	 clase.clase_id,\n"
                    + "    materia.materia_nombre,\n"
                    + "    dias_grupo.dias_grupo_grupo,\n"
                    + "    hora_clase.hora_clase_inicio,\n"
                    + "    hora_clase.hora_clase_fin\n"
                    + "from \n"
                    + "	clase\n"
                    + "    left join\n"
                    + "    materia\n"
                    + "		on clase.materia_id = materia.materia_id\n"
                    + "	left join\n"
                    + "    dias_grupo\n"
                    + "		on clase.dias_grupo_id = dias_grupo.dias_grupo_id\n"
                    + "	left join\n"
                    + "	hora_clase\n"
                    + "		on clase.hora_clase_id = hora_clase.hora_clase_id\n"
                    + "	left join\n"
                    + "    carreras\n"
                    + "		on materia.carreras_id = carreras.carreras_id\n"
                    + "where\n"
                    + "	carreras.departamento_id = " + departamento_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ClassesList.add(new Classes(
                        rs.getInt("clase_id"),
                        rs.getString("materia_nombre"),
                        rs.getString("dias_grupo_grupo"),
                        rs.getString("hora_clase_inicio"),
                        rs.getString("hora_clase_fin"),
                        ""
                ));
            }

        } catch (SQLException ex) {

        }
        return ClassesList;
    }

    public ArrayList<Classes> getInsertedScheduleByUserId(int usuario_id) {
        ArrayList<Classes> ClassesList = new ArrayList<>();
        try {
            String query = ""
                    + "SELECT \n"
                    + "	 horario_profesor.horario_profesor_id,\n"
                    + "    materia.materia_nombre,\n"
                    + "    dias_grupo.dias_grupo_grupo,\n"
                    + "    hora_clase.hora_clase_inicio,\n"
                    + "    hora_clase.hora_clase_fin\n"
                    + "FROM \n"
                    + "	horario_profesor\n"
                    + "    left join\n"
                    + "    clase\n"
                    + "		on horario_profesor.clase_id = clase.clase_id\n"
                    + "	left join\n"
                    + "    materia\n"
                    + "		on clase.materia_id = materia.materia_id\n"
                    + "	left join\n"
                    + "    dias_grupo\n"
                    + "		on clase.dias_grupo_id = dias_grupo.dias_grupo_id\n"
                    + "	left join\n"
                    + "	hora_clase\n"
                    + "		on clase.hora_clase_id = hora_clase.hora_clase_id\n"
                    + "	left join\n"
                    + "    carreras\n"
                    + "		on materia.carreras_id = carreras.carreras_id\n"
                    + "where\n"
                    + "	horario_profesor.usuario_id = " + usuario_id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ClassesList.add(new Classes(
                        rs.getInt("horario_profesor_id"),
                        rs.getString("materia_nombre"),
                        rs.getString("dias_grupo_grupo"),
                        rs.getString("hora_clase_inicio"),
                        rs.getString("hora_clase_fin"),
                        "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Editar\" onclick=\"openModal(" + rs.getInt("horario_profesor_id") + ")\"><i class=\"fas fa-pen-square\"></i></button>"
                ));
            }

        } catch (SQLException ex) {

        }
        return ClassesList;
    }
}
