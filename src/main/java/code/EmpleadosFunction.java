package code;

import DTOs.Empleado;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import connection.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpleadosFunction implements RequestHandler<Map<String, String>, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final Map<String, String> input, final Context context) {

        Gson gson = new Gson();
        String output = "";
        List<Empleado> listEmps = new ArrayList<>();

        // Configurar el header para la respuesta tipo JSON:
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        String nombreDepartamento = input.get("nombre");    // Entrada del nombre introducido por el usuario.

        //inicializo la variable con la que voy a responder con el header
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        //y lo añado al formato fijado:
//        String output = String.format("{ \"message\": \"Departamento\": " + nombreDepartamento + ", \"Lista de empleados\": ");

        try (Connection miCon = ConexionBD.conectar()) {

            PreparedStatement pstmt = miCon.prepareStatement("SELECT * FROM Departamento where nombre like ?");
            pstmt.setString(1, nombreDepartamento);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                // Recoger el id del departamento.
                int idDepto = rs.getInt("id");

                // Recoger los empleados del depto con el id seleccionado.
                PreparedStatement pstmt2 = miCon.prepareStatement("SELECT * FROM Empleado WHERE departamento_id = ?");
                pstmt2.setInt(1, idDepto);

                // OBTENER NOMBRE DEL COMERCIAL --------
                ResultSet rs2 = pstmt2.executeQuery();
                while (rs2.next()) {

                    String nomEmpleado = rs2.getString("nombre");
                    String apeEmpleado = rs2.getString("apellidos");

                    Empleado empleado = new Empleado(nomEmpleado, apeEmpleado, idDepto);
                    listEmps.add(empleado);


                }
                //almaceno en un String el Json con la información a enviar
                final String bodyContent = gson.toJson(listEmps);
                output = String.format("{ \"servicio\": \"Empleados de un departamento\", \"listaEmpleados\": \"%s\" }", bodyContent);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //devuelvo la variable APIGatewayProxyResponseEvent
        return response
                .withStatusCode(200) //si va bien devuelvo el código de OK 200
                        .

                withBody(output); //y en el body el json con la información requerida en el formato acordado.


    }

}
