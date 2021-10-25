package com.programmers.heavenpay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 해당 어노테이션을 통해 사용자의 ID 또는 Email을 Security 대용임을 알려주도록 사용
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArbitraryAuthenticationPrincipal {
}
