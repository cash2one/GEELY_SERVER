package com.fsc.xxt.client.service;

import cn.jpush.api.DeviceEnum;
import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

import com.fsc.util.StringUtil;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;


public class MessagePullService extends TimerTask {
    private static int timeToLive = 60 * 60 * 24 * 3;
    private String appKey = "7f1ec6b7956240b4684e3e6d";
    private String masterSecret = "10d0dc13c01d875199437b56";
    private JPushClient jpush = null;
    private GeelyUserService geelyUserService;
    private Logger logger = Logger.getLogger(MessagePullService.class);

    public MessagePullService() {
        jpush = new JPushClient(masterSecret, appKey, DeviceEnum.Android,
                timeToLive);
    }

    public void setGeelyUserService(GeelyUserService geelyUserService) {
        this.geelyUserService = geelyUserService;
    }

    @Override
    public void run() {
//        try {
//            List<Map> mettingList = null;
//            List<Map> warningList = null;
//            String mettingids = "";
//            String warningids = "";
//
//            warningList = geelyUserService.selectUserUnsendAndUnreadWarning();
//            mettingList = geelyUserService.selectUsreUnsendAndUnreadMetting();
//
//            int warninglength = warningList.size();
//            int mettinglength = mettingList.size();
//            int count = 0;
//
//            if (warninglength > 0) {
//                for (int i = 0; i < warninglength; i++) {
//                    Map map = warningList.get(i);
//                    MessageResult msgResult = jpush.sendCustomMessageWithAlias(new Integer(
//                                map.get("ID").toString()),
//                            map.get("ID").toString(), "warning", "warning");
//
//                    if ((msgResult != null) &&
//                            (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value())) {
//                        if (count == 0) {
//                            warningids += map.get("ID");
//                        } else {
//                            warningids += ("," + map.get("ID"));
//                        }
//
//                        count++;
//                    }
//                }
//            }
//
//            count = 0;
//
//            if (mettinglength > 0) {
//                for (int i = 0; i < mettinglength; i++) {
//                    Map map = mettingList.get(i);
//                    MessageResult msgResult = jpush.sendCustomMessageWithAlias(new Integer(
//                                map.get("ID").toString()),
//                            map.get("ID").toString(), "meeting", "meeting");
//
//                    if ((msgResult != null) &&
//                            (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value())) {
//                        if (count == 0) {
//                            mettingids += map.get("ID");
//                        } else {
//                            mettingids += ("," + map.get("ID"));
//                        }
//                    }
//                }
//            }
//
//            if (!StringUtil.isEmpty(warningids)) {
//                geelyUserService.updateWarningSendFlag(warningids);
//            }
//
//            if (!StringUtil.isEmpty(mettingids)) {
//                geelyUserService.updateMettingSendFlag(mettingids);
//            }
//        } catch (Exception e) {
//            logger.error("推送发生异常", e);
//        }
    }

    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd hh:mm");
        System.out.println(format.format(new Date()));

        //        MessagePullService messagePullService = new MessagePullService();
        //
        //        MessageResult msgResult = messagePullService.jpush.sendCustomMessageWithAlias(1212,
        //                "4", "测试信333", "warning");
        //
        //        //        MessageResult msgResult = messagePullService.jpush.sendNotificationWithAlias(1212,
        //        //                "4", "212121212", "warning");
        //        if (null != msgResult) {
        //            if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
        //                System.out.println("发送成功， sendNo=" + msgResult.getSendno());
        //            } else {
        //                System.out.println("发送失败， 错误代码=" + msgResult.getErrcode() +
        //                    ", 错误消息=" + msgResult.getErrmsg());
        //            }
        //        } else {
        //            System.out.println("无法获取数据");
        //        }
    }
}
