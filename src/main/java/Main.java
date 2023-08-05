import org.springframework.context.support.ClassPathXmlApplicationContext;
import quoters.Quoter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Quoter.class).sayQuote();

        while (true) {
            Thread.sleep(1_000);
            context.getBean(Quoter.class).sayQuote();
        }
    }
}
