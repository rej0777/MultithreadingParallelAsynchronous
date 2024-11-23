package mPA.service;

import java.util.List;

import mPA.domain.ProductInfo;
import mPA.domain.ProductOption;
import mPA.utils.CommonUtil;

public class ProductInfoService {


    public ProductInfo retrieveProductInfo(String productId) {
    	CommonUtil.delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}