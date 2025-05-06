package was.httpserver.Mapping;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD )
@Documented
public @interface Mapping {
    String value();

}
