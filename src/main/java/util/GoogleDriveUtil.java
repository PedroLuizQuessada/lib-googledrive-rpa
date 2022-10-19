package util;

import automacao.Planilha;
import exceptions.ArquivoException;
import exceptions.MoverPendenciaException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class GoogleDriveUtil {
    //planilhas -> linhas -> colunas
    public static List<Planilha> recuperarPendencias(String googleDrivePath) throws ArquivoException {
        List <Planilha> planilhas = new ArrayList<>();
        File[] arquivos = listarArquivos(googleDrivePath);
        for (File arquivo : arquivos) {
            if (!arquivo.getName().endsWith(".xls") && !arquivo.getName().endsWith(".xlsx")) {
                continue;
            }

            try {
                planilhas.add(lerPlanilha(arquivo));
            }
            catch (IOException e) {
                throw new ArquivoException(arquivo.getPath());
            }
        }

        return planilhas;
    }

    public static List<File> recuperarImagem(String googleDrivePath) {
        List<File> imagens = new ArrayList<>();
        File[] arquivos = listarArquivos(googleDrivePath);
        for (File arquivo : arquivos) {
            if (!arquivo.getName().endsWith(".jpeg") && !arquivo.getName().endsWith(".png")) {
                continue;
            }

            imagens.add(arquivo);
        }

        return imagens;
    }

    private static File[] listarArquivos(String diretorioPath) {
        File diretorio = new File(diretorioPath);
        return diretorio.listFiles();
    }

    private static Planilha lerPlanilha(File arquivo) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(arquivo);
        XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(arquivo.toPath()));
        XSSFSheet sheet = workbook.getSheetAt(0);
        Planilha planilha = new Planilha();
        planilha.setNome(arquivo.getName());

        for (Row row : sheet) {
            List<String> linha = new ArrayList<>();
            planilha.addDado(linha);

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        linha.add(String.valueOf(cell.getNumericCellValue()));
                        break;

                    case STRING:
                        linha.add(cell.getStringCellValue());
                        break;
                }
            }
        }
        fileInputStream.close();
        return planilha;
    }

    public static void moverPendencias(List<Planilha> planilhasProcessadas, File source, File dest) throws MoverPendenciaException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Planilhas Processadas");
            int numLinhas = 0;

            for (Planilha planilhaProcessada : planilhasProcessadas) {
                if (!new File(source + "\\" + planilhaProcessada.getNome()).delete()) {
                    throw new IOException();
                }

                Row row = sheet.createRow(numLinhas);
                row.createCell(0).setCellValue(planilhaProcessada.getNome());
                numLinhas++;
            }

            FileOutputStream fileOut = new FileOutputStream(dest);
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (IOException e) {
            throw new MoverPendenciaException(source);
        }
    }
}
