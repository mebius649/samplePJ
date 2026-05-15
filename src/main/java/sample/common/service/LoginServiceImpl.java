package sample.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sample.common.dao.UserDao;
import sample.common.dao.entity.User;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService { // 両方のインターフェースを実装

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder; // これが必要

    // Spring Security用の認証処理
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 独自のUserエンティティを取得
        sample.common.dao.entity.User user = userDao.findByUserId(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }

        // Securityが解釈できるUserDetailsに変換して返す
        return org.springframework.security.core.userdetails.User.withUsername(user.getUserId())
                   .password(user.getPassword())
                   .roles("USER")
                   .build();
    }

    // 独自のサインアップ処理
    @Override
    public boolean signup(String userId, String password) {
        User existingUser = userDao.findByUserId(userId);

        if (existingUser != null) {
            return false;
        }

        // パスワードをハッシュ化して保存
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserId(userId);
        user.setPassword(hashedPassword);

        userDao.insert(user);

        return true;
    }

}