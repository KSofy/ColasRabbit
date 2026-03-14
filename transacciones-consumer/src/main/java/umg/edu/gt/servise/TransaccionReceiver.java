package umg.edu.gt.servise;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import umg.edu.gt.model.Transaccion;

/**
 * Componente receptor que actúa como monitor de consola.
 * Su función principal es interceptar los mensajes de las colas de los bancos
 * y mostrar la información procesada visualmente para depuración.
 */
@Service
public class TransaccionReceiver {

    /**
     * El Consumer se queda "escuchando" permanentemente estas colas.
     * Gracias a la anotación @RabbitListener, Spring Boot invoca este método 
     * automáticamente cada vez que un nuevo mensaje llega a RabbitMQ.
     * * @param t El objeto Transaccion ya convertido desde JSON por el MessageConverter.
     */
    @RabbitListener(queues = {"BI", "GYT", "BAC", "BANRURAL"})
    public void procesarTransaccion(Transaccion t) {
        // Imprime un encabezado estético para separar cada transacción en los logs
        System.out.println("\n--- 🏦 TRANSACCIÓN RECIBIDA DEL BUS ---");
        
        // Acceso a datos de primer nivel (Cabecera)
        System.out.println("ID: " + t.getIdTransaccion());
        System.out.println("Monto: " + t.getMoneda() + " " + t.getMonto());
        
        // Acceso a datos de segundo nivel (Objeto Detalle)
        System.out.println("Beneficiario: " + t.getDetalle().getNombreBeneficiario());
        
        // Acceso a datos de tercer nivel (Objeto Referencia dentro de Detalle)
        // Esto demuestra la capacidad del sistema para manejar objetos complejos anidados.
        System.out.println("Factura: " + t.getDetalle().getReferencias().getFactura());
        
        System.out.println("---------------------------------------\n");
    }
}