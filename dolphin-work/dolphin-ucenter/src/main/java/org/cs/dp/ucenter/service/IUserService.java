package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    List getList();

    ReturnInfo login(UPBean param);

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo add(UserEntity record);

    ReturnInfo del(Integer user_id);

    ReturnInfo edit(UserEntity record);

    ReturnInfo getUsersByOrgId(RequestPage<SplitPageInfo, Integer> param);
}
