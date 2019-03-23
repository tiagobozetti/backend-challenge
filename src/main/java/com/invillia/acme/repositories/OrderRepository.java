package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
