package com.ads.adserver.service;

import com.ads.adserver.cache.LocalCache;
import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;
import com.ads.adserver.repository.CampaignRepository;
import com.ads.adserver.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdService implements IAdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdService.class);

    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    private final LocalCache localCache;

    public AdService(ProductRepository productRepository, CampaignRepository campaignRepository, LocalCache localCache) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.localCache = localCache;
    }

    @Override
    @Transactional
    // todo - Transactional parameters
    public Campaign createCampaign(Campaign campaign, List<Long> productIds) {
        LOGGER.info("Create campaign");
        for (Long productId : productIds) {
            productRepository.findById(productId).ifPresent(product -> campaign.getProducts().add(product));
        }
        if (campaign.getProducts().isEmpty()) {
            return null;
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public Product serveAd(String category) {
        LOGGER.info("Serve ad for {}", category);
        return localCache.getMaxBidByCategory(category);
    }
}
