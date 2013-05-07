package com.fsc.xxt.basedata.province.service.impl;

import com.fsc.xxt.basedata.area.dao.AreaDAO;
import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.province.dao.ProvinceDAO;
import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.xxt.basedata.province.service.ProvinceTreeService;
import com.fsc.xxt.basedata.town.dao.TownDAO;
import com.fsc.xxt.basedata.town.po.Town;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.tree.NodeInfo;
import com.fsc.framework.tree.NodeParser;
import com.fsc.framework.tree.Param;
import com.fsc.framework.tree.TreeParser;
import com.fsc.framework.tree.TreeUtil;

import com.fsc.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:地区服务</p>
 * <p>创建日期:2010-12-2</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ProvinceTreeServiceImpl extends BaseServiceImpl
    implements ProvinceTreeService {
    ProvinceDAO provinceDAO;
    AreaDAO areaDAO;
    TownDAO townDAO;
    TreeParser provinceTree = new TreeParser();
    public ProvinceTreeServiceImpl() {
    	String url="school!list.do";
        NodeParser defaultParser = new ProvinceParser(url);
        Map map = new HashMap();
        map.put(ProvinceParser.class.getSimpleName(),
            new ProvinceParser(url));
        map.put(AreaParser.class.getSimpleName(), new AreaParser(url));
        map.put(TownParser.class.getSimpleName(), new TownParser(url));
        provinceTree.setDefaultParser(defaultParser);
        provinceTree.setParserMap(map);
	}
    public void setProvinceDAO(ProvinceDAO provinceDAO) {
        this.provinceDAO = provinceDAO;
    }

    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    public void setTownDAO(TownDAO townDAO) {
        this.townDAO = townDAO;
    }

    public List selectProvinceTree(String params) throws Exception {
        List list = provinceTree.selectTree(params, null);
        return list;
    }

    private class ProvinceParser implements NodeParser {
        private String url;

        public ProvinceParser(String url) {
            super();
            this.url = url;
        }

        public List parseParams(Param params, Map paramMap)
            throws Exception {
            //查询教材体系
            List pList = provinceDAO.selectAllProvince();
            List items = new ArrayList(pList.size());

            for (int i = 0; i < pList.size(); i++) {
                Province province = (Province) pList.get(i);
                NodeInfo nodeInfo = new NodeInfo();

                //类型
                String type = null;

                //父节点
                String pno = null;
                type = AreaParser.class.getSimpleName();

                String idInfo = TreeUtil.createIdInfo(province.getId(), type,
                        pno, null);
                nodeInfo.putKeys(idInfo, province.getProvince(), STATE_CLOSED);
                nodeInfo.putAttr(null, this.getClass().getSimpleName(),
                    pno, null, null);
                items.add(nodeInfo.getItem());
            }

            return items;
        }
    }

    /**
     *
     * 地区类型
     */
    private class AreaParser implements NodeParser {
        private String url;

        public AreaParser(String url) {
            super();
            this.url = url;
        }

        public List parseParams(Param params, Map paramMap)
            throws Exception {
            String provinceId = params.getId();
            List aList = areaDAO.selectAreaByProvinceId(provinceId);
            List items = new ArrayList(aList.size());

            for (int i = 0; i < aList.size(); i++) {
                Area area = (Area) aList.get(i);
                NodeInfo nodeInfo = new NodeInfo();

                //类型
                String type = TownParser.class.getSimpleName();

                //父节点
                String pno = provinceId;
                String idInfo = TreeUtil.createIdInfo(area.getId(), type, pno,
                        null);
                nodeInfo.putKeys(idInfo, area.getArea(), STATE_CLOSED);
                String url=this.url+"?areaid="+area.getId();
                nodeInfo.putAttr(url, this.getClass().getSimpleName(),
                    pno, area.getId(), null);

                items.add(nodeInfo.getItem());
            }

            return items;
        }
    }

    private class TownParser implements NodeParser {
        private String url;

        public TownParser(String url) {
            super();
            this.url = url;
        }

        public List parseParams(Param params, Map paramMap)
            throws Exception {
            String areaId = params.getId();
            List tList = townDAO.selectTownByAreaId(areaId);
            List items = new ArrayList(tList.size());

            for (int i = 0; i < tList.size(); i++) {
                Town town = (Town) tList.get(i);
                NodeInfo nodeInfo = new NodeInfo();

                //类型
                String type = null;

                //父节点
                String pno = areaId;
                String idInfo = TreeUtil.createIdInfo(town.getId(), type, pno,
                        null);
                nodeInfo.putKeys(idInfo, town.getTown(), STATE_OPEN);
                String url=this.url+"?townid="+town.getId();
                nodeInfo.putAttr(url, this.getClass().getSimpleName(),
                    pno, town.getId(), null);
                items.add(nodeInfo.getItem());
            }
            return items;
        }
    }
}
