package com.fsc.xxt.client.service;

import com.fsc.framework.base.service.BaseService;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:手机客户服务接口</p>
 * <p>创建日期:Jan 5, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface UserService extends BaseService {
    /**
     * 按用户类型和登录名查找客户端用户
     * @param userType
     * @param loginname
     * @return
     * @throws Exception
     */
    Object getUserByLogin(String userType, String loginname)
        throws Exception;

    /**
         * 按用户类型和登录名查找客户端用户ID
         * @param userType
         * @param loginname
         * @return
         * @throws Exception
         */
    String getUserIdByLogin(String userType, String loginname)
        throws Exception;

    /**
         * 按用户类型和用户ID查找客户端用户
         * @param userType
         * @param loginname
         * @return
         * @throws Exception
         */
    Object getUserById(String userType, String id) throws Exception;
    
    /**
     * 按用户手机号码查找客户端用户
     * @param userType
     * @param loginname
     * @return
     * @throws Exception
     */
    Object getUserByMobile(String mobile) throws Exception;
}
