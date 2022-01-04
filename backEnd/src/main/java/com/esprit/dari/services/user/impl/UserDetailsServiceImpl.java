package com.esprit.dari.services.user.impl;

import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.services.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDari user = accountService.loadUserByUsername(username);
        if(user==null) throw new UsernameNotFoundException(username);
        if(!user.isActivated()) throw new UsernameNotFoundException("not activated");
        Collection<GrantedAuthority> authorities= new ArrayList<>();
        user.getRoleDaris().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new User(user.getUsername(),user.getPassword(),authorities);
    }

}
