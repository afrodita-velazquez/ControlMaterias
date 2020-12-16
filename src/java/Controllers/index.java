package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helpers.SessionHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Login_Model;

/**
 *
 * 
 */
@WebServlet(urlPatterns = {"/index"})
public class index extends HttpServlet {

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
            String redirect = "";
            try {
                String usuario_id = request.getSession(false).getAttribute("usuario_id").toString();
                redirect = "Dashboard";
            } catch (Exception e) {
                redirect = "Login.jsp";
            }

            request.getRequestDispatcher(redirect).forward(request, response);
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
        processRequest(request, response);
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
        String redirectUrl = "";
        boolean redirect = false;
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "inicioDeSesion":
                    String Usuario = request.getParameter("usuario_login");
                    String Contrasena = request.getParameter("usuario_pwd");
                    String[] resp = Login(Usuario, Contrasena);
                    out.println("<h1>Respuesta: " + resp[1] + "</h1>");
                    redirect = true;
                    if (resp[0].equals("true")) {
                        HttpSession misession = request.getSession(true);
                        misession.setAttribute("usuario_id", resp[2]);
                        misession.setAttribute("grupo_usuario_id", resp[3]);
                        misession.setAttribute("persona_nombre", resp[4]);
                        misession.setAttribute("persona_appat", resp[5]);
                        misession.setAttribute("persona_apmat", resp[6]);
                        misession.setAttribute("departamento_id", resp[7]);
                        SessionHelper seshelp = new SessionHelper();
                        misession.setAttribute("usuario_menus", seshelp.getSessionMenus(resp[3]));
                        redirectUrl = "Dashboard";
                    } else {
                        redirectUrl = "Login.jsp";
                    }
                    request.getRequestDispatcher(redirectUrl).forward(request, response);
                    break;
                default:
                    out.println("<h1>No hay acción</h1>");
                    break;
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

    public String[] Login(String Usuario, String Contrasena) {
        Login_Model login_mode = new Login_Model();
        String[] response;
        if (login_mode.getConnectionStatus()) {
            response = login_mode.Login(Usuario, Contrasena);
        } else {
            response = new String[1];
            response[0] = "false";
            response[1] = "Error al conectar la base de datos, inténtelo más tarde";
        }
        return response;
    }

}
