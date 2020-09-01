
package com.marketer.salesrecordanalysis;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import com.marketer.model.Marketer;
import com.marketer.salesreport.SalesReport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author CHIDI
 */
public class SalesRecordAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
Marketer a = new Marketer("Abel","John");
Marketer b = new Marketer("Bernard","Slye");
Marketer c = new Marketer("Clement","Efiong");
System.out.println(a.getId());
System.out.println(b.getId());
System.out.println(c.getId());

//testing the toString() method
System.out.println(a);
System.out.println(b);
System.out.println(c);


//Test reading the marketer data file, 
//displaying the bonus data and writing the bonuses to the file marketerResults.txt
SalesMonitor monitor = new SalesMonitor();
monitor.readMarketerData();
monitor.showBonusData();


//Report test
Collection<Marketer> list = new ArrayList<>();

       list.add(a);
       list.add(b);
       list.add(c);
       
        
SalesReport report = new SalesReport(list);
 
try {
     JasperPrint jasperPrint = report.getReport();
  
     JasperViewer jasperViewer = new JasperViewer(jasperPrint);
     JasperViewer.viewReport(jasperPrint, true);
     jasperViewer.setVisible(true);
 
    } catch (JRException | ColumnBuilderException | ClassNotFoundException ex) {
    System.err.println(ex.getMessage());
    } 
   
    }
    
}
