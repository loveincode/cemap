package com.hyf.cemap.util.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
*  @author  huyifan
*  @ClassName  CompressImgUtil  
*  @date  2016年12月20日 下午1:01:38
 */
public class CompressImgUtil {
    /**
     * 图片压缩算法
     * http://www.zuidaima.com/code/file/2156534163489792.htm?dir=/2156534163489792.java
     */
    /**
     * 等比例压缩算法：
     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     * @param srcURL 原图地址
     * @param deskURL 缩略图地址
     * @param comBase 压缩基数
     * @param scale 压缩限制(宽/高)比例  一般用1：
     * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
     * @throws Exception
     * @author shenbin
     * @createTime 2014-12-16
     * @lastModifyTime 2014-12-16
     */
    public static String compressImg(byte [] bytes , String uuid , double comBase, double scale){
        //File srcFile = new java.io.File(srcURL);
        try{
            ImageInputStream inStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(bytes));
            Image src = ImageIO.read(inStream);
            int srcHeight = src.getHeight(null);
            int srcWidth = src.getWidth(null);
            int deskHeight = 0;// 缩略图高
            int deskWidth = 0;// 缩略图宽
            double srcScale = (double) srcHeight / srcWidth;
            /**缩略图宽高算法*/
            if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
                if (srcScale >= scale || 1 / srcScale > scale) {
                    if (srcScale >= scale) {
                        deskHeight = (int) comBase;
                        deskWidth = srcWidth * deskHeight / srcHeight;
                    } else {
                        deskWidth = (int) comBase;
                        deskHeight = srcHeight * deskWidth / srcWidth;
                    }
                } else {
                    if ((double) srcHeight > comBase) {
                        deskHeight = (int) comBase;
                        deskWidth = srcWidth * deskHeight / srcHeight;
                    } else {
                        deskWidth = (int) comBase;
                        deskHeight = srcHeight * deskWidth / srcWidth;
                    }
                }
            } else {
                deskHeight = srcHeight;
                deskWidth = srcWidth;
            }
            BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
            tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
            FileOutputStream deskImage = new FileOutputStream(CEMAPConstants.imgDir+uuid.toString()); //输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
            encoder.encode(tag); //近JPEG编码
            deskImage.flush();
            deskImage.close();
            return uuid.toString();

        }catch(Exception e){
            System.out.println("文件保存异常"+e.toString());
        }
        return null;
    }

}

