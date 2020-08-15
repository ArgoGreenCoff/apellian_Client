package com.kinzr.apellian.configration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan("com.kinzr.apellian.entity.mapper")
public class DatabaseConfiguration {

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:mysql://172.31.27.205:3306/Apellian");
//		config.setJdbcUrl("jdbc:mysql://kinzr.com:3306/Apellian");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://110.10.130.63:6701/Apellian");
		config.setUsername("apellian");
//		config.setPassword("Spraut1$#@");
		config.setPassword("aP20160701.");
		config.addDataSourceProperty("serverTimezone", "Asia/Seoul");
		config.addDataSourceProperty("autoReconnect", "true");
		config.addDataSourceProperty("useSSL", "false");
		return config;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public JndiPropertyHolder getJndiPropertyHolder() {
		return new JndiPropertyHolder();
	}
	
	@Primary
	@Bean("dataSource")
	@Profile("jndi")
	public DataSource jndiDatasource() throws IllegalArgumentException, NamingException {
		String jndiName = getJndiPropertyHolder().getJndiName();
		return (DataSource) new JndiDataSourceLookup().getDataSource(jndiName);
	}
	
	@Bean("dataSource")
	@Profile("local")
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		//log.info("datasource : {}", dataSource);
		return dataSource;
	}
	
	@Bean(name ="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext)throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:com/kinzr/apellian/entity/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
 
    @Bean(name ="sqlSession")
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    class JndiPropertyHolder {
    	private String jndiName;
     
        public String getJndiName() {
          return jndiName;
        }
     
        public void setJndiName(String jndiName) {
          this.jndiName = jndiName;
        }
    }
}
