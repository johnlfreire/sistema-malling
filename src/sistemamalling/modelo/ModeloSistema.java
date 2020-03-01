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
import javax.persistence.Table;

/**
 *
 * @author johnpc
 */
@Entity
@Table(name="sistema")
public class ModeloSistema implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)   
private long codigo;
@Column  
private boolean carregarBanco;
@Column
private String email;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public boolean isCarregarBanco() {
        return carregarBanco;
    }

    public void setCarregarBanco(boolean carregarBanco) {
        this.carregarBanco = carregarBanco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






}
