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
        String nombreCola = t.getBancoDestino();
        
        // 1. Declarar la cola con capacidad de prioridad (Rango 0-10)
        java.util.Map<String, Object> args = new java.util.HashMap<>();
        args.put("x-max-priority", 10); 
        rabbitAdmin.declareQueue(new Queue(nombreCola, true, false, false, args));
        
        // 2. Lógica de Prioridad:
        // Monto > 1000 -> Prioridad 10 (ALTA)
        // De lo contrario -> Prioridad 1 (NORMAL)
        int nivelPrioridad = (t.getMonto() > 1000) ? 10 : 1;
        String etiqueta = (nivelPrioridad == 10) ? "ALTA" : "NORMAL";

        // 3. Envío con la prioridad configurada
        rabbitTemplate.convertAndSend(nombreCola, (Object) t, m -> {
            m.getMessageProperties().setPriority(nivelPrioridad);
            return m;
        });
        
        
        System.out.println(String.format("🚀 [RABBIT] ID: %s | Banco: %s | Prioridad: %s (%d)", 
                           t.getIdTransaccion(), nombreCola, etiqueta, nivelPrioridad));
    }
}