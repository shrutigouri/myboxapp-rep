package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myboxapplication.myboxapp.codetype.PaymentMethod;
import com.myboxapplication.myboxapp.codetype.PaymentStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_payment_detail")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerPaymentDetail implements Serializable {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;

   /* @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_order_id",referencedColumnName = "customer_order_id")
    private CustomerOrder customerOrder;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method",nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_date")
    @CreatedDate
    private String paymentDate;

    @Column(name = "transaction_ref_num")
    private String transactionRefNum;

    @Column(name = "payment_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "card_token")
    private int cardToken;

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

	public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionRefNum() {
        return transactionRefNum;
    }

    public void setTransactionRefNum(String transactionRefNum) {
        this.transactionRefNum = transactionRefNum;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getCardToken() {
        return cardToken;
    }

    public void setCardToken(int cardToken) {
        this.cardToken = cardToken;
    }
}
