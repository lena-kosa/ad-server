package com.ads.adserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {
    private Long id;

    @NotBlank(message = "Campaign name should be provided")
    private String name;

    @NotNull(message = "Campaign start date should be provided")
    @FutureOrPresent(message = "Campaign start date should be today or in the future")
    private Instant startDate = Instant.now();

    @PositiveOrZero(message = "Campaign bid should be non negative number")
    private BigDecimal bid;

    @NotEmpty(message = "Campaign should contain products")
    private List<Long> products;
}
