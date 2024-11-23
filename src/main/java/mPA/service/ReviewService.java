package mPA.service;

import mPA.domain.Review;
import mPA.utils.CommonUtil;

public class ReviewService {

    public Review retrieveReviews(String productId) {
    	CommonUtil.delay(1000);
        return new Review(200, 4.5);
    }
}
