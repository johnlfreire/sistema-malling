package sistemamalling.dbconection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GravarProperties {
    
    String user , pass;

    public GravarProperties(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    

    public void gravaProperties() {

        try {

            File file = new File("config/dbconection.properties");
            Properties props = new Properties();           
            props.setProperty("user", user);
            props.setProperty("pass", pass);
        

            FileOutputStream fos = new FileOutputStream(file);

            props.store(fos, "Configurações de acesso ao Banco de Dados");
            

            fos.flush();
            fos.close();

        } catch (IOException x) {
        }
    }
}
