package com.ads.adserver.service;

import com.ads.adserver.cache.LocalCache;
import com.ads.adserver.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AdServiceTest {

    @Mock
    private LocalCache localCache;

    @InjectMocks
    private AdService adService;

    @Test
    void createCampaign() {
    }

    @Test
    void testServeAd_MaxBidForCategoryAvailable() {
        String category = "phone";
        Product product = new Product(1L, "Iphone 15", "phone", "abc", new BigDecimal("3500"));
        Mockito.when(localCache.getMaxBidByCategory(category)).thenReturn(product);
        Product result = adService.serveAd(category);
        assertNotNull(result);
        assertEquals(result, product);
    }

    @Test
    void testServeAd_MaxBidForCategoryNotAvailable() {
        String category = "gadget";
        Product product = new Product(1L, "Iphone 15", "phone", "abc", new BigDecimal("3500"));
        Mockito.when(localCache.getMaxBidByCategory(category)).thenReturn(null);
        Mockito.when(localCache.getMaxBidProduct()).thenReturn(product);
        Product result = adService.serveAd(category);
        assertNotNull(result);
        assertEquals(result, product);
    }
}