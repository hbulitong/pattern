package com.design.pattern.strategy.impl;

import com.design.pattern.strategy.interfaces.QueryPorcesser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
@Component
public class QueryCProcesser implements QueryPorcesser {
    @Override
    public boolean check(Map<String, String> request, Map<String, String> result) {
        if("c".equals((String)request.get("type")) || Objects.isNull(request.get("type"))){
            return true;
        }
        return false;
    }

    @Override
    public void handle(Map<String, String> request, Map<String, String> result) {
        result.put("c","item_C");
    }
}
