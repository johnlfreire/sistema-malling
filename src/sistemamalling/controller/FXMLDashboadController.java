/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sistemamalling.dao.DAOCatorio;
import sistemamalling.modelo.ModeloCartorio;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class FXMLDashboadController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML
    private Label lbTotalCartorios;
     @FXML
    private Label lbTotalSaopaulo;
     @FXML
    private ListView<ModeloCartorio> listAtualização;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       List<ModeloCartorio> listaModelo = new DAOCatorio().getLista();     
       lbTotalCartorios.setText( "" + listaModelo.size());
       List<ModeloCartorio> listaModeloCidade = new DAOCatorio().pesquisarEstado("São Paulo");
       lbTotalSaopaulo.setText( "" + listaModeloCidade.size());
       List<ModeloCartorio> m =  new DAOCatorio().getListaAtualizacao();
        ArrayList lista = new ArrayList();
        m.forEach((t) -> {
            lista.add(t.getAtualizacao() + "  :  "+t.getCartorio());
        });
        ObservableList<ModeloCartorio> items = 
                
       FXCollections.observableArrayList(lista);
    listAtualização.setItems(items);
      
    }    
    
    
}
