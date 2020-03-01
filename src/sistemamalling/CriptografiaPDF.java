package sistemamalling;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
 
public class CriptografiaPDF {
   public static void main(String[] args) {
       // criação do objeto documento
      Document document = new Document();
      try {
          
          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Encrypted.pdf"));
          document.open();
          //document.add(new Paragraph("Cartorio  12345   loja    texto"));
          PdfPTable tabela = new PdfPTable(new float[]{0.3f,1.5f,0.2f,0.2f,0.2f,0.3f});
                    //tabela.setWidthPercentage(10.0f);
                    tabela.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell cabeca = new PdfPCell(new Paragraph("Testando Sistema de Impressão"));
                    cabeca.setColspan(6);
                    tabela.addCell(cabeca);
                    tabela.addCell("Referência");
                    tabela.addCell("Descrição");
                    tabela.addCell("À Vista");
                    tabela.addCell("30 Dias");
                    tabela.addCell("30/60");
                    tabela.addCell("30/60/90");
                    for(int i=0;i<=5;i++){
                        String codigo = String.valueOf("");
                        String descricao = String.valueOf("20");
                        String avista = String.valueOf("20");
                        String trinta = String.valueOf("20");
                        String trintasessenta = String.valueOf("20");
                        String trintasessentanoventa = String.valueOf("20");
                        tabela.addCell(codigo);
                        tabela.addCell(descricao);
                        tabela.addCell(avista);
                        tabela.addCell(trinta);
                        tabela.addCell(trintasessenta);
                        tabela.addCell(trintasessentanoventa);
                    }
                    document.add(tabela);
      }
      catch(DocumentException de) {
          System.err.println(de.getMessage());
      }
      catch(IOException ioe) {
          System.err.println(ioe.getMessage());
      }
      document.close();
  }   
 
}