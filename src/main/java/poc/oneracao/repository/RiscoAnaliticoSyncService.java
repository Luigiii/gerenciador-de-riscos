package poc.oneracao.repository;

import org.jboss.logging.Logger;
import poc.oneracao.model.RiscoAnalitico;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RiscoAnaliticoSyncService extends AbstractService {

    private Logger LOGGER = Logger.getLogger(RiscoAnaliticoSyncService.class);
    private DynamoDbTable<RiscoAnalitico> riscoAnaliticoTable;

    @Inject
    RiscoAnaliticoSyncService(DynamoDbEnhancedClient dynamoEnhancedClient) {
        riscoAnaliticoTable = dynamoEnhancedClient.table(getTableName(),
                TableSchema.fromClass(RiscoAnalitico.class));
    }

    public List<RiscoAnalitico> findAll() {
        try {
            return riscoAnaliticoTable.scan().items().stream().collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar todos os riscos na base de dados!", e);
            throw e;
        }
    }

    public List<RiscoAnalitico> add(RiscoAnalitico riscoAnalitico) {
        riscoAnaliticoTable.putItem(riscoAnalitico);
        return findAll();
    }

    public RiscoAnalitico get(String id) {
        try {
            Key partitionKey = Key.builder().partitionValue(id).build();
            return riscoAnaliticoTable.getItem(partitionKey);
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar risco com id:" + id +"na base de dados!", e);
            throw e;
        }
    }
}