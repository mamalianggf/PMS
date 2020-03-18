package com.pms.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
    private int id;
    private String textMoney;
    private String valueMoney;
    private String tax;
    private String comment;
    private String payerId;
    private String payeeId;
    private String createTime;
}
