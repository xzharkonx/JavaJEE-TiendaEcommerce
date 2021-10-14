/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Beans.Producto;
import Beans.ProductoMoneda;
import DAO.ProductoDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Eduardo
 */
public class ControlProducto extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlProducto at " + request.getContextPath() + "</h1>");
            out.println("<h2>Luis Eduardo Garcia Mercado</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        
        recibirDatos(request);
        String url = request.getAttribute("imagen").toString();
        
        System.out.println("Redireccionando a: foto/" + url );
        
        // Recolectamos los valores del envio por el método post.
        String nombre = request.getAttribute("nombre").toString();
        System.out.println(nombre);
        
        float precio = Float.parseFloat(request.getAttribute("precio").toString());
        float precioNuevo = Float.parseFloat(request.getAttribute("precioNuevo").toString());
        
        float precioUSD = Float.parseFloat(request.getAttribute("precioUSD").toString());
        float precioNuevoUSD = Float.parseFloat(request.getAttribute("precioNuevoUSD").toString());
        
        float precioCOP = Float.parseFloat(request.getAttribute("precioCOP").toString());
        float precioNuevoCOP = Float.parseFloat(request.getAttribute("precioNuevoCOP").toString());
        
        float precioPEN = Float.parseFloat(request.getAttribute("precioPEN").toString());
        float precioNuevoPEN = Float.parseFloat(request.getAttribute("precioNuevoPEN").toString());
        
        Integer cantidad = Integer.parseInt(request.getAttribute("cantidad").toString());
        Integer idMarca = Integer.parseInt(request.getAttribute("marca").toString());
        Integer idCategoria = Integer.parseInt(request.getAttribute("categoria").toString());
        String descripcion = request.getAttribute("descripcion").toString();
        
        Boolean nuevo;
        Boolean recomendado;
        Boolean visible;
        // Boolean n = (request.getParameter("nuevo").equalsIgnoreCase("on")) ? true : false; // Forma compleja.
        try {
            
            nuevo = request.getAttribute("nuevo").toString().equalsIgnoreCase("on");
            recomendado = request.getAttribute("recomendado").toString().equalsIgnoreCase("off");
            visible = request.getAttribute("visible").toString().equalsIgnoreCase("on");
            
        } catch (Exception e) {
            System.out.println("El error fue el siguiente" + e );
            nuevo = false;
            recomendado = false;
            visible = false;
        }

        String accion = request.getAttribute("accion").toString();
        System.out.println(accion);
        
        // Recabando los datos del Producto.
        Producto p = new Producto();
        
        p.setNombreProducto(nombre);
        p.setPrecio(precio);
        p.setPrecioNuevo(precioNuevo);
        p.setStock(cantidad);
        p.setNuevo(nuevo);
        p.setRecomendado(recomendado);
        p.setDescripcion(descripcion);
        p.setVisible(visible);
        p.setImg(url);
        
        p.setIdMarca(idMarca);
        System.out.println(idMarca);
        
        p.setIdCategoria(idCategoria);
        System.out.println(idCategoria);
        
        ProductoMoneda usd = new ProductoMoneda( "USD", precioUSD, precioNuevoUSD);
        ProductoMoneda cop = new ProductoMoneda( "COP", precioCOP, precioNuevoCOP);
        ProductoMoneda pen = new ProductoMoneda( "PEN", precioPEN, precioNuevoPEN);
        
        ProductoDao producto = new ProductoDao();
        
        if (accion.equalsIgnoreCase("registrar")){
           
            try {
                if(producto.registrarProducto(p, usd, cop, pen)){

                    System.out.println("Producto registrado correctamente");
                    request.setAttribute("mensaje", "<p style='color:green'>Producto registrado correctamente</p>");
                    
                } else {

                    System.out.println("Error al registrar el producto");
                    request.setAttribute("mensaje", "<p style='color:red'>Error: Producto no registrado</p>");

                }
                
            } catch (Exception e) {
                    System.out.println("El error es el siguiente:" + e);
                    request.setAttribute("mensaje", "<p style='color:red'>Producto no registrado</p>");
                    request.getRequestDispatcher("admin").forward(request, response);
            }
                    
            
        } else if(accion.equalsIgnoreCase("Consultar")){
        
        }else if(accion.equalsIgnoreCase("Actualizar")){
            
        }else if(accion.equalsIgnoreCase("Borrar")){
            
        }else{
            
            request.setAttribute("mensaje", "<p style='color:red'>Acción desconocida</p>");
        }
        
        
        request.getRequestDispatcher("admin").forward(request, response);
        
        // response.sendRedirect("foto\\" + url);
        
    }
    
    
    private void recibirDatos(HttpServletRequest request){
        
        try {
            
            FileItemFactory fileFactory = new DiskFileItemFactory();

            // Convertir los campos del formulario de imágen a un item que podemos
            // recorrer para verificar su información.
            // Llamamos a ServletFileUpload para poner ahí el Objeto anterior de los
            // items que vamos a ir leyendo.
            ServletFileUpload servletUpload = new ServletFileUpload(fileFactory);
            
            // Ahora vamos a extraer cada uno de los item (archivos img seleccionados)
            List items = servletUpload.parseRequest(request);
            
            // Variable para almacenar el nombre de la imagen nueva creada.
            String nombre = "";
            
            for(int i=0; i<items.size(); i++){
                FileItem item = (FileItem) items.get(i);
                
                // Preguntamos si ese campo no es un campo normal de de formulario
                // entonces conseguiremos la ruta real donde se desplego el proyecto
                // y va a tratar de subir la imagen a un directorio.
                
                if(!item.isFormField()){
                    
                    String ruta = request.getServletContext().getRealPath("/") + "foto\\";
                    
                    // Ahora como crearemos un archivo, lo haremos con un nombre
                    // único donde utilizaremos la ruta, la fecha, un número
                    // largo de forma aleatorio (new Random().nextLong())  
                    // también el nombre del archivo que estamos resiviendo.
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMyyyyhhmmss");
                    String fecha = sdf.format(new Date());
                    nombre = fecha + new Random().nextLong() + item.getName();
                    
                    // Este nuevoNombre permitira crear la img.
                    String nuevoNombre = ruta + nombre;
                    
                    System.out.println(nombre);
                    System.out.println(nuevoNombre);
                    // Ahora escribiremos este archivo en la memoria.
                    
                    // Creando el directorio
                    // Buscamos la ruta del archivo.
                    File folder = new File(ruta);
                    
                    // Si no existe el achivo
                    if(!folder.exists()){
                        
                        // Crea el directorio.
                        folder.mkdirs();
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("El directorio existe");
                    }
                    // Creando la imagen
                    // Buscamos la imagen de archivo.
                    File imagen = new File(nuevoNombre);
                    
                    // Verificamos que si ese item es un archivo de imagen para 
                    // que no nos esten subiendo algo diferente.
                    // La palabra de "image" quiere decir que si ese es un 
                    // archivo de imagen.
                    if(item.getContentType().contains("image")){
                        
                        // Crea la imagen.
                        item.write(imagen);
                        
                        // Creamos un parametro con el nombre del campo que se envio (imagen) 
                        // y el nombre de la imagen que se subio.
                        // img = "foto.jpg"
                        request.setAttribute(item.getFieldName(), nombre);
                        
                        System.out.println("Imagen creada");                        
                        
                    } else {
                        System.out.println("La imagen no existe");
                    }
                    
                } else {
                    
                    // En el caso de que no sea un campo de archivo tipo File, entonces
                    // quiere decir que es un campo normal de formulario, es decir,
                    // una entrada de texto.
                    
                    // Entonces le decimos que cree un atributo con el nombre
                    // del campo como nombre de variable y para el valor de esa
                    // variable el value contenido con getString()
                    // nombre = "producto1"
                    request.setAttribute(item.getFieldName(), item.getString());
                }
                
            }
            
        } catch (FileUploadException ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("imgSubida",false);
            System.out.println("ERROR FATAL: Al crear la imagen");
        } catch (Exception ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("imgSubida",false);
            System.out.println("ERROR FATAL: Error desconocido, llamar al GrandMaster");
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
