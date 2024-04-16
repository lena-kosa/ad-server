package com.ads.adserver.service;

import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class AdService implements IAdService {
    @Override
    public Campaign createCampaign(Campaign campaign) {
        return null;
    }

    @Override
    public Product serveAd(String category) {
        return null;
    }
}
