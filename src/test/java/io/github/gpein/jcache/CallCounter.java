package io.github.gpein.jcache;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class CallCounter {


    private int calls = 0;

    public void count(@Observes Call call) {
        calls++;
    }

    public int calls() {
        return calls;
    }

    public void reset() {
        calls = 0;
    }
}
