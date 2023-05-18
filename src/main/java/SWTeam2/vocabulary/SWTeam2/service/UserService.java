package SWTeam2.vocabulary.SWTeam2.service;


import SWTeam2.vocabulary.SWTeam2.auth.CustomUserDetail;
import SWTeam2.vocabulary.SWTeam2.dto.LoginRequestDto;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
//@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;;
    }
    public String findPassword(LoginRequestDto loginRequestDto){
        UserEntity userEntity = userRepository.findByNameAndEmail(loginRequestDto.getName(), loginRequestDto.getEmail())
                .orElseThrow(()->new IllegalArgumentException("해당하는 계정이 존재하지 않습니다."));
        String passwordHint = userEntity.getPasswordHint();
        return passwordHint;
    }

    //시큐리티 내부의 로그인 프로세스중 인증처리를 하는 메소드 중 하나
    //이 메서드를 오버라이드하여 데이터베이스의 유저 정보
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //등록된 email이 없을 때 exception 반환
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        System.out.println(user.getEmail());
        if(user == null){
            throw new UsernameNotFoundException("사용자 정보가 없습니다.");
        }
        if(user.getEmail().equals("IamNotUserIamAdmin@email.com") && user.getPassword().equals("Rhksflwkqlqjs")){
            List<GrantedAuthority>authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        return new CustomUserDetail(user);
    }
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Optional<UserEntity> getUserByNameAndEmailAndLevelAndStudyVocaCountAndTier(
            String name, String email, int level, int studyVocaCount, int tier) {
        return userRepository.findByNameAndEmailAndLevelAndStudyVocaCountAndTier(
                name, email, level, studyVocaCount, tier);
    }
}