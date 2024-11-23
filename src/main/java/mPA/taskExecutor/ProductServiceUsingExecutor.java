package mPA.taskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import mPA.domain.Product;
import mPA.domain.ProductInfo;
import mPA.domain.Review;
import mPA.service.ProductInfoService;
import mPA.service.ReviewService;
import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;


public class ProductServiceUsingExecutor {
	
	static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); 
	
    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    //private CommonUtil commonUtil;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }
    

    public Product retrieveProductDetails(String productId) throws InterruptedException, ExecutionException {
    	CommonUtil.stopWatch.start();
    	
    	 Future<ProductInfo> productInfoFuture= executorService.submit(() -> productInfoService.retrieveProductInfo(productId) );
    	 Future<Review> reviewFuture= executorService.submit(() ->  reviewService.retrieveReviews(productId) );
    	 
       // ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
      //  Review review = reviewService.retrieveReviews(productId); // blocking call

    	 ProductInfo productInfo = productInfoFuture.get();
    	 Review review = reviewFuture.get();
    	 
        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingExecutor productService = new ProductServiceUsingExecutor(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        LoggerUtil.log("Product is " + product);

        
        executorService.shutdown();
    }
}
