package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesTests {
    private final ClassLoader classLoader = FilesTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка json-файла")
    public void checkStudentFromJsonTest() throws IOException {
        File jsonFile = new File("src/test/resources/student.json");
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonFile, Student.class);

        Assertions.assertEquals("John", student.getName());
        Assertions.assertEquals(30, student.getAge());
        Assertions.assertTrue(student.isStudent());
        Assertions.assertEquals(List.of("Math", "History"), student.getCourses());
        Assertions.assertEquals("New York", student.getAddress().getCity());
        Assertions.assertEquals(10001, student.getAddress().getIndex());
    }

    @Test
    @DisplayName("Проверка pdf-файла из архива")
    public void checkPDFFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("files.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zipInputStream);
                    Assertions.assertEquals("PDF Form Example", pdf.title);
                    Assertions.assertTrue(pdf.text.contains("This is an example of a user fillable PDF form."));
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка xls-файла из архива")
    public void checkXLSFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("files.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xls")) {
                    XLS xls = new XLS(zipInputStream);
                    String questionFormat = xls.excel.getSheetAt(0).getRow(5).getCell(0).getStringCellValue();
                    String abbr = xls.excel.getSheetAt(0).getRow(7).getCell(0).getStringCellValue();
                    String questionType = xls.excel.getSheetAt(0).getRow(7).getCell(1).getStringCellValue();
                    String fileInfo = xls.excel.getSheetAt(1).getRow(5).getCell(0).getStringCellValue();
                    String versionValue = xls.excel.getSheetAt(1).getRow(13).getCell(0).getStringCellValue();
                    String contactValue = xls.excel.getSheetAt(1).getRow(17).getCell(0).getStringCellValue();
                    Assertions.assertEquals("Question Format Abbreviations", questionFormat);
                    Assertions.assertEquals("Abbreviation", abbr);
                    Assertions.assertEquals("Question Type", questionType);
                    Assertions.assertEquals("File Information", fileInfo);
                    Assertions.assertEquals("1.0 (January 2012)", versionValue);
                    Assertions.assertEquals("bb-help@andrew.cmu.edu", contactValue);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка csv-файла из архива")
    public void checkCSVFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("files.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));
                    List<String[]> content = csvReader.readAll();
                   org.assertj.core.api.Assertions.assertThat(content).contains(
                            new String[] {"Name", "Team", "Position", "Height", "Weight", "Age"},
                            new String[] {"Adam Donachie", "BAL", "Catcher", "74", "180", "22.99"},
                            new String[] {"Kevin Millar", "BAL", "First Baseman", "72", "210", "35.43"},
                            new String[] {"Chris Gomez", "BAL", "First Baseman", "73", "188", "35.71"}
                    );
                }
            }
        }
    }
}
