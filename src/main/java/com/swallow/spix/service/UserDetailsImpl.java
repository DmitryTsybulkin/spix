package com.swallow.spix.service;

import com.swallow.spix.dao.UserRep;
import com.swallow.spix.exceptions.UserNotFoundException;
import com.swallow.spix.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRep userRep;

    @Autowired
    public UserDetailsImpl(UserRep userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails userDetails = null;

        try {
            User user = userRep.findUserByLogin(login).orElseThrow(UserNotFoundException::new);
            userDetails = new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(),
                    Authority.getAuth()
            );
        } catch (UserNotFoundException | UsernameNotFoundException e) {
            log.error(e.getLocalizedMessage());
        }
        return userDetails;
    }

    static class Authority implements GrantedAuthority {

        static List<GrantedAuthority> getAuth() {
            List<GrantedAuthority> res = new ArrayList<>(1);
            res.add(new Authority());
            return res;
        }

        @Override
        public String getAuthority() {
            return "USER";
        }
    }

}
