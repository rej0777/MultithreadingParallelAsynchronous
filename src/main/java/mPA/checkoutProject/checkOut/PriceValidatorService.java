package mPA.checkoutProject.checkOut;

import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class PriceValidatorService {

    public boolean isCartItemInvalid(CartItem cartItem){
        int cartId = cartItem.getItemId();
        LoggerUtil.log("isCartItemInvalid "+ cartItem);
        CommonUtil.delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}