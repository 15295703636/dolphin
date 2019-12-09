package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.AddInteractionBean;
import org.cs.dp.sonar.domain.entity.InteractionEntity;

/**
* @ClassName IInteractionService
* @Description 互动管理接口
* @Author LiuJT
* @Date 2019-12-09 03:11:48
**/
public interface IInteractionService {

    ReturnInfo addInteraction(AddInteractionBean param);

    ReturnInfo delInteraction(Integer param);

    ReturnInfo editInteraction(InteractionEntity param);

    ReturnInfo getInteraction(RequestPage<SplitPageInfo, Object> param);

}
