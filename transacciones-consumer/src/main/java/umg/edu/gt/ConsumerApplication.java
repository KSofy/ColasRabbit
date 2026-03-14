package umg.edu.gt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia el microservicio Consumidor.
 * A diferencia del Producer, este servicio actúa como un "Listener" (Escuchador)
 * que permanece activo esperando mensajes de las colas de RabbitMQ.
 */
@SpringBootApplication // Habilita la autoconfiguración de Spring Boot y el escaneo de componentes
public class ConsumerApplication {

    public static void main(String[] args) {
        // Inicia el contexto de Spring. Al arrancar, detectará automáticamente 
        // las clases marcadas con @RabbitListener para empezar a procesar colas.
        SpringApplication.run(ConsumerApplication.class, args);
        
        /* * Nota: No se necesita @EnableScheduling aquí porque este servicio 
         * es reactivo; responde a la llegada de mensajes en lugar de a un reloj.
         */
    }
}