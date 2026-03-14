package umg.edu.gt.servise;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import umg.edu.gt.model.Transaccion;

/**
 * Servicio encargado de la comunicación externa con la nube.
 * Actúa como un cliente REST para persistir los datos procesados en AWS.
 */
@Service // Define esta clase como un servicio gestionado por Spring
public class ApiService {

    // Punto de enlace (Endpoint) de la API Gateway en AWS donde se guardan los datos
    private final String POST_URL = "https://7e0d9ogwzd.execute-api.us-east-1.amazonaws.com/default/guardarTransacciones";
    
    private final RestTemplate restTemplate;

    /**
     * Constructor que inyecta el RestTemplate configurado en ApiConfig.
     */
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Envía una transacción enriquecida a la API de la nube.
     * @param t Transacción que ya incluye Nombre, Carnet y Correo.
     * @return true si la nube respondió con éxito (HTTP 200/201), false si hubo error.
     */
    public boolean enviarANube(Transaccion t) {
        try {
            // 1. Empaquetamos el objeto Transaccion en una entidad HTTP (Request Body)
            HttpEntity<Transaccion> request = new HttpEntity<>(t);
            
            // 2. Realizamos la petición POST de forma sincrónica hacia AWS
            var response = restTemplate.postForEntity(POST_URL, request, String.class);
            
            // 3. Verificamos si el código de estado es de la familia 200 (Éxito)
            return response.getStatusCode().is2xxSuccessful();
            
        } catch (Exception e) {
            // Si la nube está caída, no hay internet o el formato es inválido, capturamos el error
            System.err.println("⚠️ Error al conectar con AWS: " + e.getMessage());
            return false; // Retornamos false para que el ReceiverService NO dé el ACK
        }
    }
}