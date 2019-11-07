package cn.jhmarryme.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class urlServletTest_1 extends HttpServlet {



    private List<String> londonAttractions;
    private List<String> parisAttractions;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");

        if (city != null && (city.equals("london") || city.equals("paris"))){
            showAttractions(req, resp, city);
        } else {
            showMainPage(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        londonAttractions = new ArrayList<String>(10);
        parisAttractions = new ArrayList<String>(10);

        londonAttractions.add("L1");
        londonAttractions.add("L2");
        londonAttractions.add("L3");
        londonAttractions.add("L4");
        londonAttractions.add("L5");
        londonAttractions.add("L6");
        londonAttractions.add("L7");
        londonAttractions.add("L8");
        londonAttractions.add("L9");
        londonAttractions.add("L10");


        parisAttractions.add("P1");
        parisAttractions.add("P2");
        parisAttractions.add("P3");
        parisAttractions.add("P4");
        parisAttractions.add("P5");
        parisAttractions.add("P6");
        parisAttractions.add("P7");
        parisAttractions.add("P8");
        parisAttractions.add("P9");
        parisAttractions.add("P10");


    }

    private void showMainPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.print("<html><head></head>"+ "<body><h2>Please select a city:</h2>"+
                "<p>" +
                "<a href = '?city=london'>London</a><br/>" +
                "<a href = '?city=paris'>Paris</a></p>"+
                "</body></html>");
    }

    private void showAttractions(HttpServletRequest req, HttpServletResponse resp, String city) throws IOException {
        int page = 1;
        String pageParameter = req.getParameter("page");

        //page的获取与判断
        if (pageParameter != null){
            try {
                page = Integer.parseInt(pageParameter);
            } catch (NumberFormatException e){
                throw e;
            }
            if (page > 2){
                page = 1;
            }
        }

        List<String> attractons = null;
        if (city.equals("london")){
            attractons = londonAttractions;
        } else if (city.equals("paris")){
            attractons = parisAttractions;
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head>"+
                "<title>Top 10 Tourist Attractions</title>" +
                "</head><body>");
        writer.println("<a href = 'choice'>Select city</a>");
        writer.println("<hr/>Page:" + page + "<hr/>");

        int start = page * 5 - 5;
        for (int i = start; i < start + 5; i++){
            writer.println(attractons.get(i));
        }


        writer.print("<a href = '?city=" + city + "&page=1'>Page1</a>");
        writer.print("<a href = '?city=" + city + "&page=2'>Page2</a>");
        writer.println(city);
        writer.println("</body></html>");

    }
}
