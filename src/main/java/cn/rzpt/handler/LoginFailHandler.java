package cn.rzpt.handler;

import cn.hutool.json.JSONUtil;
import cn.rzpt.common.lang.Result;
import cn.rzpt.common.lang.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author wax
 * @Description 登录失败拦截器
 * @since 1.0.0
 */
@Component
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset-UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = response.getOutputStream();
        String message = exception.getMessage();
        if (exception instanceof BadCredentialsException) {
            message = "账号或密码错误";
            outputStream.write(JSONUtil.toJsonStr(Result.error().code(ResultCode.USER_CREDENTIALS_ERROR.getCode()).message(message)).getBytes(StandardCharsets.UTF_8));
        }

        outputStream.flush();
        outputStream.close();
    }
}
