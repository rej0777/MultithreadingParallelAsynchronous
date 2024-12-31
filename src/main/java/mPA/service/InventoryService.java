package mPA.service;

import mPA.domain.Inventory;
import mPA.domain.ProductOption;
import mPA.utils.CommonUtil;


public class InventoryService {
    public Inventory retrieveInventory(ProductOption productOption) {
    	CommonUtil.delay(500);
        return Inventory.builder()
                .count(2).build();
    }
}
