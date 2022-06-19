package entidades;

import configuracion.Sistema;

public class Parser {

    Tabla tabla = new Tabla();
    String pila[] = new String[Sistema.TOTAL_PILA];
    String lexemaActualEntrada; //guarda una expresión de la cadena de entrada
    int indicePila; // índice de la pila
    String cadenaEntrada; // guarda la cadena de entrada
    int indiceEntrada; // índice actual de cadena de entrada
    int auxIndiceEntrada;

    public Parser() {
        this.pila[0] = "$";
        this.pila[1] = Sistema.PRIMER_NOTERMINAL;
        this.indicePila = 2;
        this.indiceEntrada = 0;
        this.cadenaEntrada = "";
    }
    
    // Retorna un lexema de la cadena de entrada
    private String devolverLexema() {
        String tira = "";   
        for (int i = indiceEntrada; i < cadenaEntrada.length(); i++) {
            if (cadenaEntrada.charAt(i) != ' ') {
                tira = tira + cadenaEntrada.charAt(i);
                auxIndiceEntrada = i + 1;
                if (cadenaEntrada.charAt(i) != '$') {
                    if (cadenaEntrada.charAt(i+1) == ' ') {
                        break;
                    }                    
                }                
            }
        }
        return tira.trim();
    }
    
    // Ingresa un elemento a la pila
    private void Apilar (String produccion) {
        String t;
        String[] auxProd = produccion.split(" ");
        for (String prod : auxProd) {
            pila[indicePila] = prod;
            indicePila++;
        }        
    }  
    
    // Muestra los elementos restantes de la cadena de entrada
    private String RetCad() {
        String tempCad = "";
        int j;
        for (j = indiceEntrada; j < cadenaEntrada.length(); j++) {
            tempCad = tempCad + cadenaEntrada.charAt(j);
        }
        return tempCad;
    }
    
    // Muestra los elementos actuales de la pila
    private String RetPila() {
        String tempCad = "";
        for (int j = 0; j < indicePila; j++) {
            tempCad = tempCad + pila[j];
            if (j != indicePila) tempCad+=" ";
        }
        return tempCad;
    }
    
    // Actualizar indiceEntrada
    private void mover(int pos) {
        indiceEntrada = pos;
    }    
    
    private int RetPos() {
        return auxIndiceEntrada;
    }
    
    
    // Analizador sintáctico
    public boolean sintactico(String entrada, String salida[][], int[] indiceSalida) {
        
        cadenaEntrada += entrada + " $";
        
        int tamañoSalida = 0; // almacena el tamaño de la matriz salida
        String cimaPila; // almacena la cima de la pila
        String produccion = "";
        int pos = 0;
        do {
            lexemaActualEntrada = devolverLexema();
            cimaPila = pila[indicePila - 1];
            pos = RetPos();
            
            //Almacena los valores de la pila y la entrada la matriz salida
            salida[tamañoSalida][0] = RetPila();
            salida[tamañoSalida][1] = RetCad();
            
            //Retorna 1 si cimaPila es un terminal, sino 0
            
            if (tabla.Terminal(cimaPila)) {
                if (cimaPila.equals(lexemaActualEntrada)) {
                    indicePila--; // se descuenta un símbolo de la pila
                    mover(pos); // se lee la cadena de entrada desde la posicion "auxIndiceEntrada"
                    salida[tamañoSalida][2] = "";
                } else {
                    salida[tamañoSalida][2] = " ERROR DE SINTAXIS";
                    indiceSalida[0] = tamañoSalida + 1;
                    return false;
                }
            } else if (tabla.ExisteInterseccion(cimaPila, lexemaActualEntrada)) {
                produccion = tabla.getProduccionActual();
                salida[tamañoSalida][2] = cimaPila + " --> " + produccion;
                indicePila--; // se descuenta un simbolo de la pila
                // si la produccion es & no se mete en la pila
                if (!produccion.equals("&")) {
                    Apilar(produccion);
                }
            } else {
                salida[tamañoSalida][2] = " ERROR DE SINTAXIS";
                indiceSalida[0] = tamañoSalida + 1;
                return false;
            }
            tamañoSalida++;
        } while (!cimaPila.equals("$"));
        salida[tamañoSalida-1][2] = " SE ACEPTA";
        indiceSalida[0] = tamañoSalida;
        return true;
    }
}