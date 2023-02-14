package cn.rzpt.service.impl;

import cn.rzpt.common.lang.Result;
import cn.rzpt.common.lang.ResultCode;
import cn.rzpt.entity.SysUser;
import cn.rzpt.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

/**
 * @author wax
 * @Description 用户登录逻辑
 * @since 1.0.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        return new User(sysUser.getUsername(),sysUser.getPassword(), Collections.emptyList());
    }
}
