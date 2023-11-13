package com.nturbo1.satWebsiteProjBack.web.controller.payment;

import com.nturbo1.satWebsiteProjBack.service.payment.PaymentService;
import com.nturbo1.satWebsiteProjBack.service.payment.StripeClient;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;
import com.stripe.model.Charge;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiVersion(1)
@RestController
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value = RestApiConst.PAYMENT_API_ROOT_PATH)
@Data
public class PaymentGatewayController {
    
	private final PaymentService paymentService;
    
    @PostMapping("/charge")
    public ResponseEntity<Void> chargeCard(
    		@RequestHeader(value="token") String token, 
    		@RequestHeader(value="email") String email,
    		@RequestHeader(value = "courseId") Long courseId
    ) {
        final Boolean isSuccess = paymentService.processPayment(token, email, courseId);
        
        if (isSuccess) {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
