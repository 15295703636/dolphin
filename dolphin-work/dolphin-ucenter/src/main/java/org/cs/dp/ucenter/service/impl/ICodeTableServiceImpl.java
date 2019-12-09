package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.CodeTableEntity;
import org.cs.dp.ucenter.mapper.CodeTableMapper;
import org.cs.dp.ucenter.service.ICodeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName ICodeTableServiceImpl
* @Description 代码表实现类
* @Author Liujt
* @Date 2019-12-09 09:36:10
**/
@Service
public class ICodeTableServiceImpl implements ICodeTableService {
    @Autowired
    private CodeTableMapper codeTableMapper;

    @Override
    public ReturnInfo addCodeTable(CodeTableEntity param) {
        codeTableMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCodeTable(String param) {
        codeTableMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCodeTable(CodeTableEntity param) {
        codeTableMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCodeTable(String explain) {
        List<CodeTableEntity> resList = codeTableMapper.selectByExplain(explain);
        return new ReturnInfo(resList);
    }
}