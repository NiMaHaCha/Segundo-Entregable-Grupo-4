/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Modelo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean( name="controllerManagedBean" )
@SessionScoped
public class ControllerManagedBean {
    
    @ManagedProperty(value="#{formularioManagedBean}")
    private FormularioManagedBean formularioManagedBean;
    
    private String nombre;
    private String videojuego;
    private int puntaje;
    private String resultado;
    private Modelo calculator;

    public ControllerManagedBean() {
        calculator = new Modelo();
    }

    public String getResultado() {
        return resultado;
    }

    public FormularioManagedBean getFormularioManagedBean() {
        return formularioManagedBean;
    }

    public void setFormularioManagedBean(FormularioManagedBean formularioManagedBean) {
        this.formularioManagedBean = formularioManagedBean;
    }
    
    public void ejecutar() {
        try {
            nombre = formularioManagedBean.getNombre();
            videojuego = formularioManagedBean.getVideojuego();
            puntaje = formularioManagedBean.getPuntaje();
            resultado = calculator.obtenerNivelJugador(puntaje);
            // Se redirecciona a la página "resultado"
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("resultado.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getVideojuego() {
        return videojuego;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    
    
}
