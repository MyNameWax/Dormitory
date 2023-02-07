package cn.rzpt.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wax
 * @Description 实体类sys_user
 * @since 1.0.0
 */
@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    @TableLogic
    private Integer isDeleted;

}
