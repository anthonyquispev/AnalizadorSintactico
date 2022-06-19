package vista;

import entidades.Componente;
import colecciones.ArregloTokens;
import configuracion.Sistema;
import javax.swing.table.DefaultTableModel;
import entidades.Parser;

public class FrmAnalizadorSintactico extends javax.swing.JFrame {
    
    public FrmAnalizadorSintactico() {
        initComponents();
        this.setLocationRelativeTo(null);
        indice = 0;
    }
    
    int indice, zzz, auxIndice;
    String car, buffer;
    
    private int buscarToken(String x) {
        String[][] matriz = ArregloTokens.tokens;
        for (int i = 0; i < matriz.length; i++) {
            //Palabra reservada
            if (x.equals(matriz[i][1])) {
                return Integer.parseInt(matriz[i][0]);
            }
        }
        return 100;//Identificador
    }
    
    private int scanner() {
        buffer = "";
        int i=0, e=0;
        char a;
        boolean punto = false;
        
        while (true) {
            a = car.charAt(indice);
            if (a == '#' && i == 0) {
                return 0;
            } else if (a == '#') {
                switch(e) {
                    case 1: return buscarToken(buffer);
                    
                    //Número entero
                    case 3: return 500;
                    
                    case 30: return 302;
                    case 31: return 304;
                    case 32: return 307;
                    case 33: return 309;
                    
                    //Error
                    case 34:
                    case 35:
                    case 36: return 911;
                    
                    //Comentario
                    case 305: return 600;
                    
                    case 306:
                    case 308: indice = auxIndice; i=e=zzz=0; buffer="";break;
                    
                    //Carácter
                    case 325:
                        if (buffer.length() == 1) {
                            return 325;
                        } else {
                            if (buffer.charAt(buffer.length()-1) == '\'') {
                                i=e=zzz=0; buffer=""; return 502;
                            } else {
                                buffer = "\'";
                                indice--;
                                i=e=zzz=0; return 325;
                            }
                        }
                    //Cadena
                    case 326:
                        if (!buffer.equals("")) {
                            indice = auxIndice;
                        }
                        buffer = "\"";
                        zzz=0;
                        return 326;
                    
                    //Número real
                    case 501:
                        zzz=0;
                        if (punto){return 501;}
                        else {indice--; return 500;}
                    
                }
            } else if (zzz == 0) {
                buffer = buffer.trim();
                switch (e) {
                    case 0:
                        buffer += a;
                        if (Character.isLetter(a)) {e=1;i++;}
                        else if (Character.isDigit(a)) {e=3;i++;}
                        
                        //Posibles símbolos dobles
                        else if (a == '<') {e=30;i++;}
                        else if (a == '>') {e=31;i++;}
                        else if (a == '/') {e=32;i++;}
                        else if (a == '*') {e=33;i++;}
                        else if (a == '!') {e=34;i++;}
                        else if (a == '|') {e=35;i++;}
                        else if (a == '&') {e=36;i++;}
                        
                        //Símbolos unitarios
                        else if (a == '=') {indice++;return 312;}
                        else if (a == '+') {indice++;return 315;}
                        else if (a == '-') {indice++;return 316;}
                        else if (a == '%') {indice++;return 317;}
                        else if (a == ':') {indice++;return 318;}
                        else if (a == '(') {indice++;return 319;}
                        else if (a == ')') {indice++;return 320;}
                        else if (a == '{') {indice++;return 321;}
                        else if (a == '}') {indice++;return 322;}
                        else if (a == '[') {indice++;return 323;}
                        else if (a == ']') {indice++;return 324;}
                        
                        
                        else if (a == '\'') {e=zzz=325;i++;}
                        else if (a == '\"') {e=zzz=326;i++;auxIndice = indice+1;buffer="";}
                        
                        else if (a == '.') {indice++;return 327;}
                        else if (a == '@') {indice++;return 328;}
                        else if (a == ';') {indice++;return 329;}
                        else if (a == ',') {indice++;return 330;}
                        else if (a == '_') {indice++;return 331;}
                        
                        else if (a != ' ' && a != '\n') return 911;//Símbolo fuera del lenguaje
                        else e=0;
                        break;
                    case 1:
                        if ((Character.isDigit(a)) || (Character.isLetter(a))) {buffer += a; e=1;}
                        else return buscarToken(buffer);//identificador o palabra reservada
                        i++;
                        break;
                        
                    //Número    
                    case 3:
                        if (Character.isDigit(a)) {buffer += a; e=3;}
                        else if (a == '.'){zzz=501; e=501;}
                        else return 500;
                        i++;
                        break;
                        
                    /*---------SÍMBOLOS---------*/
                    //Posibles símbolos dobles    
                    case 30:
                        if (a == '=') { buffer += a; indice++; return 300;}
                        else if (a == '>') { buffer += a; indice++; return 301;}
                        else return 302;
                    case 31:
                        if (a == '=') { buffer += a; indice++; return 303;}
                        else return 304;
                    case 32:
                        if (a == '/') { buffer += a; indice++; zzz=305; return 305;}
                        else if (a == '*') { buffer += a; indice++; zzz=306; auxIndice=indice; return 306;}
                        else return 307;                    
                    case 33:
                        if (a == '/') { buffer += a; indice++; return 308;}
                        else return 309;                    
                    case 34:
                        if (a == '=') { buffer += a; indice++; return 310;}
                        else return 311;                    
                    case 35:
                        if (a == '|') { buffer += a; indice++; return 313;}
                        else return 911;                    
                    case 36:
                        if (a == '&') { buffer += a; indice++; return 314;}
                        else return 911;   
                }
                indice++;                
            } else { //zzz != 0
                switch(zzz) {
                    //Comentario corto
                    case 305:
                        if (a == '\n') {
                            zzz=i=e=0;
                            if (!buffer.equals("")) {indice++; return 600;}//Termina un comentario corto

                        } else {buffer+=a; e=305; i++;}break;
                        
                    //Comentario largo    
                    case 306:
                        e=306;
                        if (a == '*'){ zzz=308;}
                        else {buffer+=a;} i++; break;
                    case 308:
                        //Termina un comentario largo. Disminuye el índice para que luego lea un (*/) 
                        //como símbolo de cierre de comentario largo
                        if (a == '/') {indice--; zzz=0; return 600;}
                        //Disminuye el índice para otro posible ( */ )
                        else{buffer+='*'; indice--; zzz=306;} break; 

                    //Carácter
                    case 325:
                        buffer+=a;
                        zzz = 3252;
                        break;
                    case 3252:
                        if (a == '\'') {
                            buffer = String.valueOf(buffer.charAt(buffer.length() - 1));
                            zzz = 0;
                            indice++;
                            return 502;
                        } else {
                            zzz=0;
                            buffer = "\'";
                            indice--;
                            return 325;
                        }
                    //Cadena    
                    case 326:
                        if (a != '\"') {
                            buffer += a;
                        } else {
                            indice++; zzz=0;
                            return 503;
                        }
                    break;    
                    //Número real
                    case 501:
                        if (Character.isDigit(a)) {
                            if (!punto) buffer+='.';
                            buffer+=a;
                        } else {
                            zzz=0;
                            if (punto) return 501; //El buffer contiene como mínimo 1 dígito después del punto / Número real
                            else { indice--; return 500;} //Número entero
                        }
                        punto = buffer.contains(".") ? true:false;
                        break;
                }
                indice++;                
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLexico = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSintactico = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtReporteLexico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtReporteSintactico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 690));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 690));

        jPanel1.setBackground(new java.awt.Color(92, 146, 224));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 690));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 690));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAnalizar.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        btnAnalizar.setText("ANALIZAR");
        btnAnalizar.setBorder(null);
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 610, 230, 70));

        tblLexico.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tblLexico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOKEN", "DESCRIPCIÓN", "LEXEMA"
            }
        ));
        jScrollPane1.setViewportView(tblLexico);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 580, 210));

        txtTexto.setColumns(20);
        txtTexto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTexto.setRows(5);
        txtTexto.setBorder(null);
        jScrollPane3.setViewportView(txtTexto);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 520, 280));

        tblSintactico.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tblSintactico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PILA", "ENTRADA", "PRODUCCIÓN"
            }
        ));
        jScrollPane2.setViewportView(tblSintactico);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 1160, 160));

        jLabel4.setBackground(new java.awt.Color(0, 153, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ANALIZADOR SINTÁCTICO \"JAB\"");
        jLabel4.setRequestFocusEnabled(false);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 70));

        jSeparator1.setForeground(new java.awt.Color(51, 0, 51));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 640, 90));

        txtReporteLexico.setBackground(new java.awt.Color(102, 255, 204));
        txtReporteLexico.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtReporteLexico.setForeground(new java.awt.Color(0, 0, 0));
        txtReporteLexico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReporteLexico.setBorder(null);
        jPanel1.add(txtReporteLexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 390, 180, 30));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setText("Resultado Analizador Léxico");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 360, 230, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setText("Resultado Analizador Sintáctico");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 610, 250, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("TABLA DE TOKENS");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, -1, -1));

        txtReporteSintactico.setBackground(new java.awt.Color(102, 255, 204));
        txtReporteSintactico.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtReporteSintactico.setForeground(new java.awt.Color(0, 0, 0));
        txtReporteSintactico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReporteSintactico.setBorder(null);
        jPanel1.add(txtReporteSintactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 640, 180, 30));

        jLabel6.setBackground(new java.awt.Color(153, 0, 0));
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 90));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("CÓDIGO FUENTE");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setRequestFocusEnabled(false);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jSeparator2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 1100, 10));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        reset();
        int token;
        String descripcion, strToken;
        Componente cmp;
        car = txtTexto.getText() + '#';

        while (true) {
            token = scanner();
            if (token == 0) {
                txtReporteLexico.setText("Finalizó correctamente");
                break;
            } else if (token == 911) {
                txtReporteLexico.setText("Finalizó por error");
                break;
            } else {
                strToken = String.valueOf(token);
                descripcion = obtenerDescripcion(strToken);
                cmp = new Componente(strToken, descripcion, buffer);
                Sistema.componentes.insertar(cmp);               
            }
        }
        
        //Cargar tabla de tokens
        cargarTablaLexico();
     
        //Cargar análisis sintáctico
        cargarTablaSintactico();
        
    }//GEN-LAST:event_btnAnalizarActionPerformed
    private String obtenerDescripcion(String t) {
        String[][] matrizTokens = ArregloTokens.tokens;
        for(int i=0; i<matrizTokens.length; i++) {
            if (t.equals(matrizTokens[i][0]))
                return matrizTokens[i][2] + ": " + matrizTokens[i][3];
//                return matrizTokens[i][3];
        }
        return null;
    } 
    
    //Tabla de tokens
    private void cargarTablaLexico() {
        //Cargar tabla1
        String[] titulos = {"Token", "Descripción", "Lexema"};
        String[][] datos = Sistema.componentes.getTokens();
        DefaultTableModel modelo = new DefaultTableModel(datos, titulos);
        tblLexico.setModel(modelo);
        
        
    }    
    //Tabla de análisis sintáctico
    private void cargarTablaSintactico() {
        
        String entrada = obtenerEntrada();
        
        Parser p = new Parser();
        String[][] auxSalida2 = new String[500][3];
        boolean sintaxisCorrecta;
        int[] indiceSalida = new int[1];
        sintaxisCorrecta = p.sintactico(entrada, auxSalida2, indiceSalida);
        String[][] salida2 = new String[indiceSalida[0]][3];
        for (int i = 0; i < indiceSalida[0]; i++) {
            salida2[i][0] = auxSalida2[i][0];
            salida2[i][1] = auxSalida2[i][1];
            salida2[i][2] = auxSalida2[i][2];
        }

        String[] titulos = {"Pila", "Entrada", "Salida"};
        DefaultTableModel modelo = new DefaultTableModel(salida2, titulos);
        tblSintactico.setModel(modelo);
        
        if (sintaxisCorrecta) txtReporteSintactico.setText("Finalizó correctamente");
        else txtReporteSintactico.setText("Error");
        
    }
    
    // Transforma los tokens obtenidos por el léxico en una cadena de entrada
    // para el analizador sintáctico
    private String obtenerEntrada() {
        //Obtenemos la tabla de tokens
        String[][] datos = Sistema.componentes.getTokens();
        String entrada = "";
        
        /*
            NO SE CONSIDERAN LOS SIGUIENTES TOKENS
        
            600     -->     Comentario
            305     -->     Símbolo comentario corto
            306     -->     Símbolo comentario largo apertura
            308     -->     Símbolo comentario largo cerradura
        
            ADEMÁS, SE DEBEN TRANSFORMAR ALGUNOS LEXEMAS EN EXPRESIONES DEFINIDAS
            EN NUESTRA GRAMÁTICA.
            ESTO CON LA FINALIDAD DE QUE LA ENTRADA PUEDA SER LEÍDA FÁCILMENTE POR
            EL ANALIZADOR SINTÁCTICO
        
            Identificador   -->     Id
            Número          -->     Cn     
            Cadena          -->     Cad
            Carácter        -->     Car
        
        */
        
        for (int i = 0; i < datos.length; i++) {
            String aux = datos[i][0];
            if (!aux.equals("600") && !aux.equals("305") && !aux.equals("306")
                   &&  !aux.equals("308")) {
                if (aux.equals("100")) {
                    entrada += "Id ";
                } else if (aux.equals("500") || aux.equals("501")) {
                    entrada += "Cn ";
                } else if (aux.equals("502")) {
                    entrada += "Car ";
                } else if (aux.equals("503")) {
                    entrada += "Cad ";
                } else {
                    entrada += datos[i][2] + " ";
                }
            }
        }
        return entrada;
    }

    //Limpia los tokens obtenidos anteriormente para realizar un nuevo análisis
    private void reset() {
        Sistema.componentes.limpiar();
        indice = zzz = auxIndice=0;
        buffer = car = "";
    }
     
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAnalizadorSintactico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAnalizadorSintactico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAnalizadorSintactico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAnalizadorSintactico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAnalizadorSintactico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblLexico;
    private javax.swing.JTable tblSintactico;
    private javax.swing.JTextField txtReporteLexico;
    private javax.swing.JTextField txtReporteSintactico;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}
