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
import models.RegisterCarreers_Model;
import models.Signin_Model;
import models.classes.Response;

/**
 *
 *
 */
@WebServlet(urlPatterns = {"/Signin"})
public class Signin extends HttpServlet {

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
            request.getRequestDispatcher("Signin.jsp").forward(request, response);
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
            Signin_Model sm = new Signin_Model();
            //RegisterDepartment_Model rdm = new RegisterDepartment_Model();
            //String json;
            switch (action) {
                case "getInsertedUsers":
                    int grupo_usuario_id = Integer.parseInt(request.getSession(false).getAttribute("grupo_usuario_id").toString());
                    int departamento_id = Integer.parseInt(request.getSession(false).getAttribute("departamento_id").toString());
                    json = new Gson().toJson(sm.getInsetedUsers(grupo_usuario_id, departamento_id));
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
            Signin_Model sm = new Signin_Model();
            switch (action) {
                case "insertUser":
                    String persona_nombre = request.getParameter("persona_nombre");
                    String persona_appat = request.getParameter("persona_appat");
                    String persona_apmat = request.getParameter("persona_apmat");
                    String persona_tel = request.getParameter("persona_tel");
                    String persona_email = request.getParameter("persona_email");
                    int grupo_usuario_id = Integer.parseInt(request.getParameter("grupo_usuario_id"));
                    int departamento_id = Integer.parseInt(request.getParameter("departamento_id"));
                    String usuario_login = request.getParameter("usuario_login");
                    String usuario_pwd = request.getParameter("usuario_pwd");

                    int rows_number = sm.insertUser(persona_nombre, persona_appat, persona_apmat, persona_tel, persona_email, usuario_login, usuario_pwd, grupo_usuario_id, departamento_id);
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
