package umg.edu.gt.servise;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import umg.edu.gt.model.Transaccion;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReceiverService {

    @Autowired
    private ApiService apiService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Memoria estática para evitar duplicados durante la ejecución
    private static final Set<String> idsProcesados = new HashSet<>();

    @RabbitListener(queues = {"BI", "GYT", "BAC", "BANRURAL"})
    public void recibirYConfirmar(Transaccion t, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        
        String idActual = t.getIdTransaccion();

  
        if (idsProcesados.contains(idActual)) {
           
            System.out.println(String.format("📝 LOG: [ID: %s] | [ESTADO: DUPLICADO] | [COLA DESTINO: cola_duplicados]", idActual));
            
          
            rabbitTemplate.convertAndSend("cola_duplicados", t);
            
           
            channel.basicAck(tag, false);
            return; 
        }

        
        t.setNombre("Kathya Sofia Melgar Marroquin");
        t.setCarnet("0905-24-11709");
        t.setCorreo("kmelgarm2@miumg.edu.gt");
        
        // 3. ENVÍO A AWS
        if (apiService.enviarANube(t)) {
            idsProcesados.add(idActual);
            
        
            System.out.println(String.format("📝 LOG: [ID: %s] | [ESTADO: PROCESADO] | [COLA DESTINO: AWS Cloud]", idActual));
            
           
            System.out.println("   > Monto: " + t.getMoneda() + " " + t.getMonto());
            System.out.println("   > Beneficiario: " + t.getDetalle().getNombreBeneficiario());
            
            channel.basicAck(tag, false);
        } else {
       
            System.err.println(String.format("❌ ERROR: [ID: %s] | [ESTADO: FALLIDO] | [COLA DESTINO: REINTENTO RABBIT]", idActual));
        }
    }
}