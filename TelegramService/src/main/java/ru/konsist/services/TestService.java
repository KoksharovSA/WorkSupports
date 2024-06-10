package ru.konsist.services;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void testMessage(String mes){
        System.out.println(mes);
    }
}
