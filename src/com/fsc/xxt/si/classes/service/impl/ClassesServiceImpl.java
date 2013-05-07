package com.fsc.xxt.si.classes.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.util.CryptoUtil;
import com.fsc.xxt.si.classes.dao.ClassesDao;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.classes.service.ClassesService;
import com.fsc.xxt.si.classes.vo.ClassesVo;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.student.po.Student;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:班级服务接口实现</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbe
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClassesServiceImpl extends BaseServiceImpl
    implements ClassesService {
    private ClassesDao classesDao;

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    public List selectClassesByTId(String teacherId) throws Exception {
        return classesDao.selectClassesByTId(teacherId);
    }

    @Override
    public void selectPageData(ClassesVo classesvo) throws Exception {
        String hql = " from Classes where 1=1 ";
        if (classesvo.getId()!=null && !"".equals(classesvo.getId())) {
			 hql+=" and schoolid='" + classesvo.getId() + "'";
		}
        hql+=" order by id desc";
        String hqlcount = "select count(*) " + hql;
        this.selectPageData(classesvo, hql, hqlcount);
    }

	@Override
	public void saveclassesInfo(ClassesVo classesvo) throws Exception {
		 Classes classes = new Classes();

	        if ((classesvo.getId() == null) || "".equals(classesvo.getId())) {
	            PropertyUtils.copyProperties(classes, classesvo);
	            classes.setId(getId());
	        } else {
	            PropertyUtils.copyProperties(classes, classesvo);
	        }

	        this.saveOrUpdateObject(classes);
		
	}

	@Override
	public Classes findclassesById(String id) throws Exception {
		// TODO Auto-generated method stub
		return classesDao.findclassesById(id);
	}

	@Override
	public void delclasses(String[] ids) throws Exception {
		try {
            String hql = "from Classes where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<Classes> listpro = (List<Classes>)this.selectDataByHQL(hql, ids);
            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
