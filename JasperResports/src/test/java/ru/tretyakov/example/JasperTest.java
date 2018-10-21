package ru.tretyakov.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.HashMap;

/**
 * TODO.
 * author Rooter
 * since 21.10.18
 **/

public class JasperTest {

    public static DefaultTableModel model;

    @Before
    public void init() {
        String[] columnNames = {"id", "description","status","title","phone"};
        String[][] data = {
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
                {"1","SOME TEST DESCRIPTION","true","SOME TEST TITLE","493-12-23"},
        };
        model = new DefaultTableModel(data,columnNames);
    }

    @Test
    public void whenThen() {
        try {
            JasperReport report =
                    JasperCompileManager.compileReport(
                            "./src/main/resources/template.jrxml");
            JasperPrint jasperPrint =
                  JasperFillManager.fillReport(
                          report,
                          new HashMap<>(), new JRTableModelDataSource(model));
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint,"/home/rooter/Documents/result.pdf");
            JasperExportManager.exportReportToHtmlFile(
                    jasperPrint,"/home/rooter/Documents/result.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
