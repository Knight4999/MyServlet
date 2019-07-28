package web;

import dao.UserDao;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ActionServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");

        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
        System.out.println(path);
        if("/login".equals(path)) {
            //处理登录功能
            login(request,response);
        }else if ("/list".equals(path)) {
            //处理用户列表功能
            list(request,response);
        }else if("/add".equals(path)) {
            //处理添加用户功能
            add(request,response);
        }else if("/del".equals(path)) {
            //处理删除功能
            del(request,response);
        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        UserDao dao = new UserDao();
        try {
            User user = dao.find(uname);
            if(user!=null && pwd.equals(user.getPassword())){
                HttpSession s = request.getSession();
                s.setAttribute("user",user);
                response.sendRedirect("list.do");
            }else {
                request.setAttribute("login_fail","用户名或密码错误");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp") ;
                rd.forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("系统繁忙，稍后再试");
        }
    }


    private void list(HttpServletRequest request,HttpServletResponse response) throws IOException{
        HttpSession s = request.getSession();
        Object obj = s.getAttribute("user");
        if(obj==null){
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            UserDao dao = new UserDao();
            List<User> users = dao.findAll();
            request.setAttribute("users",users);
            RequestDispatcher rd = request.getRequestDispatcher("listUsers.jsp");
            rd.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("系统繁忙，稍后再试");
        }
    }


    private void add(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        UserDao dao = new UserDao();
        try {
            User user = dao.find(username);
            if (user!=null){
                request.setAttribute("add_fail","用户已存在");
                RequestDispatcher rd = request.getRequestDispatcher("addUser.jsp");
                rd.forward(request,response);
            }else {
                user = new User();
                user.setUsername(username);
                user.setPassword(request.getParameter("pwd"));
                user.setEmail(request.getParameter("email"));
                dao.save(user);

                response.sendRedirect("list.do");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("系统繁忙，稍后再试");
        }
    }


    private void del(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //读取要删除的用户的id
        int id = Integer.parseInt(
                request.getParameter("id"));
        //删除指定id的用户

        UserDao dao = new UserDao();
        try {
            dao.delete(id);
            //重定向到用户列表
            response.sendRedirect("list.do");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("系统繁忙，稍后重试");
        }

    }
}
