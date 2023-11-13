package com.nturbo1.satWebsiteProjBack.service.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;


@Component
public class StripeClient {
	
    @Autowired
    StripeClient(@Value("${stripe.apiKey}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }
    
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }
    
    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }
    
    public Charge chargeNewCard(String token, Integer amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 100 * amount); // Stripe somehow divides 
        										  // the amoun in KZT by 100 	
        chargeParams.put("currency", "KZT");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
    
    public Charge chargeCustomerCard(String customerId, Integer amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 100 * amount);
        chargeParams.put("currency", "KZT");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
