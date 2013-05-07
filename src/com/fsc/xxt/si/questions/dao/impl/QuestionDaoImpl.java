package com.fsc.xxt.si.questions.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.questions.dao.QuestionDao;
import com.fsc.xxt.si.questions.po.Questions;

import org.hibernate.Query;
import org.hibernate.Session;


public class QuestionDaoImpl extends BaseDaoImpl implements QuestionDao {
    @Override
    public void saveQuestionInfo(Questions questions) throws Exception {
        insertObject(questions);
    }

    @Override
    public void setmyflagv(String hql, String[] ids) {
        Session session = getSession();
        Query query = session.createQuery(hql);

        for (int i = 0; i < ids.length; i++) {
            query.setString(i, ids[i]);
        }

        query.executeUpdate();
        releaseSession(session);
    }
}
