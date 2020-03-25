package com.pms.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private int userId;
    private String stall;
    private String license;
    private String details;
}
