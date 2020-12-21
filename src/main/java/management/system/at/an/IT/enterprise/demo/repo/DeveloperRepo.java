package management.system.at.an.IT.enterprise.demo.repo;

import management.system.at.an.IT.enterprise.demo.models.Developers;
import org.springframework.data.repository.CrudRepository;


public interface DeveloperRepo extends CrudRepository<Developers,Long> {
}
