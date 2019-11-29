package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IDeviceServiceImpl
 * @Description 设备管理实现类
 * @Author Liujt
 * @Date 2019-11-29 02:28:34
 **/
@Service
public class IDeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ReturnInfo addDevice(DeviceEntity param) {
        deviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delDevice(Integer param) {
        deviceMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editDevice(DeviceEntity param) {
        deviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<DeviceEntity> resList = deviceMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}