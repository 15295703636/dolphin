package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.OrgIdAndTokenBean;
import org.cs.dp.ucenter.domain.ResetPwdBean;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    List getList();

    ReturnInfo login(UPBean param);

    ReturnInfo getUserInfo();

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo resetPwd(ResetPwdBean param);

    ReturnInfo add(UserEntity record);

    ReturnInfo del(Integer user_id);

    ReturnInfo edit(UserEntity record);

    ReturnInfo getUsersByOrgId(RequestPage<SplitPageInfo, OrgIdAndTokenBean> param);
}
