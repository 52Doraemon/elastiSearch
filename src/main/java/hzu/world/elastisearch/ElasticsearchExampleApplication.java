package hzu.world.elastisearch;

import hzu.world.elastisearch.domain.Article;
import hzu.world.elastisearch.service.ElasticsearchService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticsearchExampleApplication implements CommandLineRunner {

    @Autowired
    private ElasticsearchService elasticsearchService;

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 创建索引
        String index = "articles";
//        elasticsearchService.createIndex(index);

        // 插入文章
        Article article = new Article("1", "Elasticsearch tutorial", "Learn how to use Elasticsearch with Spring Boot");
        IndexResponse indexResponse = elasticsearchService.insertDocument(index, article.getId(), article);
        System.out.println("Inserted article with ID: " + indexResponse.getId());

        // 搜索文章
        String searchField = "title";
        String searchValue = "Elasticsearch";
        SearchResponse searchResponse = elasticsearchService.searchDocument(index, searchField, searchValue);
        System.out.println("Search results:");
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(" - " + hit.getSourceAsString());
        }

        // 删除文章
//        elasticsearchService.deleteDocument(index, article.getId());
//        System.out.println("Deleted article with ID: " + article.getId());
    }
}
