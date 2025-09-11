package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@Entity
@Table(name="orders")
@IdClass(OrderId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    //OrderId 클래스의 자료형과 Order 클래스의 자료형과 일치해야함 - orderId, productId
    @Id
    private Long orderId;

    @Id
    private Long productId;

    private int quantity;
}


