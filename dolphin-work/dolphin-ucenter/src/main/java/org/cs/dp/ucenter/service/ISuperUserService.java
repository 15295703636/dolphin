package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ISuperUserService {
    ReturnInfo login(UPBean param);

    ReturnInfo loginOut(HttpServletRequest request);

    ReturnInfo add(SuperUserEntity record);

    ReturnInfo del(Integer user_id);

    ReturnInfo edit(SuperUserEntity record);

    ReturnInfo getManage(Integer manageId);

    ReturnInfo upload(MultipartFile file) throws IOException;
}
