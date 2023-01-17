package com.mycompany.factu;

import com.mycompany.factu.responses.Constancia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class FactuData {

    Connection connection;
    Constancia[] constancias;

    FactuData() throws SQLException {
        Db db = new Db();
        connection = db.getConnection();
    }
    String getConstanciasSAT() throws IOException {
        /* Se hara una peticion al SATanico por cada constancia, estas constancias se encuentran en el arreglo Constancia[]
         constancias que se llenan con el metodo getConstanciasWeb */
        Constancia[] constanciaActual = getConstancias();
        // Establecer URL de la peticion
        URL url = new URL(constanciaActual[0].getLiga());
        // Hacer la peticion
        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        // Establecer el metodo de la peticion
        req.setRequestMethod("GET");
        // Establecer headers de la peticion
        req.setRequestProperty("Accept", "text/html");
        // Obtener el status de la peticion
        int status = req.getResponseCode();
        if (status == 200) {
            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // Cerrar la lectura
            in.close();
            // Parsear la respuesta
            parsearHTML(content.toString());
            //Cerrar la conexion
            req.disconnect();
            return content.toString();
        }
        // Cerrar la conexion
        req.disconnect();
        // Si la peticion nos regresa un status diferente al 200 regresar un string de error
        return "Ha ocurrido un error";
    }

    HashMap<String, String> parsearHTML(String constanciaHTML) {
        /* Crear un HashMap el cual nos almacenara los valores importantes del HTML, el beneficio del HashMap es que
        podemos acceder a un valor por medio de una clave, ex. hashMap.get('regimenFiscal') */
        HashMap<String, String> HTMLConvertido = new HashMap<>();
        // Definir claves para hace referencia a los valores que nos interesan
        String[] claves = {"nombre", "sociedadMercantil", "fechaConstitucion", "fechaInicioOperaciones", "situacionContribuyente", "fechaUltimoCambioSituacion"};
        /* Crear un documento en base al string obtenido de la peticion al SAT, en pocas palabras parseamos un string a un
        HTML, donde podremos acceder a elementos por medio del id o atributos, etc.*/
        Document document = Jsoup.parse(constanciaHTML);
        // Obtener la tabla de los datos que queremos obtener por medio del id
        Element tablaDatosIdentificacion = document.getElementById("ubicacionForm:j_idt11:0:j_idt12:j_idt15_data");
        // Obtener todos los tr de la tabla, para despues conseguir sus td y almacenarlos en el hashMap
        Elements tr = tablaDatosIdentificacion.getElementsByTag("tr");
        // Recorrer todos los elementos tr
        for (int i = 0; i < tr.size(); i++) {
            Elements td = tr.get(i).getElementsByTag("td");
            HTMLConvertido.put(claves[i], td.get(1).text().toString());
        }
        System.out.println(HTMLConvertido);
        return HTMLConvertido;
    }
    String getConstanciasWeb() throws IOException {
        // Establecer URL de la peticion
        URL url = new URL("http://factudata.com.mx/ajax/datoss.php");
        // Hacer la peticion
        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        // Establecer el metodo de la peticion
        req.setRequestMethod("GET");
        // Establecer headers de la peticion
        req.setRequestProperty("Accept", "application/json");
        // Obtener el status de la peticion
        int status = req.getResponseCode();
        if (status == 200) {
            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // Cerrar la lectura
            in.close();
            // Imprimir la respuesta
            return content.toString();
        }
        //Cerrar la conexion
        req.disconnect();
        // Si la peticion nos regresa un status diferente al 200 regresar un string de error
        return "Ha ocurrido un error";
    }

    Constancia[] getConstancias() {
        return constancias;
    }

    void setConstancias(Constancia[] constancias) {
        this.constancias = constancias;
    }
}
