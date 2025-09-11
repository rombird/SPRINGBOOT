package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderId2 {

    private Long orderId;
    private Long productId;

}
