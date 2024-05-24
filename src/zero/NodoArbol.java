package zero;


public class NodoArbol {
    public Object valor;           //Valor que almacenado en el nodo
    public NodoArbol izquierda;    //Liga a la izquierda
    public NodoArbol derecha;      //Liga a la derecha

  /*
   * Constructor por omision. Inicializa con null el nodo creado.
   */
    public NodoArbol () {
        this (null, null, null);
    }

  /**
   * Constructor que inicializa el nodo con el valor dado como parametro
   * @param valor --  valor del nodo
   */
    public NodoArbol (Object valor) { 
        this (null, valor, null);
    }

  /**
   * Constructor que inicializa el nodo con los valores dados como parametros
   * @param v -- valor  del nodo
   * @param iz -- liga al hijo izquierdo
   * @param der -- liga al hijo derecho
   */
    public NodoArbol (NodoArbol iz, Object v, NodoArbol der) {
        valor = v;
        izquierda = iz;
        derecha = der;
    }
}