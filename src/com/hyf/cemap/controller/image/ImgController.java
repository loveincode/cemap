package com.hyf.cemap.controller.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.common.CommonUtil;
import com.hyf.cemap.util.common.CompressImgUtil;

/**
 * 图片上传 预览
*  @author  huyifan
*  @ClassName  ImgController  
*  @date  2017年2月25日 上午11:34:55
 */
@Controller
@RequestMapping(value = "/image")
public class ImgController {
    /**
     * 图片上传
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/uploadImage" , method = {RequestMethod.POST})
    @ResponseBody
    public String uploadImage (HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
        JSONObject returnJSON = new JSONObject();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    String fileName = CommonUtil.generateUUID();
                    File path = new File(CEMAPConstants.IMG_PATH);
                    if(!path.exists()){
                        path.mkdirs();
                    }
                    //压缩图片
                    if(!file.isEmpty()){
                        double fileSize = file.getSize();
                        fileSize = fileSize / 1024 ; //以k作为计算单位
                        if(fileSize > 150){
                            fileName = CompressImgUtil.compressImg(file.getBytes() , fileName.toString(),660, 0.9d);
                        }else{
                            file.transferTo(new File(CEMAPConstants.IMG_PATH +fileName));
                        }
                    }
                    returnJSON.put("error", 0);
                    returnJSON.put("fileName", fileName);
                    return returnJSON.toJSONString();
                }
            }
        }
        returnJSON.put("result", false);
        returnJSON.put("name", null);
        return returnJSON.toJSONString();

    }
    /**
     * 图片读取
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/readImage" , method = {RequestMethod.GET})
    public void readImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        if(name == null || name == ""){
            return;
        }
        File file = new File(CEMAPConstants.IMG_PATH+name);
        if(!(file.exists() && file.canRead())) {
            return;
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
        inputStream.close();
    }

}

