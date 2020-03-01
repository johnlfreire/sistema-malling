/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sistemamalling.SistemaMalling;

/**
 *
 * @author Edidelson
 */
public class FXMLGenerico {

    /**
     * Shows the person overview scene.
     *
     * @param rootLayout
     * @param fxml
     */
    public void showPersonOverview(AnchorPane rootLayout, String fxml) {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(SistemaMalling.class.getResource(fxml));
            rootLayout.getChildren().clear();
            //System.out.println(loader.load().toString());
            Node overviewPage = loader.load();
            rootLayout.getChildren().add(overviewPage);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

}
