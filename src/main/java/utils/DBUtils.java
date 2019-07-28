package utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
    private static BasicDataSource ds;
    static {
        //创建源数据对象
        ds = new BasicDataSource();
        //读取配置文件
        Properties p = new Properties();
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(is);
            String driver = p.getProperty("driver");
            String url = p.getProperty("url");
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            //设置连接信息
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.setInitialSize(3);
            ds.setMaxActive(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //设置一个方法以便外界获取连接对象
    public static Connection getConn() throws SQLException {
        Connection conn = ds.getConnection();
        return conn;
    }
    //提供手动关闭连接池方法
    public static void close(Statement stat, Connection conn, ResultSet rs){
        try {
            if(stat!=null){
                stat.close();
            }
            if (conn!=null) {
                conn.close();
            }
            if (rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
