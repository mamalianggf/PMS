package com.pms.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutIn {
    public int id;
    public String name;
    public String phone;
    public String license;
    public String startTime;
    public String endTime;
    public int type;
}
