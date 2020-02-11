package org.cs.dp.ucenter.domain;

import lombok.Data;
import org.cs.dp.ucenter.domain.entity.UserEntity;

/**
 * @ClassName GetUserListResBean
 * @Description 用户列表返回添加组织id字段
 * @Author Liujt
 * @Date 2020/2/11 9:56
 **/
@Data
public class GetUserListResBean extends UserEntity {
    private Integer org_id;
}
