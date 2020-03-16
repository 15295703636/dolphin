package org.cs.dp.ucenter.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.entity.CodeEntity;
import org.cs.dp.ucenter.mapper.CodeMapper;
import org.cs.dp.ucenter.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @ClassName ICodeServiceImpl
* @Description 代码信息维护实现类
* @Author Liujt
* @Date 2019-12-09 09:37:29
**/
@Service
public class ICodeServiceImpl implements ICodeService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public ReturnInfo addCode(CodeEntity param) {
        codeMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCode(Integer param) {
        codeMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCode(CodeEntity param) {
        codeMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCode(String tableName) {
        List<CodeEntity> resList = codeMapper.select(tableName);
        return new ReturnInfo(resList);
    }
}