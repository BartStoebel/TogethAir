package com.realdolmen.course.domain;

import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.PaymentChoice;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Booking objects will be the complete booking of one user. This will have 1 payment but possibly more tickets.
 * @author JBCBF07
 */

@Entity
public class Booking implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0) @Column(nullable = false)
    private BigDecimal finalPrice;

    @Min(0)
    private BigDecimal discountVolume;

    @Min(0)
    private BigDecimal discountCC;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private PaymentChoice paymentChoice;

    @Temporal(TemporalType.TIMESTAMP) @Column(nullable = false)
    private Date createdOn;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToMany (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Version
    private Integer version;

    public Booking() {
    }

    public Booking(BigDecimal finalPrice, BigDecimal discountVolume, BigDecimal discountCC, PaymentChoice paymentChoice, Date createdOn, User user, List<Ticket> tickets, BookingStatus bookingStatus) {
        this.finalPrice = finalPrice;
        this.discountVolume = discountVolume;
        this.discountCC = discountCC;
        this.paymentChoice = paymentChoice;
        this.createdOn = createdOn;
        this.user = user;
        this.tickets = tickets;
        this.bookingStatus = bookingStatus;
    }

    // Start Methods

    /**
     * Will calculate and return the final price without the discounts that had been applied before.
     * @return BigDecimal
     */
    public BigDecimal calcFinalPriceWithoutDiscounts(){
        BigDecimal dc = finalPrice;
        if (paymentChoice == PaymentChoice.CREDIT_CARD && discountCC != null) dc = dc.add(discountCC);
        if (discountVolume != null) dc = dc.add(discountVolume);
        return dc;
    }

    public void addTicket(Ticket t){
        tickets.add(t);
    }

    // End Methods


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setDiscountVolume(BigDecimal discountVolume) {
        this.discountVolume = discountVolume;
    }

    public void setDiscountCC(BigDecimal discountCC) {
        this.discountCC = discountCC;
    }

    public void setPaymentChoice(PaymentChoice paymentChoice) {
        this.paymentChoice = paymentChoice;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public BigDecimal getDiscountVolume() {
        return discountVolume;
    }

    public BigDecimal getDiscountCC() {
        return discountCC;
    }

    public PaymentChoice getPaymentChoice() {
        return paymentChoice;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public User getUser() {
        return user;
    }

    public Integer getVersion() {
        return version;
    }
}
