<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Despliega Productos</title>
    </head>
    <body>
        <%@ page import="negocios.Producto, java.util.ArrayList" %>

        <h2>Los productos que están registrados son: </h2>
        <%
            ArrayList<Producto> productos = null;
            productos
                    = (ArrayList<Producto>) request.getAttribute("Productos");
        %>

        <table border="1">
            <tr>
                <th>Clave</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
            </tr>
            <%
                for (Producto producto : productos) {
            %>
            <tr valign="top">
                <td><%=producto.getClave()%></td>
                <td><%=producto.getNombre()%></td>
                <td><%=producto.getPrecio()%></td>
                <td><%=producto.getCantidad()%></td>

            </tr>

            <% }%>

        </table>
        <p> Numero de registros : <%= productos.size()%></p>
        <% productos.clear();%>
        <br>
        <form action="index.jsp" method="post">
            <input type="submit" value="Regresar">
        </form>
    </body>
</html>
