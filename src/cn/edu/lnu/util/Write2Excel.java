package cn.edu.lnu.util;

import cn.edu.lnu.dao.ProblemDao;
import cn.edu.lnu.dto.Module;
import cn.edu.lnu.vo.Problem;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author zyp created by 2018/12/11
 * 将转换后的数据写入excel文件中
 */
public class Write2Excel {

    private static Logger logger = Logger.getLogger(Write2Excel.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param problemList 需要导入excel文件的信息列表
     * @return 返回生成的excel文件的路径
     * @throws Exception
     */
    public String problemList2Excel(List<Problem> problemList) {

        Workbook wb = new HSSFWorkbook();
        ProblemDao dao = new ProblemDao();
        Sheet stuSheet = wb.createSheet();
        int max = dao.selectMaxProId();
        try {
            logger.info("线程暂停3秒，等待读取id最大值，避免key重复...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("线程睡眠异常，停止导入...");
            System.exit(-1);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < problemList.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(max + i + 1);
            row.createCell(1).setCellValue(problemList.get(i).getTitle());
            row.createCell(2).setCellValue(problemList.get(i).getContent());
            row.createCell(3).setCellValue(problemList.get(i).getFile());
            //把时间转换为指定格式的字符串再写入excel文件中
            if (problemList.get(i).getDate() != null) {
                row.createCell(4).setCellValue(sdf.format(problemList.get(i).getDate()));
            }
            row.createCell(5).setCellValue(problemList.get(i).getKeywords());
            row.createCell(6).setCellValue(problemList.get(i).getFid());
            row.createCell(7).setCellValue(problemList.get(i).getMid());
            row.createCell(8).setCellValue(problemList.get(i).getTel());
            row.createCell(9).setCellValue(problemList.get(i).getDesc());
            row.createCell(10).setCellValue(problemList.get(i).getChecked());
            row.createCell(11).setCellValue(problemList.get(i).getClickNo());
            row.createCell(12).setCellValue(problemList.get(i).getLikeNo());
            row.createCell(13).setCellValue(problemList.get(i).getSolutionNo());
        }

        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        String folderPath = "D:\\excel";

        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = "example.xls";
        String savePath = folderPath + File.separator + fileName;
        //如果文件存在则删除
        File file = new File(savePath);
        if(file.exists()){
            file.delete();
        }

        OutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(savePath);
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            logger.error("写入时构造文件失败,程序即将退出！" , e);
            System.exit(-1);
        } catch (IOException e) {
            logger.error("写入到excel失败,程序即将退出！" , e);
            System.exit(-1);
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                logger.error("关闭输出流失败" , e);
            }
        }
        logger.info("写入excel文件成功...");
        //返回文件保存全路径
        return savePath;
    }

    /**
     *
     * @return 写入数据库的list
     */
    public List<Problem> readFromExel2List() {
        InputStream is = null;
        Problem problem = null;
        List<Problem> list = new ArrayList<Problem>();
        try {
            is = new FileInputStream("D:/excel/example.xls");
            HSSFWorkbook excel = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
                HSSFSheet sheet = excel.getSheetAt(numSheet);
                if (sheet == null)
                    continue;
                // 循环行Row
                for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    HSSFRow row = sheet.getRow(rowNum);
                    if (row == null)
                        continue;
                    problem = new Problem();
                    problem.setId((int)row.getCell(0).getNumericCellValue());
                    problem.setTitle(row.getCell(1).getStringCellValue());
                    problem.setContent(row.getCell(2).getStringCellValue());
                    problem.setFile(row.getCell(3).getStringCellValue());
                    problem.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row.getCell(4).getStringCellValue()));
                    problem.setKeywords(row.getCell(5).getStringCellValue());
                    problem.setFid((int)row.getCell(6).getNumericCellValue());
                    problem.setMid((int)row.getCell(7).getNumericCellValue());
                    problem.setTel(row.getCell(8).getStringCellValue());
                    problem.setDesc(row.getCell(9).getStringCellValue());
                    problem.setChecked((int)row.getCell(10).getNumericCellValue());
                    problem.setClickNo((int)row.getCell(11).getNumericCellValue());
                    problem.setLikeNo((int)row.getCell(12).getNumericCellValue());
                    problem.setSolutionNo((int)row.getCell(13).getNumericCellValue());
                    list.add(problem);
                }
            }
            logger.info("读取excel成功，准备写入数据库...");
        } catch (FileNotFoundException e) {
            logger.error("读取文件失败,程序即将退出！" , e);
            System.exit(-1);
        } catch (ParseException e) {
            logger.error("日期转换失败,程序即将退出！" , e);
            System.exit(-1);
        } catch (IOException e) {
            logger.error("读取excel失败,程序即将退出！" , e);
            System.exit(-1);
        }
        return list;
    }

    /**
     * 写入到数据库
     */
    public void write2DB(){
        ProblemDao dao = new ProblemDao();
        dao.insertBatch(this.readFromExel2List());
        logger.info("写入到数据库成功...");
    }

    public static void main(String[] args) {
        List<Problem> list = new Write2Excel().readFromExel2List();
        for(Problem p : list){
           //System.out.println(p.getDate());
        }
    }

}
