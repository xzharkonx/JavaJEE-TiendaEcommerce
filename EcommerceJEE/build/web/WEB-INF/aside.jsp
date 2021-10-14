<%-- 
    Document   : aside
    Created on : 4 sep. 2021, 23:19:06
    Author     : Eduardo
--%>

					
                                        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.MarcaDao"%>
<div class="left-sidebar">
						<h2>Categorías</h2>
						<div class="panel-group category-products" id="accordian"><!--category-products-->
							 
                                                        <%! ArrayList<Categoria> categorias = CategoriaDao.listar(); %>
                                                        
                                                            <% for(Categoria categoria : categorias){ %>

                                                            <div class="panel panel-default">
                                                                    <div class="panel-heading">
                                                                            <h4 class="panel-title">
                                                                                <a <% if((CategoriaDao.numSubCategorias(categoria.getIdCategoria()) > 0)) { %> data-toggle="collapse" data-parent="#accordian" <% } %> href="#<%= categoria.getIdCategoria() %>">
                                                                                           
                                                                                            <% if((CategoriaDao.numSubCategorias(categoria.getIdCategoria()) > 0)) { %>
                                                                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                                                                <a href="?categoria=<%= categoria.getIdCategoria() %>"><%= categoria.getNombreCategoria() %></a>
                                                                                                <span class="badge"><i>(<%= CategoriaDao.numSubCategorias(categoria.getIdCategoria()) %>)</i></span>
                                                                                            <% }%>
                                                                                                
                                                                                            <% if((CategoriaDao.numSubCategorias(categoria.getIdCategoria()) == 0)) { %>
                                                                                                <a href="?categoria=<%= categoria.getIdCategoria() %>"><%= categoria.getNombreCategoria() %></a>
                                                                                                <span class="badge"><i>(<%= CategoriaDao.numSubCategorias(categoria.getIdCategoria()) %>)</i></span>
                                                                                            <% } %>
                                                                                    </a>
                                                                            </h4>
                                                                    </div>
                                                                    <div id="<%= categoria.getIdCategoria() %>" class="panel-collapse collapse">
                                                                            <div class="panel-body">
                                                                                    <ul>
                                                                                        <% ArrayList<Categoria> subCategorias = CategoriaDao.listarSubCategorias(categoria.getIdCategoria()); %>
                                                                                            <% for(Categoria subCategoria : subCategorias){ %>
                                                                                            <!--<li><a href="#">Nike </a></li>-->
                                                                                                <li><a href="?categoria=<%= categoria.getIdCategoria() %>"><%= subCategoria.getNombreCategoria() %> </a></li>
                                                                                            <% } %>
<!--                                                                                            <li><a href="#">Under Armour </a></li>
                                                                                            <li><a href="#">Adidas </a></li>
                                                                                            <li><a href="#">Puma</a></li>
                                                                                            <li><a href="#">ASICS </a></li>-->
                                                                                    </ul>
                                                                            </div>
                                                                    </div>
                                                            </div>

                                                            <% } %>
                                                        <% if (categorias == null) {System.out.println("Error de conexión no se pueden mostrar los datos en el Template");} %>
                                                        
<!--							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Niños</a></h4>
								</div>
							</div>-->
						</div><!--/category-products-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Marcas</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
                                                                    <c:forEach var="m" items="<%= MarcaDao.listarTodoDeMarcas() %>">
                                                                        <c:set var="coM" value="${m.getIdMarca()}"/>
                                                                        <% Integer coM = Integer.parseInt(pageContext.getAttribute("coM").toString()); %>
                                                                        <li><a href="?marca=${m.getIdMarca()}"> <span class="pull-right">(<%= MarcaDao.contarMarcas(coM) %>)</span>${m.getNombreMarca()}</a></li>
                                                                    </c:forEach>
								</ul>
							</div>
						</div><!--/brands_products-->

						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>
