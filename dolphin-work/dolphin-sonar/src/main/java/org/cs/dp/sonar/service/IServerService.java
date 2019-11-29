package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ServerEntity;

/**
 * @ClassName ServerController
 * @Description 服务管理接口
 * @Author Liujt
 * @Date 2019/11/29 10:43
 **/
public interface IServerService {

    ReturnInfo addServer(ServerEntity param);

    ReturnInfo delServer(Integer param);

    ReturnInfo editServer(ServerEntity param);

    ReturnInfo getServer(RequestPage<SplitPageInfo, Object> param);

}
