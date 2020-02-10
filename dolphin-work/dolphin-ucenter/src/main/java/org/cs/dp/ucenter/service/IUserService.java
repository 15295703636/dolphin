package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.*;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IUserService {
    List getList();

    ReturnInfo login(UPBean param) throws BaseException;

    ReturnInfo getUserInfo();

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo resetPwd(ResetPwdBean param);

    ReturnInfo checkUserInfo(String user_name,Integer id);

    ReturnInfo add(AddUserBean record,boolean isAuto);

    ReturnInfo del(List<Integer> userIds);

    ReturnInfo edit(UserEntity record);

    ReturnInfo getUsersByOrgId(RequestPage<SplitPageInfo, OrgIdAndTokenBean> param);

    ReturnInfo getUsersList(RequestPage<SplitPageInfo, GetUserReqBean> param);

    ReturnInfo upload(MultipartFile file) throws IOException;
}
