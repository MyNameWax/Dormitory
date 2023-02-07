package cn.rzpt.common.lang;

/**
 * @Author Wax
 * @Date 2022/11/25 0:50
 * @Version 1.0
 */
public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     * @return  错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
