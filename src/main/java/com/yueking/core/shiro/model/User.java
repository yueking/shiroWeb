package com.yueking.core.shiro.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    String username;
    String password;
}
