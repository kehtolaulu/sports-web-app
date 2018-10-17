package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Helper {
    public static void render(ServletContext sc, HttpServletResponse resp, String path, Map<String, Object> root) throws IOException {
        resp.setContentType("text/html");
        Configuration cfg = ConfigSingleton.getConfig(sc);
        Template template = cfg.getTemplate(path);

        try {
            template.process(root, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            resp.sendError(500);
        }
        resp.getWriter().close();
    }
}