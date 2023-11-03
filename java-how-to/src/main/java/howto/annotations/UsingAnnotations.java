package howto.annotations;

import java.lang.annotation.*;

/**
 * Java annotations can be placed above classes, interfaces, methods, method
 * parameters, fields and local variables
 *
 * Java comes with three built-in annotations which are used to give the Java
 * compiler instructions. These annotations are:
 *
 * @Deprecated
 * @Override
 * @SuppressWarnings
 * @Contended
 *
 *
 */
@MyAnnotation(
        value="123",
        name="CC",
        newNames={"John", "Jill"}
)
public class UsingAnnotations {

    public void usingAnnotations() {
        Class<UsingAnnotations> aClass = UsingAnnotations.class;
        Annotation[] annotations = aClass.getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }

}


