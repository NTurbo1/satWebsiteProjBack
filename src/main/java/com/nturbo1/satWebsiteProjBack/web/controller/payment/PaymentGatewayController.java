package com.nturbo1.satWebsiteProjBack.web.controller.payment;

import com.nturbo1.satWebsiteProjBack.service.payment.StripeClient;
import com.nturbo1.satWebsiteProjBack.web.controller.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ApiVersion(1)
@RestController
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value = RestApiConst.PAYMENT_API_ROOT_PATH)
public class PaymentGatewayController {
    
    private StripeClient stripeClient;
    
    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }
    
    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
        return this.stripeClient.chargeNewCard(token, amount);
    }
}
