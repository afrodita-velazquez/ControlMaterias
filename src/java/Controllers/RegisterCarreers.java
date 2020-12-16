/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RegisterCarreers_Model;
import models.RegisterDepartment_Model;
import models.classes.Response;

/**
 *
 * 
 */
@WebServlet(name = "RegisterCarreers", urlPatterns = {"/RegisterCarreers"})
public class RegisterCarreers extends HttpServlet {

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
            request.getRequestDispatcher("RegisterCarreers.jsp").forward(request, response);
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
        RegisterCarreers_Model rcm = new RegisterCarreers_Model();
        String json;
        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            //RegisterDepartment_Model rdm = new RegisterDepartment_Model();
            //String json;
            switch (action) {
                case "getDepartments":
                    json = new Gson().toJson(rcm.getDepartments());
                    out.println(json);
                    break;
                case "getInsertedCarreers":
                    json = new Gson().toJson(rcm.getInsertedCarreers());
                    out.println(json);
                    break;
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
        RegisterCarreers_Model rcm = new RegisterCarreers_Model();
        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "insertCarreer":
                    String carreras_clave = request.getParameter("carreras_clave");
                    String carreras_nombre = request.getParameter("carreras_nombre");
                    int departamento_id = Integer.parseInt(request.getParameter("departamento_id"));
                    int rows_number = rcm.insertCarrees(carreras_clave, carreras_nombre, departamento_id, out);
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
