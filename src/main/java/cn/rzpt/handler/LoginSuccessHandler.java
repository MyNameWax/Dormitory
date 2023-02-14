package cn.rzpt.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import cn.rzpt.common.lang.Result;
import cn.rzpt.config.SecurityConfig;
import cn.rzpt.entity.SysUser;
import cn.rzpt.model.SysUserDTO;
import cn.rzpt.service.ISysUserService;
import cn.rzpt.utils.JwtUtils;
import com.alibaba.druid.support.json.JSONUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wax
 * @Description 登录成功拦截器
 * @since 1.0.0
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ISysUserService sysUserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置响应编码格式为json格式
        response.setContentType("application/json;charset=UTF-8");
        //获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();
        //生成token
        String token = jwtUtils.createToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(),token);
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = sysUserService.getByUsername(authentication1.getName());
        SysUserDTO sysUserDTO = BeanUtil.copyProperties(sysUser, SysUserDTO.class);
        Result ok = Result.ok().message("登录成功").data("token",token).data("userinfo",sysUserDTO);
        outputStream.write(JSONUtil.toJsonStr(ok).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
