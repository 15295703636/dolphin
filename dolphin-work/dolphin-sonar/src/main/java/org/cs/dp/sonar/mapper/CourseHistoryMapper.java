package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.sonar.domain.index.GetClaStaResBean;
import org.cs.dp.sonar.domain.index.IndexCountReqBean;
import org.cs.dp.sonar.domain.index.IndexCountDateResBean;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CourseHistoryEntity record);

    int insertSelectCurrent(AddGetIdBean apram);

    CourseHistoryEntity selectByPrimaryKey(Integer id);

    List<CourseHistoryEntity> selectByCondition(GetCourseHistoryReqBean param);

    //返回云视讯设备id
    ScheduleOneDeviceBean selectByIdResYsx(Integer id);

    ScheduleOneDeviceBean selectById(Integer id);

    int updateByPrimaryKeySelective(CourseHistoryEntity record);

    //根据天维度分组
    List<IndexCountDateResBean> getDate(IndexCountReqBean param);

    //总数量，时长
    Map getCount(IndexCountReqBean param);

    //根据类型分组
    List<IndexCountDateResBean> getType(IndexCountReqBean param);

    GetClaStaResBean getClaSta(Integer customer_id);
}
