package com.sprint.mission.blog_mission.service.file;

import com.sprint.mission.blog_mission.dto.request.UserCreateRequestDTO;
import com.sprint.mission.blog_mission.entity.User;
import com.sprint.mission.blog_mission.repository.UserRepository;
import com.sprint.mission.blog_mission.service.UserService;
import com.sprint.mission.blog_mission.service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FileUserService implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public FileUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User createUser(UserCreateRequestDTO userCreateRequestDTO) {
        if (userCreateRequestDTO.id() == null
                || userCreateRequestDTO.id().length() > 30
                || userCreateRequestDTO.id().length() < 6) {
            throw new IllegalArgumentException("ID 입력은 필수이며, 6~30 글자여야 합니다.");
        }

        String passwordRegex = "^(?=.*[A-Za-z].*[A-Za-z])(?=.*\\d.*\\d)(?=.*[!@#$%^&*].*[!@#$%^&*]).{12,50}$";

        if (userCreateRequestDTO.password() == null
                || !userCreateRequestDTO.password().matches(passwordRegex)) {
            throw new IllegalArgumentException("비밀번호는 영문/숫자/특수문자 중 2개 이상 포함해야 하며 12~50 글자여야 합니다.");
        }

        String emailRegex = "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9.-]{1,63}\\.[A-Za-z]{2,}$";

        if (userCreateRequestDTO.email() == null
                || !userCreateRequestDTO.email().matches(emailRegex)) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아니거나 100자 이내여야 합니다.");
        }

        if (userCreateRequestDTO.nickname() == null
                || userCreateRequestDTO.nickname().length() > 50
                || userCreateRequestDTO.nickname().length() < 3) {
            throw new IllegalArgumentException("닉네임 입력은 필수이며, 3~50 글자여야 합니다.");
        }

        String encodedPassword = passwordEncoder.encode(userCreateRequestDTO.password());

        return new User(userCreateRequestDTO);
    }

    public String login(String id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(id);
    }
}
