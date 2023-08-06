package quoters.screensaver;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import util.Pair;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.*;

public class PeriodicalScopeConfigurer implements Scope {
    private final Map<String, Pair<LocalTime, Object>> map = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        if (map.containsKey(name)) {
            Pair<LocalTime, Object> pair = map.get(name);
            int secondSinceLastRequest = now().getSecond() - pair.getKey().getSecond();
            if (secondSinceLastRequest > 3) {
                map.put(name, new Pair<>(now(), objectFactory.getObject()));
            }
        } else {
            map.put(name, new Pair<>(now(), objectFactory.getObject()));
        }
        return map.get(name).getValue();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
