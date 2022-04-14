package com.codegym.case_study_m4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletForm {
    private Long id;
    @NotEmpty(message = "Không được phép để trống")
    @Size(min = 5, max = 30, message = "Tên ví phải từ 5 tới 30 ký tự")
    private String name;
    private Double currentAmount;
    @NotBlank
    private MultipartFile icon;
    private User user;
}
