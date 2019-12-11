package com.design.pattern.services;

import com.design.pattern.strategy.interfaces.QueryPorcesser;
import lombok.Data;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class QueryBizService {
    private List<QueryPorcesser> queryPorcesserList;
    public Map<String,String > query(String type){
        Map<String, String> request=new HashMap<>();
        Map<String, String> result=new HashMap<>();
        request.put("type",type);
        queryPorcesserList.forEach(f->{
            if(f.check(request,result)){
                f.handle(request,result);
            }
        });
        return result;
    }
}
