package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.ServerMapper;
import org.cs.dp.sonar.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IServerServiceImpl
 * @Description 服务管理实现类
 * @Author Liujt
 * @Date 2019-11-29 12:59:33
 **/
@Service
public class IServerServiceImpl implements IServerService {
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public ReturnInfo addServer(ServerEntity param) {
        serverMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delServer(Integer param) {
        serverMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editServer(ServerEntity param) {
        serverMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getServer(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<ServerEntity> resList = serverMapper.selectByObj(null);
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}
