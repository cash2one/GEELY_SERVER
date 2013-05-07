package com.fsc.xxt.sys.user.service;

import com.fsc.xxt.sys.user.po.User;
import com.fsc.xxt.sys.user.vo.UserVo;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface UserService extends BaseService {
    /**
     * 根据登录用户名查找用户
     * @param loginName
     * @return
     * @throws DataAccessException
     */
    public User findUserByLoginName(String loginName)
        throws DataAccessException;

    /**
     * 根据用户ID查找用户
     * @param id
     * @return
     * @throws DataAccessException
     */
    public User findUserById(String id) throws DataAccessException;

    /**
     *  查询用户信息列表，查询条件通过VO传递，部门编号为空则查找所有用户
     * @param userVo
     * @return
     * @throws DataAccessException
     */
    public List selectUser(UserVo userVo) throws DataAccessException;

    /**
     * 查询分页用户信息列表，查询条件通过VO传递，部门编号为空则查找所有用户
     * @param userVo
     * @return
     * @throws Exception
     */
    public void selectPageUser(UserVo userVo) throws Exception;

    /**
     * 根据用户ID查询用户已分配角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    public List selectUserRole(String userId) throws Exception;

    /**
     * 保存用户角色信息
     * @param role
     * @throws Exception
     */
    public void saveUserRole(String userId, String[] roleId)
        throws Exception;

    /**
     * 根据部门编号查找用户信息列表
     * @param deptNo
     * @return
     * @throws Exception
     */
    public List selectUserByDeptId(String deptId) throws Exception;

}
