package com.itheima.servlet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.entity.User;

public class ServletDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���߷�����Ҫʹ��ʲô���룬ע�������ʹ�õ���ʲô���봫�����ľ���ʲô���� 
		request.setCharacterEncoding("UTF-8");//ֻ�ܽ��post��ʽ������
		
		test1(request);
	}

	private void test4(HttpServletRequest request) {
		try {
			User u = new User();
			System.out.println("��װ����ǰ��"+u);
			
			BeanUtils.populate(u, request.getParameterMap());
			
			System.out.println("��װ���ݺ�"+u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void test3(HttpServletRequest request) {
		try {
			User u = new User();
			System.out.println("��װ����ǰ��"+u);
			//��ȡ������
			Map<String,String[]> map = request.getParameterMap();
			
			for (Map.Entry<String, String[]> m : map.entrySet()) {
				String name = m.getKey();
				String[] value = m.getValue();
				
				//����һ����������
				PropertyDescriptor pd = new PropertyDescriptor(name, User.class);
				//�õ�setter����
				Method setter = pd.getWriteMethod();
				
				if(value.length==1){
					setter.invoke(u, value[0]);//��һ��ֵ�ı�����ֵ
				}else{
					setter.invoke(u, (Object)value);//����ڸ���ѡ��ֵ
				}
			}
			
			System.out.println("��װ���ݺ�"+u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void test2(HttpServletRequest request) {
		//��ȡ���еı�name������
		Enumeration names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = (String) names.nextElement();//�õ�ÿһ��name��
			String[] values = request.getParameterValues(name);//����name�����õ�valueֵ
			for (int i = 0;values!=null && i < values.length; i++) {
				System.out.println(name+"\t"+values[i]);
			}
		}
	}

	private void test1(HttpServletRequest request) throws UnsupportedEncodingException {
		//��ȡ������
		
		//���ݱ���name���Ե�������ȡvalue���Ե�ֵ���� 
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		String[] hobbys = request.getParameterValues("hobby");
		
		String city = request.getParameter("city");
		
		userName = new String(userName.getBytes("iso-8859-1"),"UTF-8");
		System.out.println(userName);
		System.out.println(pwd);
		System.out.println(sex);
		
		for (int i = 0;hobbys!=null && i < hobbys.length; i++) {
			System.out.print(hobbys[i]+"\t");
		}
		System.out.println();
		
		System.out.println(city);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
