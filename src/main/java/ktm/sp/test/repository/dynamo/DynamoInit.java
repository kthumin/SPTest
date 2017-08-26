package ktm.sp.test.repository.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import ktm.sp.test.domain.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by kthum on 26/8/2017.
 */
@Component
public class DynamoInit implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DynamoDBMapper dynamoDbMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.trace("Entering onApplicationEvent()");
        CreateTableRequest request = dynamoDbMapper.generateCreateTableRequest(PersonEntity.class)
                                      .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        try
        {
            DescribeTableResult result = amazonDynamoDB.describeTable(request.getTableName());
            log.info("Table status {}, {}", request.getTableName(), result.getTable().getTableStatus());
        }
        catch (ResourceNotFoundException expectedException)
        {
            CreateTableResult result = amazonDynamoDB.createTable(request);
            log.info("Table creation triggered {}, {}", request.getTableName(), result.getTableDescription().getTableStatus());
        }
    }

}
