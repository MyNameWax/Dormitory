package cn.rzpt.service.impl;

import cn.rzpt.common.lang.Result;
import cn.rzpt.common.lang.ResultCode;
import cn.rzpt.entity.SysUser;
import cn.rzpt.mapper.SysUserMapper;
import cn.rzpt.model.SysUserDTO;
import cn.rzpt.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @Description 用户逻辑
 * @since 1.0.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    /**
     * 用户登录逻辑
     *
     * @param username
     * @return
     */
    @Override
    public SysUser getByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().eq("username",username));
    }


    /**
     * 用户注册逻辑
     *
     * @param sysUser
     * @return
     */
    @Override
    public Result register(SysUser sysUser) {
        //判断用户名是否存在
        SysUser user = getOne(new QueryWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if (user != null) {
            return Result.error().code(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode()).message(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        String password = sysUser.getPassword();
        String newPassword = new BCryptPasswordEncoder().encode(password);
        sysUser.setPassword(newPassword);
        save(sysUser);
        return Result.ok().message("注册成功");
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @Override
    public Result addUser(SysUser sysUser) {
        //判断用户是否唯一
        SysUser username = getOne(new QueryWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if (username != null) {
            return Result.error().message("用户已存在");
        }
        save(sysUser);
        return Result.ok().message("新增成功！");
    }


}
