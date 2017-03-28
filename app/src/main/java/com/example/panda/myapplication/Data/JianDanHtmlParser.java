package com.example.panda.myapplication.Data;

import android.util.Log;

import java.io.*;
import java.net.*;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JianDanHtmlParser {
	static String result = "";
	static String surl="";
	static boolean TAG=false;
    public static String getHtml(String url) {
		// 定义即将访问的链接
		//String url = "http://you.ctrip.com/sight/beijing1/230.html";
		// 定义一个字符串用来存储网页内容
		surl=url;
		result="";
		new Thread(){
			public void run(){
				BufferedReader in = null;

				try {
					// 将string转成url对象
					URL realUrl = new URL(surl);
					// 初始化一个链接到那个url的连接
					URLConnection connection = realUrl.openConnection();
					// 开始实际的连接
					connection.connect();
					// 初始化 BufferedReader输入流来读取URL的响应
					in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					// 用来临时存储抓取到的每一行的数据
					String line;
					long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
//					while ((line = in.readLine()) != null) {
//						// 遍历抓取到的每一行并将其存储到result里面
//						Log.i(">>line",line);
//						result += line + "\n";
//					}
					CharBuffer bos = CharBuffer.allocate(20480);
					int read = 0;
					StringBuilder sb = new StringBuilder();
					try {
						while (in.read(bos) != -1) {
							bos.flip();
							sb.append(bos.toString());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//System.out.println(sb.toString());
					result=sb.toString();
					//写入byte数组中。再依次读取出来即可。
					//System.out.print(result);
					long endMili=System.currentTimeMillis();// 当前时间对应的毫秒数

					Log.i(">>time1","总耗时为："+(endMili-startMili)+"毫秒");
				} catch (Exception e) {
					Log.i(">>>parsing","发送GET请求出现异常！" + e);
					e.printStackTrace();
				}
				// 使用finally来关闭输入流
				finally {
					try {
						if (in != null) {
							in.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					finally {
						TAG=true;
					}
				}
			}
		}.start();
		// 定义一个缓冲字符输入流
		while(TAG!=true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Log.i(">>>result", result);

		}
		TAG=false;
		return getInfo(result);
	}

	private static String getInfo(String result) {
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// TODO Auto-generated method stub
		// 要验证的字符串
		// String str = "service@xsoftlab.net";
		// 邮箱验证规则
		String regEx = "(<div itemprop=\"description\" class=\"text_style\">)\\s*(.*)";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法

		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(result);
		//Log.i(">>matcher",matcher.find()+"");
		String s="";
		int count=0;
		while (matcher.find()) {
			count++;
			if(count==2){
			s=matcher.group(2);
			//Log.i(">>>>sss",s);
				break;
			}
			//String newStr = null;
//			try {
//				newStr = new String(s.getBytes(), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}

		}
		long endMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		Log.i(">>time2","总耗时为："+(endMili-startMili)+"毫秒");
		return "<html>"+s+"</html>";
	}
}