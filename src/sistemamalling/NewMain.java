/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemamalling.dao.DAOCidade;
import sistemamalling.dao.DAOEstado;
import sistemamalling.modelo.ModeloCidade;
import sistemamalling.modelo.ModeloEstado;

/**
 *
 * @author johnpc
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public void gerar (){
     File dir1 = new File("");
        try {
            String caminho =  dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\teste.txt";
            System.out.println ("Current dir : " + caminho );
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
             while(br.ready()){
               String linha = br.readLine();
               ModeloEstado model = new ModeloEstado();
               DAOEstado dao = new DAOEstado();
               String l[] = linha.split(";");
               long numero = Long.valueOf(l[0]);
               System.out.println(numero);
               
               model.setCodIbge(numero);
               model.setNome(l[1]);
               model.setSigla(l[2]);
               dao.adicionar(model);
             }
         
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    public void gerarCidade(){
     File dir1 = new File("");
        try {
            String caminho =  dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\Cidades.txt";
            System.out.println ("Current dir : " + caminho );
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
             while(br.ready()){
               String linha = br.readLine();
               ModeloCidade model = new ModeloCidade();
               DAOCidade dao = new DAOCidade();
               String l[] = linha.split(";");
               long numero = Long.valueOf(l[1]);
               System.out.println(numero);
               DAOEstado estado = new DAOEstado();
               List<ModeloEstado> modeloestado = estado.pesquisarEstadoIBGE(Long.valueOf(l[0]));
               
               model.setCodigoIBGE(numero);
               model.setNome(l[2]);
               model.setEstados(modeloestado.get(0));
               dao.adicionar(model);
             }
         
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
}
