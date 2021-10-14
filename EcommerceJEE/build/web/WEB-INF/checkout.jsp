<%-- 
    Document   : checkout
    Created on : 26 sep. 2021, 23:47:03
    Author     : Eduardo
--%>

<form action="${initParam['urlpaypal']}" method="POST" target="_top">
<!--    Aquí a este valor del formulario oculto le pasaremos nuestra cuenta de
    correo de paypal-->
    <input type="hidden" name="business" value="zharkon1@hotmail.com" />
    <input type="hidden" name="return" value="${initParam['urlretorno']}" />
    <input type="hidden" name="cmd" value="_cart" />
    <input type="hidden" name="upload" value="1" />
    
    
    
</form>