/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException;
import Model.GestorBD;
import Model.Videojuego;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "controllerManagedBean")
@SessionScoped
public class ControllerManagedBean {
 private Integer clave;
 private String nombre;
 private String genero;
 private String plataforma;
 private String precio;
 private GestorBD gestorBD;
 private ArrayList<Videojuego> juegoList;

 public ControllerManagedBean() {
 gestorBD = new GestorBD();
 juegoList = gestorBD.leerExistentes();
 }

    public Integer getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getPrecio() {
        return precio;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public ArrayList<Videojuego> getVideojuegoList() {
        return juegoList;
    }
 
    public void pedirDatosJuego_aAgregar (){
    try{
    FacesContext.getCurrentInstance()
    .getExternalContext()
    .redirect("agregar_Videojuego.xhtml");
    }catch(IOException ex) {
    Logger.getLogger(ControllerManagedBean
    .class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    public void regresar_Inicio (){
    try{
    FacesContext.getCurrentInstance()
    .getExternalContext()
    .redirect("index.xhtml");
    }catch(IOException ex) {
    Logger.getLogger(ControllerManagedBean
    .class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
     public void guardarJuego(){
        try {
            Videojuego juegoNueva = new Videojuego(clave, nombre,genero, plataforma,precio);
            if (gestorBD.guardarVideojuego(juegoNueva)){
                try{
                    juegoList = gestorBD.leerExistentes();
                    FacesContext.getCurrentInstance()
                    .getExternalContext()
                   .redirect("index.xhtml");
                }catch(IOException ex) {
                    Logger.getLogger(ControllerManagedBean
                    .class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("errorRegistro.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("errorRegistro.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public void pedirDatosJuego_aBorrar (){
    try{
    FacesContext.getCurrentInstance()
    .getExternalContext()
   .redirect("borrar_Videojuego.xhtml");
   }catch(IOException ex) {
    Logger.getLogger(ControllerManagedBean
    .class.getName()).log(Level.SEVERE, null, ex);
    }
   }
    public void borrarJuego(){
    Videojuego videoABorrar = new Videojuego(clave, nombre,
    genero, plataforma,precio);
    if (gestorBD.borrarVideojuego(videoABorrar)){
        try{
        juegoList = gestorBD.leerExistentes();
        FacesContext.getCurrentInstance()
        .getExternalContext()
       .redirect("index.xhtml");
        }catch(IOException ex) {
        Logger.getLogger(ControllerManagedBean
        .class.getName()).log(Level.SEVERE, null, ex);
        }
    }else{
     try{
     juegoList = gestorBD.leerExistentes();
     FacesContext.getCurrentInstance()
     .getExternalContext()
    .redirect("error.xhtml");
     }catch(IOException ex) {
     Logger.getLogger(ControllerManagedBean
     .class.getName()).log(Level.SEVERE, null, ex);
     }
    }
 }
    public void pedirDatosJuego_aModificar (){
    try{
    FacesContext.getCurrentInstance()
    .getExternalContext()
   .redirect("select_videojuegoAModificar.xhtml");
   }catch(IOException ex) {
    Logger.getLogger(ControllerManagedBean
    .class.getName()).log(Level.SEVERE, null, ex);
    }
   }
    public void localizarJuego(){
    if(gestorBD.localizaVideojuego(clave,nombre))
     try{
     FacesContext.getCurrentInstance()
     .getExternalContext()
    .redirect("modificar_Videojuego.xhtml");
     }catch(IOException ex) {
     Logger.getLogger(ControllerManagedBean
     .class.getName()).log(Level.SEVERE, null, ex);
     }
     else
     try{
     juegoList = gestorBD.leerExistentes();
     FacesContext.getCurrentInstance()
     .getExternalContext()
    .redirect("error.xhtml");
     }catch(IOException ex) {
     Logger.getLogger(ControllerManagedBean
     .class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void modificarJuego(){
    Videojuego video_a_Cambiar = new Videojuego(clave, nombre,
    genero, plataforma,precio);
    if (gestorBD.modificarVideojuego(video_a_Cambiar)){
    try{
    juegoList = gestorBD.leerExistentes();
    FacesContext.getCurrentInstance().getExternalContext()
    .redirect("index.xhtml");
    }catch(IOException ex) {
    Logger.getLogger(ControllerManagedBean
    .class.getName()).log(Level.SEVERE, null, ex);}
    }
    }

}
