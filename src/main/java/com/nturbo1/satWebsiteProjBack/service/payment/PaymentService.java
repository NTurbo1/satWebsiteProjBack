package com.nturbo1.satWebsiteProjBack.service.payment;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import lombok.Data;

@Service
@Data
public class PaymentService {

    private final StripeClient stripeClient;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public boolean processPayment(String token, String email, Long courseId) {
    	
        try {
            Customer customer = stripeClient.createCustomer(token, email);

            // Step 2: Charge the customer
            Integer coursePrice = courseRepository
            		.findById(courseId).get().getPrice();
            System.out.println("Course Price: " + coursePrice);
            Charge charge = stripeClient.chargeCustomerCard(customer.getId(), 
            		coursePrice);

            // Step 3: Handle the payment success
            if (charge.getPaid()) {
                Long userId = userRepository.findByEmail(email).get().getUserId();
                User user = userRepository.findById(userId).orElse(null);

                if (user != null) {
                    Course course = courseRepository
                    		.findById(courseId)
                    		.orElse(null);

                    if (course != null) {
                        user.getEnrolledCourses().add(course);                        
                        userRepository.save(user);
                        // Payment successful and course added to 
                        // user's enrolled courses
                        return true; 
                    }
                }
            }

            // Payment failed or some other issue
            return false;
            
        } catch (StripeException e) {
            e.printStackTrace();
            return false;
            
        } catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
