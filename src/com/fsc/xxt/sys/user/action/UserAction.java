package com.fsc.xxt.sys.user.action;

import com.fsc.xxt.sys.dept.service.DeptService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.org.service.OrgService;
import com.fsc.xxt.sys.role.service.RoleService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.xxt.sys.user.service.UserService;
import com.fsc.xxt.sys.user.vo.UserVo;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.util.CryptoUtil;
import com.fsc.util.StringUtil;


import org.apache.commons.beanutils.PropertyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息管理</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class UserAction extends ManageAction {
    private UserService userService; //用户管理服务组件
    private OrgService orgService; //单位信息服务组件
    private DeptService deptService; //部门信息服务组件
    private DictionaryService dictionaryService; //数据字典信息服务组件
    private RoleService roleService; //角色信息管理服务组件
    private String loginName;
    private Boolean flag;
    private UserVo userVo = new UserVo();

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public UserVo getModel() {
        return userVo;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
    * 用户管理框架
    * @return
    * @throws Exception
    */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 部门信息树形列表
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        userVo.setOrgList(orgService.selectOrgSort(CommonConstants.ROOTUNITNO));
        userVo.setDeptList(deptService.selectDeptSort("", ""));

        return "tree";
    }

    /**
     * 用户信息管理列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        userService.selectPageUser(userVo);

        for (int i = 0; i < userVo.getList().size(); i++) {
            User user = (User) userVo.getList().get(i);
        }

        //用户状态数据字典
        userVo.setStatusList(dictionaryService.selectDictionaries(
                DictionaryConstant.USER_STATUS));

        return "list";
    }

    /**
     * 新增用户
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        //单位信息选择下拉列表
        userVo.setOrgList(orgService.selectFormatOrg());
        //部门信息选择下拉列表
        userVo.setDeptList(deptService.selectDeptSort(userVo.getOrgNo(), ""));
        userVo.setDeptList(deptService.transformList(userVo.getDeptList()));

        //用户状态数据字典
        userVo.setStatusList(dictionaryService.selectDictionaries(
                DictionaryConstant.USER_STATUS));

        //设置初始密码显示
        userVo.setInitPass(CommonConstants.USERINITPWD);

        return "input";
    }

    /**
     * 用户信息编辑
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        User user = userService.findUserById(userVo.getId());

        try {
            PropertyUtils.copyProperties(userVo, user);
        } catch (RuntimeException e) {
            //e.printStackTrace();
        }

        //单位信息选择下拉列表
        userVo.setOrgList(orgService.selectFormatOrg());
        //部门信息选择下拉列表
        userVo.setDeptList(deptService.selectDeptSort(userVo.getOrgNo(), ""));
        userVo.setDeptList(deptService.transformList(userVo.getDeptList()));

        //用户状态数据字典
        userVo.setStatusList(dictionaryService.selectDictionaries(
                DictionaryConstant.USER_STATUS));


        return "input";
    }

    /**
     * 用户信息保存服务器验证
     */
    public void validateSave() {
        boolean flag = false;

        if ((userVo.getLogin() == null) || "".equals(userVo.getLogin())) {
            addFieldError("login", "请输入登录帐号");
            flag = true;
        } else {
            if (userVo.getLogin().length() > 32) {
                addFieldError("login", "登录帐号最大长度为32");
                flag = true;
            }
        }

//        if ((userVo.getUserTypes() == null) ||
//                (userVo.getUserTypes().length == 0)) {
//            addFieldError("userTypes", "请选择用户类型");
//            flag = true;
//        }

        if ((userVo.getDeptId() == null) || "".equals(userVo.getDeptId())) {
            addFieldError("deptId", "请选择所在部门");
            flag = true;
        }

//        if ((userVo.getOrgNo() == null) || "".equals(userVo.getOrgNo())) {
//            addFieldError("orgNo", "请选择所在单位");
//            flag = true;
//        }

        if ((userVo.getName() == null) || "".equals(userVo.getName())) {
            addFieldError("name", "请输入用户名称");
            flag = true;
        } else if (userVo.getName().length() > 64) {
            addFieldError("name", "用户名称最大长度为64");
            flag = true;
        }

        if ((userVo.getAbc() == null) || "".equals(userVo.getAbc())) {
            addFieldError("abc", "请输入用户简码");
            flag = true;
        }

        if ((userVo.getGenDer() == null) || "".equals(userVo.getGenDer())) {
            addFieldError("genDer", "请输入性别");
            flag = true;
        }

        if ((userVo.getMobile() == null) || "".equals(userVo.getMobile())) {
            addFieldError("mobile", "请输入手机号码");
            flag = true;
        } else {
            Pattern p = Pattern.compile("([0-9]{3})+([0-9]{4})+([0-9]{4})+");
            Matcher m = p.matcher(userVo.getMobile());

            if (!m.matches()) {
                addFieldError("mobile", "请输入正确的手机号码");
                flag = true;
            }
        }

        if (flag) {
            if ((userVo.getId() == null) || "".equals(userVo.getId())) {
                //单位信息选择下拉列表
                userVo.setOrgList(orgService.selectFormatOrg());
                //部门信息选择下拉列表
                userVo.setDeptList(deptService.selectDeptSort(
                        userVo.getOrgNo(), ""));

                try {
                    userVo.setDeptList(deptService.transformList(
                            userVo.getDeptList()));

                    //用户状态数据字典
                    userVo.setStatusList(dictionaryService.selectDictionaries(
                            DictionaryConstant.USER_STATUS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                User user = userService.findUserById(userVo.getId());

                try {
                    PropertyUtils.copyProperties(userVo, user);

                    //单位信息选择下拉列表
                    userVo.setOrgList(orgService.selectFormatOrg());
                    //部门信息选择下拉列表
                    userVo.setDeptList(deptService.selectDeptSort(
                            userVo.getOrgNo(), ""));
                    userVo.setDeptList(deptService.transformList(
                            userVo.getDeptList()));

                    //用户状态数据字典
                    userVo.setStatusList(dictionaryService.selectDictionaries(
                            DictionaryConstant.USER_STATUS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 用户信息保存
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        User user = new User();

        if ((userVo.getId() == null) || "".equals(userVo.getId())) {
            User t_user = userService.findUserByLoginName(userVo.getLogin());

            if (t_user == null) {
                //新增用户
                userVo.setId(userService.getId()); //获取用户ID编号
                userVo.setPwd(CryptoUtil.desEncrypt(CommonConstants.USERINITPWD)); //初始化密码
                userVo.setStatus("01"); //初始化状态为正常
                PropertyUtils.copyProperties(user, userVo);

//                String userType = StringUtil.arrayToString(userVo.getUserTypes(),
//                        ",");
//                user.setUserType(userType);
                userService.insertObject(user);
            } else {
                userVo.setMessage("用户登陆账号已经存在，请您更改登陆账号！");

                return add();
            }
        } else {
            //修改用户
            User u = userService.findUserById(userVo.getId());

            try {
                PropertyUtils.copyProperties(user, userVo);
            } catch (RuntimeException e) {
            }

            user.setLogin(u.getLogin()); //用户帐号
            user.setPwd(u.getPwd()); //用户密码
            user.setOnlines(u.getOnlines()); //是否在线
            user.setStatus(u.getStatus()); //用户状态

//            String userType = StringUtil.arrayToString(userVo.getUserTypes(),
//                    ",");
//            user.setUserType(userType);
            userService.updateObject(user);
        }

        return SUCCESS;
    }

    /**
     * 帐号冻结
     * @return
     * @throws Exception
     */
    public String lock() throws Exception {
        User user = userService.findUserById(userVo.getId());

        if (user != null) {
            user.setStatus("02"); //更改帐号为冻结锁定状态
            userService.updateObject(user);
        }

        return SUCCESS;
    }

    /**
     * 帐号解锁
     * @return
     * @throws Exception
     */
    public String unlock() throws Exception {
        User user = userService.findUserById(userVo.getId());

        if (user != null) {
            user.setStatus("01"); //更改帐号为解锁正常状态
            userService.updateObject(user);
        }

        return SUCCESS;
    }

    /**
     * 用户角色分配
     * @return
     * @throws Exception
     */
    public String roleSet() throws Exception {
        User user = userService.findUserById(userVo.getId());
        PropertyUtils.copyProperties(userVo, user);

        //系统角色信息列表
        userVo.setRoleList(roleService.selectRole());
        //用户已分配角色信息列表
        userVo.setUserRoleList(userService.selectUserRole(userVo.getId()));

        return "roleSet";
    }

    /**
     * 用户角色分配信息保存
     * @return
     * @throws Exception
     */
    public String saveRoleSet() throws Exception {
        userService.saveUserRole(userVo.getId(), userVo.getIds());
        userVo.setMessage("角色分配成功!!!");

        return roleSet();
    }

    /**
     * 修改密码
     * @return
     * @throws Exception
     */
    public String editPass() throws Exception {
        return SUCCESS;
    }

    /**
     * 密码修改保存
     * @return
     * @throws Exception
     */
    public String savePass() throws Exception {
        String oldPass = CryptoUtil.desEncrypt(userVo.getOldPwd());
        String newPass = CryptoUtil.desEncrypt(userVo.getPwd());
        User u = getLoginUser();

        if (u.getPwd().equals(oldPass)) {
            //旧密码验证正确
            u.setPwd(newPass);
            userService.updateObject(u);
            userVo.setMessage("修改密码成功！");
        } else {
            //旧密码验证不正确
            userVo.setMessage("旧密码输入错误！");
        }

        return SUCCESS;
    }

    /**
     * 重置密码
     * @return
     * @throws Exception
     */
    public String resetPass() throws Exception {
        User user = userService.findUserById(userVo.getId());
        user.setPwd(CryptoUtil.desEncrypt(CommonConstants.USERINITPWD)); //重置为初始化密码
        userService.updateObject(user);
        userVo.setMessage("密码重置成功！");

        return SUCCESS;
    }

    /**
     * 查询用户所在单位部门下拉列表
     * @return
     * @throws Exception
     */
    public String deptList() throws Exception {
        //根据用户所在单位编号查询单位下部门信息下拉列表
        userVo.setDeptList(deptService.selectDeptSort(userVo.getOrgNo(), ""));
        userVo.setDeptList(deptService.transformList(userVo.getDeptList()));

        return "deptList";
    }

    public String validateLoginName() throws Exception {
        User user = userService.findUserByLoginName(this.loginName);

        if (user == null) {
            flag = true;
        } else {
            flag = false;
        }

        return SUCCESS;
    }

    /**
     * 加载该部门下的用户列表
     * @return
     * @throws Exception
     */
    public String loadUserList() throws Exception {
        userVo.setUserList(userService.selectUserByDeptId(userVo.getDeptId()));

        return SUCCESS;
    }
}
