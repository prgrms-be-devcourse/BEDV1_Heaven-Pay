package com.programmers.heavenpay.store.entity;

import com.programmers.heavenpay.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Store extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false, length = 100)
    private String name;

    @Column(name = "store_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType type;

    @Column(name = "store_vendor_code", nullable = false)
    private String vendorCode;

    public void changeName(String name){
        this.name = name;
    }

    public enum StoreType{
        //TODO: 구현
    }
}