<%-- 
    Document   : index
    Created on : 6 sep. 2021, 23:51:15
    Author     : Eduardo
--%>


<%@page import="Beans.Marca"%>
<%@page import="DAO.MarcaDao"%>
<%@page import="DAO.CategoriaDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Beans.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Gestión de productos |  Crea e-Commerce JAVA EE con pagos Online Paypal y Payu</title>
    <%@include file="../css.jsp"%>
</head><!--/head-->

<body>
    <%@include file="../header.jsp"%>

    <%--<%@include file="slider.jsp"%>--%>
    
    <%--<%@include file="confianza.jsp"%>--%>
    
        <section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">

                                    <%--<%@include file="aside.jsp"%>--%>
                                    
				</div>
				
				<div class="col-sm-10 clearfix">
				
                                    ${mensaje}
                                    <h3>Gestionar producto</h3>
                                    
                                    <!--Se agrega enctype="multipart/form-data" para indicar que se subiran archivos --> 
                                    <form name="f_producto" action="ControlProducto" enctype="multipart/form-data" method="POST">
                                        <div class="form-one">
                                            
                                            Nombre: <br/><input type="text" name="nombre" placeholder="Nombre del producto" value="" required /><hr/>
                                            
                                            Precio (MXN): <br/><input type="number" name="precio" placeholder="¨Precio del producto" value="" min="0"/><br/>
                                            Precio promo (MXN): <br/><input type="number" name="precioNuevo" placeholder="Precio nuevo del producto" value="" min="0"/><hr/>
                                            Precio (USD): <br/><input type="number" name="precioUSD" placeholder="¨Precio del producto" value="" min="0"/><br/>
                                            Precio promo (USD): <br/><input type="number" name="precioNuevoUSD" placeholder="Precio nuevo del producto" value="" min="0"/><hr/>
                                            Precio (COP): <br/><input type="number" name="precioCOP" placeholder="¨Precio del producto" value="" min="0"/><br/>
                                            Precio promo (COP): <br/><input type="number" name="precioNuevoCOP" placeholder="Precio nuevo del producto" value="" min="0"/><hr/>
                                            Precio (PEN): <br/><input type="number" name="precioPEN" placeholder="¨Precio del producto" value="" min="0"/><br/>
                                            Precio promo (PEN): <br/><input type="number" name="precioNuevoPEN" placeholder="Precio nuevo del producto" value="" min="0"/><br/>
                                            
                                            
                                            Stock: <br/><input type="number" name="cantidad" placeholder="Cantidad" value="1" min="1"/><br/>
                                            
                                            Marca: <br/>
                                            <select name="marca">
                                                <option>Seleccionar marca</option>
                                                
                                                <% for (Marca m: MarcaDao.listarTodoDeMarcas()) { %>
                                                    <option value="<%= m.getIdMarca() %>"> <%= m.getNombreMarca() %> </option>
                                                <% } %>
                                            </select>
                                            <br/>
                                            
                                            Categoria: <br/>
                                            <select name="categoria">
                                                <option>Seleccionar categoría</option>
                                                
                                                <% for (Categoria c: CategoriaDao.listarTodoDeCategorias()) { %>
                                                    <option value="<%= c.getIdCategoria() %>"> <%= c.getNombreCategoria() %> </option>
                                                <% } %>
                                            </select>
                                            <br/>
                                            
                                            Descripción: <br/><textarea name="descripcion" placeholder="Descripción" rows="4" cols="20" required ></textarea>
                                            <br/>
                                            
                                            Nuevo?: <input type="checkbox" name="nuevo" value="ON" checked="checked" />
                                            Recomendado?: <input type="checkbox" name="recomendado" value="OFF" />
                                            Visible?: <input type="checkbox" name="visible" value="ON" checked="checked" />
                                            
                                            <hr/>
<!--                                        Para agregar texto a un boton de tipo file no se puede, por ello tenemos
                                            que crear otro input que muestre el nombre del botón-->
                                            <!--<input type="button" id="cargarArchivo" value="Seleccionar una imagen" onclick="document.getElementById('file').click();" />-->
                                            <input type="file"  id="file" name="imagen" required/><span>(268 x 249)</span>
                                            
                                            <hr/>
                                            <input class="btn btn-success" type="submit" name="accion" value="Registrar"/>
                                            <input class="btn btn-default" type="submit" name="accion" value="Consultar"/>
                                            <input class="btn btn-warning" type="submit" name="accion" value="Actualizar"/>
                                            <input class="btn btn-danger" type="submit" name="accion" value="Borrar"/>
                                        </div>
                                    </form>
                                    
				</div>
			</div>
		</div>
	</section>
	
    
    <%@include file="../footer.jsp"%>
    
    <%@include file="../js.jsp"%>
                                    
  
</body>
</html>

