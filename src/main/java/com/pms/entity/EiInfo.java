package com.pms.entity;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EiInfo {

    private int status;
    private String message;
    private int count;
    private List data;

}
