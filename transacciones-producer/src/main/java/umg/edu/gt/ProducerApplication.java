package umg.edu.gt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal que inicia el microservicio Productor.
 * Este servicio se encarga de obtener transacciones y enviarlas a RabbitMQ.
 */
@SpringBootApplication // Define esta clase como el corazón de la aplicación Spring Boot
@EnableScheduling      // Habilita la ejecución de tareas programadas (como el envío automático de mensajes cada X segundos)
public class ProducerApplication {

    public static void main(String[] args) {
        // Lanza la aplicación, inicializa el contexto de Spring y levanta el servidor embebido
        SpringApplication.run(ProducerApplication.class, args);
        
        // Al terminar de cargar, el Producer buscará métodos con @Scheduled 
        // para empezar a llenar las colas de RabbitMQ automáticamente.
    }
}