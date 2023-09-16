package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerMapper {
    void insertCustomer(Customer c);

    List<String> selectCustomerNameByName(String name);

    Customer selectCustomerByName(String customerName);
}
