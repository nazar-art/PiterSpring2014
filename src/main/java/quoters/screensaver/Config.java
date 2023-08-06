package quoters.screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "quoters.screensaver")
public class Config {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Bean
    @Scope("periodical")
    public Color color() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame frame() {
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
