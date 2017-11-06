package com.hyf.cemap.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.common.CommonUtil;

/**
 * 
 * @author huyifan
 * @ClassName FileController
 * @date 2016年11月11日 下午2:07:09
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST },produces = "text/html; charset=utf-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,
			HttpServletResponse response){
		JSONObject returnJSON = new JSONObject();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				
				MultipartFile file = multiRequest.getFile(iter.next());
				String oldFileName = file.getOriginalFilename();
				System.out.println("原文件名称"+oldFileName);
				if (file != null) {
					String fileName = CommonUtil.generateUUID();
					//文件字节
					
					System.out.println(file.getSize());
					Long filesize = file.getSize();
					if(filesize > 20971520){
					    returnJSON.put("error", 1);
					    returnJSON.put("message", "文件超过20Mb，请重新选择");
	                    return returnJSON.toJSONString();
					}
					File path = new File(CEMAPConstants.FILE_PATH);
					if (!path.exists()) {
						path.mkdirs();
					}
					try {
                        file.transferTo(new File(CEMAPConstants.FILE_PATH + fileName));
                    } catch (IOException e) {
                        System.out.println("文件过大");
                    }
					returnJSON.put("error", 0);
					returnJSON.put("oldFileName",oldFileName);
					returnJSON.put("fileName", fileName);
					return returnJSON.toJSONString();
				}
			}
		}
		returnJSON.put("result", false);
		returnJSON.put("name", null);
		return returnJSON.toJSONString();

	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex){
	    if(ex instanceof MaxUploadSizeExceededException){
	        System.out.println("超过最大文件长度");
	    }
	    return "";
	}

	@RequestMapping(value = "/downloadFile", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String download(@RequestParam("fileName") String fileName,
			@RequestParam("oldFileName") String oldFileName,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 声明输入输出流
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		// 下载文件路径
		String downloaddir = CEMAPConstants.FILE_PATH;
		String downLoadPath = downloaddir + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(oldFileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;

	}

}
