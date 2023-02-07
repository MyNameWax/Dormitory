package cn.rzpt.handler;

import cn.hutool.json.JSONUtil;
import cn.rzpt.common.lang.Result;
import cn.rzpt.utils.JwtUtils;
import com.alibaba.druid.support.json.JSONUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author wax
 * @Description 登录成功拦截器
 * @since 1.0.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置响应编码格式为json格式
        response.setContentType("application/json;charset=UTF-8");
        //获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();
        //生成token
        String token = jwtUtils.createToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(),token);
        Result ok = Result.ok().message("登录成功").data("token",token);
        outputStream.write(JSONUtil.toJsonStr(ok).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
