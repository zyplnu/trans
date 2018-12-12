package cn.edu.lnu.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DB 工具类
 * 将数据写入到数据库
 * @AUthor zyp
 */
public class DBHelper {

    private static Logger logger = Logger.getLogger(DBHelper.class);

    private static String dirverName;
    private static String url;
    private static String username;
    private static String password;

    //    利用静态块，在类加载的时候就被执行
    static{
        try {
            //用流读入properties配置文件
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            //从输入字节流读取属性列表（键和元素对）
            properties.load(inputStream);
            //用此属性列表中指定的键搜索属性，获取驱动，url，username，password
            dirverName = properties.getProperty("driverName").trim();
            url = properties.getProperty("url").trim();
            username = properties.getProperty("uerName").trim();
            password = properties.getProperty("password").trim();

            //加载驱动
            Class.forName(dirverName);
        } catch (IOException e) {
            logger.error("读取数据库配置文件失败" , e);
        } catch (ClassNotFoundException e) {
            logger.error("加载数据库驱动失败" , e);
        }

    }

    //    获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            logger.info("数据库连接成功...");
        } catch (SQLException e) {
            logger.error("获取数据库连接失败" , e);
        }
        return conn;
    }

    //测试
    public static void main(String[] args) {
        DBHelper.getConnection();
    }

}
