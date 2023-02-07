package cn.rzpt.common.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Wax
 * @Date 2022/11/25 8:21
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private Integer code;
    private String errMsg;


}
