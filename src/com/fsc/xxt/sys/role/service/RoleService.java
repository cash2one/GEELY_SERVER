package com.fsc.xxt.sys.role.service;

import com.fsc.xxt.sys.role.po.Role;
import com.fsc.xxt.sys.role.vo.RoleVo;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface RoleService extends BaseService {
    /**
     * 根据ID查找系统角色信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Role findRoleById(String id) throws DataAccessException;

    /**
     * 根据角色名称查找角色信息
     * @param roleName
     * @return
     * @throws DataAccessException
     */
    public Role findRoleByName(String roleName) throws DataAccessException;

    /**
     * 查找系统角色信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectRole() throws DataAccessException;

    /**
     * 分页查找系统角色信息，将查询结果置于VO中，VO中传递分页参数值
     * @param roleVo
     * @throws Exception
     */
    public void selectPageRole(RoleVo roleVo) throws Exception;

    /**
     * 根据系统角色ID查询角色已分配权限信息列表
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectRolePriv(String roleId) throws Exception;

    /**
     * 保存系统角色权限分配信息
     * @param roleId
     * @param priv
     * @throws Exception
     */
    public void saveRolePriv(String roleId, String[] priv)
        throws Exception;

    /**
     * 删除系统角色信息
     * @param roleId
     * @throws Exception
     */
    public void deleteRole(String roleId) throws Exception;

    /**
     * 根据角色ID查找分配该角色的用户角色信息列表
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectUserRole(String roleId) throws Exception;
}
