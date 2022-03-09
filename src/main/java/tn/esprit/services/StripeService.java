package tn.esprit.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Source;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.ChargeRequest;

@Service
@Slf4j
public class StripeService {

	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;

	@PostConstruct
	public void init() throws Exception {
		Stripe.apiKey = secretKey;
	}

	public Charge charge(ChargeRequest chargeRequest) throws StripeException {

		/*
		 * RequestOptions requestOptions = RequestOptions.builder()
		 * .setStripeAccount("acct_1FQDPYJ2FwI2xF1g") .build();
		 * 
		 * /* ChargeCreateParams chargeCreateParams=ChargeCreateParams.builder()
		 * .setAmount(chargeRequest.getAmount()*1L)
		 * .setCurrency(chargeRequest.getCurrency().toString())
		 * .setCustomer(chargeRequest.getEmail()).build();
		 */

		Map<String, Object> sourceParams = new HashMap<>();
		sourceParams.put("type", "ach_credit_transfer");
		sourceParams.put("currency", "usd");
		
		Map<String, Object> card = new HashMap<>();
		card.put("number", "4242424242424242");
		card.put("exp_month", 4);
		card.put("exp_year", 2023);
		card.put("cvc", "314");
		card.put("name", "jenny.rosen@example.com");
		Map<String, Object> params = new HashMap<>();

		params.put("card", card);
		Token token = Token.create(params);
		/*
		 * PaymentMethod paymentMethod = PaymentMethod.create(params);
		 */

		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", chargeRequest.getAmount() * 100L);
		chargeParams.put("currency", chargeRequest.getCurrency());
		chargeParams.put("description", chargeRequest.getDescription());
		Map<String, Object> ownerParams = new HashMap<>();
		ownerParams.put("email", "jenny.rosen@example.com");
		ownerParams.put("name", "yo");
		
		chargeParams.put("source", token.getId());
		// chargeParams.put("card", card);
		// chargeParams.put("source",Source.create(sourceParams).getId());
		// chargeParams.put("customer", chargeRequest.getEmail());

		return Charge.create(chargeParams);

	}

	public PaymentMethod paymentMethod(ChargeRequest chargeRequest) throws StripeException {

		Map<String, Object> card = new HashMap<>();
		card.put("number", "4242424242424242");
		card.put("exp_month", 3);
		card.put("exp_year", 2023);
		card.put("cvc", "314");
		Map<String, Object> params = new HashMap<>();
		params.put("type", "card");
		params.put("card", card);

		return PaymentMethod.create(params);

	}

	public Customer createCustomer(ChargeRequest chargeRequest) throws StripeException {

		CustomerCreateParams params = CustomerCreateParams.builder().setEmail(chargeRequest.getEmail())
				.setName(chargeRequest.getName()).build();

		return Customer.create(params);

	}

	/*
	 * public String paymentIntent(ChargeRequest chargeRequest) throws
	 * StripeException {
	 * 
	 * PaymentIntentCreateParams params =
	 * PaymentIntentCreateParams.builder().setAmount(chargeRequest.getAmount() * 1L)
	 * .setReceiptEmail("test@email.com").setCurrency(chargeRequest.getCurrency().
	 * toString()).build();
	 * 
	 * return PaymentIntent.create(params).getId(); }
	 * 
	 * public PaymentIntent confirmIntent(String test) throws StripeException {
	 * PaymentIntent intent = PaymentIntent.retrieve(test);
	 * PaymentIntentConfirmParams params =
	 * PaymentIntentConfirmParams.builder().setPaymentMethod("pm_card_visa")
	 * .build(); return intent.confirm(params);
	 * 
	 * }
	 */

}