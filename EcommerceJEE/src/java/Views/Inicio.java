/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo
 */
public class Inicio extends HttpServlet {

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
        
        // Antes de que redireccione obtenemos la Session.
        HttpSession session = request.getSession();
        
        System.out.println(request.getMethod());
        
        //if (request.getMethod().equals("GET")){}
        
        
        // Buscamos por el atributo de moneda en la Session
        if(session.getAttribute("moneda") == null){
            
            // Vamos a crear el valor de la moneda
            session.setAttribute("moneda", "MXN");
            session.setAttribute("nomMoneda", "$ Pesos Mexicanos");
        }
        
        System.out.println(request.getParameter("categoria") != null ? "LA CATEGORIA ES: " + request.getParameter("categoria") : "No mando nada");
        
        if(request.getParameter("categoria") != null){
            session.setAttribute("categoria", Integer.parseInt(request.getParameter("categoria")));
        } else if (request.getParameter("marca") != null){
            session.setAttribute("marca", Integer.parseInt(request.getParameter("marca")));
        } else if (request.getParameter("categoria") != null && request.getParameter("marca") != null) {
            session.setAttribute("categoria", Integer.parseInt(request.getParameter("categoria")));
            session.setAttribute("marca", Integer.parseInt(request.getParameter("marca")));
        
        }else {
            session.setAttribute("categoria", 0);
            session.setAttribute("marca", 0);
        }
        
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
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
        processRequest(request, response);
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
