import com.ecommerce.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ecommerce.ui.AuthUI;

public class Main {
    static void main() {
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate-config.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
//
//        factory.close();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        AuthUI ui = (AuthUI) context.getBean("authUI");
        ui.run();

    }
}
