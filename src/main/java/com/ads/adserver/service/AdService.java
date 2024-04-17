package com.ads.adserver.service;

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

    public AdService(ProductRepository productRepository, CampaignRepository campaignRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    @Transactional
    // todo - Transactional parameters
    public Campaign createCampaign(Campaign campaign, List<Long> productIds) {
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
        List<Product> maxBidByCategory = productRepository.findMaxBidByCategory(category);
        if (maxBidByCategory != null && !maxBidByCategory.isEmpty()) {
            return maxBidByCategory.get(0);
        }
        return null;
    }
}
