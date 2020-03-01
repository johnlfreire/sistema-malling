/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamalling.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sistemamalling.dao.DAOCatorio;
import sistemamalling.modelo.ModeloCartorio;

/**
 *
 * @author johnpc
 */
public class GerarArquivo {

    public void gerarTXT(File caminho, String Estado) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(caminho);
            List<ModeloCartorio> listaModeloCidade = new DAOCatorio().pesquisarEstado("São Paulo");
            listaModeloCidade.forEach((t) -> {
                writer.println(t.getCnj() + ";" + t.getCartorio() + ";" + t.getOficial() + ";");

            });
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(GerarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gerarExel(File caminho, String Estado) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheetAlunos = workbook.createSheet("Cartorios");
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            CellStyle textStyle = workbook.createCellStyle();
            textStyle.setAlignment(CellStyle.ALIGN_CENTER);
            textStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            FileOutputStream arquivo = new FileOutputStream(caminho);
            List<ModeloCartorio> listaModeloCidade = new DAOCatorio().pesquisarEstado("São Paulo");
            int i = 0;
            Row row = sheetAlunos.createRow(i++);
            Cell cell = row.createCell(0);
            cell.setCellStyle(headerStyle);
            cell.setCellValue((String) "CNJ");
            Cell cell2 = row.createCell(1);
            cell2.setCellStyle(headerStyle);
            cell2.setCellValue((String) "CARTORIO");

            for (ModeloCartorio modeloCartorio : listaModeloCidade) {
                row = sheetAlunos.createRow(i++);

                cell = row.createCell(0);
                cell.setCellStyle(textStyle);
                cell.setCellValue((String) modeloCartorio.getCnj());
                cell2 = row.createCell(1);
                cell2.setCellStyle(textStyle);
                cell2.setCellValue((String) modeloCartorio.getCartorio());

            }

            workbook.write(arquivo);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerarArquivo.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void gerarPdf(String caminho) {

    }

}
