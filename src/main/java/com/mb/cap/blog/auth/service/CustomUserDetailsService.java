package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.dto.UserDto;
import com.mb.cap.blog.auth.entity.Role;
import com.mb.cap.blog.auth.entity.User;
import com.mb.cap.blog.utils.CommonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service("userService")
public class CustomUserDetailsService implements UserDetailsService, InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    @Autowired
//    private ActivityLogService activityLogService;

    public CustomUserDetailsService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = null;
//        ActivityLog log = new ActivityLog();
//        log.setLogType(ActionLogTypeEnum.Login.getValue());

        try {
            userDto = this.userService.findByUsername(username);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String logContent = "Login with username:" + username;
        if (!CommonUtils.isNull(userDto)) {
            User user = userDto.toEntity(userDto);
            Collection<? extends GrantedAuthority> cols = user.getAuthorities();
            List<Role> roles = roleService.findAllByUserId(user.getId());
            roles.forEach(role -> {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
                ((Set<GrantedAuthority>) cols).add(authority);
            });
            return user;
        } else {
//            log.setContentLog(logContent + " fail");
//            this.activityLogService.save(log);
            throw new UsernameNotFoundException("User with username (" + username + ") not found.");
        }
    }

    public void afterPropertiesSet() throws Exception {
        if (CommonUtils.isNull(this.userService)) {
            System.out.println("User Service not initialized!");
        }

    }
}
