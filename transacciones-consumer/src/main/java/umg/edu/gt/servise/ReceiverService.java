package umg.edu.gt.servise;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import umg.edu.gt.model.Transaccion;

import java.io.IOException;
/**
 * Servicio Consumidor encargado de escuchar las colas de RabbitMQ.
 * Procesa cada mensaje, le asigna la autoría del estudiante y coordina la confirmación (ACK).
 */
@Service
public class ReceiverService {

    @Autowired
    private ApiService apiService; // Servicio especialista en enviar datos a AWS

    /**
     * Escucha mensajes de múltiples colas simultáneamente.
     * @param t Objeto transaccion recibido de la cola.
     * @param channel Canal de comunicación con RabbitMQ para enviar el ACK manual.
     * @param tag Etiqueta única de entrega para identificar este mensaje específico.
     */
    @RabbitListener(queues = {"BI", "GYT", "BAC", "BANRURAL"})
    public void recibirYConfirmar(Transaccion t, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        
        // --- PROCESO DE ENRIQUECIMIENTO ---
        // Aquí inyectamos nuestra identidad en el objeto antes de que llegue a la base de datos final.
        t.setNombre("Kathya Sofia Melgar Marroquin");
        t.setCarnet("0905-24-11709");
        t.setCorreo("kmelgarm2@miumg.edu.gt");
        
        // --- LÓGICA DE CONFIRMACIÓN (ACK MANUAL) ---
        // Solo si la API de la nube confirma la recepción exitosa, borramos el mensaje de RabbitMQ.
        if (apiService.enviarANube(t)) {
            // Log de éxito en consola
            System.out.println("✅ Éxito total para: " + t.getNombre() + " (" + t.getCarnet() + " | Correo: " + t.getCorreo() + "). Enviando ACK...");
            
            // Confirmamos a RabbitMQ que el mensaje fue procesado correctamente (basicAck)
            channel.basicAck(tag, false);
        } else {
            // Si la API falla, NO enviamos el ACK. 
            // Esto hace que el mensaje permanezca en la cola para un reintento automático.
            System.err.println("⚠️ Fallo en la API. El mensaje se queda en cola para seguridad.");
        }
    }
}