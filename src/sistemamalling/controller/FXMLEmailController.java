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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import sistemamalling.dao.DAOEstado;
import sistemamalling.modelo.ModeloEstado;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class FXMLEmailController implements Initializable {

    @FXML
    private TextField tf_Assunto;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private HTMLEditor htmlMessage;

    @FXML
    private TextField tf_From;

    @FXML
    private TextField tf_From1;

    @FXML
    private TextField tf_Assunto1;
    @FXML
    private ComboBox<?> cbEstado;

    @FXML
    private ComboBox<?> cdCidade;
    @FXML
    void sendEmail(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  carregar();
    }    
    public void carregar(){
  DAOEstado de = new DAOEstado();
        List<ModeloEstado> m = de.getLista();
        ArrayList lista = new ArrayList();
        m.forEach((t) -> {
            System.out.println(t.getNome());
            lista.add(t.getNome());
        });
        ObservableList listaEstado;
        listaEstado = FXCollections.observableArrayList(lista);
        cbEstado.setItems(listaEstado);
    }
}
