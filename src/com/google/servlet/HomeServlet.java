package com.google.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.os.Environment;

public class HomeServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		String path0 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelist0";
		String path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelist1";
		String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelist2";
		String path3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelist3";

		String nPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelistnull";

		String[] paths = new String[] { path0, path1, path2, path3, nPath };

		int index = Integer.valueOf(req.getParameter("index"));
		// 对index进行判断
		String path;
		if (index < 5) {// 第一次请求
			path = paths[0];
		} else if (index < 25) {// 第二次请求
			path = paths[1];
		} else if (index < 45) {// 第三次请求
			path = paths[2];
		} else if (index < 65) {// 第四次请求
			path = paths[3];
		} else {// 第五次请求及以后请求
			path = paths[4];
		}

		File file = new File(path);
		long length = file.length();
		resp.setContentLength((int) length);
		OutputStream out = resp.getOutputStream();
		FileInputStream stream = new FileInputStream(file);
		int count = -1;
		byte[] buffer = new byte[1024];
		while ((count = stream.read(buffer)) != -1) {
			out.write(buffer, 0, count);
			out.flush();
		}
		stream.close();
		out.close();
	}
}
