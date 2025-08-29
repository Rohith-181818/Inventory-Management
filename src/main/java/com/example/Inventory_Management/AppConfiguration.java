package com.example.Inventory_Management;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableCaching
@EnableTransactionManagement
public class AppConfiguration {
}
