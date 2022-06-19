package entidades;

import configuracion.Sistema;

public class Tabla {

    String M[][] = new String[Sistema.N_FILAS][Sistema.N_COLUMNAS];
    String produccionActual = "";

    public Tabla() {
        
        for (int i = 0; i < Sistema.N_FILAS; i++) 
            for (int j = 0; j < Sistema.N_COLUMNAS; j++) 
                M[i][j] = " ";
        
        
        // TERMINALES
        
        M[0][1] = "ESCRIBIR";   M[0][2] = ";";           M[0][3] = "LEER"; 
        M[0][4] = "PARA";       M[0][5] = "MIENTRAS";    M[0][6] = "SINO";
        M[0][7] = "SI";         M[0][8] = ",";           M[0][9] = ":";
        M[0][10] = "ENTERO";    M[0][11] = "REAL";       M[0][12] = "CADENA";
        M[0][13] = "CARACTER";  M[0][14] = "LOGICO";     M[0][15] = "{";
        M[0][16] = "}";         M[0][17] = "@";          M[0][18] = "OBJETO";
        M[0][19] = "VACIO";     M[0][20] = "RETORNAR";   M[0][21] = "CONSTANTE";
        M[0][22] = "[";         M[0][23] = "]";          M[0][24] = "PRINCIPAL";
        M[0][25] = "PUBLICO";   M[0][26] = "PRIVADO";    M[0][27] = "extiende";
        M[0][28] = "CLASE";     M[0][29] = "%";          M[0][30] = "/";
        M[0][31] = "*";         M[0][32] = "-";          M[0][33] = "_";
        M[0][34] = "+";         M[0][35] = "!";          M[0][36] = "||";
        M[0][37] = "&&";        M[0][38] = "=";          M[0][39] = "<>";
        M[0][40] = "<=";        M[0][41] = ">=";         M[0][42] = "<";          
        M[0][43] = ">";         M[0][44] = "(";          M[0][45] = ")";          
        M[0][46] = "falso";     M[0][47] = "verdad";     M[0][48] = "Cn";
        M[0][49] = "Id";        M[0][50] = "Car";        M[0][51] = "Cad";
        M[0][52] = "$";


        // NO TERMINALES
        
        M[1][0] = "S";             M[2][0] = "B";                M[3][0] = "A";
        M[4][0] = "AMBITO";        M[5][0] = "CLASE_BASE";       M[6][0] = "FUNCION_PRINCIPAL";
        M[7][0] = "CUERPO";        M[8][0] = "D";                M[9][0] = "C";
        M[10][0] = "CONSTRUCTOR";  M[11][0] = "E";               M[12][0] = "F";
        M[13][0] = "VECTOR";       M[14][0] = "CONSTANTE";       M[15][0] = "TIPO_DATO";
        M[16][0] = "SV";           M[17][0] = "MAS_VARIABLES";   M[18][0] = "H";
        M[19][0] = "G";            M[20][0] = "SF";              M[21][0] = "SP";
        M[22][0] = "PARAMETROS";   M[23][0] = "MAS_PARAMETROS";  M[24][0] = "J";
        M[25][0] = "I";            M[26][0] = "INSTRUCCIONES";   M[27][0] = "L";
        M[28][0] = "K";            M[29][0] = "CREAR_OBJETOS";   M[30][0] = "SENT_INV"; 
        M[31][0] = "PARAMETROS2";  M[32][0] = "MAS_PARAMETROS2"; M[33][0] = "N";
        M[34][0] = "M";            M[35][0] = "SENT_ASIG";       M[36][0] = "SENT_ASIG2";
        M[37][0] = "SENT_CF";      M[38][0] = "SENT_IF";         M[39][0] = "SENT_SINO";
        M[40][0] = "SENT_WHILE";   M[41][0] = "SENT_FOR";        M[42][0] = "SENT_LECT";
        M[43][0] = "SENT_ESCR";    M[44][0] = "EXPR_CAD";        M[45][0] = "Q";
        M[46][0] = "P";            M[47][0] = "EXP1";            M[48][0] = "X";
        M[49][0] = "Z";            M[50][0] = "INSTR_COND";      M[51][0] = "INSTR_COND2";
        M[52][0] = "IRT1";         M[53][0] = "OPER_RELAC";      M[54][0] = "OPER_LOGICO";
        M[55][0] = "OPER_LOGICO1"; M[56][0] = "OPER_ARITMETICO"; M[57][0] = "SV1";

        
        
        // PRODUCCIONES

        M[1][28]= "B A";
        M[2][28]= "B A"; M[2][52]= "&";
        M[3][28]= "} FUNCION_PRINCIPAL CUERPO { CLASE_BASE Id AMBITO CLASE";
        M[4][25]= "PUBLICO"; M[4][26]= "PRIVADO"; M[4][49]= "&";
        M[5][15]= "&"; M[5][27]= "Id extiende";
        M[6][16]= "&"; M[6][24]= "} INSTRUCCIONES { ) ( PRINCIPAL";
        M[7][10]= "CONSTRUCTOR D C"; M[7][11]= "CONSTRUCTOR D C"; M[7][12]= "CONSTRUCTOR D C"; M[7][13]= "CONSTRUCTOR D C"; M[7][14]= "CONSTRUCTOR D C"; M[7][16]= "&"; M[7][19]= "CONSTRUCTOR D C"; M[7][21]= "CONSTRUCTOR D C"; M[7][24]= "&"; M[7][28]= "&"; M[7][52]= "&";
        M[8][10]= "D C";   M[8][11]= "D C";   M[8][12]= "D C";   M[8][13]= "D C";   M[8][14]= "D C"; M[8][19]= "D C"; M[8][21]= "D C"; M[8][25]= "&";
        M[9][10]= "E";   M[9][11]= "E";   M[9][12]= "E";   M[9][13]= "E"; M[9][14]= "E"; M[9][19]= "E"; M[9][21]= "E"; M[9][25]= "&";
        M[10][25]= "} INSTRUCCIONES { ) PARAMETROS ( Id PUBLICO";
        M[11][10]= "F Id TIPO_DATO";  M[11][11]= "F Id TIPO_DATO";  M[11][12]= "F Id TIPO_DATO";  M[11][13]= "F Id TIPO_DATO";  M[11][14]= "F Id TIPO_DATO"; M[11][19]= "SP"; M[11][21]= "CONSTANTE";
        M[12][2]= "SV"; M[12][8]= "SV"; M[12][22]= "VECTOR"; M[12][44]= "SF";
        M[13][22]= "; ] Cn [";
        M[14][21]= "; Cn : Id CONSTANTE";
        M[15][10]= "ENTERO";  M[15][11]= "REAL";  M[15][12]= "CADENA";  M[15][13]= "CARACTER";  M[15][14]= "LOGICO";
        M[16][2]= "; MAS_VARIABLES"; M[16][8]= "; MAS_VARIABLES";M[16][24]= "; MAS_VARIABLES"; M[16][52]= "; MAS_VARIABLES";M[16][28]= "; MAS_VARIABLES"; M[16][16]= "; MAS_VARIABLES";M[16][10]= "; MAS_VARIABLES"; M[16][11]= "; MAS_VARIABLES";M[16][12]= "; MAS_VARIABLES"; M[16][13]= "; MAS_VARIABLES";M[16][14]= "; MAS_VARIABLES"; M[16][19]= "; MAS_VARIABLES";M[16][21]= "; MAS_VARIABLES"; M[16][25]= "; MAS_VARIABLES";M[16][20]= "; MAS_VARIABLES"; M[16][49]= "; MAS_VARIABLES";M[16][3]= "; MAS_VARIABLES"; M[16][1]= "; MAS_VARIABLES";M[16][17]= "; MAS_VARIABLES"; M[16][18]= "; MAS_VARIABLES";M[16][7]= "; MAS_VARIABLES"; M[16][5]= "; MAS_VARIABLES";M[16][4]= "; MAS_VARIABLES";
        M[17][2]= "&";   M[17][8]= "H G";
        M[18][2]= "&";   M[18][8]= "H G";
        M[19][8]= "SV1 Id ,";                      
        M[20][44]= "} ; Id RETORNAR INSTRUCCIONES { ) PARAMETROS (";
        M[21][19]= "} INSTRUCCIONES { ) PARAMETROS ( Id VACIO";
        M[22][10]= "MAS_PARAMETROS Id TIPO_DATO";  M[22][11]= "MAS_PARAMETROS Id TIPO_DATO";  M[22][12]= "MAS_PARAMETROS Id TIPO_DATO";  M[22][13]= "MAS_PARAMETROS Id TIPO_DATO";  M[22][14]= "MAS_PARAMETROS Id TIPO_DATO";M[22][45]= "&";
        M[23][8]= "J I"; M[23][45]= "&"; 
        M[24][8]= "J I"; M[24][45]= "&"; 
        M[25][8]= "Id TIPO_DATO ,";
        M[26][1]= "L K"; M[26][3]= "L K"; M[26][4]= "L K"; M[26][5]= "L K"; M[26][7]= "L K";  M[26][10]= "L K";  M[26][11]= "L K";  M[26][12]= "L K";  M[26][13]= "L K";  M[26][14]= "L K"; M[26][16]= "&";  M[26][17]= "L K";  M[26][18]= "L K"; M[26][20]= "&"; M[26][49]= "L K";
        M[27][1]= "L K"; M[27][3]= "L K"; M[27][4]= "L K"; M[27][5]= "L K"; M[27][7]= "L K";  M[27][10]= "L K";  M[27][11]= "L K";  M[27][12]= "L K";  M[27][13]= "L K";  M[27][14]= "L K"; M[27][16]= "&";  M[27][17]= "L K";  M[27][18]= "L K"; M[27][20]= "&"; M[27][49]= "L K";
        M[28][1]= "SENT_ESCR"; M[28][3]= "SENT_LECT"; M[28][4]= "SENT_CF";   M[28][5]= "SENT_CF"; M[28][7]= "SENT_CF"; M[28][10]= "SV SV1 Id TIPO_DATO";  M[28][11]= "SV SV1 Id TIPO_DATO";  M[28][12]= "SV SV1 Id TIPO_DATO";  M[28][13]= "SV SV1 Id TIPO_DATO";  M[28][14]= "SV SV1 Id TIPO_DATO"; M[28][17]= "; SENT_INV";  M[28][18]= "; CREAR_OBJETOS"; M[28][49]= "SENT_ASIG";
        M[29][18]= ") PARAMETROS ( Id Id OBJETO";  
        M[30][17]= ") PARAMETROS2 ( Id @ Id @";
        M[31][45]= "&"; M[31][49]= "MAS_PARAMETROS2 Id";
        M[32][8]= "N M"; M[32][45]= "&";
        M[33][8]= "N M"; M[33][45]= "&";
        M[34][8]= "Id ,";   
        M[35][49]= "; SENT_ASIG2 : Id";
        M[36][3]= "SENT_LECT"; M[36][44]= "EXP1"; M[36][46]= "falso";  M[36][47]= "verdad";  M[36][48]= "EXP1";  M[36][49]= "EXP1";  M[36][50]= "Car";  M[36][51]= "Cad";
        M[37][4]= "SENT_FOR";   M[37][5]= "SENT_WHILE"; M[37][7]= "SENT_IF";
        M[38][7]= "SENT_SINO } INSTRUCCIONES { ) INSTR_COND ( SI";
        M[39][1]= "&"; M[39][3]= "&"; M[39][4]= "&"; M[39][5]= "&"; M[39][6]= "} INSTRUCCIONES { SINO";   M[39][7]= "&"; M[39][10]= "&";  M[39][11]= "&";  M[39][12]= "&";  M[39][13]= "&";  M[39][14]= "&"; M[39][16]= "&";  M[39][17]= "&";  M[39][18]= "&"; M[39][20]= "&";   M[39][49]= "&";
        M[40][5]= "} INSTRUCCIONES { ) INSTR_COND ( MIENTRAS";
        M[41][4]= "} INSTRUCCIONES { ) Cn + Id ; Cn OPER_RELAC Id ; Cn : Id ENTERO ( PARA";  
        M[42][3]= ") ( LEER";
        M[43][1]= "; ) Q EXPR_CAD ( ESCRIBIR";
        M[44][49]= "Id"; M[44][51]= "Cad";  
        M[45][33]= "Q P"; M[45][45]= "&";  
        M[46][33]= "EXPR_CAD _";
        M[47][44]= "Z ) EXP1 ("; M[47][48]= "Z X"; M[47][49]= "Z X";
        M[48][48]= "Cn";  M[48][49]= "Id";
        M[49][2]= "&"; M[49][29]= "EXP1 OPER_ARITMETICO";  M[49][30]= "EXP1 OPER_ARITMETICO";  M[49][31]= "EXP1 OPER_ARITMETICO";  M[49][32]= "EXP1 OPER_ARITMETICO"; M[49][34]= "EXP1 OPER_ARITMETICO"; M[49][45]= "&";M[49][8]= "&";M[49][16]= "&";M[49][20]= "&";M[49][10]= "&";M[49][11]= "&";M[49][12]= "&";M[49][13]= "&";M[49][14]= "&";M[49][49]= "&";M[49][3]= "&";M[49][1]= "&";M[49][17]= "&";M[49][18]= "&";M[49][7]= "&";M[49][5]= "&";M[49][4]= "&";
        M[50][35]= "INSTR_COND2 IRT1";M[50][44]= "INSTR_COND2 ) INSTR_COND ("; M[50][49]= "INSTR_COND2 IRT1";                 
        M[51][36]= "INSTR_COND OPER_LOGICO";  M[51][37]= "INSTR_COND OPER_LOGICO"; M[51][45]= "&"; 
        M[52][35]= ") Id OPER_RELAC Id ( OPER_LOGICO1"; M[52][49]= "Id OPER_RELAC Id";
        M[53][38]= "=";  M[53][39]= "<>";  M[53][40]= "<=";  M[53][41]= ">=";  M[53][42]= "<";  M[53][43]= ">";
        M[54][36]= "||";  M[54][37]= "&&";
        M[55][35]= "!";
        M[56][29]= "%";  M[56][30]= "/";  M[56][31]= "*";  M[56][32]= "-"; M[56][34]= "+";
        M[57][9] = "SENT_ASIG2 :";   M[57][20] = "&";   M[57][10] = "&";   M[57][11] = "&";   M[57][12] = "&";   M[57][13] = "&";   M[57][14] = "&";   M[57][49] = "&";   M[57][3] = "&";   M[57][1] = "&";   M[57][17] = "&";   M[57][18] = "&";   M[57][7] = "&";   M[57][5] = "&";   M[57][4] = "&";   M[57][2] = "&";   M[57][8] = "&";   M[57][16] = "&";
    }

    // Determina si una expresión es un terminal
    public boolean Terminal(String car) {
        for (int i = 1; i < Sistema.N_COLUMNAS; i++) {
            if (M[0][i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    public String getProduccionActual() {
        return produccionActual;
    }

    
    // Determina si existe una intersección en al matriz
   public boolean ExisteInterseccion(String cimaPila, String lexemaActualEntrada) {
        int i, x = 0, y = 0;
        for (i = 1; i < Sistema.N_COLUMNAS; i++) {
            if (M[0][i].equals(lexemaActualEntrada)) {
                x = i;
                break;
            }
        }
        for (i = 1; i < Sistema.N_FILAS; i++) {
            if (M[i][0].equals(cimaPila)) {
                y = i;
                break;
            }
        }
        if (x == 0 || y == 0) {
            return false;
        } else if (M[y][x].equals(" ")) {
            return false;
        } else {
            produccionActual = M[y][x];
            return true;
        }
    }

}
