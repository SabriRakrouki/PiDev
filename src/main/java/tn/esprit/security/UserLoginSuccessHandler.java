package tn.esprit.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.UserService;
@Component
public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
 
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    /*
    @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	UserDetailsImpl userDetails =  (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.getByUsername(userDetails.getUsername());
        if (user.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(user.getUsername());
        }
         
        super.onAuthenticationSuccess(request, response, authentication);
    }*/
}
