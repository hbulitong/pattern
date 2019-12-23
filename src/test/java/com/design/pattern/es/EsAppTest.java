package com.design.pattern.es;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsAppTest  {
    @Autowired
    private RestHighLevelClient client;

    public static String INDEX_TEST = null;
    public static String TYPE_TEST = null;
    public static Tests tests = null;
    public static List<Tests> testsList = null;

    @BeforeClass
    public static void before() {
        INDEX_TEST = "index_test"; // 索引名称
        TYPE_TEST = "type_test"; // 索引类型
        testsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tests = new Tests();
            tests.setId(Long.valueOf(i));
            tests.setName("this is the test " + i);
            testsList.add(tests);
        }
    }

    @Test
    public void testIndex() throws IOException {
        // 判断是否存在索引
        if (!existsIndex(INDEX_TEST)) {
            // 不存在则创建索引
            createIndex(INDEX_TEST);
        }
        // 判断是否存在记录
        if (!exists(INDEX_TEST, TYPE_TEST, tests)) {
            // 不存在增加记录
            add(INDEX_TEST, TYPE_TEST, tests);
        }
        Tests tt=new Tests();
        tt.setId(Long.valueOf(1));
        tt.setName("com.abc.test.service");
        if(!exists(INDEX_TEST, TYPE_TEST, tt)){
            // 不存在增加记录
            add(INDEX_TEST, TYPE_TEST, tt);
        }
        add(INDEX_TEST, TYPE_TEST, tt);
        Map<String,Object> where=new HashMap<>();
        where.put("name","test");
        List<Map<String,Object>> ret=
        searchIndex(INDEX_TEST,0,10,where,null,null,null,60);
        System.out.println("hold...");


    }

    /**
     * 创建索引
     * @param index
     * @throws IOException
     */
    public void createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = client.indices().create(request,     RequestOptions.DEFAULT);
        System.out.println("createIndex: " + JSON.toJSONString(createIndexResponse));
    }

    /**
     * 判断索引是否存在
     * @param index
     * @return
     * @throws IOException
     */
    public boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("existsIndex: " + exists);
        return exists;
    }

    /**
     * 增加记录
     * @param index
     * @param type
     * @param tests
     * @throws IOException
     */
    public void add(String index, String type, Tests tests) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, tests.getId().toString());
        indexRequest.source(JSON.toJSONString(tests), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("add: " + JSON.toJSONString(indexResponse));
    }

    /**
     * 判断记录是都存在
     * @param index
     * @param type
     * @param tests
     * @return
     * @throws IOException
     */
    public boolean exists(String index, String type, Tests tests) throws IOException {
        GetRequest getRequest = new GetRequest(index, type, tests.getId().toString());
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists: " + exists);
        return exists;
    }


    /**
     * @param index
     * @param from
     * @param size
     * @param where
     * @param sortFieldsToAsc
     * @param includeFields
     * @param excludeFields
     * @param timeOut
     * @return
     */
    public List<Map<String, Object>> searchIndex(String index, int from, int size, Map<String, Object> where,
                                                 Map<String, Boolean> sortFieldsToAsc, String[] includeFields, String[] excludeFields,
                                                 int timeOut) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //条件
            if (where != null && !where.isEmpty()) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                where.forEach((k, v) -> {
                    if (v instanceof Map) {
                        //范围选择map  暂定时间
                        Map<String, Date> mapV = (Map<String, Date>) v;
                        if (mapV != null) {
                            boolQueryBuilder.must(
                                    QueryBuilders.rangeQuery(k).
                                            gte(format.format(mapV.get("start"))).
                                            lt(format.format(mapV.get("end"))));
                        }
                    } else {
                        //普通模糊匹配
                        //("*"+v.toString()+"*").toLowerCase()
                        boolQueryBuilder.must(QueryBuilders.wildcardQuery(k, ("*"+v.toString()+"*").toLowerCase()));
                    }
                });
                sourceBuilder.query(boolQueryBuilder);
            }

            //分页
            from = from <= -1 ? 0 : from;
            size = size >= 1000 ? 1000 : size;
            size = size <= 0 ? 15 : size;
            sourceBuilder.from(from);
            sourceBuilder.size(size);

            //超时
            sourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));

            //排序
            if (sortFieldsToAsc != null && !sortFieldsToAsc.isEmpty()) {
                sortFieldsToAsc.forEach((k, v) -> {
                    sourceBuilder.sort(new FieldSortBuilder(k).order(v ? SortOrder.ASC : SortOrder.DESC));
                });
            } else {
                sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            }

            //返回和排除列
            if (!CollectionUtils.isEmpty(includeFields) || !CollectionUtils.isEmpty(excludeFields)) {
                sourceBuilder.fetchSource(includeFields, excludeFields);
            }

            SearchRequest rq = new SearchRequest();
            //索引
            rq.indices(index);
            //各种组合条件
            rq.source(sourceBuilder);

            //请求
            System.out.println(rq.source().toString());
            SearchResponse rp = client.search(rq,RequestOptions.DEFAULT);

            //解析返回
            if (rp.status() != RestStatus.OK ) {
                return Collections.emptyList();
            }

            //获取source
            return Arrays.stream(rp.getHits().getHits()).map(b -> {
                return b.getSourceAsMap();
            }).collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }


}
