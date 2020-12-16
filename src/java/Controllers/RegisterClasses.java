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
import models.RegisterClasses_Model;
import models.classes.Response;

/**
 *
 * @author victo
 */
@WebServlet(name = "RegisterClasses", urlPatterns = {"/RegisterClasses"})
public class RegisterClasses extends HttpServlet {

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
            request.getRequestDispatcher("RegisterClasses.jsp").forward(request, response);
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
        String json;
        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            RegisterClasses_Model rcm = new RegisterClasses_Model();
            //RegisterDepartment_Model rdm = new RegisterDepartment_Model();
            //String json;
            switch (action) {
                case "getMaterias":
                    json = new Gson().toJson(rcm.getMaterias());
                    out.println(json);
                    break;
                case "getDias":
                    json = new Gson().toJson(rcm.getDias());
                    out.println(json);
                    break;
                case "getHoras":
                    json = new Gson().toJson(rcm.getHoras());
                    out.println(json);
                    break;
                case "getInsertedClasses":
                    int departamento_id = Integer.parseInt(request.getSession(false).getAttribute("departamento_id").toString());
                    json = new Gson().toJson(rcm.getInsertedClasses(departamento_id));
                    out.println(json);
                    break;
                case "getInsertedScheduleByUserId":
                    int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
                    json = new Gson().toJson(rcm.getInsertedScheduleByUserId(usuario_id));
                    out.println(json);
                    break;
                case "getInsertedProfesorSchedule":
                    int usuario_prof_id = Integer.parseInt(request.getSession(false).getAttribute("usuario_id").toString());
                    json = new Gson().toJson(rcm.getInsertedScheduleByUserId(usuario_prof_id));
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

        try (PrintWriter out = response.getWriter()) {
            String action;
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            RegisterClasses_Model rcm = new RegisterClasses_Model();
            ArrayList<Response> jsonRes;
            String json;
            Response resp;
            int rows_number;
            switch (action) {
                case "insertClasses":
                    int materia_id = Integer.parseInt(request.getParameter("materia_id"));
                    int dias_grupo_id = Integer.parseInt(request.getParameter("dias_grupo_id"));
                    int hora_clase_id = Integer.parseInt(request.getParameter("hora_clase_id"));
                    rows_number = rcm.insertCarrees(materia_id, dias_grupo_id, hora_clase_id);
                    resp = new Response(
                            (rows_number > 0),
                            rows_number,
                            "[]"
                    );
                    jsonRes = new ArrayList<>();
                    jsonRes.add(resp);
                    json = new Gson().toJson(jsonRes);
                    out.println(json);
                    break;
                case "insertSchedule":
                    int clase_id = Integer.parseInt(request.getParameter("clase_id"));
                    int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
                    rows_number = rcm.insertSchedule(clase_id, usuario_id);
                    resp = new Response(
                            (rows_number > 0),
                            rows_number,
                            "[]"
                    );
                    jsonRes = new ArrayList<>();
                    jsonRes.add(resp);
                    json = new Gson().toJson(jsonRes);
                    out.println(json);
                    break;
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
