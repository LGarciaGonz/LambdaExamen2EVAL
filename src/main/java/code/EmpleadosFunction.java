package code;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class EmpleadosFunction implements RequestHandler<Map<String, String>, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final Map<String, String> input, final Context context) {

        // Configurar el header para la respuesta tipo JSON:
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        String nombreEmp = input.get("nombre");
        String apellidoEmp = input.get("apellido");

        //inicializo la variable con la que voy a responder con el header
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        //almaceno en un String el Json con la información a enviar
        final String bodyContent = "Nombre " + nombreEmp + "Apellido " + apellidoEmp;

        //y lo añado al formato fijado:
        String output = String.format("{ \"message\": \"Empleado\", \"details\": \"%s\" }", bodyContent);


        //devuelvo la variable APIGatewayProxyResponseEvent
        return response
                .withStatusCode(200) //si va bien devuelvo el código de OK 200
                .withBody(output); //y en el body el json con la información requerida en el formato acordado.

    }

}
