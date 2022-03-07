package net.microservices.RsCommodity.repository;

import net.microservices.RsCommodity.model.RsCommodity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RsCommodityRepository extends ReactiveMongoRepository<RsCommodity,String>
{
    Flux<RsCommodity> findBycommodityGroupCodeIgnoreCase(String code);
    Flux<RsCommodity> findByStatus(boolean b);
}

//    Flux<RsCommodity> findBycommodityNameIgnoreCase(String name);
//    Flux<RsCommodity> findBycreatedByIgnoreCase(String name);
