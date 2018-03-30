package cn.com.mewifi.sdp.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-9-18.
 */
public class ParseExcel {
    private final static String XLS = ".xls";
    
    private final static String XLSX = ".xlsx";
    
    public void parseFile(String path, String filename) {
        // TODO Auto-generated method stub
        InputStream in = null;
        BufferedOutputStream out = null;
        Workbook workbook = null;
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = new ArrayList<>();
        Map<Integer, String> spname = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 错误号码
        try {
            in = new FileInputStream(path);
            workbook = null;
            if (filename.endsWith(XLS)) {
                workbook = new HSSFWorkbook(in);
            }
            else if (filename.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(in);
            }
            Sheet sheet = null;
            String value = null;
            /* for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) { */
            sheet = workbook.getSheet("报价");
            
            int i = 1;
            int k = 1;
            String companyname = "";
            // 循环行Row
            Row row = null;
            for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                else {// id,spid,productname,price,cost,catalogid,productdesc,status,createtime,tradesize,unit,code,type
                    sb.append(
                        "insert into  T_SDP_BASEPRODUCT(id,spid,productname,price,cost,catalogid,productdesc,status,createtime,tradesize,unit,code,type) values( "
                            + i + ",");
                    if (rowNum == 2) {
                        companyname = getValue(row.getCell(1));
                        spname.put(1, companyname);
                        
                    }
                    else {
                        if (null != getValue(row.getCell(1)) && !companyname.equals(getValue(row.getCell(1)))) {
                            companyname = getValue(row.getCell(1));
                            k++;
                            spname.put(k, companyname);
                        }
                    }
                    String productName = getValue(row.getCell(2));
                    // float cost =
                    // Float.parseFloat(getValue(row.getCell(3)))*Float.parseFloat(getValue(row.getCell(4)));
                    double cost =
                        Double.parseDouble(getValue(row.getCell(3))) * Double.parseDouble(getValue(row.getCell(4)));
                    DecimalFormat df = new DecimalFormat("#.00");
                    sb.append(k + ",").append("'" + getValue(row.getCell(2)) + "'," + getValue(row.getCell(3)) + ",");
                    sb.append(df.format(cost) + ",'','',1,'").append(sdf.format(new Date())).append("'");
                    double tradesize = 0;
                    String unit = "元";
                    if (productName.indexOf("包") > 0 || productName.indexOf("会员") > 0 || productName.indexOf("黄钻") >= 0
                        || productName.indexOf("绿钻") >= 0 || productName.indexOf("月充") > 0
                        || productName.indexOf("月卡") > 0) {
                        tradesize = 1;
                        unit = "月";
                    }
                    else if (productName.indexOf("季卡") > 0) {
                        tradesize = 3;
                        unit = "月";
                    }
                    else if (productName.indexOf("半年卡") > 0) {
                        tradesize = 6;
                        unit = "月";
                    }
                    else if (productName.indexOf("年卡") > 0) {
                        tradesize = 12;
                        unit = "月";
                    }
                    if (tradesize == 0) {
                        tradesize = Double.valueOf(getValue(row.getCell(3))).intValue();
                    }
                    sb.append("," + tradesize + ",'" + unit + "',");
                    sb.append("'" + getValue(row.getCell(15)))
                        .append("',")
                        .append("'mem'")
                        .append(")")
                        .append(";")
                        .append("\r\n");
                    ids.add(i);
                    i++;
                    /*
                     * for(int j =0;j<row.getLastCellNum();j++){ }
                     */
                }
            }
            // }
            
            // 遍历list
            sb.append("\r\n\r\n\r\n\r\n\r\n");
            for (Integer id : ids) {
                sb.append("insert into  T_SDP_MEM_BASEPRODUCT (id) values(" + id + ")").append(";").append("\r\n");
            }
            // 遍历map
            
            sb.append("\r\n\r\n\r\n\r\n\r\n");
            for (Map.Entry<Integer, String> entry : spname.entrySet()) {
                sb.append("insert into T_SDP_SPINFO(spname,type,id,status,createtime) values(")
                    .append("'" + entry.getValue() + "'," + "'mem'," + entry.getKey() + ",1,'" + sdf.format(new Date()))
                    .append("')")
                    .append(";")
                    .append("\r\n");
            }
            // 遍历map
            sb.append("\r\n\r\n\r\n\r\n\r\n");
            for (Map.Entry<Integer, String> entry : spname.entrySet()) {
                sb.append("insert into T_SDP_MEM_SPINFO(id) values(")
                    .append(entry.getKey())
                    .append(")")
                    .append(";")
                    .append("\r\n");
            }
            out = new BufferedOutputStream(new FileOutputStream(new File("E:\\会员权益\\changpingsql.doc")));
            out.write(sb.toString().getBytes("utf-8"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            // 释放流
            try {
                if (workbook != null) {
                    workbook.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (null != in) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private String getValue(Cell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                // DecimalFormat df = new DecimalFormat("0");
                // value = df.format(cell.getNumericCellValue());
                // String whatYourWant = df.format(cell.getNumericCellValue());
                value = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = String.valueOf(cell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            default:
                break;
        }
        return value;
    }
    
    public static void main(String[] args) {
        ParseExcel parseExcel = new ParseExcel();
        parseExcel.parseFile("E:\\会员权益\\联通电子券产品配置.xlsx", "联通电子券产品配置.xlsx");
    }
}
