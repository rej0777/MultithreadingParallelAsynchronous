package mPA.checkoutProject;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import mPA.checkoutProject.checkOut.Cart;
import mPA.checkoutProject.checkOut.CartItem;
import mPA.checkoutProject.checkOut.CheckoutResponse;
import mPA.checkoutProject.checkOut.CheckoutStatus;
import mPA.checkoutProject.checkOut.PriceValidatorService;
import mPA.utils.CommonUtil;

public class CheckoutService {

	private PriceValidatorService priceValidatorService;
		
	public CheckoutService(PriceValidatorService priceValidatorService) {
		super();
		this.priceValidatorService = priceValidatorService;
	}




	public CheckoutResponse  checkout(Cart cart) {
		CommonUtil.startTimer();		
	List<CartItem> priceValidationList = 	cart.getCartItemList()
		.stream()
		.map(cartItem ->{
		boolean isPriceValid = priceValidatorService.isCartItemInvalid(cartItem);
		cartItem.setExpired(isPriceValid);
		return cartItem;
		} )
		.filter(CartItem::isExpired)
		.collect(Collectors.toList());
	
	if(priceValidationList.size()>0) {
		return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList );
	}
	CommonUtil.timeTaken();
	return new CheckoutResponse(CheckoutStatus.SUCCESS);
	}
	
}
