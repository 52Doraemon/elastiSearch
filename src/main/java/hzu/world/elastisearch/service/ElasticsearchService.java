package hzu.world.elastisearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ElasticsearchService {

    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public ElasticsearchService(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    // 创建索引
    public CreateIndexResponse createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);

        // 根据需要，配置索引的设置
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );

        return restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }




    // 插入文档
    public IndexResponse insertDocument(String index, String id, Object document) throws IOException {
        IndexRequest request = new IndexRequest(index).id(id).source(objectMapper.convertValue(document, Map.class));
        return restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    // 搜索文档
    public SearchResponse searchDocument(String index, String field, String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field, value));
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    // 删除文档
    public DeleteResponse deleteDocument(String index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }
}
