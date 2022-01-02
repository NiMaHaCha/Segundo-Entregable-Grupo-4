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


@WebServlet(name = "registro", urlPatterns = {"/registro"}) 
public class registro extends HttpServlet { 
  protected void doPost(HttpServletRequest request, 
                                HttpServletResponse response) 
         throws ServletException, IOException { 
     response.setContentType("text/html;charset=UTF-8"); 
     PrintWriter out = response.getWriter(); 
 try { 
       String cuenta = request.getParameter("cuenta"); 
       String nombre= request.getParameter("nombre"); 
       String clave= request.getParameter("clave"); 
       String mail= request.getParameter("mail"); 
                   
       GestorBD gestorBD = new GestorBD(); 
            
       if (gestorBD.registrar(cuenta, nombre, clave, mail)){             
           request.getRequestDispatcher("/registroGuardado.jsp") 
                                      .forward(request, response);   
       }    
       else 
           request.getRequestDispatcher("/errorEnRegistro.jsp") 
                                      .forward(request, response); 
     } finally {             
         out.close(); 
     }
  
  }
}
