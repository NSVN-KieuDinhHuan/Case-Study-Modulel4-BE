package com.codegym.case_study_m4.model.dto;

import com.codegym.case_study_m4.model.PaymentCategory;
import com.codegym.case_study_m4.model.Wallet;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentForm {
    private Long id;
    private Double amount;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date date;
    private MultipartFile image;
    private PaymentCategory paymentCategory;
    private Wallet wallet;
}
