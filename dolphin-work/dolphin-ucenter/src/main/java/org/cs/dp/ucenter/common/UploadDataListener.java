package org.cs.dp.ucenter.common;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.service.ISuperUserService;

/**
 * @ClassName UploadDataListener
 * @Description 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * @Author Liujt
 * @Date 2019/12/5 14:52
 **/
@Slf4j
public class UploadDataListener extends AnalysisEventListener<SuperUserEntity> {
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private ISuperUserService iSuperUserService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param iSuperUserService
     */
    public UploadDataListener(ISuperUserService iSuperUserService) {
        this.iSuperUserService = iSuperUserService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(SuperUserEntity data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        ReturnInfo returnInfo = iSuperUserService.add(data);
        log.info("写入返回结果：{}", JSON.toJSONString(returnInfo));
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
