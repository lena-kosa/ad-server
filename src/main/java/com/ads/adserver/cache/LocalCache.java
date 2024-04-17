package com.ads.adserver.cache;

import com.ads.adserver.domain.Product;
import com.ads.adserver.repository.ProductRepository;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocalCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCache.class);

    private final ProductRepository productRepository;

    private final Map<String, Product> categoryProductMap = new HashMap<>();
    @Getter
    private Product maxBidProduct;
    private final List<String> categories = List.of("phone", "gadget");

    public LocalCache(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    private void updateCache() {
        LOGGER.info("Updating local cache");
        List<Product> maxBid = productRepository.findMaxBid();
        if (maxBid != null && !maxBid.isEmpty()) {
            maxBidProduct = maxBid.get(0);
        } else {
            maxBidProduct = null;
        }
        for (String category : categories) {
            List<Product> maxBidByCategory = productRepository.findMaxBidByCategory(category);
            if (maxBidByCategory != null && !maxBidByCategory.isEmpty()) {
                categoryProductMap.put(category, maxBidByCategory.get(0));
            } else {
                categoryProductMap.remove(category);
            }
        }
    }

    public Product getMaxBidByCategory(String category) {
        return categoryProductMap.get(category);
    }


}
