package mPA.checkoutProject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ForkJoinPool;

import org.junit.jupiter.api.Test;

import mPA.checkoutProject.checkOut.Cart;
import mPA.checkoutProject.checkOut.CheckoutResponse;
import mPA.checkoutProject.checkOut.CheckoutStatus;
import mPA.checkoutProject.checkOut.PriceValidatorService;
import mPA.utils.DataSet;

class CheckoutServiceTest {

	PriceValidatorService priceValidatorService = new PriceValidatorService();		
	CheckoutService checkoutService = new CheckoutService(priceValidatorService);
	
	@Test
	void no_of_cores() throws Exception {
		
		System.out.println("number of cores cpu" + Runtime.getRuntime().availableProcessors());
	}
	
	@Test
	void parallelism() throws Exception {
		
		System.out.println("Returns the targeted parallelism level of the common pool:  " + ForkJoinPool.getCommonPoolParallelism());
	}
	
	@Test
	void testCheckout_6items() {		
	Cart cart= DataSet.createCart(6);		
	CheckoutResponse checkoutResponse =	checkoutService.checkout(cart);
	assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
	assertTrue(checkoutResponse.getFinalRate() >0);
	}

	@Test
	void testCheckout_25items() {		
	Cart cart= DataSet.createCart(18);		
	CheckoutResponse checkoutResponse =	checkoutService.checkout(cart);
	assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
	}
	
	@Test
	void modyfi_paralleism() {		
	Cart cart= DataSet.createCart(100);		
	CheckoutResponse checkoutResponse =	checkoutService.checkout(cart);
	assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
	}
	
	@Test
	void paralleism_limitSet() {		
		  System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
			
	Cart cart= DataSet.createCart(100);		
	CheckoutResponse checkoutResponse =	checkoutService.checkout(cart);
	assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
	}
}
