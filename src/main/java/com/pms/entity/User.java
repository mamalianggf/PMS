package com.pms.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private  int id;
    private  String name;
    private  String pwd;
}
