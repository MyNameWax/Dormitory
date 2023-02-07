package cn.rzpt.service.impl;

import cn.rzpt.entity.SysUser;
import cn.rzpt.mapper.SysUserMapper;
import cn.rzpt.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * @param username
     * @return
     */
    @Override
    public SysUser getByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().eq("username",username));
    }

    @Override
    public void register(SysUser sysUser) {
        this.save(sysUser);
    }


}
