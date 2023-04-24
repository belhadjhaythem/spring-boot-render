package com.example.demo.model;

import com.example.demo.enumeration.PurchaseStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Achat {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne()
    @JoinColumn(name = "architect_id", referencedColumnName = "id")
    private Architect architect;

    @OneToOne()
    @JoinColumn(name = "gift_id", referencedColumnName = "id")
    private Gift gift;

    private LocalDateTime date;

    private PurchaseStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Architect getArchitectId() {
        return architect;
    }

    public void setArchitectId(Architect architectId) {
        this.architect = architectId;
    }

    public Gift getGiftId() {
        return gift;
    }

    public void setGiftId(Gift giftId) {
        this.gift = giftId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
}
