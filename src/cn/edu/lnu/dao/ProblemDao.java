package cn.edu.lnu.dao;

import cn.edu.lnu.util.DBHelper;
import cn.edu.lnu.vo.Problem;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class ProblemDao {

    private static Logger logger = Logger.getLogger(ProblemDao.class);

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public int selectMaxProId(){
        int maxId = 0;
        try {
            connection = DBHelper.getConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(pro_id) FROM t_pro");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                maxId = resultSet.getInt(1);
            }
            logger.info("获取id最大值成功");
        } catch (SQLException e) {
            logger.error("读取id最大值时初始化sql语句失败" , e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭数据库连接失败" , e);
            }
        }
        return maxId;
    }

    /**
     * 批量插入数据到数据库
     * @param list
     */
    public void insertBatch(List<Problem> list){
        try {
            connection = DBHelper.getConnection();
            String sql = "INSERT INTO t_pro VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            for(int i = 0; i < list.size(); i++){
                preparedStatement.setInt(1 , list.get(i).getId());
                preparedStatement.setString(2 , list.get(i).getTitle());
                preparedStatement.setString(3 , list.get(i).getContent());
                preparedStatement.setString(4 , list.get(i).getFile());
                //解决Java中Date和数据库中Date时间精度丢失的问题，采用TimeStamp，若转换为Java.sql.Date会出现时间部分丢失
                preparedStatement.setTimestamp(5 , new java.sql.Timestamp(list.get(i).getDate().getTime()));
                preparedStatement.setString(6 , list.get(i).getKeywords());
                preparedStatement.setInt(7 , list.get(i).getFid());
                preparedStatement.setInt(8 , list.get(i).getMid());
                preparedStatement.setString(9 , list.get(i).getTel());
                preparedStatement.setString(10 , list.get(i).getDesc());
                preparedStatement.setInt(11 , list.get(i).getChecked());
                preparedStatement.setInt(12 , list.get(i).getClickNo());
                preparedStatement.setInt(13 , list.get(i).getLikeNo());
                preparedStatement.setInt(14 , list.get(i).getSolutionNo());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("初始化sql语句失败,程序即将退出！" , e);
            System.exit(-1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭数据库连接失败" , e);
            }
        }
    }

}
