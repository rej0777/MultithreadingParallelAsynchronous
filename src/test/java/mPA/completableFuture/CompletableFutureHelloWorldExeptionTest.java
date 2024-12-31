package mPA.completableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExeptionTest {
	
	@Mock
	HelloWorldService helloWorldService =  mock(HelloWorldService.class);
	
	@InjectMocks
	CompletableFutureHelloWorldExeption hwcfe;

	@Test
	void testHellowold_3_async_callas_handle() {
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
		when(helloWorldService.world()).thenCallRealMethod();
		
	String result = hwcfe.hellowold_3_async_callas_handle();
		
	assertEquals(" WORLD! HI COMPLETABLEFUTURE", result);
	}
	
	@Test
	void testHellowold_3_async_callas_handle_2() {
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured2"));
		
	String result = hwcfe.hellowold_3_async_callas_handle();
		
	assertEquals(" HI COMPLETABLEFUTURE", result);
	}
	
	@Test
	void testHellowold_3_async_callas_handle_3() {
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
	String result = hwcfe.hellowold_3_async_callas_handle();
		
	assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
	}
	
	@Test
	void testHellowold_3_async_callas_exceptionally() {
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
	String result = hwcfe.hellowold_3_async_callas_exceptionally();
		
	assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
	}
	
	@Test
	void testHellowold_3_async_callas_exceptionally_2() {
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured2"));
		
	String result = hwcfe.hellowold_3_async_callas_exceptionally();
		
	assertEquals(" HI COMPLETABLEFUTURE", result);
	}
	
	
	@Test
	void testhellowold_3_async_callas_whenHandle() {
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
	String result = hwcfe.hellowold_3_async_callas_whenHandle();
		
	assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
	}

	@Test
	void testtesthellowold_3_async_callas_whenHandle_2() {
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured2"));
		
	String result = hwcfe.hellowold_3_async_callas_whenHandle();
		
	assertEquals(" HI COMPLETABLEFUTURE", result);
	}
	
	
}
