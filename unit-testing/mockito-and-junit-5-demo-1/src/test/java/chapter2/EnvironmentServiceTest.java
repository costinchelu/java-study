package chapter2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentServiceTest {

    EnvironmentService service = new EnvironmentService();

    @Test
    void returns_NULL_when_environment_not_configured() {
        assertNull(service.getEnvironmentType("xyz"));
    }

    @Test
    void production_environment_configured() {
        EnvironmentType environmentType = service.getEnvironmentType("prod");
        assertThat(environmentType).isEqualTo(EnvironmentType.PROD);
    }
}