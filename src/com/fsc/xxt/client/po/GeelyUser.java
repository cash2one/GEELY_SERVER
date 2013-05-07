package com.fsc.xxt.client.po;

import com.fsc.framework.base.po.Base;

import java.util.Set;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:吉利用户信息表对应的实体类</p>
 * <p>创建日期:2013-4-9</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyUser extends Base {
    private static final long serialVersionUID = -1210075897180900723L;

    //ID 唯一
    private Integer id;

    //用户代码
    private String code;

    //用户姓名
    private String text;

    //用户密码
    private String pwd;

    //所属部门
    private String dept;

    //用户电话
    private String tel;

    //停用标识(0启用1停用)
    private String flag;

    //创建人
    private String createUser;

    //创建时间
    private String createTime;

    //修改人
    private String changeUser;

    //修改时间
    private String changeTime;

    //用户权限集合
    private Set<GeelyProle> proleSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public Set<GeelyProle> getProleSet() {
        return proleSet;
    }

    public void setProleSet(Set<GeelyProle> proleSet) {
        this.proleSet = proleSet;
    }
}
