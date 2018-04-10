package com.wenpu.jeelinks.modules.center.utils;

import java.util.Date;
import java.util.List;

import com.wenpu.jeelinks.common.utils.SpringContextHolder;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.modules.center.entity.EUser;
import com.wenpu.jeelinks.modules.center.entity.TEvent;
import com.wenpu.jeelinks.modules.center.entity.TEventLog;
import com.wenpu.jeelinks.modules.center.service.EUserService;
import com.wenpu.jeelinks.modules.center.service.TEventLogService;
import com.wenpu.jeelinks.modules.center.service.TEventService;

public class TEventUtils {

	public static String LOGIN_EVNET_CODE = "d001";
	public static String TESTPASS_EVNET_CODE = "d002";
	public static String TESTEXCLENT_EVNET_CODE = "d003";
	public static String TESTFULL_EVNET_CODE = "d004";
	public static String READONEBOOK_EVNET_CODE = "d005";
	public static String EVERYREAD_EVNET_CODE = "d006";
	public static String REATIONRECOMMEND_EVNET_CODE = "d007";
	public static String REATIONSUPPORT_EVNET_CODE = "d008";

	static TEventService tEventService = SpringContextHolder
			.getBean(TEventService.class);
	static TEventLogService tEventLogService = SpringContextHolder
			.getBean(TEventLogService.class);
	static EUserService eUserService = SpringContextHolder
			.getBean(EUserService.class);

	public static int setPoint(String userId, String eventCode) {
		try {
			EUser euser = eUserService.get(userId);
			TEvent t = tEventService.getByEventCode(eventCode);
			//事件积分上限判断
			if(t.getDayLimit()!=0){
				Date toDay=new Date();
				TEventLog tEventLogParam = new TEventLog();
				tEventLogParam.setEventId(t.getId());
				tEventLogParam.setToDay(toDay);
				List<TEventLog> tEventLogList=tEventLogService.findList(tEventLogParam);
				int score=0;//当日已经积分总值
				if(tEventLogList.size()>0){
					for (TEventLog tEventLog : tEventLogList) {
						score+=tEventLog.getScore();
					}
				}
				if(score>=t.getDayLimit()){
					return t.getEventScore();
				}
			}
			
			
			//事件积分上限判断-end
			TEventLog l = new TEventLog();
			l.setEventId(t.getId());
			l.setUser(euser);
			l.setScore(t.getEventScore());
			tEventLogService.save(l);
			
			euser.setPoint(euser.getPoint() + t.getEventScore());
			eUserService.save(euser);
			
			return t.getEventScore();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;

	}
	/**
	 * 用户测验结果设置得分
	 * @param userId
	 * @param eventCode
	 * @return
	 */
	public static int setPointExam(String userId, Double paperScore,Double userScore) {
		try {
			EUser euser = eUserService.get(userId);
			String eventCode = "";
			TEvent t = null;
			if (userScore == paperScore) {
				t = tEventService.getByEventCode("d004");
			} else if (userScore/paperScore >= 0.8) {
				t = tEventService.getByEventCode("d003");
			} else if (userScore/paperScore >= 0.6) {
				t = tEventService.getByEventCode("d002");
			} else {
				return 0;
			}
			
			TEventLog l = new TEventLog();
			l.setEventId(t.getId());
			l.setUser(euser);
			l.setScore(t.getEventScore());
			tEventLogService.save(l);
			
			euser.setPoint(euser.getPoint() + t.getEventScore());
			eUserService.save(euser);
			
			return t.getEventScore();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;

	}
}
