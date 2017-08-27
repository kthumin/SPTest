package ktm.sp.test.repository;

import ktm.sp.test.domain.PersonEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kthum on 26/8/2017.
 */
@EnableScan
public interface PersonRepository extends CrudRepository<PersonEntity, String>{


}
