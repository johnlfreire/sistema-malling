/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author johnpc
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ModeloCidade.getCidade", query = "SELECT l FROM ModeloCidade l WHERE l.estados=:estados ORDER BY l.nome"),
    @NamedQuery(name = "ModeloCidade.getCodigoIBGE", query = "SELECT l FROM ModeloCidade l WHERE l.codigoIBGE=:codigoIBGE ORDER BY l.nome"),
})
@Table(name = "cidade")
public class ModeloCidade implements Serializable, IModelo<Long> {

    /**
     * @return the codigoIBGE
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @Column
    private long codigoIBGE;
    @Column
    private String nome;
    @OneToOne
    @JoinColumn(name = "estado")
    private ModeloEstado estados;

    @Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(long codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ModeloEstado getEstados() {
        return estados;
    }

    public void setEstados(ModeloEstado estados) {
        this.estados = estados;
    }

}
