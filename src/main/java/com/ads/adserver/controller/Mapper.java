package com.ads.adserver.controller;

import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;
import com.ads.adserver.dto.CampaignDTO;
import com.ads.adserver.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public CampaignDTO toDto(Campaign campaign) {
        return new CampaignDTO(campaign.getId(), campaign.getName(), campaign.getStartDate(), campaign.getBid());
    }

    public Campaign toCampaign(CampaignDTO campaignDTO) {
        return new Campaign(campaignDTO.getId(), campaignDTO.getName(), campaignDTO.getStartDate(), campaignDTO.getBid());
    }

    public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(product.getId(), product.getTitle(), product.getCategory(), product.getSerialNumber(), product.getPrice());
    }

}