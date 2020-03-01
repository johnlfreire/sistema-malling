package sistemamalling.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author johnpc
 */
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "ModeloCartorio.getAll", query = "SELECT l FROM ModeloCartorio l "),
    @NamedQuery(name = "ModeloCartorio.getAtualizacao", query = "SELECT l FROM ModeloCartorio l order by l.atualizacao DESC"),
    @NamedQuery(name = "ModeloCartorio.getCNJ", query = "SELECT l FROM ModeloCartorio l WHERE l.cnj=:cnj ORDER BY l.codigo"),
    @NamedQuery(name = "ModeloCartorio.getEstado", query = "SELECT l FROM ModeloCartorio l WHERE l.uf=:uf ORDER BY l.codigo"),})

public class ModeloCartorio implements Serializable, IModelo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @Column(unique = true, nullable = false)
    private String cnj;
    @Column
    private String cartorio;
    @Column
    private String oficial;
    @Column
    private String endereco;
    @Column
    private String distrito;
    @Column
    private String comarca;
    @Column
    private String cep;
    @Column
    private String cidade;
    @Column
    private String uf;
    @Column
    private String regional;
    @Column
    private String telefone;
    @Column
    private String celular;
    @Column
    private String email;
    @Column
    private String funcionamento;
    @Column
    private String seade;
    @Column
    private String acervo;
    @Column
    private String substituto;
    @Column
    private String rgDoOficial;
    @Column
    private String cpfDoOficial;
    @Column
    private String cnpj;
    @Column
    private String nomeAntigo;
    @Column
    private Date dataNascimento;
    @Column
    private Date anoDeIstalacao;
    @Column
    private Date datadeTitularidade;
    @Column
    private Date certidaoDeNascimento;
    @Column
    private Date certidaoDeCasamento;
    @Column
    private Date certidaoDeObito;
    @Column
    private Date atualizacao;

    @Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCnj() {
        return cnj;
    }

    public void setCnj(String cnj) {
        this.cnj = cnj;
    }

    public String getCartorio() {
        return cartorio;
    }

    public void setCartorio(String cartorio) {
        this.cartorio = cartorio;
    }

    public String getOficial() {
        return oficial;
    }

    public void setOficial(String oficial) {
        this.oficial = oficial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(String funcionamento) {
        this.funcionamento = funcionamento;
    }

    public String getSeade() {
        return seade;
    }

    public void setSeade(String seade) {
        this.seade = seade;
    }

    public String getAcervo() {
        return acervo;
    }

    public void setAcervo(String acervo) {
        this.acervo = acervo;
    }

    public String getSubstituto() {
        return substituto;
    }

    public void setSubstituto(String substituto) {
        this.substituto = substituto;
    }

    public String getRgDoOficial() {
        return rgDoOficial;
    }

    public void setRgDoOficial(String rgDoOficial) {
        this.rgDoOficial = rgDoOficial;
    }

    public String getCpfDoOficial() {
        return cpfDoOficial;
    }

    public void setCpfDoOficial(String cpfDoOficial) {
        this.cpfDoOficial = cpfDoOficial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getAnoDeIstalacao() {
        return anoDeIstalacao;
    }

    public void setAnoDeIstalacao(Date anoDeIstalacao) {
        this.anoDeIstalacao = anoDeIstalacao;
    }

    public Date getDatadeTitularidade() {
        return datadeTitularidade;
    }

    public void setDatadeTitularidade(Date datadeTitularidade) {
        this.datadeTitularidade = datadeTitularidade;
    }

    public Date getCertidaoDeNascimento() {
        return certidaoDeNascimento;
    }

    public void setCertidaoDeNascimento(Date certidaoDeNascimento) {
        this.certidaoDeNascimento = certidaoDeNascimento;
    }

    public Date getCertidaoDeCasamento() {
        return certidaoDeCasamento;
    }

    public void setCertidaoDeCasamento(Date certidaoDeCasamento) {
        this.certidaoDeCasamento = certidaoDeCasamento;
    }

    public Date getCertidaoDeObito() {
        return certidaoDeObito;
    }

    public void setCertidaoDeObito(Date certidaoDeObito) {
        this.certidaoDeObito = certidaoDeObito;
    }

    public Date getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }
}
