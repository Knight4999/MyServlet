package dao;

import entity.User;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    public User find(String username){
        User user = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConn();
            stat = conn.prepareStatement("select * from t_user where username=?");
            stat.setString(1,username);
            rs = stat.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn= DBUtils.getConn();
            stat = conn.prepareStatement("select * from t_user");
            rs = stat.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(stat,conn,rs);
        }
        return users;
    }

    public static void save(User user){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBUtils.getConn();
            stat = conn.prepareStatement("insert into t_user values(null,?,?,?)");
            stat.setString(1,user.getUsername());
            stat.setString(2,user.getPassword());
            stat.setString(3,user.getEmail());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(stat,conn,null);
        }
    }

    public static void delete(int id){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBUtils.getConn();
            stat = conn.prepareStatement("delete from t_user where id=?");
            stat.setInt(1,id);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(stat,conn,null);
        }
    }
}
