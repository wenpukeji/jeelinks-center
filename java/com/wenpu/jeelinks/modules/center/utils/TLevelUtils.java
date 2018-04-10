package com.wenpu.jeelinks.modules.center.utils;

import com.wenpu.jeelinks.common.utils.SpringContextHolder;
import com.wenpu.jeelinks.modules.center.entity.EUser;
import com.wenpu.jeelinks.modules.center.entity.TEvent;
import com.wenpu.jeelinks.modules.center.entity.TEventLog;
import com.wenpu.jeelinks.modules.center.service.EUserService;
import com.wenpu.jeelinks.modules.center.service.TEventLogService;
import com.wenpu.jeelinks.modules.center.service.TEventService;

public class TLevelUtils {

	public static String LOGIN_EVNET_CODE = "d001";

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
