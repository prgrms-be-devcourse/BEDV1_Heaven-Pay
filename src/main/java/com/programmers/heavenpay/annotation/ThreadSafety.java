package com.programmers.heavenpay.annotation;

import java.lang.annotation.*;

/**
 * 불변객체로 생성한 class에 대해 ThreadSafety 표기
 */
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.CLASS)
public @interface ThreadSafety {
}
