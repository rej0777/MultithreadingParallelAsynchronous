package mPA.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import mPA.domain.Inventory;
import mPA.domain.Product;
import mPA.domain.ProductInfo;
import mPA.domain.ProductOption;
import mPA.domain.Review;
import mPA.service.InventoryService;
import mPA.service.ProductInfoService;
import mPA.service.ReviewService;
import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;


public class ProductServiceUsingCompletableFuture {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    private CommonUtil commonUtil;
    private InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }
    
    
    

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService,
			InventoryService inventoryService) {
		super();
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
		this.inventoryService = inventoryService;
	}




	public Product retrieveProductDetails(String productId) {
    	CommonUtil.stopWatch.start();
    	
    	CompletableFuture<ProductInfo> cfProductInfo =CompletableFuture.supplyAsync(() ->productInfoService.retrieveProductInfo(productId) );
    	CompletableFuture<Review> cfReviews 	 =CompletableFuture.supplyAsync(() ->reviewService.retrieveReviews(productId));
       //// ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
      //  Review review = reviewService.retrieveReviews(productId); // blocking call

    Product product=	cfProductInfo.thenCombine(cfReviews, (productInfo,review)-> new Product(productId, productInfo, review)).join();    	
        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return product;
    }
    
 
    public CompletableFuture<Product>  retrieveProductDetails_approach2(String productId) {     	
    	CompletableFuture<ProductInfo> cfProductInfo =CompletableFuture.supplyAsync(() ->productInfoService.retrieveProductInfo(productId) );
    	CompletableFuture<Review> cfReviews 	 =CompletableFuture.supplyAsync(() ->reviewService.retrieveReviews(productId));

    	return cfProductInfo.thenCombine(cfReviews, (productInfo,review)-> new Product(productId, productInfo, review));
    }
    
    
    public Product retrieveProductDetailsWihInventory(String productId) {
    	CommonUtil.stopWatch.start();
    	
    	CompletableFuture<ProductInfo> cfProductInfo =CompletableFuture
    			.supplyAsync(() ->productInfoService.retrieveProductInfo(productId) )
    			.thenApply(productInfo-> {
    				productInfo.setProductOptions(updaateInventory(productInfo));
    				return productInfo;
    				//updaateInventory(productInfo);
    			});
    	
    	CompletableFuture<Review> cfReviews 	 =CompletableFuture.supplyAsync(() ->reviewService.retrieveReviews(productId));

    Product product=	cfProductInfo.thenCombine(cfReviews, (productInfo,review)-> new Product(productId, productInfo, review)).join();    	
        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return product;
    }
    

    private List<ProductOption> updaateInventory(ProductInfo productInfo) {
		
    List<ProductOption> productOptionList =	productInfo.getProductOptions()
    	.stream()
    	.map(productOption -> {
    		Inventory inventory =inventoryService.retrieveInventory(productOption);
    		productOption.setInventory(inventory);
    		return productOption;
    	} )
    	.collect(Collectors.toList());
		
    return productOptionList;
	}

    public Product retrieveProductDetailsWihInventory_approach2(String productId) {
    	CommonUtil.stopWatch.start();
    	
    	CompletableFuture<ProductInfo> cfProductInfo =CompletableFuture
    			.supplyAsync(() ->productInfoService.retrieveProductInfo(productId) )
    			.thenApply(productInfo-> {
    				productInfo.setProductOptions(updaateInventory_approach2(productInfo));
    				return productInfo;
    				//updaateInventory(productInfo);
    			});
    	
    	CompletableFuture<Review> cfReviews 	 =CompletableFuture
    			.supplyAsync(() ->reviewService.retrieveReviews(productId))
    			.exceptionally((e) -> {
    				 LoggerUtil.log("Handle the Exception in Ptoduct Sevice : " +e.getMessage());
    				 return Review.builder().noOfReviews(0).overallRating(0).build();
    			});

    Product product=	cfProductInfo
    		.thenCombine(cfReviews, (productInfo,review)-> new Product(productId, productInfo, review))
    		.whenComplete((prod, ex) -> {
    			 LoggerUtil.log("inside whenComplete "+prod +"and exception is "+ ex);
    		} )
    		.join();    	
     
    
    CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return product;
    }
    
    private List<ProductOption> updaateInventory_approach2(ProductInfo productInfo) {
		
    List<CompletableFuture<ProductOption>> productOptionList =	productInfo.getProductOptions()
    	.stream()
    	.map(productOption -> {
    	return	CompletableFuture.supplyAsync(() -> inventoryService.retrieveInventory(productOption))
    		.thenApply(inventory ->{
    			productOption.setInventory(inventory);
    			return productOption;
    		} );

    	} )
    	.collect(Collectors.toList());
		
    return productOptionList.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        LoggerUtil.log("Product is " + product);

    }
}
