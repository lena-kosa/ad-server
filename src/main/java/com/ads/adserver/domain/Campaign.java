package com.ads.adserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant startDate;

    @Column(nullable = false)
    private BigDecimal bid;

    @ManyToMany
    @JoinTable(
            name="campaign_product",
            joinColumns=@JoinColumn(name="campaign_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="product_id", referencedColumnName="id"))
    private List<Product> products = new ArrayList<>();

    public Campaign(Long id, String name, Instant startDate, BigDecimal bid) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.bid = bid;
    }
}
