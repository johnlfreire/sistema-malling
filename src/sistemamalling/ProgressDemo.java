/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling;

/**
 *
 * @author johnpc
 */

import javafx.application.Platform;
import javafx.concurrent.Task;
import sistemamalling.util.GerarBancodeDados;
 
public class ProgressDemo{
    
    
    
    public static void main(String[] args) {
          System.out.println("oii");
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){                          
               GerarBancodeDados.CarregarCNJ();
                Platform.runLater(() -> {
               
                });
                return null;
            }
        };
        new Thread(task).start();
    }
 
}
    

