package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.sonar.domain.AddMeetingBean;
import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.entity.MeetingDeviceEntity;
import org.cs.dp.sonar.domain.entity.MeetingEntity;
import org.cs.dp.sonar.domain.entity.MeetingUserEntity;
import org.cs.dp.sonar.mapper.MeetingDeviceMapper;
import org.cs.dp.sonar.mapper.MeetingMapper;
import org.cs.dp.sonar.mapper.MeetingUserMapper;
import org.cs.dp.sonar.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IMeetingServiceImpl
 * @Description 会议管理实现类
 * @Author LiuJT
 * @Date 2019-12-09 01:34:31
 **/
@Slf4j
@Service
public class IMeetingServiceImpl implements IMeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    @Autowired
    private MeetingDeviceMapper meetingDeviceMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addMeeting(AddMeetingBean param) {
        MeetingEntity meeting = param.getMeetingEntity();
        int resInt = meetingMapper.insertSelective(meeting);
        log.info("会议主表添加结果：{}", resInt);
        Integer meetingId = meeting.getMeeting_id();

        dealUserAndDevices(meetingId, param.getUser_ids(), param.getDevice_ids());

        return new ReturnInfo();
    }

    /**
     * 处理会议对应的用户和端的关系;先删后插
     *
     * @param meetingId
     * @param userId
     * @param devicesId
     */
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public void dealUserAndDevices(Integer meetingId, List<Integer> userId, List<Integer> devicesId) {

        meetingUserMapper.deleteByMeetingId(meetingId);
        meetingDeviceMapper.deleteByMeetingId(meetingId);

        List<MeetingUserEntity> meetingUsers = new ArrayList<>();
        userId.forEach(o -> meetingUsers.add(new MeetingUserEntity(meetingId, o)));
        int resInt = meetingUserMapper.insertBatch(meetingUsers);
        log.info("会议用户信息添加结果：{}", resInt);

        List<MeetingDeviceEntity> meetingDevices = new ArrayList<>();
        devicesId.forEach(o -> meetingDevices.add(new MeetingDeviceEntity(meetingId, o)));
        resInt = meetingDeviceMapper.insertBatch(meetingDevices);
        log.info("会议端信息添加结果：{}", resInt);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo delMeeting(Integer param) {
        meetingMapper.deleteByPrimaryKey(param);
        meetingDeviceMapper.deleteByMeetingId(param);
        meetingUserMapper.deleteByMeetingId(param);
        return new ReturnInfo();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo editMeeting(AddMeetingBean param) {
        meetingMapper.updateByPrimaryKeySelective(param.getMeetingEntity());
        dealUserAndDevices(param.getMeetingEntity().getMeeting_id(), param.getUser_ids(), param.getDevice_ids());
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getMeeting(RequestPage<SplitPageInfo, GetAppSchReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<MeetingEntity> resList = meetingMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}