/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling;

import org.apache.log4j.Logger;





/**
 *
 * @author johnpc
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
     /* Get actual class name to be printed on */
  public static Logger logger = Logger.getLogger(NewMain1.class.getName());
    public static void main(String[] args) {

 // logger.info("Iniciando procedimentos");
  //logger.info("venda salvo no banco com sucesso");
  //logger.debug("gfdsgfd");
  //logger.warn("erro");
String texto = "38180857816";
    if(texto.length() == 11){
        String cpf = texto.substring(0, 3) + "."+texto.substring(3, 6)+"."+texto.substring(6, 9)+"-"+texto.substring(9, 11);
         System.out.println(cpf);                
    }

    }
    
}
