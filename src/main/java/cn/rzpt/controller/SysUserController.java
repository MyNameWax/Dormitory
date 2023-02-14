package cn.rzpt.controller;

import cn.hutool.core.util.StrUtil;
import cn.rzpt.common.lang.Result;
import cn.rzpt.entity.SysUser;
import cn.rzpt.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wax
 * @Description 用户系统
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody SysUser sysUser) {
        return sysUserService.register(sysUser);
    }


    /**
     * 分页查询
     * @param username  分页查询条件 -- 根据用户名模糊查询
     * @param pageIndex  页码
     * @param pageSize   一页显示数量
     * @return
     */
    @GetMapping("/list")
    public Result userList(@RequestParam(defaultValue = "",required = false)String username,@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        if (StrUtil.isNotBlank(username)) {
            Page<SysUser> userPage = sysUserService.page(new Page<SysUser>(pageIndex, pageSize), Wrappers.<SysUser>lambdaQuery().orderByDesc(SysUser::getId).like(SysUser::getUsername, username));
            return Result.ok().data("userinfo",userPage);
        }else{
            Page<SysUser> userPage = sysUserService.page(new Page<SysUser>(pageIndex, pageSize), Wrappers.<SysUser>lambdaQuery().orderByDesc(SysUser::getId));
            return Result.ok().data("userinfo",userPage);
        }

    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        return sysUserService.addUser(sysUser);
    }


}
