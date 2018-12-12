package cn.edu.lnu.app;

import cn.edu.lnu.util.Dto2Vo;
import cn.edu.lnu.util.ModuleValidate;
import cn.edu.lnu.util.Write2Excel;
import cn.edu.lnu.vo.Problem;

import java.util.List;

/**
 * 面向用户
 * @AUthor zyp created by 2018/12/11
 */
public class Application {

    public static void main(String[] args) {
        ModuleValidate validate = new ModuleValidate();
        List<Problem> list = Dto2Vo.dto2vo(validate.readXls());
        Write2Excel excel = new Write2Excel();
        String path = excel.problemList2Excel(list);
        excel.write2DB();
    }

}
