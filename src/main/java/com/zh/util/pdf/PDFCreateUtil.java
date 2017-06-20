package com.zh.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zh.bean.TramcarCount;
import com.zh.bean.result.CkData;
import com.zh.bean.result.DataResult;
import com.zh.bean.result.DetalData;
import com.zh.bean.result.TimeData;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public class PDFCreateUtil {

    Document document = new Document();// 建立一个Document对象
    /**
     * 查询类型
     */
    public final static String DAY_TYPE = "0";
    private static Font headfont;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小

    static {
        BaseFont bfChinese;
        try {
            //bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 20, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 16, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 16, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PDFCreateUtil(OutputStream out) {
        document.setPageSize(PageSize.A4);// 设置页面大小
        try {
            PdfWriter.getInstance(document, out);
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int maxWidth = 520;

    public PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBackgroundColor(new BaseColor(255, 255, 255));
        return cell;
    }

    public PdfPCell createBackCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBackgroundColor(new BaseColor(235, 235, 235));
        return cell;
    }
    public PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public PdfPCell createCell(String value, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 是否显示边框
     */
    public PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag,
                               BaseColor color) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        if (color != null) {
            cell.setBackgroundColor(color);
        }
        return cell;
    }

    public PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createBlankTable() {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("", keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }

    /**
     * 生成报表
     */
    public void generatePDF( DataResult dataResult) throws Exception {
        PdfPTable title = createTable(2);
        title.addCell(createCell("无底柱分段崩落法放矿效果监测系统", headfont, Element.ALIGN_CENTER, 2, false,
                new BaseColor(255, 255, 255)));
        CkData ckData = dataResult.getCkData();
        PdfPTable table = createTable(2);
        table.setTotalWidth(280);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(createBlank(10));

        table.addCell(createCell("出矿次数汇总", textfont, Element.ALIGN_LEFT, 2, false, null));
        table.addCell(createBackCell("总共次数", textfont, Element.ALIGN_CENTER));
        table.addCell(createCell(ckData.getTotalNum()+"次", textfont, Element.ALIGN_CENTER));
        table.addCell(createBackCell("平均每天工作次数", textfont, Element.ALIGN_CENTER));
        table.addCell(createCell(ckData.getAverNum()+"次", textfont, Element.ALIGN_CENTER));

        table.addCell(createBlank(10));

        TimeData timeData = dataResult.getTimeData();
        PdfPTable table1 = createTable(6);
        table1.addCell(createCell("时间间隔汇总", textfont, Element.ALIGN_LEFT, 6, false, null));
        table1.addCell(createCell("", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("每次最长时间间隔", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("每次最短时间间隔", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("每天平均最长时间间隔", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("每天平均最短时间间隔", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("总平均时间间隔", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("时间", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMaxInterTime()+"分钟", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMinInterTime()+"分钟", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMaxEveryTime()+"分钟", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMinEveryTime()+"分钟", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getTotalAverTime()+"分钟", textfont, Element.ALIGN_CENTER));
        table1.addCell(createBackCell("日期", textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMaxInterDate(), textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMinInterDate(), textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMaxEveryDate(), textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell(timeData.getMinEveryDate(), textfont, Element.ALIGN_CENTER));
        table1.addCell(createCell("/", textfont, Element.ALIGN_CENTER));

        table1.addCell(createBlank(10));
        DetalData detalData = dataResult.getDetalData();
        List<DetalData.ListData> listData = detalData.getListData();
        PdfPTable table2 = createTable(5);
        table2.addCell(createCell("查询详细结果", textfont, Element.ALIGN_LEFT, 5, false, null));
        table2.addCell(createBackCell("序号", textfont, Element.ALIGN_CENTER));
        table2.addCell(createBackCell("日期", textfont, Element.ALIGN_CENTER));
        table2.addCell(createBackCell("进入时间", textfont, Element.ALIGN_CENTER));
        table2.addCell(createBackCell("离开时间", textfont, Element.ALIGN_CENTER));
        table2.addCell(createBackCell("间隔时间", textfont, Element.ALIGN_CENTER));
        for (DetalData.ListData listDatum : listData) {
            table2.addCell(createCell(listDatum.getId()+"", textfont, Element.ALIGN_CENTER));
            table2.addCell(createCell(listDatum.getDate(), textfont, Element.ALIGN_CENTER));
            table2.addCell(createCell(listDatum.getInTime(), textfont, Element.ALIGN_CENTER));
            table2.addCell(createCell(listDatum.getOutTime(), textfont, Element.ALIGN_CENTER));
            table2.addCell(createCell(listDatum.getInterTime()+"分钟", textfont, Element.ALIGN_CENTER));
        }
        document.add(title);
        document.add(table);
        document.add(table1);
        document.add(table2);
        document.close();
    }

    /**
     * 创建个空白cell
     */
    public PdfPCell createBlank(int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(colspan);
        cell.setBorder(0);
        cell.setPaddingTop(15.0f);
        cell.setPaddingBottom(8.0f);
        return cell;
    }

}
