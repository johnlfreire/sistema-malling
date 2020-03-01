/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.util;


import static javafx.application.Platform.runLater;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import javax.validation.constraints.NotNull;
/**
 *
 * @author johnpc
 */
public abstract  class Servico {
    public static void mascaraCPF(final TextField t) {
        maxField(t, 14);
        onlyNumber(t);
        t.textProperty().addListener((ObservableValue<? extends String> ov, String antigo, String novo) -> {
            checkCPF(t);
            if (!novo.isEmpty() && novo.length() > antigo.length()) {
                try {

                    switch (novo.length()) {
                        case 3:
                            t.setText(novo + ".");
                            runLater(t::end);

                            break;
                        case 7:
                            t.setText(novo + ".");
                            runLater(t::end);

                            break;
                        case 11:
                            t.setText(novo + "-");
                            runLater(t::end);

                            break;
                        case 15:
                            t.setText(antigo);
                            break;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void maxField(final TextField textField, final Integer length) {
        try{
        textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (newValue.length() > length) {
                textField.setText(oldValue);
            }
        });
        }catch (IllegalArgumentException e) {
                    e.printStackTrace();
          }
    }
    public static void onlyNumber(@NotNull final TextField text) {
        text.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            if (t.getCharacter().matches("[a-zA-Z\\s,.]+$")) {
                text.setStyle("-fx-focus-color: #FF0012;");
                t.consume();
            } else {
                text.setStyle(null);
            }
        });
        text.setStyle(null);
    }
     public static void checkCPF(TextField cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf.getText());
            cpf.setStyle("-fx-focus-color: #00f02d;-fx-background-color:#00f02d;");

        } catch (InvalidStateException e) {

            cpf.setStyle("-fx-focus-color: #FF0012;-fx-background-color:#FF0012");
            
        }
     }
     
     public static void validarCPF(final TextField t,final int n){
        maxField(t, n);
    
     }
     public static void atualizarFilds(@NotNull final TextField text){
         
 /*
text.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            
        }
        else
        {
           
        }
    }
});*/
     }
  public static String resources(){
  return sistemamalling.SistemaMalling.class.getResource("").toString();
  }
}
