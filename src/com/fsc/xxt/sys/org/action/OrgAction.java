package com.fsc.xxt.sys.org.action;

import com.fsc.xxt.sys.dic.constant.DictionaryConstant;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.org.po.Org;
import com.fsc.xxt.sys.org.service.OrgService;
import com.fsc.xxt.sys.org.vo.OrgVo;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.constant.CommonConstants;


import org.apache.commons.beanutils.PropertyUtils;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:单位信息管理控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class OrgAction extends ManageAction {
    private OrgVo orgVo;
    private OrgService orgService;
    private DictionaryService dictionaryService;

    public OrgAction() {
        orgVo = new OrgVo();
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public OrgVo getModel() {
        return orgVo;
    }

    /**
    * 进入单位管理框架页面
    * @return
    * @throws Exception
    */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 查询单位信息树形列表
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        //unitVo.setUnitList(unitService.selectUnit());
        orgVo.setOrgList(orgService.selectOrgSort(CommonConstants.ROOTUNITNO));

        return "tree";
    }

    /**
     * 单位信息下级列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        orgVo.setOrgList(orgService.selectOrgByOrgNo(orgVo.getPorgNo()));

        //查询单位类别字典列表
        orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                DictionaryConstant.UNIT_TYPE));

        return "list";
    }

    /**
     * 新增单位
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        //查询格式化的单位信息列表
        orgVo.setList(orgService.selectFormatOrg());
        //查询单位类别字典列表
        orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                DictionaryConstant.UNIT_TYPE));

        return "input";
    }

    /**
     * 单位信息编辑
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Org org = orgService.findOrgByOrgNo(orgVo.getOrgNo());
        PropertyUtils.copyProperties(orgVo, org);
        //查询格式化的单位信息列表（去掉当前编辑的单位及下级单位）
        orgVo.setList(orgService.selectFormatOrg(orgVo.getOrgNo()));
        //查询单位类别字典列表
        orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                DictionaryConstant.UNIT_TYPE));

        return "input";
    }

    /**
     * 保存单位信息服务器验证
     */
    public void validateSave() {
        boolean flag = false;

        /*if ((orgVo.getOrgNo() == null) || "".equals(orgVo.getOrgNo())) {
            addFieldError("orgNo", "请输入单位编号");
            flag = true;
        } else if ((orgVo.getOrgNo().length() < 5) ||
                (orgVo.getOrgNo().length() > 15)) {
            addFieldError("orgNo", "单位编号为最少为5位，最长为15位，请您仔细确认");
            flag = true;
        }*/
        if ((orgVo.getAbc() == null) || "".equals(orgVo.getAbc())) {
            addFieldError("abc", "请输入单位简码");
            flag = true;
        } else if (orgVo.getAbc().length() > 128) {
            addFieldError("abc", "单位简码最大长度为128");
            flag = true;
        }

        if ((orgVo.getOrgType() == null) || "".equals(orgVo.getOrgType())) {
            addFieldError("orgType", "请选择单位类别");
            flag = true;
        }

        if (orgVo.getSortNo() == null) {
            addFieldError("sortNo", "请输入排序号");
            flag = true;
        }

        if ((orgVo.getOrgName() == null) || "".equals(orgVo.getOrgName())) {
            addFieldError("orgName", "请输入单位名称");
            flag = true;
        } else if (orgVo.getOrgName().length() > 128) {
            addFieldError("orgName", "单位名称最大长度为128");
            flag = true;
        }

        if ((orgVo.getOrgNo() == null) || "".equals(orgVo.getOrgNo())) {
            try {
                if (orgService.ifExistsSameNameInOneOrg(orgVo.getPorgNo(),
                            orgVo.getOrgName())) {
                    addFieldError("orgName", "单位名称已经存在");
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag) {
            if ((orgVo.getOrgNo() == null) || "".equals(orgVo.getOrgNo())) {
                //查询格式化的单位信息列表
                orgVo.setList(orgService.selectFormatOrg());

                //查询单位类别字典列表
                try {
                    orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                            DictionaryConstant.UNIT_TYPE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    //查询格式化的单位信息列表（去掉当前编辑的单位及下级单位）
                    orgVo.setList(orgService.selectFormatOrg(orgVo.getOrgNo()));
                    //查询单位类别字典列表
                    orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                            DictionaryConstant.UNIT_TYPE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存单位信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Org org = new Org();

        if (orgVo.getOrgNo() == null) {
            if (orgService.findOrgByOrgNo(orgVo.getOrgNo()) != null) {
                orgVo.setMessage("该单位编号已经存在，请重新输入新的单位编号!!!");

                //查询格式化的单位信息列表（去掉当前编辑的单位及下级单位）
                orgVo.setList(orgService.selectFormatOrg(orgVo.getOrgNo()));
                //查询单位类别字典列表
                orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                        DictionaryConstant.UNIT_TYPE));

                return "input";
            }

            orgVo.setOrgNo(orgService.getId());
            orgVo.setLogOut("01");

            if ((orgVo.getPorgNo() == null) || "".equals(orgVo.getPorgNo())) {
                orgVo.setPorgNo(CommonConstants.ROOTUNITNO);
            }

            PropertyUtils.copyProperties(org, orgVo);
            orgService.insertObject(org);
        } else {
            Org u2 = orgService.findOrgById(orgVo.getOrgNo());

            if (!u2.getOrgNo().equals(orgVo.getOrgNo())) {
                if (orgService.findOrgByOrgNo(orgVo.getOrgNo()) != null) {
                    orgVo.setMessage("该单位编号已经存在，请重新输入新的单位编号!!!");

                    //查询格式化的单位信息列表（去掉当前编辑的单位及下级单位）
                    orgVo.setList(orgService.selectFormatOrg(orgVo.getOrgNo()));
                    //查询单位类别字典列表
                    orgVo.setOrgTypeList(dictionaryService.selectDictionaries(
                            DictionaryConstant.UNIT_TYPE));

                    return "input";
                }
            }

            if ((orgVo.getPorgNo() == null) || "".equals(orgVo.getPorgNo())) {
                orgVo.setPorgNo(CommonConstants.ROOTUNITNO);
            }

            PropertyUtils.copyProperties(org, orgVo);
            orgService.updateObject(org);
        }

        return SUCCESS;
    }

    /**
     * 删除单位信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        Org org = orgService.findOrgById(orgVo.getOrgNo());
        PropertyUtils.copyProperties(orgVo, org);

        List list = orgService.selectOrgByOrgNo(orgVo.getOrgNo());

        if (list.size() == 0) {
            orgService.deleteObject(org);

            return SUCCESS;
        } else {
            orgVo.setMessage("该单位下已存在下级单位信息，不能删除！");

            return edit();
        }
    }

    /**
     * jason 验证单位编号
     * @return
     * @throws Exception
     */
    public String validOrgNo() throws Exception {
        orgVo.setMessage("1");

        if (orgVo.getOrgNo() == null) {
            if (orgService.findOrgByOrgNo(orgVo.getOrgNo()) != null) {
                orgVo.setMessage("0");
            }
        } else {
            Org u = orgService.findOrgById(orgVo.getOrgNo());

            if (!u.getOrgNo().equals(orgVo.getOrgNo())) {
                if (orgService.findOrgByOrgNo(orgVo.getOrgNo()) != null) {
                    orgVo.setMessage("0");
                }
            }
        }

        return SUCCESS;
    }
}
