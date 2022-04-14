package com.codegym.case_study_m4.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date date;
    private String image;
    @ManyToOne
    private PaymentCategory paymentCategory;
    @ManyToOne
    private  Wallet wallet;
}
