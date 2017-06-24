package event.handlers

import com.amazonaws.services.lambda.runtime.events.KinesisEvent
import org.apache.logging.log4j.LogManager

class BaseEvent : KinesisEvent()

interface EventHandler {
    fun onEvent(event: KinesisEvent)
}

class InventoryEventHandler : EventHandler {

    val logger = LogManager.getLogger(InventoryEventHandler::class.java)

    public override fun onEvent(event: KinesisEvent) {
        logger.info("ProcessingEvents")

        event.records.forEach { e ->
            logger.info("eventType:ProcesingEvent,body:" + String(e.kinesis.data.array()))
        }
    }
}
