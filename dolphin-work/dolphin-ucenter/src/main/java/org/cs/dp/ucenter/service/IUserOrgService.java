package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;

/**
* @ClassName IUserOrgService
* @Description 租户管理接口
* @Author Liujt
* @Date 2019-12-05 06:45:57
**/
public interface IUserOrgService {

    ReturnInfo addUserOrg(UserOrgEntity param);

    ReturnInfo delUserOrg(Integer param);

    ReturnInfo editUserOrg(UserOrgEntity param);

    ReturnInfo getUserOrg(RequestPage<SplitPageInfo, Object> param);

}
