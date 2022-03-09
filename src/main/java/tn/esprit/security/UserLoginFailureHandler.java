package tn.esprit.security;
import java.io.IOException;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import tn.esprit.entities.User;
import tn.esprit.payload.LoginRequest;
import tn.esprit.payload.MessageResponse;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.UserService;
@Component

public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
   @Autowired
   private UserService userService;
   @Autowired
   UserRepository userRepository;
    
   public void onAuthenticationFailure(LoginRequest loginRequest) throws IOException, ServletException {
       String username = loginRequest.getUsername();
   /*    User user = userRepository.getByUsername(username);
        
       if (user != null) {
           if (user.isEnabled() && user.isAccountNonLocked()) {
               if (user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                   userService.increaseFailedAttempts(user);
               } else {
                   userService.lock(user);
                    ResponseEntity.ok(new MessageResponse("Your account has been locked due to 3 failed attempts."
                           + " It will be unlocked after 24 hours."));
                   
               }
           } else if (!user.isAccountNonLocked()) {
               if (userService.unlockWhenTimeExpired(user)) {
            	    ResponseEntity.ok(new MessageResponse("Your account has been unlocked. Please try to login again."));
               }
           }
            
       }*/
   }
}
