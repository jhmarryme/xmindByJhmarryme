package cn.jhmarryme.servlet;

import cn.jhmarryme.entity.Book;
import cn.jhmarryme.entity.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String id = req.getParameter("id");
        Book book = DbUtil.findBookById(id);

        HttpSession session = req.getSession();

        List<Book> books = (List<Book>) session.getAttribute("cart");

        if (books == null){
            books = new ArrayList<Book>();
        }

        books.add(book);
        session.setAttribute("cart", books);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
