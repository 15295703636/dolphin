package org.cs.dp.ucenter.common;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.service.ISuperUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UploadDataListener
 * @Description 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * @Author Liujt
 * @Date 2019/12/5 14:52
 **/
@Slf4j
public class UploadSuperUserListener extends AnalysisEventListener<SuperUserEntity> {

    public List<SuperUserEntity> getSuperUserList() {
        return superUserList;
    }

    public void setSuperUserList(List<SuperUserEntity> superUserList) {
        this.superUserList = superUserList;
    }

    private List<SuperUserEntity> superUserList = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(SuperUserEntity data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        superUserList.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("所有数据解析完成！");

    }
}
