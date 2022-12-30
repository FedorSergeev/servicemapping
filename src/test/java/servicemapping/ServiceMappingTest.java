package servicemapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceMappingTest {

    public static final String DOG_RESULT = "Вас укусили";

    @Test
    public void testMapping() {
        TypedServiceContainer serviceContainer = new TypedServiceContainer();

        serviceContainer.put(Dog.class, d -> {
            d.bite();
            return "Вас укусили";
        });
        serviceContainer.put(Cat.class, c -> c.sayMeow());

        Assertions.assertEquals(DOG_RESULT, serviceContainer.get(Dog.class).apply(new Dog()));
        Assertions.assertEquals("meow", serviceContainer.get(Cat.class).apply(new Cat()));
    }
}
