package com.ads.adserver.service;

import com.ads.adserver.controller.AdServerException;
import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;

import java.util.List;

public interface IAdService {
    Campaign createCampaign(Campaign campaign, List<Long> productIds) throws AdServerException;
    Product serveAd(String category);
}
