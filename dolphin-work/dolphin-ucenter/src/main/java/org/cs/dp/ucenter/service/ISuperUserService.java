package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;

import javax.servlet.http.HttpServletRequest;

public interface ISuperUserService {
    ReturnInfo login(UPBean param);

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo add(SuperUserEntity record);

    ReturnInfo del(Integer user_id);

    ReturnInfo edit(SuperUserEntity record);
}
