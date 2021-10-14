/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Beans.Item;
import Beans.Producto;
import DAO.ProductoDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo
 */
public class Cart extends HttpServlet {

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
        
        // # Capturaremos el parametro de la acción que se quiere realizar.
        
        // ? Capturamos  el parametro de accion en la url para ver si contiene algo
        if(request.getParameter("action") != null){
            
            // ? Si hubo algo lo guardamos el valor de ese parametro.
            String a = request.getParameter("action");
            Integer id = Integer.parseInt(request.getParameter("id"));
            // Creamos un Objeto de la Clase Producto que almacenará los datos del 
            // producto que agreguemos al carrito.
            Producto p;
            // Creamos un Objeto de tipo HttpSession para capturar los datos en la Session.
            HttpSession session = request.getSession();
            // Si el valor del parametro capturado de action es "order" entonces...
            if(a.equals("order")){
                // Ahora preguntaremos a el atributo de la Session si cart es null entonces...
                if(session.getAttribute("cart") == null){
                    // Lo creamos
                    ArrayList<Item>  cart = new ArrayList();
                    // Consultamos 1 producto en base al valor del precio de la moneda y el id 
                    // del producto agregado al carrito.
                    p = ProductoDao.consultarProducto(session.getAttribute("moneda").toString(), id);
                    // Le pasamos el producto y la cantidad de ese producto a ese Objeto item preparado
                    // a la lista de de items del carrito de compras..
                    cart.add(new Item(p,1));
                    // Lo agregamos como atributo a la sessión para tenerlo disponible.
                    session.setAttribute("cart", cart);
                    
                } else {
                    // Si ya existe el carrito de compras, entonces...
                    ArrayList<Item>  cart = (ArrayList<Item>) session.getAttribute("cart");
                    
                    // Verificamos si ya existe el producto
                    int indice = yaExisteProducto(id,cart);
                    
                    if (indice == -1) {
                        // Consultamos 1 producto en base al valor del precio de la moneda y el id 
                        // del producto agregado al carrito.
                        p = ProductoDao.consultarProducto(session.getAttribute("moneda").toString(), id);
                        // Le pasamos el producto y la cantidad de ese producto a ese Objeto item preparado
                        // a la lista de de items del carrito de compras..
                        cart.add(new Item(p,1));
                        
                    } else {
                        int cantidad = cart.get(indice).getCantidad()+1;
                        cart.get(indice).setCantidad(cantidad);
                    }
                    
                    
                    session.setAttribute("cart", cart);
                }
            } else if (a.equals("delete")){
                // Si ya existe el carrito de compras, entonces...
                ArrayList<Item>  cart = (ArrayList<Item>) session.getAttribute("cart");

                // Verificamos si ya existe el producto
                int indice = yaExisteProducto(id,cart);
                cart.remove(indice);
                session.setAttribute("cart", cart);
            }
            
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request,response);
    }
    
    private int yaExisteProducto(int idProducto, ArrayList<Item> cart){
        for (int i = 0; i < cart.size() ; i++) {
            
            if(cart.get(i).getP().getIdProducto() == idProducto){
                return i;
            }
            
        }
        return -1;
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
