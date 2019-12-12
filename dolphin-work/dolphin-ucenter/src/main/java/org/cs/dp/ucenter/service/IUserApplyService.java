package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.UserApplyEntity;

/**
* @ClassName IUserApplyService
* @Description 账号申请接口
* @Author Liujt
* @Date 2019-12-06 03:28:30
**/
public interface IUserApplyService {

    ReturnInfo addUserApply(UserApplyEntity param);

    ReturnInfo delUserApply(Integer param);

    ReturnInfo editUserApply(UserApplyEntity param);

    ReturnInfo getUserApply(RequestPage<SplitPageInfo, Integer> param);

}
