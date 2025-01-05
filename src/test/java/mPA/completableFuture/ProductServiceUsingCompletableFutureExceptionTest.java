package mPA.completableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import mPA.domain.Product;
import mPA.service.InventoryService;
import mPA.service.ProductInfoService;
import mPA.service.ReviewService;
import mPA.utils.CommonUtil;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

	@InjectMocks
	ProductServiceUsingCompletableFuture pscf;
	
	@Mock
    private ProductInfoService psiMock;
	@Mock
    private ReviewService rsMock;
    private CommonUtil commonUtil;
    @Mock
    private InventoryService isMock;

	
	@Test
	void testRetrieveProductDetailsWihInventory_approach2() {
		String productID = "ABC123";
		
		when(psiMock.retrieveProductInfo(any())).thenCallRealMethod();
		when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException("Exepcion ocured testRetrieveProductDetailsWihInventory_approach2 TEST"));
		when(isMock.retrieveInventory(any())).thenCallRealMethod();
		
		Product product= pscf.retrieveProductDetailsWihInventory_approach2(productID);
		
		assertNotNull(product);
		assertTrue(product.getProductInfo()
				.getProductOptions().size()>1);
		
		product.getProductInfo()
		.getProductOptions()
		.forEach(productOption -> {
			assertNotNull(productOption.getInventory());
		});
		
		assertNotNull(product.getReview());
		assertEquals(0, product.getReview().getNoOfReviews());
	}

	
	@Test
	void testRetrieveProductDetailsWihInventory_productInfoServiceError() {
		String productID = "ABC123";
		
		when(psiMock.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exepcion ocured testRetrieveProductDetailsWihInventory_productInfoServiceError TEST"));
		when(rsMock.retrieveReviews(any())).thenCallRealMethod();
	//	when(isMock.retrieveInventory(any())).thenCallRealMethod();
		
	//	Product product= pscf.retrieveProductDetailsWihInventory_approach2(productID);
		
		org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> pscf.retrieveProductDetailsWihInventory_approach2(productID) );
	}
	
	
}
