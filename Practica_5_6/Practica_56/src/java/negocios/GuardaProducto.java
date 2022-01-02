/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.io.*;

public class GuardaProducto {

    public static void add(Producto p, String path) throws IOException {
        File archivo;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            //archivo = new File(ruta);
            archivo = new File(path);
            fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
            pw.println(p.getClave() + "," + p.getNombre()
                    + "," + p.getPrecio() + "," + p.getCantidad());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
