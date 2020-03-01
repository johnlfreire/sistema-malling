/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.dao;

import java.util.List;
import sistemamalling.modelo.IModelo;
import sistemamalling.modelo.ModeloCartorio;




/**
 *
 * @author johnpc
 */
public class DAOSistema extends DAOGenerico {

    @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ModeloCartorio> getLista() {
        return super.getLista("ModeloCartorio.getAll");
    }
 @Override
    public void alterar(IModelo m){
        super.alterar(m); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public void remover(IModelo m){
        super.remover(m); //To change body of generated methods, choose Tools | Templates.
    }
    
}
