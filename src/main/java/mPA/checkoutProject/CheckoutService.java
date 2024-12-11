package mPA.checkoutProject;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import mPA.checkoutProject.checkOut.Cart;
import mPA.checkoutProject.checkOut.CartItem;
import mPA.checkoutProject.checkOut.CheckoutResponse;
import mPA.checkoutProject.checkOut.CheckoutStatus;
import mPA.checkoutProject.checkOut.PriceValidatorService;
import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class CheckoutService {

	private PriceValidatorService priceValidatorService;
		
	public CheckoutService(PriceValidatorService priceValidatorService) {
		super();
		this.priceValidatorService = priceValidatorService;
	}




	public CheckoutResponse  checkout(Cart cart) {
		CommonUtil.startTimer();		
		List<CartItem> priceValidationList = cart.getCartItemList().stream().map(cartItem -> {
			boolean isPriceValid = priceValidatorService.isCartItemInvalid(cartItem);
			cartItem.setExpired(isPriceValid);
			return cartItem;
		}).filter(CartItem::isExpired).collect(Collectors.toList());
	
		
	if(priceValidationList.size()>0) {
		return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
	}
	
	//double finalPrice =calculateFinalPrice(cart);
	double finalPrice =calculateFinalPrice_reduce(cart);
	
	CommonUtil.timeTaken();
	LoggerUtil.log("checkout Complitted and final price is " + finalPrice );
	return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
	}


	private double calculateFinalPrice(Cart cart) {

	return	cart.getCartItemList()
		.parallelStream()
		.map(cartItem -> cartItem.getQuantity() * cartItem.getRate()  )
		.mapToDouble(Double::doubleValue)
		.sum();
	}


	private double calculateFinalPrice_reduce(Cart cart) {

	return	cart.getCartItemList()
		.parallelStream()
		.map(cartItem -> cartItem.getQuantity() * cartItem.getRate()  )
		.mapToDouble(Double::doubleValue)
		//.reduce(0.00, (x,y)->x+y);
		.reduce(0.00, Double::sum);
	}




}
