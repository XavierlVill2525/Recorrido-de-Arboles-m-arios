
package Controlador;

import Modelo.Arbol;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Xavier Villavicencio
 */
public class SimuladorArbolBinario {

    Arbol miArbol = new Arbol();

    public SimuladorArbolBinario() {
    }

    public boolean insertar(Integer dato) {
        return (this.miArbol.agregar(dato));
    }

    public String borrar(Integer dato) {
        Integer x = this.miArbol.borrar(dato);
        if (x == null) {
            return ("No existe el dato en el arbol");
        }
        return ("Borrado el dato: " + x.toString());
    }

    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }

    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }

    public String posOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }


    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "\n";
            i++;
        }
        return (r);
    }
    
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
