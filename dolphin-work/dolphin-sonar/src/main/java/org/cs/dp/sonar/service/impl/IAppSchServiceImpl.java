package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.StringUtil;
import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.GetAppSchResBean;
import org.cs.dp.sonar.domain.entity.InteractionEntity;
import org.cs.dp.sonar.mapper.InteractionMapper;
import org.cs.dp.sonar.mapper.MeetingMapper;
import org.cs.dp.sonar.mapper.RecordBroadcastMapper;
import org.cs.dp.sonar.service.IAppSchService;
import org.cs.dp.sonar.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName IAppSchServiceImpl
 * @Description 预约课程主页
 * @Author Liujt
 * @Date 2019/12/9 16:21
 **/
@Service
public class IAppSchServiceImpl implements IAppSchService {

    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private RecordBroadcastMapper recordBroadcastMapper;
    @Autowired
    private InteractionMapper interactionMapper;

    @Override
    public ReturnInfo getAppSch(GetAppSchReqBean param) {
        GetAppSchResBean getAppSchResBean = new GetAppSchResBean();
        if (StringUtil.isNull(param.getType())) {
            getAppSchResBean.setMeetings(meetingMapper.selectByCondition(param));
            getAppSchResBean.setRbs(recordBroadcastMapper.selectByCondition(param));
            getAppSchResBean.setInteractions(interactionMapper.selectByCondition(param));
        }else if("1".equals(param.getType())){
            getAppSchResBean.setMeetings(meetingMapper.selectByCondition(param));
        }else if("2".equals(param.getType())){
            getAppSchResBean.setInteractions(interactionMapper.selectByCondition(param));
        }else if("3".equals(param.getType())){
            getAppSchResBean.setRbs(recordBroadcastMapper.selectByCondition(param));
        }
        return new ReturnInfo(getAppSchResBean);
    }
}
