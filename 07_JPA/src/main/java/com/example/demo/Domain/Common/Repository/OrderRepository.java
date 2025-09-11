package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Order;
import com.example.demo.Domain.Common.Entity.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

// OrderId(복합키 설정을 했기 때문에
public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
