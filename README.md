# ColasRabbit

Proyecto de Consumo y Procesamiento de Transacciones Distribuidas 🚀
Estudiante: Kathya Sofia Melgar Marroquin

Carnet: 0905-24-11709

Curso:Programación

Universidad: Universidad Mariano Gálvez de Guatemala, Campus Jutiapa

📝 Descripción del Proyecto
Este sistema implementa una arquitectura de microservicios orientada a mensajes utilizando RabbitMQ como intermediario. El objetivo es procesar transacciones bancarias provenientes de distintas entidades financieras, enriquecerlas con datos de autoría y persistirlas de forma segura en una arquitectura de nube (AWS).

🛠️ Tecnologías Utilizadas
Java 11 / Spring Boot: Framework principal para el desarrollo de la lógica.

RabbitMQ: Message Broker para la gestión de colas (BI, GYT, BAC, BANRURAL).

Maven: Gestor de dependencias.

REST Template: Para la comunicación con APIs externas (AWS Lambda/API Gateway).

JSON: Formato de intercambio de datos.

⚙️ Componentes del Sistema
1. Producer (Emisor)
Se encarga de obtener datos de transacciones desde una API externa (Seed API).

Distribuye las transacciones en 4 colas específicas dentro de RabbitMQ basadas en el banco de origen.

2. Consumer (Receptor y Procesador)
Escucha de forma asíncrona las colas de mensajes.

Enriquecimiento de datos: Inyecta de forma obligatoria el Nombre, Carnet y Correo del estudiante en cada objeto de transacción.

Garantía de Entrega (ACK Manual): Implementa un sistema de confirmación manual. Solo se elimina el mensaje de la cola de RabbitMQ si la API de la nube confirma una recepción exitosa (HTTP 200/201).

🚀 Instrucciones de Ejecución
Levantar RabbitMQ: Asegurarse de que el servidor local esté corriendo en el puerto 5672.

Ejecutar Producer: Iniciar la aplicación para cargar las colas con los mensajes de prueba.

Ejecutar Consumer: Iniciar el proceso de escucha. Los logs mostrarán el éxito del envío a la nube con el formato:
✅ Éxito total para: Kathya Sofia Melgar Marroquin (0905-24-11709)

📈 Resultados
Procesamiento masivo: Capacidad para manejar cientos de transacciones simultáneas.

Persistencia: Datos almacenados en la infraestructura de AWS proporcionada por la cátedra.

Monitoreo: Seguimiento en tiempo real a través de la interfaz de administración de RabbitMQ (localhost:15672).
