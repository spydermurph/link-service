package is.webworks.link_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import is.webworks.link_service.model.User;
import is.webworks.link_service.model.UserPrincipal;
import is.webworks.link_service.dao.UserDao;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userDao.findByUsername(username);

        if (user==null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }

}