/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
public class CambiarMoneda extends HttpServlet {

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
        
        // Buscamos por el atributo de moneda en la que se envio como parametro
        if(request.getParameter("moneda") != null){
            
            switch(request.getParameter("moneda")) {
                
                case "COP":
                    // Vamos a asignar el valor de la moneda
                    session.setAttribute("moneda", request.getParameter("moneda"));
                    session.setAttribute("nomMoneda", "$ Pesos Colombianos");
                    break;
                case "USD":
                    // Vamos a asignar el valor de la moneda
                    session.setAttribute("moneda", request.getParameter("moneda"));
                    session.setAttribute("nomMoneda", "$ Dolár(USA)");
                    break;
                case "PEN":
                    // Vamos a asignar el valor de la moneda
                    session.setAttribute("moneda", request.getParameter("moneda"));
                    session.setAttribute("nomMoneda", "$ Sol Peruano");
                    break;
                    
                default:
                    // Vamos a asignar el valor de la moneda MXN
                    session.setAttribute("moneda", request.getParameter("moneda"));
                    session.setAttribute("nomMoneda", "$ Pesos Mexicanos");
            }
            
            
        }
        
        // request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        // sendRedirect para que en la URL no aparezca CambiarMoneda, si no,
        // con la primera si redireccionará apareciendo en la url ese nombre del Servelet
        // http://localhost:8080/ecommerce/CambiarMoneda?moneda=USD
        
        // Así ya no aparecera.
        response.sendRedirect("Inicio");
        
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
