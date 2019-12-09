package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingUserEntity;
import org.cs.dp.sonar.mapper.MeetingUserMapper;
import org.cs.dp.sonar.service.IMeetingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IMeetingUserServiceImpl
* @Description 会议用户管理实现类
* @Author LiuJT
* @Date 2019-12-09 01:47:08
**/
@Service
public class IMeetingUserServiceImpl implements IMeetingUserService {
    @Autowired
    private MeetingUserMapper meetingUserMapper;

    @Override
    public ReturnInfo addMeetingUser(MeetingUserEntity param) {
        meetingUserMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delMeetingUser(Integer param) {
        meetingUserMapper.deleteByMeetingId(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editMeetingUser(MeetingUserEntity param) {
        meetingUserMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getMeetingUser(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<MeetingUserEntity> resList = null;//TODO 分页sql要自己实现 meetingUserMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}