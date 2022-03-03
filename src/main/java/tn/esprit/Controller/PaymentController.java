package tn.esprit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import tn.esprit.entities.ChargeRequest;
import tn.esprit.entities.ChargeRequest.Currency;
import tn.esprit.services.StripeService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	
	 @Autowired
	    private StripeService paymentsService;
	
	 @RequestMapping("/home")
	 public ModelAndView  tocheckout() {
		 ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("index");
		 return modelAndView;
	 }
	 
	 
	   @PostMapping("/charge")
	    public String charge(ChargeRequest chargeRequest, Model model)
	      throws StripeException {
	        chargeRequest.setDescription("Example charge");
	        chargeRequest.setCurrency(Currency.EUR);
	        Charge charge = paymentsService.charge(chargeRequest);
	        model.addAttribute("id", charge.getId());
	        model.addAttribute("status", charge.getStatus());
	        model.addAttribute("chargeId", charge.getId());
	        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
	        return "result";
	    }

	    @ExceptionHandler(StripeException.class)
	    public String handleError(Model model, StripeException ex) {
	        model.addAttribute("error", ex.getMessage());
	        return "result";
	    }
	
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}