package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;

import java.io.File;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品管理视图层</p>
 * <p>创建日期:Feb 8, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductVo extends ClientVo {
    private String ID;
    private String LOGIN_NAME;
    private String USER_TYPE;
    private String PRODUCT_ID;
    
    private String id;

    /** 产品名称 */
    private String title;

    /** 价格 */
    private Integer price;

    /** 服务对象 */
    private String customer;

    /** 介绍 */
    private String intro;

    /** 发布时间 */
    private Date putTime;

    /** 产品图片  */
    private String image;
    
    /** 推荐星级 */
    private Integer star;

    /** 推荐短信内容 */
    private String recommend;

    /** 详细产品图片 */
    private String introimg;

    /** 短信码 */
    private String smscode;

    /** 短信内容 */
    private String smscontent;
    
    /** 产品图片 */
    private File images;

    /** image文件名 */
    private String imagesFileName;

    /** image文件类型 */
    private String imagesContentType;
    
    /** 详细图片 */
    private File introimgs;
    
    /** introimgs文件名 */
    private String introimgsFileName;
    
    /** introimgs类型 */
    private String introimgsContentType;

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getLOGIN_NAME() {
		return LOGIN_NAME;
	}

	public void setLOGIN_NAME(String login_name) {
		LOGIN_NAME = login_name;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(String user_type) {
		USER_TYPE = user_type;
	}

	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(String product_id) {
		PRODUCT_ID = product_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getIntroimg() {
		return introimg;
	}

	public void setIntroimg(String introimg) {
		this.introimg = introimg;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

	public String getSmscontent() {
		return smscontent;
	}

	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}

	public File getImages() {
		return images;
	}

	public void setImages(File images) {
		this.images = images;
	}

	public String getImagesFileName() {
		return imagesFileName;
	}

	public void setImagesFileName(String imagesFileName) {
		this.imagesFileName = imagesFileName;
	}

	public String getImagesContentType() {
		return imagesContentType;
	}

	public void setImagesContentType(String imagesContentType) {
		this.imagesContentType = imagesContentType;
	}

	public File getIntroimgs() {
		return introimgs;
	}

	public void setIntroimgs(File introimgs) {
		this.introimgs = introimgs;
	}

	public String getIntroimgsFileName() {
		return introimgsFileName;
	}

	public void setIntroimgsFileName(String introimgsFileName) {
		this.introimgsFileName = introimgsFileName;
	}

	public String getIntroimgsContentType() {
		return introimgsContentType;
	}

	public void setIntroimgsContentType(String introimgsContentType) {
		this.introimgsContentType = introimgsContentType;
	}
	
	
  
}
