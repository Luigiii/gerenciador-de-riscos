package poc.oneracao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import poc.oneracao.config.JacksonConfig;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JacksonConfig jacksonConfig = new JacksonConfig();

    public static String objectToJson(Object object) throws JsonProcessingException {
        jacksonConfig.customize(mapper);
        return mapper.writeValueAsString(object);
    }

}
