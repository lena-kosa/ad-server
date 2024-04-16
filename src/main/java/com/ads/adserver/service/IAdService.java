package com.ads.adserver.service;

import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;

public interface IAdService {
    Campaign createCampaign(Campaign campaign);
    Product serveAd(String category);
}
