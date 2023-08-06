import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import quoters.PropertyFileApplicationContext;
import quoters.Quoter;
import quoters.screensaver.ColorFrame;
import quoters.screensaver.Config;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // use three phases of constructor
        // use profiling - which could be changed with visual VM or JMX console (jconsole)
        // create new bean instance from @DeprecatedClass annotation
        useXmlContextResource();

        // inject prototype bean to singleton + use custom scope implementation - periodical
        // showColorFrame();

        // build context from properties file
        //buildContextFromPropertiesFile("context.properties");
    }

    private static void useXmlContextResource() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Quoter.class).sayQuote();
        while (true) {
            Thread.sleep(1_000);
            context.getBean(Quoter.class).sayQuote();
        }
    }

    private static void showColorFrame() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    private static void buildContextFromPropertiesFile(String fileName) {
        ApplicationContext context = new PropertyFileApplicationContext(fileName);
        context.getBean(Quoter.class).sayQuote();
    }
}
