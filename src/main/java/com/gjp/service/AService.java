package com.gjp.service;

import org.springframework.stereotype.Service;

@Service
public class AService {

    private String name = "test";

    public Integer getNum(){
        return 99;
    }
}
