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
public class DAOCatorio extends DAOGenerico {

    @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ModeloCartorio> getLista() {
        return super.getLista("ModeloCartorio.getAll");
    }
      public List<ModeloCartorio> getListaAtualizacao() {
           try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloCartorio> cartorio = session.getNamedQuery("ModeloCartorio.getAtualizacao").setMaxResults(10).list();
            getSession().getTransaction().commit();
            return cartorio;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
          
     
    }
public List<ModeloCartorio> pesquisarCnj(String cnj) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloCartorio> cartorio = session.getNamedQuery("ModeloCartorio.getCNJ")
                    .setParameter("cnj",cnj).list();
            getSession().getTransaction().commit();
            return cartorio;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
public List<ModeloCartorio> pesquisarEstado(String uf) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloCartorio> cartorio = session.getNamedQuery("ModeloCartorio.getEstado")
                    .setParameter("uf",uf).list();
            getSession().getTransaction().commit();
            return cartorio;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
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
