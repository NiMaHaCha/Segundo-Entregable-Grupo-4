/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GestorBD {
    private Connection conexion=null;
    private Statement stm = null;
    private ResultSet videoResultSet;
    private Integer clave;
    private String nombre, genero, plataforma,precio;
    private int resultUpdate = 0;

    public ArrayList<Videojuego> leerExistentes(){
    ArrayList<Videojuego> ueas = new ArrayList<Videojuego>();
    Videojuego videoHallada;
    try{
    ConectaBD conectaBD = new ConectaBD();
    conexion = conectaBD.getConexion();
    stm = conexion.createStatement();
    videoResultSet = stm.executeQuery("select * from juego");
    if(!videoResultSet.next()){
    System.out.println(" No se encontraron registros");
    conexion.close();
    return null;
    }else{
    do{
    clave = videoResultSet.getInt("clave");
    nombre = videoResultSet.getString("nombre");
    genero = videoResultSet.getString("genero");
    plataforma = videoResultSet.getString("plataforma");
    precio = videoResultSet.getString("precio");
    videoHallada = new Videojuego(clave,nombre,genero,plataforma,precio);
    ueas.add(videoHallada);
    }while(videoResultSet.next());
    conexion.close();
    return ueas;
    }
    }catch(Exception e){
    System.out.println("Error en la base de datos.");
    e.printStackTrace();
    return null;}
    }
    public boolean guardarVideojuego(Videojuego juegoNuevo) {
       try{
       ConectaBD conectaBD = new ConectaBD();
       conexion = conectaBD.getConexion();
       stm = conexion.createStatement();
       if(juegoNuevo.getGenero().isEmpty() || juegoNuevo.getNombre().isEmpty() || juegoNuevo.getPlataforma().isEmpty() || juegoNuevo.getClave()== null){
           return false;
       }else{
            resultUpdate = stm.executeUpdate("INSERT INTO juego VALUES("
            +juegoNuevo.getClave()
           +",'" + juegoNuevo.getNombre()
           +"','" +juegoNuevo.getGenero()
           +"','"+juegoNuevo.getPlataforma()
           +"','"+juegoNuevo.getPrecio()+"');");
            if(resultUpdate != 0){
            conexion.close();
            return true;
            }else{
            conexion.close();
            System.out.println("No se pudo insertar el Videojuego.");
            return false;
            }
       }
       }catch (Exception e) {
       System.out.println("Error en la base de datos.");
       e.printStackTrace();
       return false;
       }
       
    }
    public boolean borrarVideojuego(Videojuego videoABorrar) {
   try{
    ConectaBD conectaBD = new ConectaBD();
    conexion = conectaBD.getConexion();
    stm = conexion.createStatement();
    resultUpdate = stm.executeUpdate("DELETE FROM juego WHERE(clave = '"+videoABorrar.getClave()
   +"' AND nombre ='"
   +videoABorrar.getNombre()+"');");
    if(resultUpdate != 0){
    conexion.close();
    return true;
    }else{
    conexion.close();;
    System.out.println("No se pudo borrar el Videojuego.");
    return false;
    }
   }catch (Exception e) {
    System.out.println("Error en la base de datos.");
    e.printStackTrace();
    return false;
   }
   }
    public boolean localizaVideojuego(Integer clave, String nombre) {
       try{
        ConectaBD conectaBD = new ConectaBD();
        conexion = conectaBD.getConexion();
        stm = conexion.createStatement();
        videoResultSet = stm.executeQuery("SELECT * FROM juego WHERE(clave = '"
        +clave+"' AND nombre ='"
        +nombre+"');");
        if(!videoResultSet.next()){
        System.out.println(" No se encontraron registros");
        conexion.close();
        return false;
        }else{
        conexion.close();
        return true; }
       }catch(Exception e){
        System.out.println("Error en la base de datos.");
        e.printStackTrace();
        return false;
       }
   }
   public boolean modificarVideojuego(Videojuego videoACambiar) {
       try{
        ConectaBD conectaBD = new ConectaBD();
        conexion = conectaBD.getConexion();
        stm = conexion.createStatement();
        resultUpdate = stm.executeUpdate("UPDATE juego SET nombre = '"
        +videoACambiar.getNombre()
       +"', genero = '"+ videoACambiar.getGenero()
       +"', plataforma = '"+ videoACambiar.getPlataforma()
       +"', precio = '"+ videoACambiar.getPrecio()
       +"' WHERE clave = "
       +videoACambiar.getClave()+";");
        if(resultUpdate != 0){
        conexion.close();
        return true;
        }else{
        conexion.close();;
        System.out.println("No se pudo borrar el Videojuego.");
        return false;
        }
       }catch (Exception e) {
        System.out.println("Error en la base de datos.");
        e.printStackTrace();
        return false;
       }
   } 
    
}
