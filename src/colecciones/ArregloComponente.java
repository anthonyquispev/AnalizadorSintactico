
package colecciones;

import entidades.Componente;
import java.util.ArrayList;

public class ArregloComponente {
    private ArrayList<Componente> componentes;
    
    public ArregloComponente() {
        this.componentes = new ArrayList();
    }
    
    public boolean insertar (Componente c) {
        return componentes.add(c);
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }
    
    public int longitud(){
        return componentes.size();
    }

    //Transforma el ArrayList de tokens a una matriz de tokens
    public String[][] getTokens() {
        String[][] result = new String[this.componentes.size()][3];
        int i = 0;
        
        for (Componente cmp : this.componentes) {
            if (cmp != null) {
                result[i][0] = cmp.getToken();
                result[i][1] = cmp.getDescripcion();
                result[i][2] = cmp.getLexema();
                i++;
            }
        }
        return result;
    }
    
    public void limpiar() {
        this.componentes.clear();
    }
    
}
