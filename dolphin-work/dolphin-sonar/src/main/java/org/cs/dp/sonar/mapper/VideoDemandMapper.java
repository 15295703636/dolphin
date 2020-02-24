package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.VideoDemandEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoDemandMapper {
    int deleteByPrimaryKey(@Param("ids") List<Integer> ids);

    int insert(VideoDemandEntity record);

    int insertSelective(VideoDemandEntity record);

    List<VideoDemandEntity> selectByCon(@Param(value = "name") String name,
                                        @Param(value = "customer_id") Integer customer_id);

    int updateByPrimaryKeySelective(VideoDemandEntity record);

    int updateByPrimaryKey(VideoDemandEntity record);
}
