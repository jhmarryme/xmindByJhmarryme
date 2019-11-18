package cn.jhmarryme.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class ImageServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用工具
        ValidateCode code = new ValidateCode(110, 25, 4, 10);
        code.write(resp.getOutputStream());

        //自写
        //test1(resp);
    }

    private void test1(HttpServletResponse resp) throws IOException {
        int width = 110;
        int height = 25;
        //在内存中创建一个图像对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //创建一个画笔
        Graphics g = img.getGraphics();

        //给图片添加背景色
        g.setColor(Color.PINK); //设置一个颜色
        g.fillRect(1, 1, width-2, height-2);//填充颜色

        //给边框一个色
        g.setColor(Color.RED);
        g.drawRect(0, 0, width - 1, height - 1);//设置边框的显示坐标

        //设置文本样式
        g.setColor(Color.BLUE);
        g.setFont(new Font("宋体", Font.BOLD|Font.ITALIC, 15));//

        //给图片添加文本
        Random rand = new Random();
        int position = 20;
        for (int i = 0; i < 4; i++){
            g.drawString(rand.nextInt(10)+"", position, 20);
            position += 20;
        }
        //添加9条干扰线
        for (int i = 0; i < 9; i++){
            g.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
        }

        //给边框一个颜色
        g.setColor(Color.RED);
        g.drawRect(0, 0, width-1, height-1);

        //将图片对象以流的方式输出到客户端
        ImageIO.write(img, "jpg", resp.getOutputStream());
    }
}
