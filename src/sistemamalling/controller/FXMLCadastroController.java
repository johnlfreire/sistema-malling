/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import sistemamalling.dao.DAOCatorio;
import sistemamalling.dao.DAOCidade;
import sistemamalling.dao.DAOEstado;
import sistemamalling.modelo.ModeloCartorio;
import sistemamalling.modelo.ModeloCidade;
import sistemamalling.modelo.ModeloEstado;
import sistemamalling.util.Servico;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author johnpc
 */
public class FXMLCadastroController implements Initializable {
  
    public static Logger logger = Logger.getLogger(FXMLCadastroController.class.getName());
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfMudancaNome;

    @FXML
    private TextField tfAcervo;

    @FXML
    private DatePicker dtInstalacao;

    @FXML
    private TextField tfHrFuncionamento;

    @FXML
    private DatePicker dtDataNasc;

    @FXML
    private TextField tfCnpj;

    @FXML
    private TextField tfSeade;

    @FXML
    private Button btcSalvarCadastro;

    @FXML
    private TextField tfCnj;

    @FXML
    private TextField tfNomeOficial;

    @FXML
    private DatePicker dtTitularidade;

    @FXML
    private ComboBox<?> cbCidade;

    @FXML
    private TextField tfEmail;

    @FXML
    private DatePicker dt1Cas;

    @FXML
    private TextField tfComarca;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfRegional;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfRg;

    @FXML
    private TextField tfSubstituto;

    @FXML
    private TextField tfDistrito;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private TextField tfNomeCartorio;

    @FXML
    private TextField tfCPFOficial;

    @FXML
    private DatePicker dt1Nasc;

    @FXML
    private DatePicker dt1Obito;

    @FXML
    private ComboBox<?> cbPesquisar;

    @FXML
    private ComboBox<?> cbUf;
    //private ModeloCartorio mCartorio;

    @FXML
    private TableView<?> tabelaCartorio;
    private ModeloCidade modelCidade;
    private ModeloEstado modelestado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Servico.mascaraCPF(tfCPFOficial);
        Servico.onlyNumber(tfCnj);
        Servico.validarCPF(tfCnj, 6);
        Servico.atualizarFilds(tfCnj);
        criarTabela();
        preencherEstado();

        cbUf.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
            long selecao = cbUf.getSelectionModel().getSelectedIndex() + 1;
            preencherCidades(selecao);
        });

        //CarregarTabela();
        //System.out.println();
    }

    @FXML
    void salvarCadastro(ActionEvent event) {
        TrayNotification tray = new TrayNotification();
        try {
            ModeloCartorio mCartorio = new ModeloCartorio();
            mCartorio.setCnj(tfCnj.getText());
            mCartorio.setCartorio(tfNomeCartorio.getText());
            mCartorio.setOficial(tfNomeOficial.getText());
            mCartorio.setEndereco(tfEndereco.getText());
            mCartorio.setDistrito(tfDistrito.getText());
            mCartorio.setComarca(tfComarca.getText());
            mCartorio.setCep(tfCep.getText());
            mCartorio.setCidade((String) cbCidade.getSelectionModel().getSelectedItem());
            mCartorio.setUf((String) cbUf.getSelectionModel().getSelectedItem());
            mCartorio.setRegional(tfRegional.getText());
            mCartorio.setTelefone(tfTelefone.getText());
            mCartorio.setEmail(tfEmail.getText());
            mCartorio.setFuncionamento(tfHrFuncionamento.getText());
            mCartorio.setSeade(tfSeade.getText());
            mCartorio.setAcervo(tfAcervo.getText());
            mCartorio.setSubstituto(tfSubstituto.getText());
            mCartorio.setRgDoOficial(tfRg.getText());
            mCartorio.setCpfDoOficial(tfCPFOficial.getText());
            mCartorio.setCnpj(tfCnpj.getText());
            if (dtDataNasc.getValue() != null) {
                mCartorio.setDataNascimento(Date.valueOf(dtDataNasc.getValue()));
            }
            if (dtInstalacao.getValue() != null) {
                mCartorio.setAnoDeIstalacao(Date.valueOf(dtInstalacao.getValue()));
            }
            if (dtTitularidade.getValue() != null) {
                mCartorio.setDatadeTitularidade(Date.valueOf(dtTitularidade.getValue()));
            }
            mCartorio.setNomeAntigo(tfMudancaNome.getText());
            if (dt1Nasc.getValue() != null) {
                mCartorio.setCertidaoDeNascimento(Date.valueOf(dt1Nasc.getValue()));
            }
            if (dt1Cas.getValue() != null) {
                mCartorio.setCertidaoDeCasamento(Date.valueOf(dt1Cas.getValue()));
            }
            if (dt1Obito.getValue() != null) {
                mCartorio.setCertidaoDeObito(Date.valueOf(dt1Obito.getValue()));
            }
            mCartorio.setAtualizacao(Date.valueOf(LocalDate.now()));
            DAOCatorio dc = new DAOCatorio();
            dc.adicionar(mCartorio);
            tray.setTitle("Salvo com Sucesso!");
            tray.setMessage("O Cartório " + tfNomeCartorio.getText() + " foi salvo com sucesso!");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setAnimationType(AnimationType.SLIDE);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(2));
            logger.info("O Cartório " + tfNomeCartorio.getText() + " foi salvo com sucesso!");
            limparCadastro(event);
        } catch (NumberFormatException e) {
           logger.warn("Erro ao Salvar Cadastro:  "+ e);
        }
    }

    @FXML
    void editarCadastro(ActionEvent event) {

        java.util.List<ModeloCartorio> lancamentos = new DAOCatorio().pesquisarCnj(tfCnj.getText());
        for (ModeloCartorio lancamento : lancamentos) {
            // preencherText(lancamento);
        }
        //lancamentos.get(0);

        TrayNotification tray = new TrayNotification();
        try {
            ModeloCartorio mCartorio = lancamentos.get(0);
            mCartorio.setCnj(tfCnj.getText());
            mCartorio.setCartorio(tfNomeCartorio.getText());
            mCartorio.setOficial(tfNomeOficial.getText());
            mCartorio.setEndereco(tfEndereco.getText());
            mCartorio.setDistrito(tfDistrito.getText());
            mCartorio.setComarca(tfComarca.getText());
            mCartorio.setCep(tfCep.getText());
            mCartorio.setCidade((String) cbCidade.getSelectionModel().getSelectedItem());
            mCartorio.setUf((String) cbUf.getSelectionModel().getSelectedItem());
            mCartorio.setRegional(tfRegional.getText());
            mCartorio.setTelefone(tfTelefone.getText());
            mCartorio.setEmail(tfEmail.getText());
            mCartorio.setFuncionamento(tfHrFuncionamento.getText());
            mCartorio.setSeade(tfSeade.getText());
            mCartorio.setAcervo(tfAcervo.getText());
            mCartorio.setSubstituto(tfSubstituto.getText());
            mCartorio.setRgDoOficial(tfRg.getText());
            mCartorio.setCpfDoOficial(tfCPFOficial.getText());
            mCartorio.setCnpj(tfCnpj.getText());
            if (dtDataNasc.getValue() != null) {
                mCartorio.setDataNascimento(Date.valueOf(dtDataNasc.getValue()));
            }
            if (dtInstalacao.getValue() != null) {
                mCartorio.setAnoDeIstalacao(Date.valueOf(dtInstalacao.getValue()));
            }
            if (dtTitularidade.getValue() != null) {
                mCartorio.setDatadeTitularidade(Date.valueOf(dtTitularidade.getValue()));
            }
            mCartorio.setNomeAntigo(tfMudancaNome.getText());
            if (dt1Nasc.getValue() != null) {
                mCartorio.setCertidaoDeNascimento(Date.valueOf(dt1Nasc.getValue()));
            }
            if (dt1Cas.getValue() != null) {
                mCartorio.setCertidaoDeCasamento(Date.valueOf(dt1Cas.getValue()));
            }
            if (dt1Obito.getValue() != null) {
                mCartorio.setCertidaoDeObito(Date.valueOf(dt1Obito.getValue()));
            }
            mCartorio.setAtualizacao(Date.valueOf(LocalDate.now()));
            DAOCatorio dc = new DAOCatorio();
            dc.alterar(mCartorio);
            tray.setTitle("Atualizado com Sucesso!");
            tray.setMessage("O Cartório " + tfNomeCartorio.getText() + " foi Atualizado com sucesso!");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setAnimationType(AnimationType.SLIDE);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.seconds(2));
            logger.info("O Cartório " + tfNomeCartorio.getText() + " foi Atualizado com sucesso!");
            limparCadastro(event);
        } catch (NumberFormatException e) {
           logger.warn("Erro ao Atualizar Cadastro:  "+ e);
        }

    }

    @FXML
    void pesquisar(ActionEvent event) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                progressBar.setVisible(true);
                CarregarTabela();
                Platform.runLater(() -> {
                progressBar.setVisible(false);
                });
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    void excluirCadastro(ActionEvent event) {
        TrayNotification tray = new TrayNotification();
        List<ModeloCartorio> lancamentos = new DAOCatorio().pesquisarCnj(tfCnj.getText());
        ModeloCartorio mCartorio = lancamentos.get(0);
        DAOCatorio dc = new DAOCatorio();
        try {
            dc.remover(mCartorio);
            tray.setTitle("Excluido com Sucesso!");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setAnimationType(AnimationType.SLIDE);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(2));
            logger.info("O Cartório " + tfNomeCartorio.getText() + " foi Excluido com sucesso!");
            limparCadastro(event);
        } catch (Exception e) {
              logger.warn("Erro ao Excluir Cadastro:  "+ e);
        }

    }
    @FXML
    void clickTabela(MouseEvent event) {
        ModeloCartorio cartorio = (ModeloCartorio) tabelaCartorio.getSelectionModel().getSelectedItem();
        System.out.println(cartorio.getCartorio());
        preencherText(cartorio);
        tfCnj.setText(cartorio.getCnj() + "");
    }
    @FXML
    void limparCadastro(ActionEvent event) {
        tabelaCartorio.getItems().clear();
        tfCnj.setText("");
        tfNomeCartorio.setText("");
        tfNomeOficial.setText("");
        tfEndereco.setText("");
        tfDistrito.setText("");
        tfComarca.setText("");
        tfCep.setText("");
        tfRegional.setText("");
        tfTelefone.setText("");
        tfEmail.setText("");
        tfHrFuncionamento.setText("");
        tfSeade.setText("");
        tfAcervo.setText("");
        tfSubstituto.setText("");
        tfRg.setText("");
        tfCPFOficial.setText("");
        tfCnpj.setText("");
        tfMudancaNome.setText("");
        dtDataNasc.setValue(null);
        dtInstalacao.setValue(null);
        dtTitularidade.setValue(null);
        dt1Nasc.setValue(null);
        dt1Cas.setValue(null);
        dt1Obito.setValue(null);
        cbCidade.getSelectionModel().clearSelection();
        cbUf.getSelectionModel().clearSelection();
        tfCPFOficial.setStyle("-fx-focus-color: #fff;-fx-background-color:#fff;");
    }

    @FXML
    void pesquisarCadastro(ActionEvent event) {
        if (tfCnj.getText().length() > 0) {
            List<ModeloCartorio> lancamentos = new DAOCatorio().pesquisarCnj(tfCnj.getText());

            if (lancamentos.isEmpty()) {
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Falha ao pesquisar!");
                tray.setMessage("Nenhum registro encontrado");
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
                tray.setAnimationType(AnimationType.SLIDE);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(4));
            } else {
                ModeloCartorio mCartorio = lancamentos.get(0);
                preencherText(mCartorio);
            }
        }
    }

    @FXML
    void gerarArquivo(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(sistemamalling.SistemaMalling.class.getResource("view/FXMLArquivo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            logger.warn("Erro ao acessar FXMLArquivo.fxml:  "+ ex);
        }

    }

    public void CarregarTabela() {
        tabelaCartorio.getItems().clear();
        String selecao = cbPesquisar.getSelectionModel().getSelectedItem() + "";
        System.out.println(selecao);
        ObservableList n;
        if (selecao.equals("Tudo")) {

            List<ModeloCartorio> listaModelo = new DAOCatorio().getLista();
            n = FXCollections.observableArrayList(listaModelo);

        } else {
            List<ModeloCartorio> listaModelo = new DAOCatorio().pesquisarEstado(selecao);
            n = FXCollections.observableArrayList(listaModelo);

        }
        
        tabelaCartorio.setItems(FXCollections.observableArrayList(n));

    }

    public void criarTabela() {
        TableColumn colunaCNJ = new TableColumn<>("CNJ");
        TableColumn colunaOficial = new TableColumn<>("OFICIAL");
        TableColumn colunaCartorio = new TableColumn<>("CARTÓRIO");
        TableColumn colunaTelefone = new TableColumn<>("TELEFONE");
        TableColumn colunaEmail = new TableColumn<>("E-mail");
        colunaCNJ.setCellValueFactory(new PropertyValueFactory<>("cnj"));
        colunaCartorio.setCellValueFactory(new PropertyValueFactory<>("cartorio"));
        colunaOficial.setCellValueFactory(new PropertyValueFactory<>("oficial"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefone.setMinWidth(80);
        colunaCartorio.setMinWidth(250);
        colunaOficial.setMinWidth(200);
        colunaEmail.setMinWidth(250);
        tabelaCartorio.getColumns().addAll(colunaCNJ, colunaOficial, colunaCartorio, colunaTelefone, colunaEmail);

    }

    public void preencherText(ModeloCartorio modelo) {
        tfCPFOficial.setStyle("-fx-focus-color: #00f02d;-fx-background-color:#00f02d;");
        tfNomeCartorio.setText(modelo.getCartorio());
        tfNomeOficial.setText(modelo.getOficial());
        tfEndereco.setText(modelo.getEndereco());
        tfDistrito.setText(modelo.getDistrito());
        tfComarca.setText(modelo.getComarca());
        tfCep.setText(modelo.getCep());
        cbUf.getSelectionModel().select(cbUf.getItems().indexOf(modelo.getUf()));
        cbCidade.getSelectionModel().select(cbCidade.getItems().indexOf(modelo.getCidade()));
        tfRegional.setText(modelo.getRegional());
        tfTelefone.setText(modelo.getTelefone());
        tfEmail.setText(modelo.getEmail());
        tfHrFuncionamento.setText(modelo.getFuncionamento());
        tfSeade.setText(modelo.getSeade());
        tfAcervo.setText(modelo.getAcervo());
        tfSubstituto.setText(modelo.getSubstituto());
        tfRg.setText(modelo.getRgDoOficial());
        tfCPFOficial.setText(modelo.getCpfDoOficial());
        tfCnpj.setText(modelo.getCnpj());
        tfMudancaNome.setText(modelo.getNomeAntigo());
        tfCnpj.setText(modelo.getCnpj());
        if (modelo.getAnoDeIstalacao() != null) {
            dtInstalacao.setValue(modelo.getAnoDeIstalacao().toLocalDate());
        }
        if (modelo.getDataNascimento() != null) {
            dtDataNasc.setValue(modelo.getDataNascimento().toLocalDate());
        }
        if (modelo.getDatadeTitularidade() != null) {
            dtTitularidade.setValue(modelo.getDatadeTitularidade().toLocalDate());
        }
        if (modelo.getCertidaoDeNascimento() != null) {
            dt1Nasc.setValue(modelo.getCertidaoDeNascimento().toLocalDate());
        }
        if (modelo.getCertidaoDeCasamento() != null) {
            dt1Cas.setValue(modelo.getCertidaoDeCasamento().toLocalDate());
        }
        if (modelo.getCertidaoDeObito() != null) {
            dt1Obito.setValue(modelo.getCertidaoDeObito().toLocalDate());
        }
    }

    public void preencherEstado() {
        DAOEstado de = new DAOEstado();
        List<ModeloEstado> m = de.getLista();
        ArrayList lista = new ArrayList();
        m.forEach((t) -> {
            System.out.println(t.getNome());
            lista.add(t.getNome());
        });
        ObservableList listaEstado;
        listaEstado = FXCollections.observableArrayList(lista);
        cbUf.setItems(listaEstado);
        ObservableList listaEstadoPesquisar = FXCollections.observableArrayList(lista);
        String t = "Tudo";
        listaEstadoPesquisar.add(0, t);
        cbPesquisar.setItems(listaEstadoPesquisar);

    }

    public void preencherCidades(Long selecao) {
        cbCidade.getItems().clear();
        DAOCidade de = new DAOCidade();
        DAOEstado de2 = new DAOEstado();
        List<ModeloEstado> estado = de2.pesquisarEstadoNome((String) cbUf.getSelectionModel().getSelectedItem());
        if (!estado.isEmpty()) {
            List<ModeloCidade> cidades = de.pesquisarCidade(estado.get(0));
            ArrayList lista = new ArrayList();
            cidades.forEach((t) -> {
                lista.add(t.getNome());
            });
            ObservableList listaCidade;
            listaCidade = FXCollections.observableArrayList(lista);
            cbCidade.setItems(listaCidade);
        }

    }
  
}
