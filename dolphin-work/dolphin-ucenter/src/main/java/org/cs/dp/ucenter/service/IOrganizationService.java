package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IOrganizationService {

    ReturnInfo addOrg(OrganizationEntity param) ;

    ReturnInfo upload(MultipartFile file) throws IOException;

    ReturnInfo editOrg(OrganizationEntity param);

    ReturnInfo delOrg( int id);

    ReturnInfo getOrg(RequestPage<SplitPageInfo,OrganizationEntity> param) ;

    ReturnInfo getOrg() ;

    ReturnInfo getOrgTree(OrganizationEntity param);

}
