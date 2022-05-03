import java.util.Optional;

import com.games.firmaGames.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private usuarioRepository repository;

    public Usuario registerUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passWordEncoder = encoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncoder);

        return repository.save(user);
    }

    public Optional<UserLogin> Login (Optional <UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> usuario = repository.findByUsuario(user.get().getUser());

        if(user.isPresent()){
            if(encoder.matches(user.get().getPassword(), user.get().getPassword())){

                String auth = user.get().getUser() + ":" + user.get().getPassword();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setName(user.get().getName());

                return user;
            }
        }
        return null;
    }

    
}