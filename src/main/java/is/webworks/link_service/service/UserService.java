package is.webworks.link_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import is.webworks.link_service.model.User;
import is.webworks.link_service.dao.UserDao;



@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userDao.save(user) ;

    }
}