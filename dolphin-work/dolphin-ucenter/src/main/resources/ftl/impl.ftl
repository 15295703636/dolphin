package ${packagePath}service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import ${packagePath}domain.entity.${name}Entity;
import ${packagePath}mapper.${name}Mapper;
import ${packagePath}service.I${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName I${name}ServiceImpl
* @Description ${explain}实现类
* @Author ${author}
* @Date ${date}
**/
@Service
public class I${name}ServiceImpl implements I${name}Service {
    @Autowired
    private ${name}Mapper ${name?uncap_first}Mapper;

    @Override
    public ReturnInfo add${name}(${name}Entity param) {
        ${name?uncap_first}Mapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo del${name}(Integer param) {
        ${name?uncap_first}Mapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo edit${name}(${name}Entity param) {
        ${name?uncap_first}Mapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo get${name}(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<${name}Entity> resList = null;//TODO 分页sql要自己实现 ${name?uncap_first}Mapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}