package com.ads.adserver.controller;

import com.ads.adserver.domain.Campaign;
import com.ads.adserver.domain.Product;
import com.ads.adserver.dto.CampaignDTO;
import com.ads.adserver.dto.ProductDTO;
import com.ads.adserver.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class AdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    private final AdService adService;
    private final Mapper mapper;

    public AdController(AdService adService, Mapper mapper) {
        this.adService = adService;
        this.mapper = mapper;
    }

    @PostMapping("/campaign")
    public ResponseEntity<CampaignDTO> createCampaign(@Valid @RequestBody CampaignDTO campaignDTO) throws AdServerException {
        LOGGER.info("Create campaign {}", campaignDTO);
        Campaign createdCampaign = adService.createCampaign(mapper.toCampaign(campaignDTO), campaignDTO.getProducts());
        return new ResponseEntity<>(mapper.toDto(createdCampaign), HttpStatus.OK);
    }

    @GetMapping("/ad/{category}")
    public ResponseEntity<ProductDTO> serveAd(@NotNull @PathVariable String category) {
        LOGGER.info("Serve ad for {}", category);
        Product product = adService.serveAd(category);
        return new ResponseEntity<>(mapper.toDto(product), HttpStatus.OK);
    }

}

