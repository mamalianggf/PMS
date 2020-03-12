package com.pms.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {
    private int id;
    private String intro;
    private String details;
    private int creatorId;
    private String createTime;
    private int status;
}
