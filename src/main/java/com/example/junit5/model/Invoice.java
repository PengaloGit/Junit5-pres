package com.example.junit5.model;

import java.math.BigDecimal;

public record Invoice(Integer id, BigDecimal amount, Integer userId, boolean isRushDelivery) {

}
