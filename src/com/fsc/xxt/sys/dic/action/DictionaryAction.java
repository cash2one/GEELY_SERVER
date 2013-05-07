package com.fsc.xxt.sys.dic.action;

import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.dic.vo.DictionaryVo;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.util.StringUtil;


import org.apache.commons.beanutils.PropertyUtils;


/**
 * <p>Title: 办公设备及用品信息管理系统</p>
 * <p>Description:数据字典管理业务控制</p>
 * <p>创建日期:2010-10-09</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class DictionaryAction extends ManageAction {
    /** 数据字典管理表现层对象（用于封装请求参数和处理结果） */
    private DictionaryVo dictionaryVo;

    /** 数据字典管理业务逻辑组件 */
    private DictionaryService dictionaryService;

    public DictionaryAction() {
        dictionaryVo = new DictionaryVo();
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public DictionaryVo getModel() {
        return dictionaryVo;
    }

    /** 设置数据字典管理业务逻辑组件 */
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * 进入数据字典管理框架主界面
     * @return
     * @throws Exception
     */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 进入数据字典管理列表树
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        dictionaryVo.setDicTypeList(dictionaryService.selectDictionaries(
                dictionaryVo.getDicType()));

        return "tree";
    }

    /**
     * 获取所有数据字典信息，进入数据字典管理列表界面
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        Dictionary superDic = dictionaryService.findDictionaryById(dictionaryVo.getSuperDicId());
        dictionaryVo.setSuperDicName(superDic.getDicName());
        dictionaryVo.setDicType(superDic.getDicCode());
        dictionaryService.selectPerPageDictionaries(dictionaryVo.getDicType(),
            dictionaryVo);

        if ("1".equals(dictionaryVo.getRedirectSign()) == false) {
            dictionaryVo.initMessage();
        }

        return "list";
    }

    /**
     * 初始化数据字典信息，进入数据字典新增界面
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        if ((dictionaryVo.getSuperDicId() == null) ||
                "".equals(dictionaryVo.getSuperDicId())) {
            dictionaryVo.setMessage("请先选择类别！");

            return "success";
        } else {
            Dictionary superDic = dictionaryService.findDictionaryById(dictionaryVo.getSuperDicId());
            dictionaryVo.setSuperDicName(superDic.getDicName());
            dictionaryVo.setDicType(superDic.getDicCode());
            dictionaryVo.setDicCodeLen(superDic.getDicCodeLen());
            dictionaryVo.setDicCodeType(superDic.getDicCodeType());
            dictionaryVo.setSign(superDic.getSign());
            dictionaryVo.setDicCode(dictionaryService.getDicCode(
                    dictionaryVo.getDicCodeLen(), dictionaryVo.getDicType()));

            return "input";
        }
    }

    /**
     * 查找数据字典信息，进入数据字典信息修改界面
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        dictionaryVo.initMessage();

        Dictionary dictionary = new Dictionary();
        dictionary = dictionaryService.findDictionaryById(dictionaryVo.getDicId());
        PropertyUtils.copyProperties(dictionaryVo, dictionary);

        Dictionary superDic = dictionaryService.findDictionaryById(dictionaryVo.getSuperDicId());
        dictionaryVo.setSuperDicName(superDic.getDicName());
        dictionaryVo.setDicCodeLen(superDic.getDicCodeLen());
        dictionaryVo.setDicCodeType(superDic.getDicCodeType());
        dictionaryVo.setSign(superDic.getSign());
        dictionaryVo.setDicSortNo(dictionary.getDicSortNo());
        return "input";
    }

    /**
     * 保存数据字典信息的服务器验证
     */
    public void validateSave() {
        if ((dictionaryVo.getSuperDicName() == null) ||
                "".equals(dictionaryVo.getSuperDicName())) {
            addFieldError("superDicName", "数据类别不能为空");
        }

        if ((dictionaryVo.getDicCode() == null) ||
                "".equals(dictionaryVo.getDicCode())) {
            addFieldError("dicCode", "数据代码不能为空");
        }

        if ((dictionaryVo.getDicName() == null) ||
                "".equals(dictionaryVo.getDicName())) {
            addFieldError("dicName", "数据名称不能为空");
        }
        
        if ((dictionaryVo.getDicSortNo() == null) ||
                "".equals(dictionaryVo.getDicName())) {
            addFieldError("dicSortNo", "排序不能为空");
        }
    }

    /**
     * 保存数据字典信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Dictionary dictionary = new Dictionary();
        PropertyUtils.copyProperties(dictionary, dictionaryVo);

        if ((dictionaryVo.getDicId() == null) ||
                "".equals(dictionaryVo.getDicId())) {
            if (dictionaryService.judgeDictionaryIfUnique(dictionary, "insert")) {
                if (dictionaryService.judgeDictionaryIfUnique2(dictionary,
                            "insert")) {
                    dictionary.setDicId(dictionaryService.getId());
                    dictionaryService.insertObject(dictionary);
                    dictionaryVo.setMessage("操作执行成功！");

                    return "success";
                } else {
                    dictionaryVo.setDicId("");
                    dictionaryVo.setMessage("操作执行失败，同一类别的名称不能重复！");

                    return "input";
                }
            } else {
                dictionaryVo.setDicId("");
                dictionaryVo.setMessage("操作执行失败，同一类别的代码不能重复！");

                return "input";
            }
        } else {
            if (dictionaryService.judgeDictionaryIfUnique(dictionary, "update")) {
                if (dictionaryService.judgeDictionaryIfUnique2(dictionary,
                            "update")) {
                    dictionaryService.updateObject(dictionary);
                    dictionaryVo.setMessage("操作执行成功！");

                    return "success";
                } else {
                    log.info(dictionaryVo.getDicId());
                    dictionaryVo.setMessage("操作执行失败，同一类别的名称不能重复！");

                    return "input";
                }
            } else {
                log.info(dictionaryVo.getDicId());
                dictionaryVo.setMessage("操作执行失败，同一类别的代码不能重复！");

                return "input";
            }
        }
    }

    /**
     * 删除数据字典信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        dictionaryVo.initMessage();
        dictionaryService.delete(dictionaryVo, dictionaryVo.getSbfMessage());
        dictionaryVo.setMessage(String.valueOf(dictionaryVo.getSbfMessage()));

        return "success";
    }
}
