
package entidades;

public class Componente {

    private String token;
    private String descripcion;
    private String lexema;

    public Componente(String token, String descripcion, String lexema) {
        this.token = token;
        this.descripcion = descripcion;
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {    
        this.token = token;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
}
