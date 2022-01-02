/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.GestorBD;
import Model.Usuario;
import java.io.IOException; 
import java.io.PrintWriter; 
import java.util.ArrayList;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 


@WebServlet(name = "muestraUsuarios", 
            urlPatterns = {"/muestraUsuarios"})
public class muestraUsuarios extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, 
                                HttpServletResponse response) 
          throws ServletException, IOException { 
    response.setContentType("text/html;charset=UTF-8"); 
    PrintWriter out = response.getWriter(); 
    try { 
         ArrayList <Usuario> usuarios = new ArrayList<Usuario>(); 
         Usuario usuario; 
         GestorBD gestorBD = new GestorBD(); 
            
         usuarios = gestorBD.leeTodos(); 
         if (usuarios != null){ 
            request.setAttribute("Usuarios",usuarios);            
            request.getRequestDispatcher("/listaUsuarios.jsp") 
                                      .forward(request, response); 
         }else 
            request.getRequestDispatcher("/noHayRegistros.jsp") 
                                      .forward(request, response);   
     } finally {             
         out.close(); 
     } 
  } 
    
}

