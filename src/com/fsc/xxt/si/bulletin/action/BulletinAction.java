package com.fsc.xxt.si.bulletin.action;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.bulletin.po.Bulletin;
import com.fsc.xxt.si.bulletin.service.BulletinService;


public class BulletinAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private BulletinVo bulletinvo = new BulletinVo();
    private BulletinService bulletinService;

    public BulletinService getBulletinService() {
        return bulletinService;
    }

    public void setBulletinService(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @Override
    public BaseVo getModel() {
        return bulletinvo;
    }

    /**
     * 获得公告信息列表
     * @author: 刘源
     * @date：2012-5-15 上午08:51:07
     * @return：String
     * @throws: Exception
     */
    public String getbulletinlist() throws Exception{
    	bulletinService.selectPageData(bulletinvo);
        return "bullentilist";
    }
    
    /**
     * 新公告信息跳转
     * @author: 刘源
     * @date：2012-5-15 下午02:39:17   
     * @return：String   
     * @throws: Exception
     */
    public String newbulletin()throws Exception{
    	 
    	return "newbulletin";
    }
    
    /**
     * 新公告发布
     * @author: 刘源
     * @date：2012-5-15 下午03:35:05   
     * @return：String   
     * @throws: Exception
     */
    public String savebulletin()throws Exception{
    	bulletinvo.setUserId(this.getLoginUser().getId());
    	this.bulletinService.saveBulletin(bulletinvo);
    	return "savebulletin";
    }
    
    /**
     * 公告信息修改
     * @author: 刘源
     * @date：2012-5-15 下午03:56:36   
     * @return：String   
     * @throws: Exception
     */
    public String edibulletin()throws Exception{
    	if (bulletinvo.getId()!=null && !"".equals(bulletinvo.getId())) {
    		Bulletin bulletin =this.bulletinService.getBulletinById(bulletinvo);
    		
    		try {
                PropertyUtils.copyProperties(bulletinvo, bulletin);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
		}
    	
    	return "edibulletin";
    }
    
    public String delbulletin()throws Exception{
    	if ((bulletinvo.getId() != null) && !"".equals(bulletinvo.getId())) {
            String[] ids = bulletinvo.getId().split(",");
            this.bulletinService.delBulletinById(ids);
        }
    	return "delbulletin";
    }
    
    
    
}
