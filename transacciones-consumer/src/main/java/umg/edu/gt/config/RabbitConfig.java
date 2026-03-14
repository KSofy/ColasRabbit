package umg.edu.gt.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * Configuración de infraestructura para RabbitMQ en el lado del Consumidor.
 * Asegura que los mensajes recibidos en formato JSON se conviertan correctamente
 * a objetos de la clase Transaccion.
 */
@Configuration 
public class RabbitConfig {

    /**
     * Define el conversor de mensajes. 
     * Es crucial que sea el mismo que usa el Producer para que la 
     * deserialización (de JSON a Objeto) sea exitosa.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
	
    /**
     * Permite al Consumidor gestionar o verificar la existencia de colas
     * en el servidor de RabbitMQ si fuera necesario.
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Configura el RabbitTemplate para el consumidor.
     * Aunque el consumidor usa principalmente @RabbitListener, el template
     * es necesario para operaciones de envío o respuestas si el flujo lo requiere.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        
        // Sincroniza el formato de mensaje con el del emisor (JSON)
        template.setMessageConverter(jsonMessageConverter()); 
        
        return template;
    }
}