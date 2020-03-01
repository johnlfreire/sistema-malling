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
import sistemamalling.modelo.ModeloCidade;
import sistemamalling.modelo.ModeloEstado;

/**
 *
 * @author johnpc
 */
public class DAOCidade extends DAOGenerico {
   @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }  
    public List<ModeloCidade> pesquisarCidade(ModeloEstado estado) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloCidade> cidades = session.getNamedQuery("ModeloCidade.getCidade")
                    .setParameter("estados",estado).list();
            getSession().getTransaction().commit();
            return cidades;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     public List<ModeloCidade> pesquisarCidadeIBGE(Long codigoIBGE) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloCidade> cidade = session.getNamedQuery("ModeloCidade.getCodigoIBGE")
                    .setParameter("codigoIBGE",codigoIBGE).list();
            getSession().getTransaction().commit();
            return cidade;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
}
