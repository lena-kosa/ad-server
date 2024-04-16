package com.ads.adserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {
    private Long id;
    private String name;
    private Instant startDate;
    private BigDecimal bid;
    private List<Long> products;

    public CampaignDTO(Long id, String name, Instant startDate, BigDecimal bid) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.bid = bid;
    }
}
