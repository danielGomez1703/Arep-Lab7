/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.ieti.secureapp;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Filter;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author Jairo Gomez
 */
public class SecureSparkServiceApp {

    private static Map<String, String> usernamePasswords = new HashMap<>();

    public static void main(String[] args) {
        usernamePasswords.put("foo", "bar");
        usernamePasswords.put("admin", "admin");
        
        URLReader.init();

        port(getPort());
        secure("secureApp/keystores/ecikeystore.p12", "arep1234","secureApp/keystores/myTrustStore", "arep1234");

        get("/", (req, res) -> loginView(req, res));
        before("/hello", new Filter() {
            @Override
            public void handle(Request request, Response response) {
                String user = request.queryParams("user");
                String password = request.queryParams("password");

                String dbPassword = usernamePasswords.get(user);
                if (!(password != null && password.equals(dbPassword))) {
                    halt(401, "You are NOT welcome here!!!");
                }
            }
        });

        /*before((Filter) (Request request, Response response) -> {
            String user = request.queryParams("user");
            String password = request.queryParams("password");

            String dbPassword = usernamePasswords.get(user);
            if (!(password != null && password.equals(dbPassword))) {
                halt(401, "You are not welcome here!!!");
            }
        });*/
        get("/hello", (request, response) -> {
            return "Hello World!";
        });
        
        get("/home", (req, res) -> homeView(req, res));
        get("/service", (req, res) -> serviceView(req, res));

        after("/home", (request, response) -> {
            response.header("spark", "added by after-filter");
        });

        /*before("/app/*",  (req, res)  -> {
            System.out.println(req.session().attributes().size());
            System.out.println(req.session().id());
            System.out.println(req.session().isNew());
            req.session().attribute("test", "test");
        });*/
    }

    static String loginView(Request req, Response res) {

        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>"
                + "</head>"
                + "<body>"
                + "<center>"
                + "<h1>Laboratorio 7 </h1>"
                + "<h3> Laboratorio AWS y certificados SSL </h3>"
                + "<hr/>"
                + "<form action=\"/home\" method=\"get\">"
                + "<label>"
                + " Usuario : <br/>"
                + "  <input id=\"user\" type=\"text\" name=\"user\" placeholder=\"User\">"
                + "<br/>"
                + " <br/>"
                + "</label>"
                + " <br/>"
                + "<label> "
                + "Constraseña : <br/>"
                + "  <input id=\"password\" type=\"password\" name=\"password\" placeholder=\"Password\">"
                + "</label>"
                + "  <br>"
                + "  <br><br>"
                + "  <button  type=\"submit\"  class=\"btn btn-success\"> Ingresar </button>"
                + "</form>"
                + "<p> al hacer click se validara la informacion del usuario. \"/home\".</p>"
                + "<center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    static String homeView(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>"
                + "</head>"
                + "<body>"
                + "<center>"
                + "<h1>Laboratorio 7 </h1>"
                + "<h3> Laboratorio AWS y certificados SSL </h3>"
                + "<hr/>"
                + "<form action=\"/service\" method=\"get\">"
                + "AUTENTICACION EXITOSA, VAYA AL BOTON PARA ACCEDER A LOS SERVICIOS"
                + "  <br>"
                + "  <br><br>"
                + "  <button  type=\"submit\"  class=\"btn btn-success\"> Ir a Servicios </button>"
                + "</form>"
                + "<center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    static String serviceView(Request req, Response res) {

        String url = "https://ec2-34-226-211-147.compute-1.amazonaws.com:46000/app";
        String view = URLReader.readURL(url);
        return view;

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
