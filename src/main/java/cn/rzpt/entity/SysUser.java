package cn.rzpt.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wax
 * @Description 实体类sys_user
 * @since 1.0.0
 */
@Data
public class SysUser {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
    private String phone;
    private String address;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @TableLogic
    private Integer isDeleted;

}
