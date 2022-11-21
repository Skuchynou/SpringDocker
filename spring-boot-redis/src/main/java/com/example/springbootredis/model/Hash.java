package com.example.springbootredis.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hash implements Serializable {

    private String string;
    private String hash;
}
