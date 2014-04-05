package com.deniz.newstock.piece.persistence.entity;

import com.deniz.framework.persistence.entity.AbstractEntity;

import javax.persistence.*;

/**
 * @author: TRYavasU
 */
@Entity
@Table(name = "PIECE_ENTITY", uniqueConstraints = @UniqueConstraint(columnNames = {"COUNTRY_ID", "countryOrder"}))
@SequenceGenerator(name = "ONE_SEQUENCE_PER_ENTITY_GENERATOR", sequenceName = "PIECE_SEQUENCE")
public class PieceEntity extends AbstractEntity {

    private OwnerEntity owner;
    private CountryEntity country;
    private int countryOrder;
    private double value = 0d;

    public PieceEntity(OwnerEntity owner, CountryEntity country, int countryOrder) {
        this.owner = owner;
        this.country = country;
        this.countryOrder = countryOrder;
    }

    public PieceEntity() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    @Column(nullable = false)
    public int getCountryOrder() {
        return countryOrder;
    }

    public void setCountryOrder(int countryOrder) {
        this.countryOrder = countryOrder;
    }

    @Column
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Transient
    public boolean isOnSell() {
        return value != 0d;
    }
}
