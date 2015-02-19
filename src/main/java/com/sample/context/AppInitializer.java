package com.sample.context;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.register(
                AppContext.class,
                WebContext.class,
                WebSocketContext.class,
                SecurityContext.class,
                SchedulingContext.class,
                RawWebSocketContext.class
        );
        applicationContext.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        addEncodingFilter(servletContext);
        addSecurityFilter(servletContext);

        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);
    }

    private void addEncodingFilter(ServletContext container) {
        FilterRegistration.Dynamic filter = container.addFilter("encodingFilter", new CharacterEncodingFilter());
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, true, "/*");
        filter.setAsyncSupported(true);
    }

    private void addSecurityFilter(ServletContext context) {
        FilterRegistration.Dynamic filter = context.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        filter.addMappingForUrlPatterns(null, true, "/*");
    }
}
