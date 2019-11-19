package org.cs.dp.ucenter.service;

import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.SplitPageInfo;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List getList();
    PageInfo selectUserByOrg(SplitPageInfo page, Map map);
}
