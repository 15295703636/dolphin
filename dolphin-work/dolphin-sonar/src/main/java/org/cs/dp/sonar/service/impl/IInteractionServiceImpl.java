package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.sonar.domain.AddInteractionBean;
import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.entity.InteractionDeviceEntity;
import org.cs.dp.sonar.domain.entity.InteractionEntity;
import org.cs.dp.sonar.mapper.InteractionDeviceMapper;
import org.cs.dp.sonar.mapper.InteractionMapper;
import org.cs.dp.sonar.service.IInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IInteractionServiceImpl
 * @Description 互动管理实现类
 * @Author LiuJT
 * @Date 2019-12-09 03:11:48
 **/
@Slf4j
@Service
public class IInteractionServiceImpl implements IInteractionService {
    @Autowired
    private InteractionMapper interactionMapper;

    @Autowired
    private InteractionDeviceMapper interactionDeviceMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addInteraction(AddInteractionBean param) {
        InteractionEntity interaction = param.getInteraction();
        interactionMapper.insertSelective(interaction);
        dealDevices(interaction.getInteraction_id(), param.getDevice_ids());
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
    public void dealDevices(Integer interactionId, List<Integer> devicesId) {
        interactionDeviceMapper.deleteByInteractionId(interactionId);
        List<InteractionDeviceEntity> meetingDevices = new ArrayList<>();
        devicesId.forEach(o -> meetingDevices.add(new InteractionDeviceEntity(interactionId, o)));
        int resInt = interactionDeviceMapper.insertBatch(meetingDevices);
        log.info("会议端信息添加结果：{}", resInt);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo delInteraction(Integer param) {
        interactionMapper.deleteByPrimaryKey(param);
        interactionDeviceMapper.deleteByInteractionId(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editInteraction(AddInteractionBean param) {
        interactionMapper.updateByPrimaryKeySelective(param.getInteraction());
        dealDevices(param.getInteraction().getInteraction_id(), param.getDevice_ids());
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getInteraction(RequestPage<SplitPageInfo, GetAppSchReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<InteractionEntity> resList = interactionMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}