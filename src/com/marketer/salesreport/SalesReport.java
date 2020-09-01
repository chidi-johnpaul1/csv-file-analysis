
package com.marketer.salesreport;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.marketer.model.Marketer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author CHIDI
 */
public class SalesReport {
  private final Collection<Marketer> list = new ArrayList<>();
 
    public SalesReport(Collection<Marketer> c) {
        list.addAll(c);
    }
 
    public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
        Style headerStyle = createHeaderStyle();
        Style detailTextStyle = createDetailTextStyle();        
        Style detailNumberStyle = createDetailNumberStyle();        
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle,detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(list));
        return jp;
    }
    
    private Style createHeaderStyle() {        
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM_BOLD);
        sb.setBorder(Border.THIN());
        sb.setBorderBottom(Border.PEN_2_POINT());
        sb.setBorderColor(Color.BLACK);
        sb.setBackgroundColor(Color.ORANGE);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setTransparency(Transparency.OPAQUE);        
        return sb.build();
    }
    
    private Style createDetailTextStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM);
        sb.setBorder(Border.DOTTED());        
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingLeft(5);        
        return sb.build();
    }
    
      private Style createDetailNumberStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM);
        sb.setBorder(Border.DOTTED());        
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingRight(5);        
        return sb.build();
    }
    
    
 
    private AbstractColumn createColumn(String property, Class type,
            String title, int width, Style headerStyle, Style detailStyle)
            throws ColumnBuilderException {
        AbstractColumn columnState = ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName())
                .setTitle(title).setWidth(width)
                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();
        return columnState;
    }
 
    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle) throws ColumnBuilderException, ClassNotFoundException {
        
        DynamicReportBuilder report = new DynamicReportBuilder();
        
        AbstractColumn columnFirstName = createColumn("firstname", String.class,"First name", 30, headerStyle, detailTextStyle);
        AbstractColumn columnLastName = createColumn("lastname", String.class,"Last name", 30, headerStyle, detailTextStyle);        
        AbstractColumn columnGender = createColumn("gender", String.class,"Gender", 30, headerStyle, detailTextStyle);
        AbstractColumn columnYear = createColumn("year", Integer.class,"Year", 30, headerStyle, detailNumStyle);
        AbstractColumn columnProductCode = createColumn("productCode", String.class,"Product Code", 30, headerStyle, detailNumStyle);
        AbstractColumn columnSales = createColumn("sales", Integer.class,"Sales", 30, headerStyle, detailNumStyle);
        AbstractColumn columnBonus = createColumn("bonus", Integer.class,"Bonus", 30, headerStyle, detailNumStyle);
        
        report.addColumn(columnFirstName).addColumn(columnLastName).addColumn(columnGender).addColumn(columnYear)
                .addColumn(columnProductCode).addColumn(columnSales).addColumn(columnBonus);
                
        StyleBuilder titleStyle=new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
        
        StyleBuilder subTitleStyle=new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        
        //FastReportBuilder frb = new FastReportBuilder();
        //frb.addColumn(columnFirstName).addWatermark("TOP SECRET");
        
        report.addFirstPageImageBanner("images/logo.jpg", 0, 0, ImageBanner.Alignment.Center);
        report.setTitle("Sales Report");
        
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("Marketers Bonus List");
        
        report.addWatermark("TOP SECRET");
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true); 
        return report.build();
    }    
  
}
