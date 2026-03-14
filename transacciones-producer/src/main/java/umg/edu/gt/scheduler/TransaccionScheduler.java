package umg.edu.gt.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import umg.edu.gt.model.LoteTransacciones;
import umg.edu.gt.model.Transaccion;
import umg.edu.gt.service.MassageService; // Importamos el nuevo servicio

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Esta clase actúa como un temporizador automático.
 * Su función es pedir datos a la API externa periódicamente y enviarlos al servicio de mensajería.
 */
@Component // Indica que Spring debe administrar esta clase como un componente automático
public class TransaccionScheduler {

    // URL de la API del ingeniero que nos proporciona las transacciones de prueba (Semilla)
    private final String URL = "https://hly784ig9d.execute-api.us-east-1.amazonaws.com/default/transacciones";
    
    // Set para almacenar IDs ya procesados y evitar enviar la misma transacción varias veces a RabbitMQ
    private final Set<String> processedIds = ConcurrentHashMap.newKeySet(); 
    
    // Herramienta para realizar peticiones HTTP GET a la URL externa
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired // Inyecta la lógica de negocio para el envío de mensajes a RabbitMQ
    private MassageService messageService;

    /**
     * Método que se ejecuta automáticamente cada 10,000 milisegundos (10 segundos).
     */
    @Scheduled(fixedDelay = 10000) 
    public void ejecutar() {
        try {
            // 1. Consumir la API externa y convertir el JSON recibido en un objeto LoteTransacciones
            LoteTransacciones lote = restTemplate.getForObject(URL, LoteTransacciones.class);
            
            if (lote != null && lote.getTransacciones() != null) {
                // 2. Recorrer la lista de transacciones obtenidas
                for (Transaccion t : lote.getTransacciones()) {
                    
                    // 3. Control de Duplicados: Solo procesamos si el ID no ha sido visto antes
                    if (!processedIds.contains(t.getIdTransaccion())) {
                        
                        // 4. Delegar al servicio la tarea de clasificar por banco y enviar a la cola
                        messageService.enviarABanco(t);
                        
                        // 5. Registrar el ID como "procesado" para no repetirlo en el siguiente ciclo
                        processedIds.add(t.getIdTransaccion());
                        
                        System.out.println("🚀 Enviada a RabbitMQ: " + t.getIdTransaccion() + " de " + t.getBancoDestino());
                    }
                }
            }
        } catch (Exception e) {
            // Manejo de errores en caso de que la API externa esté caída o no haya internet
            System.err.println("⚠️ Error en el Scheduler (API externa inaccesible): " + e.getMessage());
        }
    }
}