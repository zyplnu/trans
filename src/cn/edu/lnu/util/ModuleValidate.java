package cn.edu.lnu.util;

import cn.edu.lnu.dto.Module;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.awt.event.MouseAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模板验证类
 * 判断用户是否根据模板添加数据
 * @AUthor zyp
 */
public class ModuleValidate {

    private static Logger logger = Logger.getLogger(ModuleValidate.class);

    public List<Module> readXls() {
        InputStream is = null;
        Module moudle = null;
        List<Module> list = new ArrayList<Module>();
        try {
            is = new FileInputStream("D:/example.xls");
            HSSFWorkbook excel = new HSSFWorkbook(is);
            HSSFSheet sheet = excel.getSheetAt(0);
            if (sheet == null){
                logger.info("excel内容为空，请核对后重新执行...");
                System.exit(-1);
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (row == null){
                    logger.info("excel内容为空，请核对后重新执行...");
                    System.exit(-1);
                }
                moudle = new Module();
                HSSFCell cell0 = row.getCell(0);
                if (cell0 == null){
                    moudle.setId(0);
                } else{
                    moudle.setId((int)cell0.getNumericCellValue());
                }
                HSSFCell cell1 = row.getCell(1);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                if (cell1 == null || "".equals(cell1.getStringCellValue())){
                    logger.error("标题为空，请检查，程序即将退出...");
                    System.exit(-1);
                }else{
                    moudle.setTitle(cell1.getStringCellValue());
                }
                HSSFCell cell2 = row.getCell(2);
                cell2.setCellType(Cell.CELL_TYPE_STRING);
                if (cell2 == null || "".equals(cell2.getStringCellValue())){
                    moudle.setContent("");
                }else{
                    moudle.setContent(cell2.getStringCellValue());
                }
                HSSFCell cell3 = row.getCell(3);
                if (cell3 != null && !("".equals(cell3.getStringCellValue()))){
                    moudle.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(cell3.getStringCellValue()));
                }else {
                    moudle.setDate(new Date());
                }
                HSSFCell cell4 = row.getCell(4);
                cell4.setCellType(Cell.CELL_TYPE_STRING);
                if (cell4 == null){
                    logger.error("关键字为空，请检查，程序即将退出...");
                    System.exit(-1);
                }else{
                    moudle.setKeywords(cell4.getStringCellValue());
                }
                HSSFCell cell5 = row.getCell(5);
                if (cell5 == null || cell5.getNumericCellValue() == 0.0){
                    logger.error("一级学科为空，请检查，程序即将退出...");
                    System.exit(-1);
                }
                if(!((int)(cell5.getNumericCellValue()) >= 1 && (int)(cell5.getNumericCellValue()) <= 10) ){
                    logger.error("一级学科超出，系统退出，请重新检查...");
                    System.exit(-1);
                }
                moudle.setFid((int)cell5.getNumericCellValue());
                HSSFCell cell6 = row.getCell(6);
                if (cell6 == null || cell6.getNumericCellValue() == 0.0){
                    logger.error("二级学科为空，请检查，程序即将退出...");
                    System.exit(-1);
                }
                if(!((int)(cell6.getNumericCellValue()) >= 1 && (int)(cell6.getNumericCellValue()) <= 81) ){
                    logger.error("二级学科超出，系统退出，请重新检查...");
                    System.exit(-1);
                }
                moudle.setMid((int)cell6.getNumericCellValue());
                list.add(moudle);
            }
            logger.info("模板数据校验成功...");
        } catch (FileNotFoundException e) {
            logger.error("读取文件失败,程序即将退出！");
            System.exit(-1);
        } catch (ParseException e) {
            logger.error("日期转换失败,程序即将退出！");
            System.exit(-1);
        } catch (IOException e) {
            logger.error("读取excel失败,程序即将退出！");
            System.exit(-1);
        }
        return list;
    }

    /*public static void main(String[] args) {
        ModuleValidate validate = new ModuleValidate();
        List<Module> modules = validate.readXls();
        for(Module module : modules){
            System.out.println(module.getId() + "\t" + module.getTitle() + "\t" + module.getContent() + "\t" + module.getDate() + "\t" + module.getKeywords()
            +"\t" + module.getFid() + "\t" + module.getMid() + "\t" + module.getAuthor());
        }
    }*/
}
