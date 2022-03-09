package tn.esprit.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.net.StripeResponse;
import com.stripe.param.CustomerCreateParams;

import tn.esprit.entities.ChargeRequest;
import tn.esprit.entities.ChargeRequest.Currency;
import tn.esprit.services.StripeService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private StripeService paymentsService;

	@PostMapping("/charge")
	@ResponseBody
	public String charge( @RequestBody ChargeRequest chargeRequest) throws StripeException {

		return paymentsService.charge(chargeRequest).toString();

	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}

}