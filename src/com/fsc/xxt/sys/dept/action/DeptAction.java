package com.fsc.xxt.sys.dept.action;

import com.fsc.xxt.sys.dept.po.Dept;
import com.fsc.xxt.sys.dept.service.DeptService;
import com.fsc.xxt.sys.dept.vo.DeptVo;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.org.service.OrgService;
import com.fsc.xxt.sys.user.service.UserService;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.constant.CommonConstants;


import org.apache.commons.beanutils.PropertyUtils;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:部门信息管理控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DeptAction extends ManageAction {
    private DeptVo deptVo;
    private OrgService orgService;
    private DeptService deptService;
    private UserService userService;
    private DictionaryService dictionaryService;
    private String url;

    public DeptAction() {
        deptVo = new DeptVo();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public DeptVo getModel() {
        return deptVo;
    }

    /**
    * 进入部门信息管理框架页面
    * @return
    * @throws Exception
    */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 查询供电单位及部门组织信息树形列表
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        deptVo.setOrgList(orgService.selectOrgSort(CommonConstants.ROOTUNITNO));
        deptVo.setDeptList(deptService.selectDeptSort("", ""));

        return "tree";
    }

    /**
     * 部门信息下级列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        deptVo.setList(deptService.selectDeptByPdeptNo(deptVo.getOrgNo(),
                deptVo.getPdeptNo()));

        //查询上级部门信息
        if ((deptVo.getPdeptNo() != null) && !"".equals(deptVo.getPdeptNo())) {
            deptVo.setPdept(deptService.findDeptById(deptVo.getPdeptNo()));
        }

        //查询所属单位
        if (deptVo.getPdept() != null) {
            deptVo.setOrg(deptVo.getPdept().getOrg());
        } else {
            deptVo.setOrg(orgService.findOrgByOrgNo(deptVo.getOrgNo()));
        }

        for (int i = 0; i < deptVo.getList().size(); i++) {
            Dept dept = (Dept) deptVo.getList().get(i);
            dept.setPdpet(deptVo.getPdept());
            dept.setOrg(deptVo.getOrg());
        }

        return "list";
    }

    /**
     * 新增部门信息
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        //查询所属单位信息
        deptVo.setOrg(orgService.findOrgByOrgNo(deptVo.getOrgNo()));

        //查询上级部门下拉列表
        deptVo.setDeptList(deptService.selectDeptSort("", deptVo.getOrgNo(), ""));
        //格式化部门列表
        deptVo.setDeptList(deptService.transformList(deptVo.getDeptList()));

        //生成部门编号
        //deptVo.setDeptNo(deptService.genDeptNo(deptVo.getOrgNo()));
        return "input";
    }

    /**
     * 部门信息编辑
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Dept dept = deptService.findDeptById(deptVo.getId());
        PropertyUtils.copyProperties(deptVo, dept);
        //查询所属单位信息
        deptVo.setOrg(orgService.findOrgByOrgNo(deptVo.getOrgNo()));

        //查询上级部门下拉列表
        deptVo.setDeptList(deptService.selectDeptSort(deptVo.getId(),
                deptVo.getOrgNo(), ""));
        //格式化部门列表
        deptVo.setDeptList(deptService.transformList(deptVo.getDeptList()));

        return "input";
    }

    /**
     * 保存部门信息的服务器验证
     */
    public void validateSave() {
        boolean flag = false;

        if ((deptVo.getName() == null) || "".equals(deptVo.getName())) {
            addFieldError("name", "部门名称不能为空");
            flag = true;
        } else if (deptVo.getName().length() > 256) {
            addFieldError("name", "部门名称长度最大为256");
            flag = true;
        }

        if ((deptVo.getAbbr() == null) || "".equals(deptVo.getAbbr())) {
            addFieldError("abbr", "部门简称不能为空");
            flag = true;
        } else if (deptVo.getAbbr().length() > 256) {
            addFieldError("abbr", "部门简称长度最大为256");
            flag = true;
        }

        if ((deptVo.getTypeCode() == null) || "".equals(deptVo.getTypeCode())) {
            addFieldError("typeCode", "部门类型不能为空");
            flag = true;
        }

        if (deptVo.getDispSN() == null) {
            addFieldError("dispSN", "排序号不能为空");
            flag = true;

            return;
        } else {
            if (deptVo.getDispSN().toString().length() > 5) {
                addFieldError("dispSN", "排序号最大长度不能为5");
                flag = true;
            }
        }

        if ((deptVo.getId() == null) || "".equals(deptVo.getId())) {
            try {
                if (deptService.ifExistsSameNameInOneDept(
                            deptVo.getId().trim(), deptVo.getName().trim())) {
                    addFieldError("name", "该部门名称已经存在");
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag) {
            if ((deptVo.getId() == null) || "".equals(deptVo.getId())) {
                deptVo.setOrg(orgService.findOrgByOrgNo(deptVo.getOrgNo()));

                //查询上级部门下拉列表
                deptVo.setDeptList(deptService.selectDeptSort("",
                        deptVo.getOrgNo(), ""));

                //格式化部门列表
                try {
                    deptVo.setDeptList(deptService.transformList(
                            deptVo.getDeptList()));

                    //deptVo.setDeptNo(deptService.genDeptNo(deptVo.getOrgNo()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //生成部门编号
            } else {
                Dept dept = deptService.findDeptById(deptVo.getId());

                try {
                    PropertyUtils.copyProperties(deptVo, dept);
                    //查询所属单位信息
                    deptVo.setOrg(orgService.findOrgByOrgNo(deptVo.getOrgNo()));

                    //查询上级部门下拉列表
                    deptVo.setDeptList(deptService.selectDeptSort(
                            deptVo.getId(), deptVo.getOrgNo(), ""));
                    //格式化部门列表
                    deptVo.setDeptList(deptService.transformList(
                            deptVo.getDeptList()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存部门信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Dept dept = new Dept();

        if (deptVo.getId() == null) {
            deptVo.setId(deptService.getId());
            deptVo.setLogout("01");

            //生成部门编号
            //deptVo.setDeptNo(deptService.genDeptNo(deptVo.getOrgNo()));
            PropertyUtils.copyProperties(dept, deptVo);
            deptService.insertObject(dept);
        } else {
            PropertyUtils.copyProperties(dept, deptVo);
            deptService.updateObject(dept);
        }

        return SUCCESS;
    }

    /**
     * 删除部门信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String delete() throws Exception {
        Dept dept = deptService.findDeptById(deptVo.getId());
        PropertyUtils.copyProperties(deptVo, dept);

        List list = deptService.selectDeptByPdeptNo(dept.getOrgNo(),
                dept.getId());
        List userList = userService.selectUserByDeptId(dept.getId());

        if ((list.size() == 0) && (userList.size() == 0)) {
            deptService.deleteObject(dept);

            return SUCCESS;
        } else if (list.size() > 0) {
            deptVo.setMessage("该部门下已存在下级部门信息，不能删除！");

            return list();
        } else if (userList.size() > 0) {
            deptVo.setMessage("该部门下已存在用户，不能删除！");

            return list();
        }

        return SUCCESS;
    }

    /**
     * 根据所选单位加载该单位下的部门列表
     * @return
     * @throws Exception
     */
    public String loadDeptList() throws Exception {
        //部门信息选择下拉列表
        deptVo.setDeptList(deptService.selectDeptSort(deptVo.getOrgNo(), ""));
        deptVo.setDeptList(deptService.transformList(deptVo.getDeptList()));

        return SUCCESS;
    }
}
