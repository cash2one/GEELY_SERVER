package com.fsc.xxt.si.product.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品表对应的POJO类</p>
 * <p>创建日期:Feb 9, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Product extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 5585707784021170331L;
    private String id;

    //产品名称
    private String title;

    //价格
    private Integer price;

    //服务对象
    private String customer;

    //介绍
    private String intro;

    //发布时间
    private Date putTime;

    //产品图片
    private String image;

    //推荐星级
    private Integer star;
    
    //推荐短信内容
    private String recommend;
    
    //详细产品图片
    private String introimg;
    
    //短信码
    private String smscode;
    
    //短信内容
    private String smscontent;

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
    
	
    
}
