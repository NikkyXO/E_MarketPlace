package com.nikky.market.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {
    private String message;
}
