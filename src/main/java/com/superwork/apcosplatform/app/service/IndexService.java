package com.superwork.apcosplatform.app.service;


import java.util.Map;

public interface IndexService {
    Map<String, Integer> getUserDataWithOther();

    Integer getCountBySum(String sum);

}
