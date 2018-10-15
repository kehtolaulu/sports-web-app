package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

public class ConfigSingleton {
    private static Configuration cfg;

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_20);
            cfg.setServletContextForTemplateLoading(
                    sc,
                    "/WEB-INF/templates"
            );

            cfg.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return cfg;
    }
}
