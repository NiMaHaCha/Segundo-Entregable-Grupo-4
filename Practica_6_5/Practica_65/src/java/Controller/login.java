/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import Model.*; 
import java.util.ArrayList; 

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet { 
    protected void doPost(HttpServletRequest request, 
                                  HttpServletResponse response) 
            throws ServletException, IOException { 
      response.setContentType("text/html;charset=UTF-8"); 
      PrintWriter out = response.getWriter(); 
      String cuenta = request.getParameter("cuenta"); 
      String clave= request.getParameter("clave"); 
         
      try { 
         Usuario usuario; 
         GestorBD gestorBD = new GestorBD(); 
            
         usuario = gestorBD.consultar(cuenta,clave); 
           
         if(usuario != null){ 
           request.setAttribute("nombre",usuario.getNombre());            
           request.getRequestDispatcher("/inicioSistema.jsp") 
                                    .forward(request, response); 
    }else 
           request.getRequestDispatcher("/noEncontrado.jsp") 
                                    .forward(request, response); 
             
      } finally {             
           out.close(); 
      } 
    } 
}
