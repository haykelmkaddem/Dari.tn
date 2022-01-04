package com.esprit.dari.dao.userrepository;



import com.esprit.dari.entities.userentity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
     List<Task> findAllByCallRequest (String callRequest);
     List<Task> findAllByDateTaskGreaterThanEqual (LocalDateTime minDate);
     List<Task> findAllByDateTaskGreaterThanEqualAndCallRequest (LocalDateTime minDate,String etatRequest);

}
