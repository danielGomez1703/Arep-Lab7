/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.ieti.secureservice;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author Jairo Gomez
 */
public class SecureServiceApp {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "arep1234", "keystores/myTrustStore", "arep1234");
        get("/app", (request, response) -> homeView(request,response));

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
                + " se encuentra en el Host de servicios "
                + "<hr/>"
                + "<h2> Usted esta ahora en el canal de servios ECI </h2>" 
                + " <button type=\"button\" class=\"btn btn-lg btn-primary\" disabled>Prueba Terminada Lab 7</button>"
                + "  <br>"
                + "  <br><br>"
                + "<center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     *
     * @return Retorna el puerto indicado por el entorno, en caso de no
     * encontrarlo retorna el puerto 4567 por defecto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }
}
