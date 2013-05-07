package com.fsc.xxt.client.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:验证码识别</p>
 * <p>创建日期:Dec 26, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class XXT_VerifyCode {
	static int[][] vlaue = {
		// num 0; 6*9 字模
				{ 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
						1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0,
						1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 },
				// num 1
				{ 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
						1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1,
						1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
				// num2
				{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
						0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1,
						0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
				// num3
				{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0,
						1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1,
						1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, },
				// num4
				{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1,
						0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				// num5
				{ 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1,
						1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1,
						1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1 },
				// num6
				{ 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0,
						1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0,
						1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 },
				// num7
				{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0,
						1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1,
						1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
				// num8
				{ 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1,
						0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0,
						0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1 },
				// num9
				{ 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
						1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0,
						1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1 } };

		public static String getValidate(InputStream im) {
			// TODO Auto-generated method stub
			int iw, ih;
			int pix[];
			String valicode = "";

			BufferedImage img;
			try {
				img = ImageIO.read(im);
				// ImageIO.write(img, "BMP", new File("C:\\b.bmp"));
				// 4个6*9数字
				BufferedImage newim[] = new BufferedImage[4];
				newim[0] = img.getSubimage(5, 2, 6, 9);
				newim[1] = img.getSubimage(12, 2, 6, 9);
				newim[2] = img.getSubimage(19, 2, 6, 9);
				newim[3] = img.getSubimage(26, 2, 6, 9);

				for (int k = 0; k < 4; k++) {
					// 二值化
					iw = newim[k].getWidth();
					ih = newim[k].getHeight();
					pix = new int[iw * ih];
					for (int i = 0; i < ih; i++) {
						for (int j = 0; j < iw; j++)
							pix[i * iw + j] = (newim[k].getRGB(j, i) & 0xff) > 30 ? 1
									: 0;
					}
					valicode += getMatchNum(pix);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return valicode;
		}

		public static int getMatchNum(int[] pix) {
			int result = -1;
			int temp = 100;
			int x;
			for (int k = 0; k <= 9; k++) {
				x = 0;
				for (int i = 0; i < 54; i++)
					x = x + Math.abs(pix[i] - vlaue[k][i]);
				if (x < temp) {
					temp = x;
					result = k;
				}
			}
			return result;
		}
}
