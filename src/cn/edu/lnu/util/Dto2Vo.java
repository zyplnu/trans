package cn.edu.lnu.util;

import cn.edu.lnu.dto.Module;
import cn.edu.lnu.vo.Problem;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 实体转换类，将模板转换为导入数据库需要的数据，
 */
public class Dto2Vo {

    private static Logger logger = Logger.getLogger(Dto2Vo.class);

    /**
     * 将模板数据转换为数据库需要的数据
     * @param list
     * @return
     */
    public static List<Problem> dto2vo(List<Module> list){
        List<Problem> problemList = new ArrayList<Problem>(list.size());
        for(int i = 0; i < list.size(); i++){
            Module module = list.get(i);
            Problem problem = new Problem();
            problem.setId(module.getId());
            problem.setTitle(module.getTitle());
            problem.setContent(module.getContent());
            problem.setFile("");
            problem.setDate(module.getDate());
            problem.setKeywords(module.getKeywords());
            problem.setFid(module.getFid());
            problem.setMid(module.getMid());
            problem.setTel("");
            problem.setDesc("");
            problem.setChecked(1);
            problem.setClickNo(new Random().nextInt(3000) % (3000 - 1000 + 1) + 1000); //生成1000-3000之间的随机数
            problem.setLikeNo(new Random().nextInt(500) % (500 - 300 + 1) + 300); //生成300-500之间的随机数
            problem.setSolutionNo(0);
            problemList.add(problem);
        }
        logger.info("将模板数据转换为数据库需要的数据成功...");
        return problemList;
    }

    /*public static void main(String[] args) {
        ModuleValidate validate = new ModuleValidate();
        List<Problem> list = Dto2Vo.dto2vo(validate.readXls());
        for(Problem p : list){
            System.out.println(p.getId() + "\t" + p.getTitle() +"\t" + p.getContent() + "\t" + p.getFile() + "\t" +p.getDate()) ;
        }
    }*/
}
