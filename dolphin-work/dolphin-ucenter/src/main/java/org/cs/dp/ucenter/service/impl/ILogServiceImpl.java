package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.ExcelUtils;
import org.cs.dolphin.common.utils.StringUtil;
import org.cs.dp.ucenter.domain.GetLogBean;
import org.cs.dp.ucenter.mapper.LogMapper;
import org.cs.dp.ucenter.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.cs.dolphin.common.utils.Constants.SHOW_DATE_PATTERN;

/**
 * @ClassName ILogServiceImpl
 * @Description 日志接口实现
 * @Author Liujt
 * @Date 2019/11/27 17:07
 **/
@Service
public class ILogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public ReturnInfo getLog(RequestPage<SplitPageInfo, GetLogBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<LogEntity> logEntities = logMapper.selectByTerm(param.getInfo());
        PageInfo p = new PageInfo(logEntities);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, logEntities);
    }

    @Async
    @Override
    public ReturnInfo addLog(LogEntity param) {
        int res = logMapper.insertSelective(param);
        return new ReturnInfo(res);
    }

    @Override
    public void exportLog(String moduleName, String startTime, String endTime, HttpServletResponse response) throws Exception {
        GetLogBean getLogBean = new GetLogBean();
        if(!StringUtil.isNull(moduleName)){
            getLogBean.setModuleName(moduleName);
        }
        if(!StringUtil.isNull(endTime)){
            getLogBean.setEndTime(DateUtil.StringToDate(endTime,SHOW_DATE_PATTERN));
        }
        if(!StringUtil.isNull(startTime)){
            getLogBean.setStartTime(DateUtil.StringToDate(startTime,SHOW_DATE_PATTERN));
        }
        List<LogEntity> logEntities = logMapper.selectByTerm(getLogBean);
        ExcelUtils.export("异常日志","异常记录", logEntities, response, LogEntity.class);
    }
}
