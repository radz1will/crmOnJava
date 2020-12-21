package management.system.at.an.IT.enterprise.demo.repo;

import management.system.at.an.IT.enterprise.demo.models.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TaskRepo extends CrudRepository<Tasks,Long> {

}
