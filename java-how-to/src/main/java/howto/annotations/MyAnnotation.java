package howto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention - This is what signals to the Java compiler and JVM that
 * the annotation should be available via howto.reflection at runtime.
 *
 * RetentionPolicy.CLASS means that the annotation is stored in the .class file, but not available at runtime.
 * This is the default retention policy, if you do not specify any retention policy at all.
 *
 * RetentionPolicy.SOURCE means that the annotation is only available in the source code,
 * and not in the .class files and not a runtime.
 * If you create your own annotations for use with build tools that scan the code, you can use this retention policy.
 * That way the .class files are not polluted unnecessarily.
 *
 * @Target - You can specify which Java elements your custom annotation can be used to annotate.
 *
 * @Inherited - signals that a custom Java annotation used in a class should be inherited by subclasses inheriting from that class.
 *
 * @Documented annotation is used to signal to the JavaDoc tool that your custom annotation
 * should be visible in the JavaDoc for classes using your custom annotation.
 *
 * An annotation type with no elements is termed a marker annotation type
 *
 * In annotations with a single element, the element should be named value.
 * It is permissible to omit the element name and equals sign (=) in a single-element annotation whose element name is value
 *
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {

    String value() default "";

    String name() default "";

    int age() default 1;

    String[] newNames();

}
