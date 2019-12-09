package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingDeviceEntity;
import org.cs.dp.sonar.mapper.MeetingDeviceMapper;
import org.cs.dp.sonar.service.IMeetingDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IMeetingDeviceServiceImpl
* @Description 会议端管理实现类
* @Author LiuJT
* @Date 2019-12-09 01:46:41
**/
@Service
public class IMeetingDeviceServiceImpl implements IMeetingDeviceService {
    @Autowired
    private MeetingDeviceMapper meetingDeviceMapper;

    @Override
    public ReturnInfo addMeetingDevice(MeetingDeviceEntity param) {
        meetingDeviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delMeetingDevice(Integer param) {
        meetingDeviceMapper.deleteByMeetingId(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editMeetingDevice(MeetingDeviceEntity param) {
        meetingDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getMeetingDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<MeetingDeviceEntity> resList = null;//TODO 分页sql要自己实现 meetingDeviceMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}