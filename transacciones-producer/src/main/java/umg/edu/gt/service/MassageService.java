package umg.edu.gt.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.gt.model.Transaccion;

/**
 * Servicio encargado de la mensajería hacia RabbitMQ.
 * Implementa la lógica de ruteo dinámico basado en el banco de destino.
 */
@Service // Marca esta clase como un servicio de Spring para ser inyectado donde se necesite
public class MassageService {

    @Autowired // Inyecta la plantilla de Rabbit para enviar mensajes
    private RabbitTemplate rabbitTemplate;

    @Autowired // Inyecta el administrador de Rabbit para crear componentes en el servidor
    private RabbitAdmin rabbitAdmin;

    /**
     * Toma una transacción, identifica el banco y la envía a la cola correspondiente.
     * @param t Objeto transacción completo obtenido de la API.
     */
    public void enviarABanco(Transaccion t) {
        // Extraemos el nombre del banco (ej. "BI", "GYT") para usarlo como nombre de la cola
        String nombreCola = t.getBancoDestino();
        
        /**
         * CREACIÓN DINÁMICA: 
         * Si el banco es nuevo y su cola no existe en RabbitMQ, RabbitAdmin la crea.
         * El parámetro 'true' indica que la cola es DURABLE (sobrevive a reinicios).
         */
        rabbitAdmin.declareQueue(new Queue(nombreCola, true));
        
        /**
         * ENVÍO DE DATOS:
         * convertAndSend transforma automáticamente el objeto Transaccion a JSON
         * gracias al MessageConverter que configuramos anteriormente.
         */
        rabbitTemplate.convertAndSend(nombreCola, t);
        
        // Log de control para monitorear el flujo en la consola del Producer
        System.out.println("[RABBIT] Transacción " + t.getIdTransaccion() + " enviada a: " + nombreCola);
    }
}