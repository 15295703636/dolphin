package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.VideoDemandEntity;

import java.util.List;

/**
* @ClassName IVideoDemandService
* @Description 点播管理接口
* @Author LiuJT
* @Date 2020-02-13 10:04:14
**/
public interface IVideoDemandService {

    ReturnInfo addVideoDemand(VideoDemandEntity param);

    ReturnInfo delVideoDemand(List<Integer> param);

    ReturnInfo editVideoDemand(VideoDemandEntity param);

    ReturnInfo getVideoDemand(RequestPage<SplitPageInfo, String> param);

}
