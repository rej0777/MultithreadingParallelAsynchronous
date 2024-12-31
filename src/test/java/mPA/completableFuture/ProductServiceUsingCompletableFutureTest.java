package mPA.completableFuture;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mPA.domain.Product;
import mPA.service.InventoryService;
import mPA.service.ProductInfoService;
import mPA.service.ReviewService;

class ProductServiceUsingCompletableFutureTest {

	 private ProductInfoService pis = new ProductInfoService();
	 private ReviewService rw = new ReviewService();
	 private InventoryService is = new InventoryService();
	 ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(pis, rw, is);
	
	 
	@Test
	void testRetrieveProductDetails() {
		
		String productId = "ADFG123";

		Product product = pscf.retrieveProductDetails(productId);

		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() >0);
		assertNotNull(product.getReview());
		
	}
	
	@Test
	void testretrieveProductDetailsWihInventory() {
		
		String productId = "ADFG123";
		Product product = pscf.retrieveProductDetailsWihInventory(productId);

		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() >0);
		product.getProductInfo().getProductOptions()
		.forEach(t -> {
			assertNotNull(t.getInventory());
		});
		assertNotNull(product.getReview());
		
	}
	
	@Test
	void testretrieveProductDetailsWihInventory_approach2() {
		
		String productId = "ADFG123";
		Product product = pscf.retrieveProductDetailsWihInventory_approach2(productId);

		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() >0);
		product.getProductInfo().getProductOptions()
		.forEach(t -> {
			assertNotNull(t.getInventory());
		});
		assertNotNull(product.getReview());
		
	}

}
