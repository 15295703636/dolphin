package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IOrganizationService {

    ReturnInfo addOrg(OrganizationEntity param) ;

    ReturnInfo upload(MultipartFile file) throws IOException;

    void export(HttpServletResponse response);

    ReturnInfo editOrg(OrganizationEntity param);

    ReturnInfo delOrg( List<Integer> id);

    ReturnInfo getOrg(RequestPage<SplitPageInfo,OrganizationEntity> param) ;

    ReturnInfo getOrg() ;

    ReturnInfo getOrgTree(OrganizationEntity param);

}
