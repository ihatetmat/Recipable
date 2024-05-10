package capstone.recipable.domain.login.service;


import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class LoginService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public boolean userExists(String email) {
        User user = userRepository.findByLoginId(email);
        if (user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUserValid(String email, String password) {
        User user = userRepository.findByLoginId(email);
        if (user == null) {
            return false;
        }
        return encoder.matches(password, user.getPassword());
    }

}
