package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ui.ProfileUI;

import java.util.Scanner;


@Configuration
public class SpringConfig {
    @Bean(name = "scannerNum")
    public Scanner scannerNum() {
        return new Scanner(System.in);
    }

    @Bean(name = "scannerStr")
    public Scanner scannerStr() {
        return new Scanner(System.in);
    }

    @Bean
    public SessionFactory sessionFactory(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate-config.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        return meta.getSessionFactoryBuilder().build();
    }

    @Bean
    public ProfileUI profileUI(){
        return new ProfileUI();
    }
}
