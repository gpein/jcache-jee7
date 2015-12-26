package io.github.gpein.jcache;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.jboss.weld.environment.se.Weld;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JCacheTest {

    @BeforeClass
    public void beforeClass() {
        new Weld().initialize();
        BeanProvider.getContextualReference(Cache.class).createCache();
    }

    @Test
    public void should_hit_cache() {

        CacheableService service = BeanProvider.getContextualReference(CacheableService.class);

        assertThat(service.get(1L, "1st call")).isEqualTo("1 1st call");
        assertThat(service.get(2L, "1st call")).isEqualTo("2 1st call");
        assertThat(service.get(1L, "2nd call")).isEqualTo("1 1st call");
        assertThat(service.get(1L, "3rd call")).isEqualTo("1 1st call");
        assertThat(service.get(2L, "2nd call")).isEqualTo("2 1st call");

        CallCounter callCounter = BeanProvider.getContextualReference(CallCounter.class);
        assertThat(callCounter.calls()).isEqualTo(2);

        service.removeAll();
        callCounter.reset();
    }

    @Test
    public void should_evict_cache() {

        CacheableService service = BeanProvider.getContextualReference(CacheableService.class);

        assertThat(service.get(1L, "1st call")).isEqualTo("1 1st call");
        assertThat(service.get(1L, "2nd call")).isEqualTo("1 1st call");
        assertThat(service.get(2L, "1st call")).isEqualTo("2 1st call");

        service.update(1L, "update");

        assertThat(service.get(1L, "3rd call")).isEqualTo("1 3rd call");
        assertThat(service.get(2L, "2nd call")).isEqualTo("2 1st call");

        CallCounter callCounter = BeanProvider.getContextualReference(CallCounter.class);
        assertThat(callCounter.calls()).isEqualTo(3);

        service.removeAll();
        callCounter.reset();
    }
}