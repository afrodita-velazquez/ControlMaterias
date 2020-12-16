/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.google.gson.Gson;
import helpers.SessionHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RegisterDepartment_Model;
import models.classes.Department;
import models.classes.Response;

/**
 *
 * 
 */
@WebServlet(name = "RegisterDepartment", urlPatterns = {"/RegisterDepartment"})
public class RegisterDepartment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.getRequestDispatcher("RegisterDepartment.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            RegisterDepartment_Model rdm = new RegisterDepartment_Model();
            String json;
            switch (action) {
                case "getCampus":
                    json = new Gson().toJson(rdm.getCampus());
                    out.println(json);
                    break;
                case "getInsertedDepartments":
                    ArrayList<Department> departments = rdm.getInsertedDepartments();
                    json = new Gson().toJson(departments);
                    out.println(json);
                    break;
                /*case "json":
                    ArrayList<Menu> Menus = new ArrayList<>();
                    Menus.add(new Menu("hola", "Hola", "hola cls-hola"));
                    Menus.add(new Menu("hola1", "Hola1", "hola cls-hola1"));
                    Menus.add(new Menu("hola2", "Hola2", "hola cls-hola2"));
                    String json = new Gson().toJson(Menus);
                    out.println(json);
                    break;*/
                default:
                    processRequest(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "insertDepartment":
                    String departamento_nombre = request.getParameter("departamento_nombre");
                    int campus_id = Integer.parseInt(request.getParameter("campus_id"));
                    RegisterDepartment_Model rdm = new RegisterDepartment_Model();
                    int rows_number = rdm.insertDepartment(departamento_nombre, campus_id);
                    Response resp = new Response(
                           (rows_number > 0),
                            rows_number,
                            "[]"
                            
                    );
                    ArrayList<Response> jsonRes = new ArrayList<>();
                    jsonRes.add(resp);
                    String json = new Gson().toJson(jsonRes);
                    out.println(json);
                    break;
                /*case "json":
                    ArrayList<Menu> Menus = new ArrayList<>();
                    Menus.add(new Menu("hola", "Hola", "hola cls-hola"));
                    Menus.add(new Menu("hola1", "Hola1", "hola cls-hola1"));
                    Menus.add(new Menu("hola2", "Hola2", "hola cls-hola2"));
                    String json = new Gson().toJson(Menus);
                    out.println(json);
                    break;*/
                default:
                    processRequest(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
