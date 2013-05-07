package com.fsc.xxt.si.questions.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.si.constant.QuestionsConstant;
import com.fsc.xxt.si.product.po.Product;
import com.fsc.xxt.si.questions.dao.QuestionDao;
import com.fsc.xxt.si.questions.po.Questions;
import com.fsc.xxt.si.questions.service.QuestionsService;
import com.fsc.xxt.si.questions.vo.QuestionsVo;

import java.util.Date;
import java.util.List;


public class QuestionsServiceImpl extends BaseServiceImpl
    implements QuestionsService {
    private QuestionDao questiondao;

    public QuestionDao getQuestiondao() {
        return questiondao;
    }

    public void setQuestiondao(QuestionDao questiondao) {
        this.questiondao = questiondao;
    }

    @Override
    public void SaveQuestionInfo(String content, String submission)
        throws Exception {
        Questions questions = new Questions();
        questions.setId(getId());
        questions.setContent(content);
        questions.setSubmission(submission);
        questions.setSubmi_time(new Date());
        questions.setHanflag(QuestionsConstant.QUESTION_NO);
        this.questiondao.saveQuestionInfo(questions);
    }

    @Override
    public void selectPageData(QuestionsVo questionsvo)
        throws Exception {
        try {
            String hql = "from Questions where 1=1";

            if ((questionsvo.getHanflag() != null) &&
                    !"".equals(questionsvo.getHanflag())) {
                hql += (" and hanflag='" + questionsvo.getHanflag() + "'");
            }

            if ((questionsvo.getStartime() != null) &&
                    !"".equals(questionsvo.getStartime())) {
                hql += (" and submi_time>=to_date('" +
                questionsvo.getStartime() + "','yyyy-MM-dd')");
            }

            if ((questionsvo.getEndtime() != null) &&
                    !"".equals(questionsvo.getEndtime())) {
                hql += (" and submi_time<=to_date('" +
                questionsvo.getEndtime() + "','yyyy-MM-dd')");
            }

            hql += " order by submi_time desc";

            String countHql = "select count(*) " + hql;
            this.selectPageData(questionsvo, hql, countHql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setmyflag(String[] ids) throws Exception {
        try {
            String hql = "UPDATE Questions set hanflag='01' where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            this.questiondao.setmyflagv(hql, ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
