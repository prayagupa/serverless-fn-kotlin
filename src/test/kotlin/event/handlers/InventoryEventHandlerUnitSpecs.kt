package event.handlers

import com.amazonaws.services.lambda.runtime.events.KinesisEvent
import io.kotlintest.specs.StringSpec
import java.nio.ByteBuffer
import java.util.Arrays.asList

/**
 * Created by prayagupd
 * on 6/24/17.
 */

class InventoryEventHandlerUnitSpecs : StringSpec() {

    init {
        "handleRequest should process a message" {
            val handler = InventoryEventHandler()

            val records = KinesisEvent()
            records.records = ArrayList<KinesisEvent.KinesisEventRecord>()
            handler.onEvent(records)
        }

        "handleRequest should process two messages" {
            val handler = InventoryEventHandler()

            val records = KinesisEvent()
            val record = KinesisEvent.KinesisEventRecord()
            record.eventID = "100"

            val k_event = KinesisEvent.Record()
            k_event.data = ByteBuffer.wrap("somedata".toByteArray(Charsets.UTF_8))
            record.kinesis = k_event

            records.records = asList(record)
            handler.onEvent(records)
        }

    }
}
