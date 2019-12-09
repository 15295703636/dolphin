package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.entity.RecordBroadcastEntity;

/**
* @ClassName IRecordBroadcastService
* @Description 录播管理接口
* @Author LiuJT
* @Date 2019-12-09 03:14:27
**/
public interface IRecordBroadcastService {

    ReturnInfo addRecordBroadcast(RecordBroadcastEntity param);

    ReturnInfo delRecordBroadcast(Integer param);

    ReturnInfo editRecordBroadcast(RecordBroadcastEntity param);

    ReturnInfo getRecordBroadcast(RequestPage<SplitPageInfo, GetAppSchReqBean> param);

}
