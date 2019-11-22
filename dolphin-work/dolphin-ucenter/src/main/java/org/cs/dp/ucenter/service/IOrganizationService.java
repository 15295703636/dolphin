package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.OrganizationEntity;

public interface IOrganizationService {

    ReturnInfo addOrg(OrganizationEntity param) ;

    ReturnInfo editOrg(OrganizationEntity param);

    ReturnInfo delOrg( int id);

    ReturnInfo getOrg(RequestPage<SplitPageInfo,OrganizationEntity> param) ;

    ReturnInfo getOrgTree(OrganizationEntity param);

}
