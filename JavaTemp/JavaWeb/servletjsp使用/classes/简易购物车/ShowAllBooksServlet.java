package cn.jhmarryme.servlet;

import cn.jhmarryme.entity.Book;
import cn.jhmarryme.entity.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ShowAllBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print("本网站有以下好书<br/>");
        Map<String, Book> bookMap = DbUtil.findAllBooks();
        for (Map.Entry<String, Book> book: bookMap.entrySet()
             ) {
            out.print("<a href='"+req.getContextPath()+"/addCart?id="+book.getKey()+ "'>"+book.getValue().getName()+"</a><br/>");
        }
        out.print("<a href='"+req.getContextPath()+"/showCart"+"'>查看购物车</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
