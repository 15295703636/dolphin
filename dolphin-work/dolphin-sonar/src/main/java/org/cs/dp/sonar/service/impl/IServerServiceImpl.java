package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.sonar.common.Constant;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.ServerMapper;
import org.cs.dp.sonar.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName IServerServiceImpl
 * @Description 服务管理实现类
 * @Author LiuJT
 * @Date 2019-12-06 09:22:10
 **/
@Service
public class IServerServiceImpl implements IServerService {
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public ReturnInfo addServer(ServerEntity param) {
        if (((List) getServer(param.getServer_type()).getReturnData()).size() > 0) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.SERVER_EXIT_MSG);
        }
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
        if (((List) getServer(param.getServer_type()).getReturnData()).size() > 1) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.SERVER_EXIT_MSG);
        }
        serverMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo<List<ServerEntity>> getServer(Integer server_type) {
        List<ServerEntity> resList = serverMapper.selectAll(server_type);
        return new ReturnInfo(resList);
    }

   /* @Override
    public ReturnInfo getServer(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<ServerEntity> resList = null;//TODO 分页sql要自己实现 serverMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }*/
}