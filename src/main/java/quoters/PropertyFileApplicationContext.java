package quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertyFileApplicationContext extends GenericApplicationContext {

    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int beanCount = reader.loadBeanDefinitions(fileName);
        System.out.printf("Found %d beans\n", beanCount);
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext context = new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }
}
