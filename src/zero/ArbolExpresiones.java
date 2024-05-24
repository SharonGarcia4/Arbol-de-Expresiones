package zero;

import java.util.*;

/**
 * Clase para generar arboles binarios a partir de expresiones aritmeticas
 * @author  Amparo L�pez Gaona
 * @version 1a. ed.
 */
public class ArbolExpresiones {
    Pila pOperandos;               // Pila de operandos
    Pila pOperadores;              // Pila de operadores
    final String blanco;           // Cadena de espacios en blanco
    final String operadores;       // Cadena con operadores para expresiones

  /**
   * Constructor por omision
   */
    public ArbolExpresiones() {
	pOperandos = new Pila();
	pOperadores = new Pila();
	blanco = " \t";
	operadores = ")+-*/%^(";  //acomodados por precedencia;
    }

  /**
   * Metodo para construir un arbol para una expresion aritmetica dada.
   * @param expresion -- Cadena con la expresion aritmetica
   * @return NodoArbol -- nodo raiz del arbol creado
   */
    public NodoArbol construirArbol(String expresion) {
       	StringTokenizer tokenizer;
	String token;
	NodoArbol raiz = null;

	tokenizer = new StringTokenizer(expresion, blanco+operadores, true);
	while (tokenizer.hasMoreTokens()) {
	    token = tokenizer.nextToken();
	    if (blanco.indexOf(token) >= 0) 
		;               // Es un espacio en blanco, se ignora
	    else if (operadores.indexOf(token) < 0) {
		                // Es operando y lo guarda como nodo del arbol
		pOperandos.push(new NodoArbol(token));
	    } else if(token.equals(")")) { // Saca elementos hasta encontrar (
		while (!pOperadores.estaVacia() && !pOperadores.top().equals("(")) {
		    guardarSubArbol();
		}
		pOperadores.pop();  // Saca el parentesis izquierdo
	    } else {
		if (!token.equals("(") && !pOperadores.estaVacia()) {
		           //operador diferente de cualquier parentesis
		    String op = (String) pOperadores.top();
		    while (!op.equals("(") && !pOperadores.estaVacia() 
			   && operadores.indexOf(op) >= operadores.indexOf(token)) {
			guardarSubArbol();
			if (!pOperadores.estaVacia()) 
			    op = (String)pOperadores.top();
		    }
		}
		pOperadores.push(token);  //Guarda el operador
	    }
	}
	//Sacar todo lo que queda
	raiz = (NodoArbol)pOperandos.top();
	while (!pOperadores.estaVacia()) {
	    if (pOperadores.top().equals("(")) {
		pOperadores.pop();
	    } else {
	    guardarSubArbol();
	    raiz = (NodoArbol) pOperandos.top();
	    }
	}
	return raiz;
    }

  /*
   * Metodo privado para almacenar en la pila un subarbol
   */
    private void guardarSubArbol() {
	NodoArbol op2 = (NodoArbol)pOperandos.pop();
	NodoArbol op1 = (NodoArbol)pOperandos.pop();
	pOperandos.push(new NodoArbol(op1, pOperadores.pop(), op2));

    }

    /**
     * Metodo para imprimir un arbol en inorden
     * @param n -- nodo raiz
     */
    public String imprime(NodoArbol n) {
	if (n == null) return "";
	return imprime(n.izquierda) + n.valor + "" + imprime(n.derecha);
    }

    /**
     * Metodo para imprimir un arbol en postorden
     * @param n -- nodo raiz
     */
    public String imprimePos(NodoArbol n) {
        if (n == null) return "";
        return imprimePos(n.izquierda) + imprimePos(n.derecha) + n.valor + " ";
    }

    /**
     * Metodo para imprimir un arbol en preorden
     * @param n -- nodo raiz
     */
    public String imprimePre(NodoArbol n) {
        if (n == null) return "";
        return n.valor + " " + imprimePre(n.izquierda) + imprimePre(n.derecha);
    }

    /*public static void main (String[] pps) {
        String expre = "3+5*8/2";
	ArbolExpresiones expr = new ArbolExpresiones();
        NodoArbol raiz = expr.construirArbol(expre);
        System.out.print("El arbol es ");
        expr.imprime(raiz);
        System.out.print("\nEl arbol en postfija es ");
        expr.imprimePos(raiz);
        //System.out.println("\n");
        System.out.print("\nEl arbol en prefija es ");
        expr.imprimePre(raiz);
	System.out.println("\n\n");
	    
    }*/
}