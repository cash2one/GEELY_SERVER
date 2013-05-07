package com.fsc.xxt.si.questions.service;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.questions.vo.QuestionsVo;

/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:问题反馈服务层接口</p>
 * <p>创建日期:2012/03/05</p>
 * @author LiuYuan
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface QuestionsService extends BaseService{
	/**
     * 保存问题反馈信息
     * @param CONTENT
     * @param SUBMISSION
     * @return
     * @throws Exception
     */
	public void SaveQuestionInfo(String content,String submission)throws Exception;
	
	/**
	 * 分页查询(后台)
	 * @author: 刘源
	 * @date：2012-5-16 上午11:02:10   
	 * @return：void   
	 * @throws: Exception
	 */
	public void selectPageData(QuestionsVo questionsvo)throws Exception;
	
	/**
	 * 设置为已读
	 * @author: 刘源
	 * @date：2012-5-16 下午05:16:14   
	 * @return：void   
	 * @throws: Exception
	 */
	public void setmyflag(String ids[])throws Exception;
	
	
}
