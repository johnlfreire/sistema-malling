/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sistemamalling.util.Servico;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private AnchorPane paneCentral;
    @FXML
    private AnchorPane acDash;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private VBox leftSideBarScroolPan;
    @FXML
    private ImageView imgMail;
    @FXML
    private ImageView imgHome;
    @FXML
    private ImageView imgSave;
    @FXML
    private ImageView imgTool;

    @FXML
    void mgCLik(ActionEvent event) {
        if (sideMenuToogleBtn.isSelected()) {
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(400.0), acDash);
            sideMenu.setByX(-140);
            sideMenu.play();
            acDash.getChildren().clear();
            TranslateTransition sideMenu2 = new TranslateTransition(Duration.millis(400.0), paneCentral);
            sideMenu2.setByX(100);
            sideMenu2.play();
        } else {
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(400.0), acDash);
            sideMenu.setByX(140);
            sideMenu.play();
            acDash.getChildren().add(leftSideBarScroolPan);;
            TranslateTransition sideMenu2 = new TranslateTransition(Duration.millis(400.0), paneCentral);
            sideMenu2.setByX(-100);
            sideMenu2.play();

        }

    }

    @FXML
    void btHome(ActionEvent event) {
        isActived(1);
        loadScreens("view/FXMLDashboad_1.fxml");
    }

    @FXML
    void btAMail(ActionEvent event) throws IOException {
        isActived(2);
        loadScreens("view/FXMLEmail.fxml");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isActived(1);
        FXMLLoader loader = new FXMLLoader(sistemamalling.SistemaMalling.class.getResource("view/FXMLDashboad_1.fxml"));
       
        AnchorPane overviewPage;
        try {
            overviewPage = (AnchorPane) loader.load();
            paneCentral.getChildren().add(overviewPage);
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadScreens(String fxmlScreen) {

        FXMLGenerico generico = new FXMLGenerico();
        generico.showPersonOverview(paneCentral, fxmlScreen);
    }

    

    @FXML
    void btaCadastro(ActionEvent event) {
        isActived(3);
        loadScreens("view/FXMLCadastro_1.fxml");

    }
@FXML
    void btaFerramentas(ActionEvent event) {
       isActived(4);
       //loadScreens("view/FXMLEmail.fxml");
    }
    public void isActived(int value) {
        Image mailR = new Image(Servico.resources() + "resource/Message Filled_52_IndialR.png");
        Image mailG = new Image(Servico.resources() + "resource/Message Filled_52_Gray.png");
        Image homeG = new Image(Servico.resources() + "resource/Media Q_50_Gray.png");
        Image homeR = new Image(Servico.resources() + "resource/Media Queries_50_IndialR.png");
        Image saveG = new Image(Servico.resources() + "resource/Save_52_Gray.png");
        Image saveR = new Image(Servico.resources() + "resource/Save_52_IndialR.png");
        Image toolG = new Image(Servico.resources() + "resource/Settings_64_Gray.png");
        Image toolR = new Image(Servico.resources() + "resource/Settings_64_IndialR.png");

        switch (value) {
            case 1:
                imgHome.setImage(homeR);
                imgMail.setImage(mailG);
                imgSave.setImage(saveG);
                imgTool.setImage(toolG);
                break;
            case 2:
                imgHome.setImage(homeG);
                imgMail.setImage(mailR);
                imgSave.setImage(saveG);
                imgTool.setImage(toolG);
                break;
            case 3:
                imgHome.setImage(homeG);
                imgMail.setImage(mailG);
                imgSave.setImage(saveR);
                imgTool.setImage(toolG);
                break;
            case 4:
                imgHome.setImage(homeG);
                imgMail.setImage(mailG);
                imgSave.setImage(saveG);
                imgTool.setImage(toolR);
                break;
            default:
                System.out.println("Este não é uma imagem válido!");

        }
    }

}
