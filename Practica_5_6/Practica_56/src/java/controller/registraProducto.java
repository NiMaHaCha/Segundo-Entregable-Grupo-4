/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import negocios.*;


@WebServlet(name = "registraProducto",
        urlPatterns = {"/registraProducto"})
public class registraProducto extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int clave = Integer.parseInt(request.getParameter("clave"));
            String nombre = request.getParameter("nombre");
            Double precio
                    = Double.parseDouble(request.getParameter("precio"));
            int cantidad
                    = Integer.parseInt(request.getParameter("cant"));
            Producto producto
                    = new Producto(clave, nombre, precio, cantidad);

            ServletContext sc = this.getServletContext();
            String path = sc.getRealPath("/WEB-INF/Productos.txt");
            path = path.replace('\\', '/');

            // Guardar en archivo
            GuardaProducto.add(producto, path);
            request.setAttribute("atribProd", producto);
            request.getRequestDispatcher("/muestraRegistro.jsp")
                    .forward(request, response);
        } finally {
            out.close();
        }
    }
}
