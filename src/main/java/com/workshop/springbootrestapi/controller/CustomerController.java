package com.workshop.springbootrestapi.controller;

import com.workshop.springbootrestapi.model.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    public Customer create(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customers;
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{email}")
    public Customer update(@PathVariable String email, @RequestBody Customer updated) {
        Customer customer = getByEmail(email);

        customer.setName(updated.getName());
        customer.setAge(updated.getAge());
        customer.setAddress(updated.getAddress());

        return customer;
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}
