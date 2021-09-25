package com.annakhuseinova;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextElement;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportsMain {

    public static void main(String[] args) {
        String templatePath = "somePath";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studentName", "John");
        Student student1 = new Student(1L, "John", "Doe", "Some Street","Paris");
        Student student2 = new Student(2L, "Eugene", "Smith", "Another Street","London");
        List<Student> list = List.of(student1, student2);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        String fileExportPath = "somePath";
        try {
            JasperReport report = JasperCompileManager.compileReport(templatePath);
            // changing properties of the element at runtime
            JRBaseTextElement textElement = (JRBaseTextElement) report.getTitle().getElementByKey("name");
            textElement.setForecolor(Color.RED);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(print, fileExportPath);
            System.out.println("Report created");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
