package SWTeam2.vocabulary.SWTeam2.service;

import SWTeam2.vocabulary.SWTeam2.auth.CustomUserDetail;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;;
    }
    //시큐리티 내부의 로그인 프로세스중 인증처리를 하는 메소드 중 하나
    //이 메서드를 오버라이드하여 데이터베이스의 유저 정보
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //등록된 email이 없을 때 exception 반환
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        System.out.println(user.getEmail());
        if (user == null) {
            //throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
            throw new UsernameNotFoundException("등록된 이메일이 없습니다.");
        }
        return new CustomUserDetail(user);
    }
}
public class CustomUserDetail implements UserDetails{
    private UserEntity user;
}