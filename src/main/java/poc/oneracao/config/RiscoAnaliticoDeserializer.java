package poc.oneracao.config;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import poc.oneracao.model.RiscoAnalitico;

public class RiscoAnaliticoDeserializer extends ObjectMapperDeserializer<RiscoAnalitico> {

    public RiscoAnaliticoDeserializer() {
        super(RiscoAnalitico.class);
    }
}
