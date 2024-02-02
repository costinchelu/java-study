package module01.question10.scopes.web;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration;

import jakarta.servlet.ServletContext;

public class AnnotationConfigWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(container);
        context.register(ApplicationConfiguration.class);
        context.refresh();

        ServletRegistration.Dynamic dispatcher =
                (ServletRegistration.Dynamic) container.addServlet("dispatcher", new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
