package com.design.pattern.strategy.interfaces;

import java.util.Map;

public interface QueryPorcesser {
    boolean check(Map<String, String> request, Map<String, String> result);

    void handle(Map<String, String> request, Map<String, String> result);


}
