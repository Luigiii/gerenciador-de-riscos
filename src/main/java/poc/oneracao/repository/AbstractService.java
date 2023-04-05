package poc.oneracao.repository;

import poc.oneracao.model.RiscoAnalitico;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractService {

    public final static String RISCO_COL_ID = "id";
    public final static String RISCO_COL_DATA = "data";
    public final static String RISCO_COL_VALOR = "valor";

    public String getTableName() {
        return "riscos-analiticos-interno";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(RISCO_COL_ID, RISCO_COL_DATA, RISCO_COL_VALOR).build();
    }

    protected PutItemRequest putRequest(RiscoAnalitico riscoAnalitico) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(RISCO_COL_ID, AttributeValue.builder().s(riscoAnalitico.getId()).build());
        item.put(RISCO_COL_DATA, AttributeValue.builder().s(riscoAnalitico.getData().toString()).build());
        item.put(RISCO_COL_VALOR, AttributeValue.builder().s(riscoAnalitico.getValor().toString()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(RISCO_COL_ID, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(RISCO_COL_ID, RISCO_COL_VALOR, RISCO_COL_DATA)
                .build();
    }
}
