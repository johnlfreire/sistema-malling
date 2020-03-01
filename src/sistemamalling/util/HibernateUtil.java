package sistemamalling.util;

import com.zap.arca.hibernate.AlteraHibernateCfg;
import com.zap.arca.hibernate.HibernateConfig;
import com.zap.arca.hibernate.ManipuladorConfig;
import java.io.File;
import java.io.InputStream;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Everton
 */
public class HibernateUtil {

  
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.  
            System.out.println(Servico.class.getClass().getResource("/hibernate.cfg.xml"));
            InputStream hibernateConfig = Servico.class.getClass().getResourceAsStream("/hibernate.cfg.xml");
            
            AlteraHibernateCfg altconfig = new AlteraHibernateCfg();
            Document hConfig = altconfig.carregarXML(hibernateConfig);

            Configuration cfg = new Configuration().configure(hConfig);
            sessionFactory = cfg.buildSessionFactory();

        } catch (Throwable ex) {
            // Log the exception. 
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new RuntimeException(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Carrega as propriedades definidas no arquivo configuracoes.cfg para
     * configurar as opções de conexão
     *
     * @param cfg
     */
    private static void adicionarPropriedades(Configuration cfg) {
        File arquivo = new File("configuracoes.cfg");
        if (arquivo.exists()) {
            HibernateConfig hConfig = new ManipuladorConfig().carregar(arquivo);

            StringBuilder url = new StringBuilder("jdbc:mysql://");
            url.append(hConfig.getServidor());
            url.append(":");
            url.append(hConfig.getPorta());
            url.append("/orcamento?useOldAliasMetadataBehavior=true");

            cfg.setProperty("hibernate.connection.url", url.toString());
            cfg.setProperty("hibernate.connection.username", hConfig.getUsuario());
            cfg.setProperty("hibernate.connection.password", hConfig.getSenha());
        }
    }
}
