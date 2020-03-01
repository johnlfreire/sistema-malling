package sistemamalling.dbconection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class LerProperties {
 
    private static  Properties properties;
 
    static{
 
        try {
 
            
            File file = new File("config/dbconection.properties");
            FileInputStream fis = new FileInputStream(file);
 
            if(file.exists()){
                properties = new Properties();                
                properties.load(fis);
                fis.close();
            }
 
        }catch(Exception x){
            File erro = new File("erro.ini");
            try {
                x.printStackTrace(new PrintStream(erro));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LerProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
    public static String lerProperties(String atributo){
 
        String retorno = null;
 
        try {
 
           
            File file = new File("dbconection.properties");
 
            if(properties != null){
                retorno = properties.getProperty(atributo);
            }
 
        }catch(Exception x){
            System.out.println("sistemamalling.dbconection.LerProperties.lerProperties()");
        }
 
        return retorno;
    }
}