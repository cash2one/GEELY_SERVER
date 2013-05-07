package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.po.MobileUser;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.util.DateUtils2;
import com.fsc.util.StringUtil;

import com.fsc.xxt.client.vo.MsgVo;
import com.fsc.xxt.si.constant.MsgConstant;
import com.fsc.xxt.si.msg.po.ClassMsg;
import com.fsc.xxt.si.msg.po.Msg;
import com.fsc.xxt.si.msg.po.MsgCollection;
import com.fsc.xxt.si.msg.service.MsgCollectionService;
import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.si.product.po.Product;
import com.fsc.xxt.si.product.po.ProductCollection;
import com.fsc.xxt.si.product.service.ProductService;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.service.TeacherService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import org.apache.commons.beanutils.PropertyUtils;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:校讯通手机服务端
 * </p>
 * <p>
 * Description:平台交互信息Action
 * </p>
 * <p>
 * 创建日期:Dec 22, 2011
 * </p>
 * 
 * @author tbw
 * @version 1.0
 *          <p>
 *          湖南家校圈科技有限公司
 *          </p>
 *          <p>
 *          http://www.139910.com/
 *          </p>
 *          <p>
 *          http://old.139910.com:8080/
 *          </p>
 */
public class MsgAction extends ClientAction {
	private MsgVo msgVo = new MsgVo();
	private MsgService msgService;
	private StudentService studentService;
	private MsgCollectionService msgCollectionService;
	private TeacherService teacherService;
	private ProductService productService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}

	public void setMsgCollectionService(
			MsgCollectionService msgCollectionService) {
		this.msgCollectionService = msgCollectionService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public BaseVo getModel() {
		return msgVo;
	}

	/**
	 * 返回消息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listMsg() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户名
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		// 发送人ID
		if (StringUtil.isEmpty(msgVo.getLINK_MAN_ID())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送人ID");

			return SUCCESS;
		}

		// 发送人类型
		if (StringUtil.isEmpty(msgVo.getLINK_MAN_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送人类型");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		String id = null;

		MobileUser user = (MobileUser) getUserByLogin(msgVo.getUSER_TYPE(),
				msgVo.getLOGIN_NAME());
		id = user.getId();

		List list = msgService.selectLinkManMsg(id, msgVo.getUSER_TYPE(), msgVo
				.getLINK_MAN_ID(), msgVo.getLINK_MAN_TYPE(), 200);

		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap(5);
			Msg msg = (Msg) list.get(i);
			Date motime = msg.getMotime();
			map.put("MESSAGE_TIME", DateUtils2.formatDate3(motime));
			map.put("MESSAGE_INFO", msg.getContent());
			map.put("MESSAGE_NO", msg.getId());
			map.put("SUSER", msg.getSuser());
			map.put("SUSERTYPE", msg.getSusertype());

			String flag = id.equals(msg.getSuser()) ? "S" : "R";
			map.put("MESSAGE_FLAG", flag);
			msgVo.addListData(map);
		}

		return "list";
	}

	/**
	 * 个人发送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendOne() throws Exception {
		String susername = msgVo.getLOGIN_NAME();
		String susertype = msgVo.getUSER_TYPE();
		String ruserid = msgVo.getRUSER_ID();
		String rusertype = msgVo.getRUSER_TYPE();
		String id = msgVo.getID();

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]
		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(susername)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送者用户名");

			return SUCCESS;
		}

		// 用户名
		if (StringUtil.isEmpty(susertype)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送者用户类型");

			return SUCCESS;
		}

		// 发送人ID
		if (StringUtil.isEmpty(ruserid)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：接受者用户ID");

			return SUCCESS;
		}

		// 发送人类型
		if (StringUtil.isEmpty(rusertype)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：接受者用户类型");

			return SUCCESS;
		}
		
		if (StringUtil.isEmpty(id)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：ID");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		String mobile = null;
		Msg msg = new Msg();

		MobileUser sendUser = (MobileUser) userService.getUserByLogin(
				susertype, susername);
		msg.setSuser(sendUser.getId());
		msg.setSusertype(susertype);
		msg.setSusername(sendUser.getName());

		MobileUser recvUser = (MobileUser) userService.getUserById(rusertype,
				ruserid);
		msg.setRuser(recvUser.getId());
		msg.setRusertype(rusertype);
		msg.setRusername(recvUser.getName());

		mobile = recvUser.getMobile();
		msg.setId(id);
		msg.setContent(msgVo.getMESSAGE_CONTENT());

		msg.setType(msgVo.getMESSAGE_TYPE());
		msg.setMttype(MsgConstant.MTTYPE_SMS);
		msg.setMotype(MsgConstant.MOTYPE_ONE);
		msg.setReadflag("01");
//		msg.setMttime(null);
//		msg.setMtflag("01");
		msgService.saveMsg(msg, mobile);

		msgVo.setMessageNo(msgVo.getID());
		msgVo.setResultDesc("发送成功");
		msgVo.setResultCode("0");

		return SUCCESS;
	}

	/**
	 * 班级发送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendClass() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户名
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		// 班级ID
		if (StringUtil.isEmpty(msgVo.getCLASS_ID())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：班级ID");

			return SUCCESS;
		}

		// 消息内容
		if (StringUtil.isEmpty(msgVo.getMESSAGE_CONTENT())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：消息内容");

			return SUCCESS;
		}

		// 消息类型
		if (StringUtil.isEmpty(msgVo.getMESSAGE_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：消息类型");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		Teacher teacher = (Teacher) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());
		List stuList = studentService.selectStuByClass(msgVo.getCLASS_ID());
		List msgList = new ArrayList(stuList.size());
		List mobileList = new ArrayList(stuList.size());

		for (int i = 0; i < stuList.size(); i++) {
			String sendUserType = msgVo.getUSER_TYPE();

			Student student = (Student) stuList.get(i);

			String mobile = null;
			Msg msg = new Msg();

			MobileUser sendUser = (MobileUser) userService.getUserById(
					sendUserType, teacher.getId());
			msg.setSuser(sendUser.getId());
			msg.setSusertype(sendUserType);
			msg.setSusername(sendUser.getName());

			MobileUser recvUser = (MobileUser) userService.getUserById(
					DictionaryConstant.USER_STUDENT, student.getId());
			msg.setRuser(recvUser.getId());
			msg.setRusertype(recvUser.getUserType());
			msg.setRusername(recvUser.getName());

			mobile = recvUser.getMobile();
			msg.setId(msgService.getId());
			msg.setContent(msgVo.getMESSAGE_CONTENT());

			msg.setType(msgVo.getMESSAGE_TYPE());
			msg.setMttype(MsgConstant.MTTYPE_SMS);
			msg.setMotype(MsgConstant.MOTYPE_CLASS);
			msg.setReadflag("01");
			msg.setMttime(null);
			msg.setMtflag("01");
			msgList.add(msg);
			mobileList.add(mobile);
		}

		msgService.saveMsg(msgList, mobileList);

		// Carfield 2012-01-13 [Add] 添加班级发送信息历史记录 [Start]
		ClassMsg classMsg = new ClassMsg();
		classMsg.setId(msgService.getId());
		classMsg.setClassId(msgVo.getCLASS_ID());
		classMsg.setTeacherId(teacher.getId());
		classMsg.setContent(msgVo.getMESSAGE_CONTENT());
		classMsg.setSendTime(new Date());
		classMsg.setDelFlag(CommonConstants.DELFLAG_UNDEL);
		classMsg.setMsgType(msgVo.getMESSAGE_TYPE());
		msgService.saveClassMsg(classMsg);
		// Carfield 2012-01-13 [Add] 添加班级发送信息历史记录 [End]
		msgVo.setMessageNo(classMsg.getId());
		msgVo.setResultCode("0");
		msgVo.setResultDesc("发送成功");

		return SUCCESS;
	} 

	/**
	 * 发送多人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMany() throws Exception {
		String susername = msgVo.getLOGIN_NAME();
		String susertype = msgVo.getUSER_TYPE();
		String ruserids = msgVo.getRUSER_IDS();
		String rusertypes = msgVo.getRUSER_TYPES();
		String content = msgVo.getMESSAGE_CONTENT();

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]
		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(susername)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送者用户名");

			return SUCCESS;
		}

		// 用户名
		if (StringUtil.isEmpty(susertype)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：发送者用户类型");

			return SUCCESS;
		}

		// 发送人ID
		if (StringUtil.isEmpty(ruserids)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：接受者用户名ID");

			return SUCCESS;
		}

		// 发送人类型
		if (StringUtil.isEmpty(rusertypes)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：接受者用户类型");

			return SUCCESS;
		}

		if (StringUtil.isEmpty(content)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：信息内容");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		String mobile = null;
		String[] ids = ruserids.split(",");
		String[] types = rusertypes.split(",");

		List<Msg> msgList = new ArrayList<Msg>();
		List<String> mobileList = new ArrayList<String>();

		for (int i = 0; i < ids.length; i++) {
			Msg msg = new Msg();

			MobileUser sendUser = (MobileUser) userService.getUserByLogin(
					susertype, susername);
			msg.setSuser(sendUser.getId());
			msg.setSusertype(susertype);
			msg.setSusername(sendUser.getName());

			MobileUser recvUser = (MobileUser) userService.getUserById(
					types[i], ids[i]);
			msg.setRuser(recvUser.getId());
			msg.setRusertype(types[i]);
			msg.setRusername(recvUser.getName());

			mobile = recvUser.getMobile();
			msg.setId(msgService.getId());
			msg.setContent(content);

			msg.setType(msgVo.getMESSAGE_TYPE());
			msg.setMttype(MsgConstant.MTTYPE_SMS);
			msg.setMotype(MsgConstant.MOTYPE_ONE);
			msg.setReadflag("01");
			msg.setMttime(null);
			msg.setMtflag("01");
			msgList.add(msg);
			mobileList.add(mobile);
		}

		msgService.saveMsg(msgList, mobileList);

		msgVo.setResultDesc("发送成功");
		msgVo.setResultCode("0");

		return SUCCESS;
	}

	/**
	 * 插入一条消息记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		String content = msgVo.getMESSAGE_CONTENT();
		String mobile = msgVo.getMOBILE();

		// String mobile = RegexpUtils.getHardRegexpArray(content,
		// "1\\d{10,10}")
		// .get(0);
		// Carfield 2012-01-13 [Add] 添加参数验证 [End]
		// 班级ID
		if (StringUtil.isEmpty(mobile)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：手机号码");

			return SUCCESS;
		}

		// 班级ID
		if (StringUtil.isEmpty(content)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：消息内容");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 添加参数验证 [End]
		if (mobile == null) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("参数中无手机号码");

			return SUCCESS;
		}

		MobileUser sendUser = (MobileUser) userService.getUserByMobile(mobile);

		MobileUser recvUser = (MobileUser) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());
		Msg msg = new Msg();
		msg.setId(msgService.getId());
		// 【来自小明家长（15675854445）的回复短信】短信内容我孩子最近可好？
		content = content.substring(content.indexOf("】") + 1);
		msg.setContent(content);
		msg.setMotype(MsgConstant.MOTYPE_ONE);
		msg.setMttype(MsgConstant.MTTYPE_CLIENT);
		msg.setRuser(recvUser.getId());
		msg.setRusertype(msgVo.getUSER_TYPE());
		msg.setRusername(recvUser.getName());

		if (sendUser == null) {
			// 发送人为10658561等，无对应数据在用户信息表中存在的
			msg.setSuser(sendUser.getId());
			msg.setSusername(sendUser.getName());
			msg.setSusertype(sendUser.getUserType());
		}

		msg.setType(null);
		msgService.saveMsg(msg);

		msgVo.setMessageNo(msg.getId());
		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}

	/**
	 * 删除消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		String id = msgVo.getMESSAGE_NO();

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(id)) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：信息ID");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		msgService.delMsg(id);
		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}

	/**
	 * 收藏消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String collect() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getMESSAGE_NO())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：信息ID");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		MobileUser user = (MobileUser) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());
		Msg msg = msgService.selectMsgById(msgVo.getMESSAGE_NO());
		MsgCollection msgCollection = new MsgCollection();
		PropertyUtils.copyProperties(msgCollection, msg);
		msgCollection.setTitle("消息收藏");
		msgCollection.setUserid(user.getId());
		msgCollection.setUsertype(msgVo.getUSER_TYPE());
		msgCollection.setSuser(msg.getSuser());
		msgCollection.setSusertype(msg.getSusertype());
		msgCollectionService.saveMsgCollection(msgCollection);

		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}

	/**
	 * 更新阅读状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateReadflag() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLINK_MAN_ID())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：联系人ID");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLINK_MAN_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：联系人类型");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		MobileUser ruser = (MobileUser) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());
		msgService.updateReadFlag(ruser.getId(), msgVo.getUSER_TYPE(), msgVo
				.getLINK_MAN_ID(), msgVo.getLINK_MAN_TYPE(),
				MsgConstant.READFLAG_NOREAD, MsgConstant.READFLAG_READ);
		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}

	/**
	 * 获取针对某个班级发送的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClassMsgHistory() throws Exception {
		// 参数强制判定
		if (null == msgVo.getUSER_TYPE()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		if (null == msgVo.getLOGIN_NAME()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户账号");

			return SUCCESS;
		}

		if (null == msgVo.getCLASS_ID()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：班级ID");

			return SUCCESS;
		}

		Teacher teacher = (Teacher) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());

		// 获取发送信息历史记录
		List list = msgService.getClassMsgList(teacher.getId(), msgVo
				.getCLASS_ID());

		// 转化HashMap
		for (int i = 0; i < list.size(); i++) {
			ClassMsg classMsg = (ClassMsg) list.get(i);
			Map map = new HashMap();
			map.put("MSGID", classMsg.getId());
			map.put("CONTENT", classMsg.getContent());
			map.put("MOTIME", DateUtils2.formatDate3(classMsg.getSendTime()));
			msgVo.addListData(map);
		}

		return "list";
	}

	/**
	 * 逻辑删除某条班级信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delClass() throws Exception {
		// 参数强制判定
		if (null == msgVo.getMESSAGE_NO()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：班级信息ID");

			return SUCCESS;
		}

		msgService.delClassMsg(msgVo.getMESSAGE_NO(), true);

		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}
	
	/**
	 * 删除与某个人的交互信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delOne()throws Exception{
		if (null == msgVo.getLOGIN_ID()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：登陆人ID");
			return SUCCESS;
		}
		if (null == msgVo.getUSER_TYPE()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：登陆人类型");
			return SUCCESS;
		}
		if (null == msgVo.getLINK_MAN_ID()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：联系人ID");
			return SUCCESS;
		}
		if (null == msgVo.getLINK_MAN_TYPE()) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：联系人类型");
			return SUCCESS;
		}
		msgService.delOneInfo(msgVo.getLOGIN_ID(),msgVo.getUSER_TYPE(),msgVo.getLINK_MAN_ID(),msgVo.getLINK_MAN_TYPE());
		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");
		return SUCCESS;
	}

	/**
	 * 收藏班级信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String collectClassMsg() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getMESSAGE_NO())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：消息ID");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
		MobileUser user = (MobileUser) userService.getUserByLogin(msgVo
				.getUSER_TYPE(), msgVo.getLOGIN_NAME());
		ClassMsg classMsg = msgService.getClassMsgById(msgVo.getMESSAGE_NO());
		MsgCollection msgCollection = new MsgCollection();
		msgCollection.setId(msgService.getId());
		msgCollection.setUserid(user.getId());
		msgCollection.setContent(classMsg.getContent());
		msgCollection.setUsertype(msgVo.getUSER_TYPE());
		msgCollection.setTitle("班级消息收藏");
		msgCollection.setType(classMsg.getMsgType());

		msgCollectionService.saveMsgCollection(msgCollection);

		msgVo.setResultCode("0");
		msgVo.setResultDesc("操作成功");

		return SUCCESS;
	}

	/**
	 * 信息收藏列表
	 * 
	 * @return
	 */
	public String msgCollectList() throws Exception {
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		if (DictionaryConstant.USER_TEACHER.equals(msgVo.getUSER_TYPE())) {
			return teacherMsgCollectList();
		}

		return SUCCESS;
	}

	/**
	 * 教师收藏信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String teacherMsgCollectList() throws Exception {
		try {
			Teacher teacher = teacherService.selectTeacherByLogin(msgVo
					.getLOGIN_NAME());
			List cList = msgCollectionService.selectMsgCollectList(
					DictionaryConstant.USER_TEACHER, teacher.getId());
			List rList = new ArrayList();

			for (int i = 0; i < cList.size(); i++) {
				Object[] objs = (Object[]) cList.get(i);
				
				if (((BigDecimal) objs[0]).intValue() > 0) {
					Map map = new HashMap();
					map.put("count", objs[0]);
					map.put("type", objs[1]);
					map.put("typeName", objs[2]);
					rList.add(map);
				}
			}

			Long count = productService.selectUserCollectCount(
					DictionaryConstant.USER_TEACHER, teacher.getId());

			if (count > 0) {
				Map map = new HashMap();
				map.put("count", count);
				map.put("type", "06");
				map.put("typeName", "教育产品");
				rList.add(map);
			}

			msgVo.setReturnList(rList);
		} catch (Exception e) {
			e.printStackTrace();
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("程序出错，请联系管理员或稍后再试。");

			return SUCCESS;
		}

		return "list";
	}

	/**
	 * 删除收藏信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delMsgCollect() throws Exception {
		if (StringUtil.isEmpty(msgVo.getMSG_COLLECT_ID())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：收藏信息ID");

			return SUCCESS;
		}

		if (StringUtil.isEmpty(msgVo.getTYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：收藏类型");

			return SUCCESS;
		}

		boolean flag = false;

		if ("06".equals(msgVo.getTYPE())) {
			flag = productService.delProCoolect(msgVo.getMSG_COLLECT_ID());
		} else {
			flag = msgCollectionService
					.delMsgCollect(msgVo.getMSG_COLLECT_ID());
		}

		if (flag) {
			msgVo.setResultCode("0");
			msgVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
		} else {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("删除失败，请稍后再试！");
		}

		return SUCCESS;
	}

	public String collectDetailList() throws Exception {
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");
		
			return SUCCESS;
		}

		// 用户类型
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}

		if (StringUtil.isEmpty(msgVo.getTYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：收藏类型");

			return SUCCESS;
		}

		if (DictionaryConstant.USER_TEACHER.equals(msgVo.getUSER_TYPE())) {
			return teacherCollectDetailList();
		}

		return SUCCESS;
	}

	private String teacherCollectDetailList() throws Exception {
		List rList = new ArrayList();
		Teacher teacher = teacherService.selectTeacherByLogin(msgVo
				.getLOGIN_NAME());

		if ("06".equals(msgVo.getTYPE())) {
			productService.selectProCollectPageData(msgVo, teacher.getId());

			for (int i = 0; i < msgVo.getList().size(); i++) {
				ProductCollection pc = (ProductCollection) msgVo.getList().get(
						i);
				Product product = productService.findProductById(pc
						.getProductId());
				Map map = new HashMap();
				map.put("type", "06");
				map.put("id", pc.getId());
				map.put("content", product.getIntro());
				map.put("productId", pc.getProductId());
				map.put("time", DateUtils2.formatDate3(pc.getColTime()));
				rList.add(map);
			}
		} else {
			msgCollectionService.selectMsgCollectPageData(msgVo, teacher
					.getId());

			for (int i = 0; i < msgVo.getList().size(); i++) {
				MsgCollection mc = (MsgCollection) msgVo.getList().get(i);
				Map map = new HashMap();
				map.put("type", mc.getType());
				map.put("id", mc.getId());
				map.put("content", mc.getContent());
				map.put("time", DateUtils2.formatDate3(mc.getPuttime()));
				rList.add(map);
			}
		}

		msgVo.setReturnList(rList);

		return "list";
	}

	/**
	 * 获取未读未删除信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNotReadInfo() throws Exception {
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}
		if (StringUtil.isEmpty(msgVo.getUSER_TYPE())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}
		if (DictionaryConstant.USER_TEACHER.equals(msgVo.getUSER_TYPE()) || DictionaryConstant.USER_STUDENT.equals(msgVo.getUSER_TYPE())) {
			return getTeacherNotReadInfo();
		}

		return SUCCESS;
	}

	private String getTeacherNotReadInfo() throws Exception {
		String loginName = msgVo.getLOGIN_NAME();
		String rusertype = msgVo.getUSER_TYPE();
		try{
			List list = new ArrayList();
		if (DictionaryConstant.USER_TEACHER.equals(rusertype)) {
			Teacher teacher = teacherService.selectTeacherByLogin(loginName.trim());
			list = this.msgService.getNotReadInfoByName(teacher.getId(),
					rusertype);
		}
		if (DictionaryConstant.USER_STUDENT.equals(rusertype)) {
			Student student = studentService.selectStudentByLogin(loginName.trim());
			 list = msgService.getNotReadInfoByName(student.getId(),
					rusertype);
		}
		List rList = new ArrayList();
		if (list.size()!=0) {
		for (int i = 0; i < list.size(); i++) {
			Msg msg = (Msg) list.get(i);
			Map map = new HashMap();
			map.put("content", msg.getContent());
			map.put("delflag", msg.getDelflag());
			map.put("id", msg.getId());
			map.put("readflag", msg.getReadflag());
			map.put("susername", msg.getSusername());
			map.put("suser", msg.getSuser());
			map.put("susertype", msg.getSusertype());
			map.put("rusername", msg.getRusername());
			map.put("ruser", msg.getRuser());
			map.put("rusertype", msg.getRusertype());
			if (msg.getMttime()==null) {
				map.put("mtime", null);
			}else {
				map.put("mtime", DateUtils2.formatDate3(msg.getMttime()));
			}
			map.put("msgtype", msg.getType());
			map.put("delflag", msg.getDelflag());
			rList.add(map);
			msg.setReadflag("02");// 设置为已读
		}
		msgVo.setReturnList(rList);
		teacherService.saveOrUpdateCollection(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 获取已删除未读信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDelNotReadInfo() throws Exception {
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}
		String susername = msgVo.getLOGIN_NAME();
		msgVo.setReturnList(this.msgService.getDelNotReadInfoByName(susername));
		return "list";
	}

	/**
	 * 获取教师信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTeacherInfo() throws Exception {
		if (StringUtil.isEmpty(msgVo.getLOGIN_NAME())) {
			msgVo.setResultCode("-1");
			msgVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}
		String login_name = msgVo.getLOGIN_NAME();
		// String user_type = msgVo.;
		// msgVo.setReturnList(this.teacherService.getTeacherInfo(login_name,user_type));
		Teacher teacher = this.teacherService.getTeacherInfo(login_name);
		return "list";
	}

}
