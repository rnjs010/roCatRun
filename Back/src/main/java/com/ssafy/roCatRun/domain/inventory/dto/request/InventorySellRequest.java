package com.ssafy.roCatRun.domain.inventory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InventorySellRequest {
    private List<Long> inventoryIds;
    private int totalPrice;
}
