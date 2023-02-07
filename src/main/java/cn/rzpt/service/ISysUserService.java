package cn.rzpt.service;

import cn.rzpt.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wax
 * @Description TODO
 * @since 1.0.0
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getByUsername(String username);

    void register(SysUser sysUser);

}
