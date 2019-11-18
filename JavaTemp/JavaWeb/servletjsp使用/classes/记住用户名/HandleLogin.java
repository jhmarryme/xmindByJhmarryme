package cn.jhmarryme.servlet;

import cn.jhmarryme.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class HandleLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = "";
        String userpass = "";
        String remember = "";

        User user = new User();

        PrintWriter out = resp.getWriter();

        try {
            BeanUtils.populate(user, req.getParameterMap());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        Cookie ck = new Cookie("username", user.getUsername());
        ck.setPath("/");
        if ("wjh".equals(user.getUsername()) && "123".equals(user.getUserpass())){
            out.write("µÇÂ½³É¹¦");

            if ("iscookie".equals(user.getRemember())){
                ck.setMaxAge(Integer.MAX_VALUE);

            } else {
                ck.setMaxAge(0);
            }
            resp.addCookie(ck);
        } else {
            out.write("µÇÂ½Ê§°Ü");
            resp.setHeader("refresh", "3;url="+ req.getContextPath() +"/jsp/login.jsp");
            System.out.println(req.getContextPath());
        }

    }
}
