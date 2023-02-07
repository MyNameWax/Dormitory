package cn.rzpt.handler;

import cn.rzpt.common.lang.Result;
import cn.rzpt.common.lang.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @Description 权限不足
 * @since 1.0.0
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream outputStream = response.getOutputStream();
        Result message = Result.error().message(ResultCode.NO_PERMISSION.getMessage());
        outputStream.write(new ObjectMapper().writeValueAsString(message).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
