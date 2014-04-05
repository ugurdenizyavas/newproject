package com.deniz.newstock.piece.persistence.entity;

import com.deniz.framework.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: TRYavasU
 */
@Entity
@Table(name = "TRANSACTION_ENTITY", uniqueConstraints = {})
@SequenceGenerator(name = "ONE_SEQUENCE_PER_ENTITY_GENERATOR", sequenceName = "TRANSACTION_SEQUENCE")
public class TransactionEntity extends AbstractEntity {

    private OwnerEntity seller;
    private OwnerEntity buyer;
    private PieceEntity piece;
    private Date completed = new Date();
    private double value;

    public TransactionEntity(OwnerEntity seller, OwnerEntity buyer, PieceEntity piece, double value) {
        this.seller = seller;
        this.buyer = buyer;
        this.piece = piece;
        this.value = value;
    }

    public TransactionEntity() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    public OwnerEntity getSeller() {
        return seller;
    }

    public void setSeller(OwnerEntity seller) {
        this.seller = seller;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BUYER_ID", nullable = false)
    public OwnerEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(OwnerEntity buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PIECE_ID", nullable = false)
    public PieceEntity getPiece() {
        return piece;
    }

    public void setPiece(PieceEntity piece) {
        this.piece = piece;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    @Column
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
