package SWTeam2.vocabulary.SWTeam2.service;

import SWTeam2.vocabulary.SWTeam2.dto.LoginRequestDto;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignupService {
    @Autowired
    private final UserRepository userRepository;
    public UserSignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity signup(LoginRequestDto loginRequestDto) {
        if (userRepository.findByEmail(loginRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("가입된 이메일이 이미 존재합니다.");
        }
        UserEntity user = new UserEntity();
        user.setEmail(loginRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(loginRequestDto.getPassword()));
        return userRepository.save(user);
    }

}
