/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import sistemamalling.util.GerarArquivo;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class FXMLArquivoController implements Initializable {

     @FXML
    private ComboBox<?> cbEstado;

    @FXML
    void batArquivoPdf(ActionEvent event) {

    }

    @FXML
    void btaAquivoTexto(ActionEvent event) {
 FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open Resource File");
File file = fileChooser.showSaveDialog(null);
GerarArquivo gerar = new GerarArquivo();
gerar.gerarTXT(file,"S");

    }

    @FXML
    void btaPlanilhaExcel(ActionEvent event) {
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open Resource File");
fileChooser.setInitialFileName(".xlsx");

fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xlsx"));
File file = fileChooser.showSaveDialog(null);
GerarArquivo gerar = new GerarArquivo();
gerar.gerarExel(file,"S");
    }

    @FXML
    void btaSair(ActionEvent event) {

    }

    @FXML
    private ProgressIndicator progIndicador;

    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList listaEstado;
        listaEstado = FXCollections.observableArrayList("Tudo","Acre", "Alagoas", "Amazonas", "Amapá", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Minas", "Gerais", "Mato Grosso do Sul", "Mato Grosso", "Pará", "Paraíba", "Pernambuco", "Piauí", "Paraná", "Rio de Janeiro", "Rio Grande do Norte", "Rondônia", "Roraima", "Rio Grande do Sul", "Santa Catarina", "Sergipe", "São Paulo", "Tocantins");
         cbEstado.setItems(listaEstado);
    }    
    
}
