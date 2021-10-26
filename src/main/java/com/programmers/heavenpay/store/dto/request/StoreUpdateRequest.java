package com.programmers.heavenpay.store.dto.request;

import com.programmers.heavenpay.annotation.ArbitraryAuthenticationPrincipal;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class StoreUpdateRequest {
    @NotNull
    @ArbitraryAuthenticationPrincipal
    @Min(value = 1)
    private Long id;

    @Pattern(regexp = "\\S{2,10}", message = "store name은 공백없는 2~10자이어야 합니다")
    private String name;

    @NotBlank(message = "store type validation fail")
    private String type;

    @Pattern(regexp = "[a-zA-Z1-9]{6,12}", message = "vendor code validation fail")
    private String vendorCode;
}
