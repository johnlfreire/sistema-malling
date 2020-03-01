/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.dao;

import java.util.List;
import static sistemamalling.dao.DAOGenerico.abrirSessao;
import static sistemamalling.dao.DAOGenerico.getSession;
import static sistemamalling.dao.DAOGenerico.session;
import sistemamalling.modelo.IModelo;
import sistemamalling.modelo.ModeloEstado;

/**
 *
 * @author johnpc
 */
public class DAOEstado extends DAOGenerico {
   @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }  
    
   
    public List<ModeloEstado> getLista() {
        return super.getLista("ModeloEstado.getAll");
    }
    public List<ModeloEstado> pesquisarEstado(Long codigo) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloEstado> estados = session.getNamedQuery("ModeloEstado.getCodigo")
                    .setParameter("codigo",codigo).list();
            getSession().getTransaction().commit();
            return estados;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
    public List<ModeloEstado> pesquisarEstadoIBGE(Long codIbge) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloEstado> estados = session.getNamedQuery("ModeloEstado.getCodigoIBGE")
                    .setParameter("codIbge",codIbge).list();
            getSession().getTransaction().commit();
            return estados;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
    public List<ModeloEstado> pesquisarEstadoNome(String nome) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloEstado> estados = session.getNamedQuery("ModeloEstado.getCodigoNome")
                    .setParameter("nome",nome).list();
            getSession().getTransaction().commit();
            return estados;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
      public List<ModeloEstado> pesquisarEstadoSigla(String sigla) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloEstado> estados = session.getNamedQuery("ModeloEstado.getCodigoSigla")
                    .setParameter("sigla",sigla).list();
            getSession().getTransaction().commit();
            return estados;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
}
