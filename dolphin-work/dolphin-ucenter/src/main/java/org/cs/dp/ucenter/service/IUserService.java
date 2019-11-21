package org.cs.dp.ucenter.service;

import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.UPBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IUserService {
    List getList();

    PageInfo selectUserByOrg(SplitPageInfo page, Map map);

    ReturnInfo login(UPBean param);

    ReturnInfo loginOut(HttpServletRequest request);
}
