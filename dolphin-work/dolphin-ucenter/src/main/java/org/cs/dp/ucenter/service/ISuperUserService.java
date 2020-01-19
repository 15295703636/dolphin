package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.CheckAddInfoReqBean;
import org.cs.dp.ucenter.domain.ResetSuperPwdBean;
import org.cs.dp.ucenter.domain.SuperUserGetReqBean;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface ISuperUserService {
    ReturnInfo login(UPBean param) throws BaseException;

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo add(SuperUserEntity record);

    ReturnInfo checkAddInfo(CheckAddInfoReqBean record);

    ReturnInfo del(List<Integer> user_ids);

    ReturnInfo edit(SuperUserEntity record);

    ReturnInfo getManage(RequestPage<SplitPageInfo, SuperUserGetReqBean> param);

    ReturnInfo upload(MultipartFile file) throws IOException;

    ReturnInfo resetPwd(ResetSuperPwdBean param);
}
