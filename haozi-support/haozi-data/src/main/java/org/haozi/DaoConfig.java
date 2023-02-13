package org.haozi;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.MapSearcher;
import com.ejlchina.searcher.SearcherBuilder;
import com.ejlchina.searcher.SqlExecutor;
import com.ejlchina.searcher.convertor.NumberFieldConvertor;
import com.ejlchina.searcher.implement.DefaultSqlExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/10 14:08
 */
@Configuration
public class DaoConfig {


    @Bean
    public MapSearcher MapSearcher(DataSource dataSource){
        // DefaultSqlExecutor 也支持多数据源
        SqlExecutor sqlExecutor = new DefaultSqlExecutor(dataSource);

        MapSearcher mapSearcher = SearcherBuilder.mapSearcher()
                .sqlExecutor(sqlExecutor)
                .build();

        return mapSearcher;
    }
    @Bean
    public BeanSearcher beanSearcher(DataSource dataSource){
        // DefaultSqlExecutor 也支持多数据源
        SqlExecutor sqlExecutor = new DefaultSqlExecutor(dataSource);

        BeanSearcher beanSearcher = SearcherBuilder.beanSearcher()
                .sqlExecutor(sqlExecutor)
                .build();

        return beanSearcher;
    }

    @Bean
    public NumberFieldConvertor numberFieldConvertor() {
        return new NumberFieldConvertor();
    }

}
