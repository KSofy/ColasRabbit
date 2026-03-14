package umg.edu.gt.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * Clase de configuración para personalizar la conexión con RabbitMQ.
 * Aquí se define cómo se transforman los datos y cómo se administran las colas.
 */
@Configuration // Indica que esta clase contiene definiciones de Beans para el contenedor de Spring
public class RabbitConfig {

    /**
     * Define un conversor de mensajes para que Spring pueda transformar
     * automáticamente objetos Java a formato JSON y viceversa.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        // Jackson2Json es el estándar para convertir objetos complejos a texto JSON
        return new Jackson2JsonMessageConverter();
    }
	
    /**
     * RabbitAdmin es responsable de declarar colas, intercambios (exchanges) 
     * y enlaces (bindings) de forma automática en el servidor de RabbitMQ.
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * RabbitTemplate es la herramienta principal para enviar y recibir mensajes.
     * Se configura con el conversor JSON para asegurar que los datos viajen 
     * en un formato legible para otros sistemas (como la API de AWS).
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        
        // Asignamos el conversor JSON definido arriba para evitar errores de serialización
        template.setMessageConverter(jsonMessageConverter()); 
        
        return template;
    }
}