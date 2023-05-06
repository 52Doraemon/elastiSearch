package hzu.world.elastisearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在Elasticsearch中存储文章信息
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String id;
    private String title;
    private String content;

    // 构造函数、getter、setter省略
}
