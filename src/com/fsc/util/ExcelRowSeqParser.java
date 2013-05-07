package com.fsc.util;


import com.fsc.util.StringUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;


public class ExcelRowSeqParser {
	//单sheet解析模式，每个sheet初始编号为00
	public static final int SINGLE_SHEET_MODEL=0;
	//多sheet连续解析模式，每个sheet初始编号为上一个sheet的顶级节点递加值
	public static final int MULTI_SHEET_MODEL=1;
	
    //根节点数
    public int rootLayer = -1;

    //从第几层开始算知识点
    public int layerPermit = 2;
    //解析模式，默认为0
    public int parseModel=0;

    public ExcelRowSeqParser() {
        super();
    }

    public ExcelRowSeqParser(int layerPermit,int parseModel) {
        super();
        this.layerPermit = layerPermit;
        this.parseModel=parseModel;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        RowDataCallBack rowDataCallBack = new RowDataCallBack() {
                public void invoke(String sheetName, String pno, String no,
                    String value, int layer, String path) {
                    System.out.println(sheetName + ":" + no + ":" + pno + ":" +
                        value + ":" + layer + ":[" + path + "]");
                }
            };
            String path="C:\\Users\\Administrator\\Desktop\\人教新课标版目录.xls";
        new ExcelRowSeqParser(2,SINGLE_SHEET_MODEL).readExcel(new File(path), rowDataCallBack);

        //System.out.println(str);
    }

    /**
    * 读取Excel文件的内容
    * @param file
    * @param rowDataCallBack
    * @throws Exception
    */
    public void readExcel(String file, RowDataCallBack rowDataCallBack)
        throws Exception {
        this.readExcel(new File(file), rowDataCallBack);
    }

    /**读取Excel文件的内容
    * @param file  待读取的文件
    * @return
     * @throws Exception
    */
    public void readExcel(File file, RowDataCallBack rowDataCallBack)
        throws Exception {
        Workbook wb = getWorkbook(file);

        //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
        Sheet[] sheet = wb.getSheets();
        //上一个节点的迭代数
        int preCount=0;
        if ((sheet != null) && (sheet.length > 0)) {
            //对每个工作表进行循环
            for (int i = 0; i < sheet.length; i++) {
                //对每个sheet进行解析
                SheetParser sheetParser = new SheetParser(sheet[i],
                        rowDataCallBack, layerPermit,preCount);
                if(parseModel==SINGLE_SHEET_MODEL){
                	sheetParser.parse("", null, rootLayer, "");
                }else{
                	preCount=sheetParser.parse("", null, rootLayer, "");
                }
            }
        }

        realseWordBook(wb);
    }

    /**
     * 获取excel读取对象
     * @param file
     * @return
     * @throws Exception
     */
    private Workbook getWorkbook(File file) throws Exception {
        //构造Workbook（工作薄）对象
        Workbook wb = Workbook.getWorkbook(file);

        return wb;
    }

    /**
     * 关闭worbook
     * @param wb
     */
    private void realseWordBook(Workbook wb) {
        //最后关闭资源，释放内存
        wb.close();
    }

    /**
     *
     * <p>Title: 答疑网</p>
     * <p>Description:行回调函数，需要得到解析出的行数据必须实现此接口</p>
     * <p>创建日期:2011-1-19</p>
     * @author lcb
     * @version 1.0
     * <p>湖南家校圈科技有限公司</p>
     * <p>http://www.139910.com/</p>
     * <p>http://old.139910.com:8080/</p>
     */
    public interface RowDataCallBack {
        /**
         * 传入解析出的值
         * @param pno 父节点编号
         * @param no        当前编号
         * @param value        当前值
         * @param layer        当前层数
         * @param path                当前路径
         */
        void invoke(String sheetName, String pno, String no, String value,
            int layer, String path) throws Exception;
    }

    /**
     * <p>Title: 答疑网</p>
     * <p>Description:Sheet解析器</p>
     * <p>创建日期:2011-1-19</p>
     * @author lcb
     * @version 1.0
     * <p>湖南家校圈科技有限公司</p>
     * <p>http://www.139910.com/</p>
     * <p>http://old.139910.com:8080/</p>
     */
    private class SheetParser {
        //当前的列数
        private int currentRowNum = 0;

        //传入的sheet对象
        private Sheet sheet;

        //行总数
        private int rowNum;

        //数据获取回调函数
        private RowDataCallBack rowDataCallBack;

        //从第几层开始计算编码
        private int codeStartLayer;
        //顶层初始化的计数
        private int topLayerInitNum=0;

        public SheetParser(Sheet sheet, RowDataCallBack rowDataCallBack,
            int codeStartLayer,int topLayerInitNum) {
            super();
            this.sheet = sheet;
            //得到当前工作表的行数
            this.rowNum = sheet.getRows();
            this.rowDataCallBack = rowDataCallBack;
            this.codeStartLayer = codeStartLayer;
            this.topLayerInitNum=topLayerInitNum;
        }
        
        /**
         * 递归解析列
         * @param pno                父亲节点编号
         * @param pValue        父亲节点值
         * @param pLayer        父亲节点层数
         * @param pPath                 父亲节点路径
         * @throws Exception
         */
        private int parse(String pno, String pValue, int pLayer, String pPath)
            throws Exception {
        	return 0;
        	 //当前层的序号计数
//            int layerNumCount = 0;
//        	if(-1==pLayer){
//        		layerNumCount=topLayerInitNum;
//        	}
//           
//
//            //当前层数
//            int curLayer = pLayer + 1;
//
//            //最后一个生产的code
//            String lastCode = null;
//
//            //最后一个值
//            String lastValue = null;
//
//            //最后一个路径
//            String lastPath = null;
//
//            for (; currentRowNum < rowNum; currentRowNum++) {
//                //得到当前行的所有单元格
//                Cell[] cells = sheet.getRow(currentRowNum);
//
//                if (0 == cells.length) {
//                    continue;
//                }
//
//                //当前层数
//                int layer = getLayer(cells);
//
//                //如果本层位于父节点以上，则返回
//                if (layer < curLayer) {
//                    currentRowNum--;
//
//                    return -1;
//                } else if (layer > curLayer) {
//                    parse(lastCode, lastValue, curLayer, lastPath);
//
//                    //如果本层和父节点同层，则继续循环本层
//                    continue;
//                }
//
//                //当前值
//                lastValue = cells[layer].getContents();
//
//                if (layer >= codeStartLayer) {
//                    //当前编码
//                    lastCode = pno +
//                        transINTtoSTRING(layerNumCount,
//                            KnowledgeConstant.CODENO_LEN,
//                            KnowledgeConstant.FIX_CODENO);
//
//                    //当前路径
//                    if (0 == pPath.length()) {
//                        lastPath = lastValue;
//                    } else {
//                        lastPath = pPath + '>' + lastValue;
//                    }
//                } else {
//                    lastCode = pno;
//                    lastPath = pPath;
//                }
//
//                //如果本层位于父节点一下，则继续解析下层
//                if (layer == curLayer) {
//                	String outputPno=pno;
//                	if(StringUtil.isEmpty(outputPno)){
//                		outputPno=KnowledgeConstant.KNOWLEDGE_TOP_PARENTID;
//                	}
//                    rowDataCallBack.invoke(sheet.getName(), outputPno, lastCode,
//                        lastValue, layer, lastPath);
//                    layerNumCount++;
//                }
//            }
//            //如果是最顶层，则返回迭代数
//            if(-1==pLayer){
//            	return layerNumCount;
//            }else{
//            	return -1;
//            }
        }

        /**
        * 将int按位数转为字符串，不足左补0
        * @param sn
        * @param len
        * @return
        */
        private String transINTtoSTRING(int sn, int len, char prefix) {
            String ssn = String.valueOf(sn);

            if (ssn.length() == len) {
            } else if (ssn.length() < len) {
                for (int i = 0; i < (len - ssn.length()); i++) {
                    ssn = prefix + ssn;
                }
            } else if (ssn.length() > len) {
                //此情况暂不做处理，也不截取字段，也不报错
            }

            return ssn;
        }

        /**
         * 获得层数,通过遍历Cell数组,取得有值的列,返回有值的列所处的层数
         * @param cells
         * @return
         */
        private int getLayer(Cell[] cells) {
            int index = 0;

            if ((cells != null) && (cells.length > 0)) {
                //对每个单元格进行循环
                for (int k = 0; k < cells.length; k++) {
                    //读取当前单元格的值
                    String cellValue = cells[k].getContents();

                    if (!StringUtil.isEmpty(cellValue)) {
                        break;
                    } else {
                        index++;
                    }
                }
            }

            return index;
        }
    }
}
