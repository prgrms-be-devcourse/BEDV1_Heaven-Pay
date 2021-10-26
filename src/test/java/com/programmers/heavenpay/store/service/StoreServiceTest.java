package com.programmers.heavenpay.store.service;

import com.programmers.heavenpay.store.converter.StoreConverter;
import com.programmers.heavenpay.store.entity.Store;
import com.programmers.heavenpay.store.entity.vo.StoreType;
import com.programmers.heavenpay.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest(classes = StoreService.class)
class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    StoreConverter storeConverter;

    @Test
    @DisplayName("store를 삽입할 수 있다.")
    void createtest(){
        //given
        String name = "store1";
        String typeStr = "식품";
        String vendorCode = UUID.randomUUID().toString();
        StoreType storeType = StoreType.FOOD;

        Store store = Store.builder()
                .type(storeType)
                .vendorCode(vendorCode)
                .name(name)
                .build();

        when(storeConverter.toStoreEntity(name, storeType, vendorCode)).thenReturn(store);
        when(storeRepository.save(store)).thenReturn(store);

        //when
        Long id = storeService.create(name, typeStr, vendorCode);

        //then
        assertThat(store.getId(), is(id));
    }
}