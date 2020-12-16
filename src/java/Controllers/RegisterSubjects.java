package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RegisterSubjects_Model;
import models.classes.Response;

/**
 *
 * 
 */
@WebServlet(urlPatterns = {"/RegisterSubjects"})
public class RegisterSubjects extends HttpServlet {

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
            request.getRequestDispatcher("RegisterSubjects.jsp").forward(request, response);
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
            RegisterSubjects_Model rsm = new RegisterSubjects_Model();
            String json;
            switch (action) {
                case "getInsertedSubjects":
                    json = new Gson().toJson(rsm.getInsertedSubjects());
                    out.println(json);
                    break;
                case "getInsertedSubjectsByCarreerId":
                    String carrera_id = request.getParameter("carrera_id");
                    json = new Gson().toJson(rsm.getInsertedSubjectsByCarreerId(carrera_id));
                    out.println(json);
                    break;
                case "getSubjectById":
                    int materia_id = Integer.parseInt(request.getParameter("materia_id"));
                    json = new Gson().toJson(rsm.getInsertedSubjectsById(materia_id));
                    out.println(json);
                    break;
                case "getCarreers":
                    int departamento_id = Integer.parseInt(request.getSession(false).getAttribute("departamento_id").toString());
                    json = new Gson().toJson(rsm.getCarreers(departamento_id));
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
            String action = request.getParameter("action");

            if (action == null) {
                action = "";
            }
            RegisterSubjects_Model rsm = new RegisterSubjects_Model();
            String materia_clave;
            String materia_nombre;
            int materia_creditos;
            int carreras_id;
            int rows_number;
            ArrayList<Response> jsonRes;
            String json;
            
            switch (action) {
                case "insertSubject":
                    materia_clave = request.getParameter("materia_clave");
                    materia_nombre = request.getParameter("materia_nombre");
                    materia_creditos = Integer.parseInt(request.getParameter("materia_creditos"));
                    carreras_id = Integer.parseInt(request.getParameter("carreras_id"));

                    rows_number = rsm.insertSubject(materia_clave, materia_nombre, materia_creditos, carreras_id);
                    Response resp = new Response(
                            (rows_number > 0),
                            rows_number,
                            "[]"
                    );
                    jsonRes = new ArrayList<>();
                    jsonRes.add(resp);
                    json = new Gson().toJson(jsonRes);
                    out.println(json);
                    break;
                case "updateSubject":
                    int materia_id = Integer.parseInt(request.getParameter("materia_id"));
                    materia_clave = request.getParameter("materia_clave");
                    materia_nombre = request.getParameter("materia_nombre");
                    materia_creditos = Integer.parseInt(request.getParameter("materia_creditos"));
                    carreras_id = Integer.parseInt(request.getParameter("carreras_id"));

                    rows_number = rsm.updateSubject(materia_id, materia_clave, materia_nombre, materia_creditos, carreras_id);
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
