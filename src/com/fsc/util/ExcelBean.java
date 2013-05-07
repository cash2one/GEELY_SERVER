package com.fsc.util;

import jxl.Cell;
import jxl.CellView;

import jxl.format.Alignment;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:EXCEL导出工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ExcelBean {
    //Http协议响应对象 
    private HttpServletResponse response;

    //文件名称
    private String fileName;

    //文件类型
    private String fileType = "xls";

    //sheet名称
    private String sheetName;

    //数据列表 
    private List dataList;

    //标题列表
    private List<String> titleList;

    //工作薄
    private HSSFWorkbook workbook;

    //工作单元
    private HSSFSheet sheet;

    //样式
    private HSSFCellStyle style;

    //客户名称
    private String custName;

    //用电地址
    private String custAddr;

    //业务类型
    private String applName;
    private Logger log = Logger.getLogger(this.getClass());

    public ExcelBean() {
    }

    //初始化ExcelBean
    public void initExcelBean(HttpServletResponse response, String fileName,
        String fileType, String sheetName, List dataList,
        List<String> titleList, String custName, String custAddr,
        String applName) {
        this.response = response;
        this.fileName = fileName + ".xls";
        this.fileType = fileType;
        this.sheetName = sheetName;
        this.dataList = dataList;
        this.titleList = titleList;
        this.custName = custName;
        this.custAddr = custAddr;
        this.applName = applName;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
        this.style = workbook.createCellStyle();
    }

    //设置响应头
    public void setResponseHeader() {
        response.setContentType("application/octet-stream;charset=iso-8859-1");

        try {
            response.setHeader("Content-Disposition",
                "attachment;filename=" +
                java.net.URLEncoder.encode(fileName, "utf-8"));

            //客户端不缓存
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //    //导出数据到Excel
    //    public void exportExcel(OutputStream os) {
    //        
    //        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm"));
    //        
    //        for (int i = 0; i < titleList.size(); i++) {
    //        	HSSFRow row = sheet.createRow(0);
    //        	log.info("标题" + (i+1) + "=" + titleList.get(i));
    //            row.createCell(0).setCellValue(titleList.get(0));
    //            row.createCell(1).setCellValue(titleList.get(1));
    //            row.createCell(2).setCellValue(titleList.get(2));
    //            row.createCell(3).setCellValue(titleList.get(3));
    //            row.createCell(4).setCellValue(titleList.get(4));
    //            row.createCell(5).setCellValue(titleList.get(5));
    //            row.createCell(6).setCellValue(titleList.get(6));
    //            row.createCell(7).setCellValue(titleList.get(7));
    //            row.createCell(8).setCellValue(titleList.get(8));
    //            row.createCell(9).setCellValue(titleList.get(9));
    //            row.createCell(10).setCellValue(titleList.get(10));
    //            row.createCell(11).setCellValue(titleList.get(11));
    //        }
    //
    //        for (int i = 0; i < dataList.size(); i++) {
    //            FlowNodeR flowNodeR = (FlowNodeR) dataList.get(i);
    //            HSSFRow row = sheet.createRow((i+1));
    //            
    //            log.info("客户名称=" + custName);
    //            row.createCell(0).setCellValue(custName);
    //            
    //            log.info("用电地址=" + custAddr);
    //            row.createCell(1).setCellValue(custAddr);
    //            
    //            log.info("业务类型="+applName);
    //            row.createCell(2).setCellValue(applName);
    //            
    //            log.info("环节名称="+flowNodeR.getNodeName());
    //            row.createCell(3).setCellValue(flowNodeR.getNodeName());
    //            
    //            String status = flowNodeR.getStatus().trim();
    //            if("01".equals(status)) {
    //            	status = "待办";
    //            }
    //            if("02".equals(status)){
    //            	status = "进行中";
    //            }
    //            if("03".equals(status)) {
    //            	status = "完成";
    //            }
    //            if("04".equals(status)) {
    //            	status = "返工";
    //            }
    //            if("05".equals(status)){
    //            	status = "未完成通过";
    //            }
    //            log.info("环节状态=" + status);
    //            row.createCell(4).setCellValue(status);
    //            
    //            log.info("计划开始时间=" + flowNodeR.getPsTime());
    //            HSSFCell cell = row.createCell(5);
    //            cell.setCellValue(flowNodeR.getPsTime().toString().substring(0,16));
    //            cell.setCellStyle(style);
    //            
    //            log.info("计划完成时间=" + flowNodeR.getPeTime());
    //            HSSFCell cell2 = row.createCell(6);
    //            cell2.setCellValue(flowNodeR.getPeTime().toString().substring(0,16));
    //            cell2.setCellStyle(style);
    //            
    //            log.info("实际开始时间=" + flowNodeR.getPeTime());
    //            HSSFCell cell3 = row.createCell(7);
    //            cell3.setCellValue(flowNodeR.getRsTime().toString().substring(0,16));
    //            cell3.setCellStyle(style);
    //            
    //            log.info("实际完成时间=" + flowNodeR.getPeTime());
    //            HSSFCell cell4 = row.createCell(8);
    //            cell4.setCellValue(flowNodeR.getReTime().toString().substring(0,16));
    //            cell4.setCellStyle(style);
    //            
    //            log.info("实际预计工作日=" + flowNodeR.getRealWD());
    //            row.createCell(9).setCellValue(flowNodeR.getRealWD());
    //            
    //            log.info("环节排序号=" + flowNodeR.getOrderNO());
    //            row.createCell(10).setCellValue(flowNodeR.getOrderNO());
    //            
    //            log.info("项目经理=" + flowNodeR.getManagerName());
    //            row.createCell(11).setCellValue(flowNodeR.getManagerName());
    //        }
    //
    //        try {
    //            workbook.write(os);
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }
    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
}
