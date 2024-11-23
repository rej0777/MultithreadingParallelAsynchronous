package mPA.service;

import mPA.domain.Product;
import mPA.domain.ProductInfo;
import mPA.domain.Review;
import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;


public class ProductService {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    private CommonUtil commonUtil;

    public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }
    

    public Product retrieveProductDetails(String productId) {
    	CommonUtil.stopWatch.start();

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call

        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        LoggerUtil.log("Product is " + product);

    }
}
