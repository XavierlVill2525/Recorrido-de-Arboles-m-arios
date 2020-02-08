
package Modelo;

import Controlador.ArbolExpresionGrafico;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Xavier Villavicencio
 */
public class Arbol {

    private Nodo nodo;
    int cant;
    int altura;

   

    public Nodo getRaiz() {
        return this.nodo;
    }

    public void setRaiz(Nodo r) {
        this.nodo = r;
    }

    public Arbol() {
        this.nodo = null;
    }

    public boolean agregar(int dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, nodo);
        return true;
    }

    public void insertar(Nodo nuevo, Nodo pivote) {
        if (this.nodo == null) {
            nodo = nuevo;
        } else {
            if (nuevo.getDato() <= pivote.getDato()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }

    }
    //cantidad de nodos del arbol
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(nodo);
        return ""+cant;
    }

    private void cantidadNodos(Nodo reco) {
        if (reco != null) {
            cant++;
            cantidadNodos(reco.getIzq());
            cantidadNodos(reco.getDer());
        }
    }

    //Borrar el que sea
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.nodo, x);
        this.setRaiz(z);
        return x;

    }

    private Nodo borrar(Nodo r, int x) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzq(borrar(r.getIzq(), x));
        } else if (compara < 0) {
            r.setDer(borrar(r.getDer(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzq() != null && r.getDer() != null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                Nodo cambiar = buscarMin(r.getDer());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDer(borrar(r.getDer(), x));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzq() != null) ? r.getIzq() : r.getDer();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }

    //buscar
    public boolean buscar(int x) {
        return (buscar(this.nodo, x));


    }

    private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzq(), x));
        } else if (compara < 0) {
            return (buscar(r.getDer(), x));
        } else {
            return (true);
        }
    }

    //buscar min
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzq() != null; r = r.getIzq());
        return (r);
    }
       //imprimir preorden
    public ArrayList preOrden() {
        ArrayList l=new ArrayList();
        preOrden(nodo,l);
        return l;
    }

    private void preOrden(Nodo n, ArrayList l) {
        if (n != null) {
            l.add(n.getDato() + " ");
            preOrden(n.getIzq(),l);
            preOrden(n.getDer(),l);
        }
    }
    //imprimir InOrden
    public ArrayList inOrden() {
        ArrayList l=new ArrayList();
        inOrden(nodo,l);
        return l;
    }

    private void inOrden(Nodo n,ArrayList l) {
        if (n != null) {
            inOrden(n.getIzq(),l);
            l.add(n.getDato() + " ");
            inOrden(n.getDer(),l);
        }
    }

//imprimir post orden
    public ArrayList postOrden() {
        ArrayList l=new ArrayList();
        postOrden(nodo,l);
        return l;
    }

    private void postOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            postOrden(reco.getIzq(),l);
            postOrden(reco.getDer(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    //con nivel
       public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(nodo, 1,l);
        return l;
    }

    private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzq(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDer(), nivel + 1, l);
        }
    }
    
    //hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.nodo, l);
        return (l);
    }

    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzq(), l);
            getHojas(r.getDer(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzq() == null && x.getDer() == null);
    }
    
    
    public int padre(int info) {
        if (info == 0 || this.nodo == null) {
            return 0;
        }
        Nodo x = padre(this.nodo, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzq() != null && x.getIzq().getDato()==(info)) || (x.getDer() != null && x.getDer().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzq(), info);
        if (y == null) {
            return (padre(x.getDer(), info));
        } else {
            return (y);
        }
    } 
    
    //dibujar arbol
     public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
}
