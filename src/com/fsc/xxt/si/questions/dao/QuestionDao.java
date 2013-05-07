package com.fsc.xxt.si.questions.dao;

import com.fsc.framework.base.dao.BaseDao;

import com.fsc.xxt.si.questions.po.Questions;


public interface QuestionDao extends BaseDao {
    /**
     * 保存问题反馈信息
     * @author: 刘源
     * @date：2012-5-16 下午09:36:35
     * @return：void
     * @throws: Exception
     */
    public void saveQuestionInfo(Questions questions) throws Exception;
    
    /**
     * 设置处理状态(后台)
     * @author: 刘源
     * @date：2012-5-16 下午09:37:35   
     * @return：void   
     * @throws: Exception
     */
    public void setmyflagv(String hql,String ids[]);
}
