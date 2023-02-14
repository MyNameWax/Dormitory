package cn.rzpt.service;

import cn.rzpt.common.lang.Result;
import cn.rzpt.entity.SysUser;
import cn.rzpt.model.SysUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wax
 * @Description TODO
 * @since 1.0.0
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getByUsername(String username);


    Result register(SysUser sysUser);

    Result addUser(SysUser sysUser);
}
