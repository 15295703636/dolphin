package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ServerEntity;

/**
* @ClassName IServerService
* @Description 服务管理接口
* @Author LiuJT
* @Date 2019-12-06 09:22:10
**/
public interface IServerService {

    ReturnInfo addServer(ServerEntity param);

    ReturnInfo delServer(Integer param);

    ReturnInfo editServer(ServerEntity param);

    //ReturnInfo getServer(RequestPage<SplitPageInfo, Object> param);

    ReturnInfo getServer();
}
