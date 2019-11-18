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
        //���ù���
        ValidateCode code = new ValidateCode(110, 25, 4, 10);
        code.write(resp.getOutputStream());

        //��д
        //test1(resp);
    }

    private void test1(HttpServletResponse resp) throws IOException {
        int width = 110;
        int height = 25;
        //���ڴ��д���һ��ͼ�����
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //����һ������
        Graphics g = img.getGraphics();

        //��ͼƬ��ӱ���ɫ
        g.setColor(Color.PINK); //����һ����ɫ
        g.fillRect(1, 1, width-2, height-2);//�����ɫ

        //���߿�һ��ɫ
        g.setColor(Color.RED);
        g.drawRect(0, 0, width - 1, height - 1);//���ñ߿����ʾ����

        //�����ı���ʽ
        g.setColor(Color.BLUE);
        g.setFont(new Font("����", Font.BOLD|Font.ITALIC, 15));//

        //��ͼƬ����ı�
        Random rand = new Random();
        int position = 20;
        for (int i = 0; i < 4; i++){
            g.drawString(rand.nextInt(10)+"", position, 20);
            position += 20;
        }
        //���9��������
        for (int i = 0; i < 9; i++){
            g.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
        }

        //���߿�һ����ɫ
        g.setColor(Color.RED);
        g.drawRect(0, 0, width-1, height-1);

        //��ͼƬ���������ķ�ʽ������ͻ���
        ImageIO.write(img, "jpg", resp.getOutputStream());
    }
}
