package com.fsc.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <p>Title: 系统框架</p>
 * <p>Description:图片压缩文件工具类</p>
 * <p>创建日期:2010-8-25</p>
 * @author wdd
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PicUtils {
    
	/**
	 * 图片压缩
	 * @param file 待压缩的图片文件
	 * @param outPath 输出路径
	 * @param per 压缩百分比
	 */ 
	public static void compressPic(File file, String outPath, float per) {
		Image src;
		try {
			src = javax.imageio.ImageIO.read(file); //构造Image对象 

			int old_w = src.getWidth(null); //得到源图宽 
			int old_h = src.getHeight(null);

			BufferedImage tag = new BufferedImage(old_w, old_h,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(
					src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0,
					0, null);
			FileOutputStream newimage = new FileOutputStream(outPath); //输出到文件流 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/* 压缩质量 */
			jep.setQuality(per, true);
			encoder.encode(tag, jep);
			newimage.close();
		} catch (IOException ex) {
			Logger.getLogger(PicUtils.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
