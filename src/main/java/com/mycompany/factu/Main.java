/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.factu;
import com.google.gson.Gson;
import com.mycompany.factu.responses.Constancia;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Evotek
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        FactuData factuData = new FactuData();
        String constancias = factuData.getConstanciasWeb();

        // Crear gson, nos ayuda a convertir string a un objeto de java, para poder acceder a sus atributos mas facilmente
        Gson gson = new Gson();
        Constancia[] constanciasMsg = gson.fromJson(constancias, Constancia[].class);
        // Establecer constancias obtenidas de FactuData Web
        factuData.setConstancias(constanciasMsg);
        // Llamar al metodo del SAT
        factuData.getConstanciasSAT();
    }
}
