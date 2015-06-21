package jgs.bluemix.sample;

import jgs.bluemix.sample.entity.BaseEntity;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * Database、およびO/R Mapper関連の設定を行うJavaConfigです.
 *
 * @author ryozo
 */
@MapperScan("jgs.bluemix.sample.persistence")
@Configuration
public class DataConfig {

    @Autowired
    DataSourceProperties dataSourceProperties;

    /**
     * {@link DataSource}を取得します。
     * @return DataSource
     */
    @Bean
    DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create(this.dataSourceProperties.getClassLoader())
                .url(this.dataSourceProperties.getUrl())
                .username(this.dataSourceProperties.getUsername())
                .password(this.dataSourceProperties.getPassword());
        return builder.build();
    }

    /**
     * 初期設定済の{@link SqlSessionFactoryBean}を取得します。
     * @return 初期設定済のSqlSessionFactoryBean
     */
    @Bean
    SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        return bean;
    }

}
