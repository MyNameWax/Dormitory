package cn.rzpt.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author wax
 * @Description 系统公告实体类
 * @since 1.0.0
 */
@Data
public class SysNotice {
    private String id;
    //公告标题
    private String title;
    //发送时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    @TableLogic
    private Integer isDeleted;
}
