/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sistemamalling.util.GerarBancodeDados;


/**
 *
 * @author johnpc
 */
public class SistemaMalling extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLHome_1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Stage stado =new Stage();
        Parent SplashScreen = FXMLLoader.load(getClass().getResource("view/SplashScreem.fxml"));
        Scene scene2 = new Scene(SplashScreen);
        stado.setScene(scene2);
        stado.show();
        
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws IOException{  
          if(GerarBancodeDados.CarregarConfig()){
              GerarBancodeDados.gerarEstados();
              GerarBancodeDados.gerarCidade();
              GerarBancodeDados.CarregarCNJ();
              GerarBancodeDados.alterarConfig("False");
            }
            
                
                Platform.runLater(() -> {
                    stado.close();
                     stage.show();            
                });
                return null;
            }
        };
        new Thread(task).start();
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    launch();
    }
 
}
