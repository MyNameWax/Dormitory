package cn.rzpt.common.lang;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wax
 * @Date 2022/11/25 0:58
 * @Version 1.0
 * 公共返回结果
 */
@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造方法私有化,里面的方法都是静态使用的，达到保护属性的作用
     */
    private Result() {

    }
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }
    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    /**
     * 自定义返回成功与否
     * @param success
     * @return
     */
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 自定义返回消息与否
     * @param message
     * @return
     */
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }
    public Result data(String key,Object value) {
        this.data.put(key,value);
        return this;
    }
    public Result data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }
}
