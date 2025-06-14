package was.v7;

import was.httpserver.HttpServer;
import was.httpserver.HttpServlet;
import was.httpserver.Mapping.AnnotationServletV1;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;

import java.io.IOException;
import java.util.List;

public class ServerMain7 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

        List<Object> controllers = List.of(new SiteControllerV7(), new SearchControllerV7());
        HttpServlet annotationServlet = new AnnotationServletV1(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();

    }
}
