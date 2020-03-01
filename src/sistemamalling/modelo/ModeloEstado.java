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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author johnpc
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ModeloEstado.getAll", query = "SELECT l FROM ModeloEstado l order by nome"),
    @NamedQuery(name = "ModeloEstado.getCodigo", query = "SELECT l FROM ModeloEstado l WHERE l.codigo=:codigo ORDER BY nome"),
    @NamedQuery(name = "ModeloEstado.getCodigoIBGE", query = "SELECT l FROM ModeloEstado l WHERE l.codIbge=:codIbge ORDER BY nome"),
    @NamedQuery(name = "ModeloEstado.getCodigoNome", query = "SELECT l FROM ModeloEstado l WHERE l.nome=:nome ORDER BY nome"),
    @NamedQuery(name = "ModeloEstado.getCodigoSigla", query = "SELECT l FROM ModeloEstado l WHERE l.sigla=:sigla ORDER BY nome"),
})
@Table(name="estado")
public class ModeloEstado implements Serializable, IModelo<Long>{

   
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long codigo;
 @Column
 private long codIbge;
 @Column
private String nome; 
 @Column
private String sigla; 

 @Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

  public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
     /**
     * @return the codIbge
     */
    public long getCodIbge() {
        return codIbge;
    }

    /**
     * @param codIbge the codIbge to set
     */
    public void setCodIbge(long codIbge) {
        this.codIbge = codIbge;
    }

}
