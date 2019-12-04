package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.ucenter.domain.entity.CodeCustomerTypeEntity;
import org.cs.dp.ucenter.mapper.CodeCustomerTypeMapper;
import org.cs.dp.ucenter.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CodeComsterTypeServiceIpml
 * @Description 租户类型代码表 注：value值作为前端操作哪个代码那表的标志
 * @Author Liujt
 * @Date 2019/11/27 14:45
 **/
@Service(value = "customerType")
public class CodeCustomerTypeServiceImpl implements ICodeService {

    @Autowired
    private CodeCustomerTypeMapper customerTypeMapper;

    @Override
    public ReturnInfo getCodeInfo() {
        return new ReturnInfo(customerTypeMapper.selectAll());
    }

    @Override
    public ReturnInfo editCodeInfo(Object param) {
        CodeCustomerTypeEntity codeCustomerType = JSONObject.parseObject(JSON.toJSONString(param), CodeCustomerTypeEntity.class);
        if (null == codeCustomerType.getId()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "ID不能为空");
        }
        customerTypeMapper.updateByPrimaryKeySelective(codeCustomerType);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo addCodeInfo(Object param) {
        CodeCustomerTypeEntity codeCustomerType = JSONObject.parseObject(JSON.toJSONString(param), CodeCustomerTypeEntity.class);
        customerTypeMapper.insert(codeCustomerType);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCodeInfo(Integer param) {
        customerTypeMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }
}
