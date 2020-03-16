package ${packagePath}service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import ${packagePath}domain.entity.${name}Entity;

/**
* @ClassName I${name}Service
* @Description ${explain}接口
* @Author ${author}
* @Date ${date}
**/
public interface I${name}Service {

    ReturnInfo add${name}(${name}Entity param);

    ReturnInfo del${name}(Integer param);

    ReturnInfo edit${name}(${name}Entity param);

    ReturnInfo get${name}(RequestPage<SplitPageInfo, Object> param);

}
