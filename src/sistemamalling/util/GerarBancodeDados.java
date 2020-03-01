/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.util;

import br.jus.cnj.corregedoria.ws.extrajudicial.ServicoRequestType;
import br.jus.cnj.corregedoria.ws.extrajudicial.ServicoResponseType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import sistemamalling.dao.DAOCatorio;
import sistemamalling.dao.DAOCidade;
import sistemamalling.dao.DAOEstado;
import sistemamalling.modelo.ModeloCartorio;
import sistemamalling.modelo.ModeloCidade;
import sistemamalling.modelo.ModeloEstado;

/**
 *
 * @author johnpc
 */
public class GerarBancodeDados {

    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GerarBancodeDados.class.getName());

    public static void gerarEstados() {
        File dir1 = new File("");
        try {
            String caminho = dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\teste.txt";
            System.out.println("Current dir : " + caminho);
            try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
                while (br.ready()) {
                    String linha = br.readLine();
                    ModeloEstado model = new ModeloEstado();
                    DAOEstado dao = new DAOEstado();
                    String l[] = linha.split(";");
                    long numero = Long.valueOf(l[0]);
                    System.out.println(numero);

                    model.setCodIbge(numero);
                    model.setNome(l[1]);
                    model.setSigla(l[2]);
                    dao.adicionar(model);
                }

            } catch (IOException ioe) {
                logger.warn("Falha ao Carregar Estados no banco de Dados" + ioe);
            }
        } catch (IOException ex) {
            logger.warn("Falha ao Carregar Estados no banco de Dados" + ex);
        }

    }

    public static void gerarCidade() {
        File dir1 = new File("");
        try {
            String caminho = dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\Cidades.txt";
            System.out.println("Current dir : " + caminho);
            try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
                while (br.ready()) {
                    String linha = br.readLine();
                    ModeloCidade model = new ModeloCidade();
                    DAOCidade dao = new DAOCidade();
                    String l[] = linha.split(";");
                    long numero = Long.valueOf(l[1]);
                    System.out.println(numero);
                    DAOEstado estado = new DAOEstado();
                    List<ModeloEstado> modeloestado = estado.pesquisarEstadoIBGE(Long.valueOf(l[0]));
                    model.setCodigoIBGE(numero);
                    model.setNome(l[2]);
                    model.setEstados(modeloestado.get(0));
                    dao.adicionar(model);
                }

            } catch (IOException ioe) {
                logger.warn("Falha ao Carregar Cidades no banco de Dados" + ioe);
            }
        } catch (IOException ex) {
            logger.warn("Falha ao Carregar Cidades no banco de Dados" + ex);
        }

    }

    public static boolean CarregarConfig() {
        File dir1 = new File("");
        boolean ativo = false;
        try {
            String caminho = dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\Configuracoes.txt";
            System.out.println("Current dir : " + caminho);
            try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
                while (br.ready()) {
                    String linha = br.readLine();
                    System.out.println(linha);
                    ativo = Boolean.parseBoolean(linha);
                }

            } catch (IOException ioe) {
                logger.warn("Ao carregar as configurações" + ioe);
            }
        } catch (IOException ex) {
            logger.warn("Ao carregar as configurações" + ex);
        }
        return ativo;
    }

    public static void CarregarCNJ() {
        String [] estados = {"SP","PR","SC","RS","MS","RO","AC","AM","RR","PA","AP","TO","MA","RN","PB","PE","AL","SE","BA","MG","RJ","MT","GO","DF","PI","CE","ES"}; 
       int cont=0;
        while(cont < estados.length) {
        br.jus.cnj.corregedoria.ws.extrajudicial.Servico serv = new br.jus.cnj.corregedoria.ws.extrajudicial.Servico();
        ServicoRequestType serva = new ServicoRequestType();
        serva.setIndUf(estados[cont]);
        serva.setDtInicio("0");
        serva.setDtFinal("3000");

        ServicoResponseType b = serv.getServicoPort().servico(serva);
        SAXBuilder builder = new SAXBuilder();
        try {
            InputStream stream = new ByteArrayInputStream(b.getServentias().getBytes("UTF-8"));
            System.out.println(("CNS"));
            Document readDoc = builder.build(stream);

            for (Iterator i$ = readDoc.getRootElement().getChildren().iterator(); i$.hasNext();) {
                Element mMov = (Element) i$.next();
                //System.out.println(mMov.getChildText("CNS"));
                for (Element object : mMov.getChildren("ATRIBUICAO")) {
                    for (Element object1 : object.getChildren("ID_ATRIBUICAO")) {
                        if (object1.getValue().equals("6")) {
                            ModeloCartorio cartorio = new ModeloCartorio();
                            System.out.println(mMov.getChildText("CNS"));
                            cartorio.setCnj(mMov.getChildText("CNS"));
                            cartorio.setCartorio(mMov.getChildText("DENOMINACAO_SERVENTIA"));
                            cartorio.setOficial(mMov.getChildText("NOME_TITULAR"));
                            cartorio.setEndereco(mMov.getChildText("ENDERECO"));
                            cartorio.setDistrito(mMov.getChildText("DISTRITO"));
                            //cartorio.setComarca(mMov.getChildText("DENOMINACAO_SERVENTIA"));
                            cartorio.setCep(mMov.getChildText("DENOMINACAO_SERVENTIA"));
                            cartorio.setTelefone(mMov.getChildText("TELEFONE1"));
                            //cartorio.setCelular(mMov.getChildText("TELEFONE2"));
                            cartorio.setEmail(mMov.getChildText("EMAIL"));
                            //cartorio.setFuncionamento(mMov.getChildText("DENOMINACAO_SERVENTIA"));
                            cartorio.setSubstituto(mMov.getChildText("NOME_SUBSTITUTO"));
                            //cartorio.setRgDoOficial(mMov.getChildText("EMAIL"));
                            if (mMov.getChildText("CPF_TITULAR").length() == 11) {
                                String cpf = mMov.getChildText("CPF_TITULAR").substring(0, 3) + "." + mMov.getChildText("CPF_TITULAR").substring(3, 6) + "." + mMov.getChildText("CPF_TITULAR").substring(6, 9) + "-" + mMov.getChildText("CPF_TITULAR").substring(9, 11);
                                cartorio.setCpfDoOficial(cpf);
                            } else {
                                cartorio.setCpfDoOficial(mMov.getChildText("CPF_TITULAR"));
                            }
                            cartorio.setCnpj(mMov.getChildText("CPNJ"));
                            DAOCidade cidade = new DAOCidade();
                            List<ModeloCidade> modelocidade = cidade.pesquisarCidadeIBGE(Long.valueOf(mMov.getChildText("COD_IBGE")));
                            DAOEstado estado = new DAOEstado();
                            List<ModeloEstado> modeloEstado = estado.pesquisarEstadoSigla(mMov.getChildText("UF"));
                            cartorio.setCidade(modelocidade.get(0).getNome());
                            cartorio.setUf(modeloEstado.get(0).getNome());
                            //cartorio.setDatadeTitularidade(Date.valueOf(mMov.getChildText("DT_ASSUNCAO_SERVENTIA_TITULAR")));
                            //cartorio.setAnoDeIstalacao(Date.valueOf(mMov.getChildText("DT_INSTALACAO")));
                            DAOCatorio daoCartorio = new DAOCatorio();
                            daoCartorio.adicionar(cartorio);
                            System.out.println("Carregando");
                        }
                    }
                }
                ;

            }
        } catch (JDOMException ex) {
            logger.warn("Falha ao Carregar dados do CNJ" + ex);
        } catch (IOException ex) {
            logger.warn("Falha ao Carregar dados do CNJ" + ex);
        }
        cont ++;
    }
    }
     public static void alterarConfig(String linha) {
        File dir1 = new File("");
        try {
            String caminho = dir1.getCanonicalPath() + "\\src\\sistemamalling\\resource\\Configuracoes.txt"; 
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(caminho));
        buffWrite.append(linha);
        buffWrite.close();
    }   catch (IOException ex) {
            Logger.getLogger(GerarBancodeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
