package ml.socshared.db.repositories;

import ml.socshared.db.models.Pair;
import org.springframework.data.repository.CrudRepository;

public interface PairRepository extends CrudRepository<Pair, Long> {
    Pair findByKey(String Key);
}
