package umg.edu.gt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase de configuración para servicios externos vía HTTP.
 */
@Configuration // Indica que esta clase define beans de configuración para el proyecto
public class ApiConfig {

    /**
     * Crea y registra un bean de RestTemplate en el contenedor de Spring.
     * RestTemplate es la librería cliente que permite realizar peticiones HTTP 
     * (GET, POST, etc.) de forma sencilla hacia APIs REST externas.
     * * @return una instancia lista para ser usada en ApiService.
     */
    @Bean
    public RestTemplate restTemplate() {
        // Retorna la implementación estándar para el intercambio de datos vía HTTP
        return new RestTemplate();
    }
}