package com.zh.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zh.bean.TramcarCount;

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
    public void generatePDF(String queryTime, int count, String avgDiffMin, List<TramcarCount> dataList, String type) throws Exception {
        PdfPTable table = createTable(3);
        table.addCell(createCell("矿车时点日期查询情况表", headfont, Element.ALIGN_CENTER, 3, false,
                new BaseColor(141, 180, 226)));
        table.addCell(createCell("查询日期:", keyfont, Element.ALIGN_CENTER, 1, false, null));
        table.addCell(createCell(queryTime, textfont, Element.ALIGN_CENTER, 1, false, null));
        table.addCell(createBlank(1));
        table.addCell(createCell("矿车工作总次数:", keyfont, Element.ALIGN_CENTER, 1, false, null));
        table.addCell(createCell(String.valueOf(count) + "次", textfont, Element.ALIGN_CENTER, 1,
                false, null));
        table.addCell(createBlank(1));
        table.addCell(createCell("矿车平均间隔时间:", keyfont, Element.ALIGN_CENTER, 1, false, null));
        table.addCell(createCell(avgDiffMin + "分钟", textfont, Element.ALIGN_CENTER, 1, false, null));
        table.addCell(createBlank(1));
        table.addCell(createBlank(3));
        table.addCell(createBlank(3));

        table.addCell(createCell("矿车当日运行时间情况", keyfont, Element.ALIGN_CENTER, 3, false, null));
        table.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("日期", keyfont, Element.ALIGN_CENTER));

        String title = "";
        if (DAY_TYPE.equals(type)) {
            title = "间隔时间";
        } else {
            title = "平均间隔时间";
        }
        table.addCell(createCell(title, keyfont, Element.ALIGN_CENTER));
        for (int i = 0; i < dataList.size(); i++) {
            table.addCell(createCell(String.valueOf(i + 1), textfont));
            table.addCell(createCell(dataList.get(0).getQueryTime(), textfont));
            table.addCell(createCell(dataList.get(i).getDiffSecond() + "分钟", textfont));
        }
        document.add(table);
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
