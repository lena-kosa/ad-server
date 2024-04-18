package com.ads.adserver.service;

import com.ads.adserver.cache.LocalCache;
import com.ads.adserver.controller.AdServerException;
import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;
import com.ads.adserver.repository.CampaignRepository;
import com.ads.adserver.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AdService implements IAdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdService.class);

    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    private final LocalCache localCache;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public AdService(ProductRepository productRepository, CampaignRepository campaignRepository,
                     LocalCache localCache, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.localCache = localCache;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Override
    @Transactional
    public Campaign createCampaign(Campaign campaign, List<Long> productIds) throws AdServerException {
        LOGGER.info("Create campaign");
        for (Long productId : productIds) {
            productRepository.findById(productId).ifPresent(product -> campaign.getProducts().add(product));
        }
        if (campaign.getProducts().isEmpty()) {
            throw new AdServerException("Campaign should contain existing products", HttpStatus.BAD_REQUEST);
        }
        Campaign createdCampaign = campaignRepository.save(campaign);
        threadPoolTaskScheduler.schedule(() -> deleteCampaign(createdCampaign),
                createdCampaign.getStartDate().plus(10, ChronoUnit.DAYS));

        LOGGER.info("Created campaign {}", createdCampaign);
        return createdCampaign;
    }


    // in real life we would create separate table for finished campaigns and move it there.
    // here finished campaign is just removed from the db for simplicity
    protected void deleteCampaign(Campaign campaign) {
        LOGGER.info("Delete campaign {}", campaign);
        campaignRepository.delete(campaign);
    }

    @Override
    public Product serveAd(String category) {
        LOGGER.info("Serve ad for {}", category);
        Product product = localCache.getMaxBidByCategory(category);
        if (product != null) {
            return product;
        }
        return localCache.getMaxBidProduct();
    }

}
