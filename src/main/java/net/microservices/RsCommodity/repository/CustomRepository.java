package net.microservices.RsCommodity.repository;

import net.microservices.RsCommodity.model.RsCommodity;
import reactor.core.publisher.Flux;


public interface CustomRepository
{
        Flux<RsCommodity> findProperties(String commodityCode,
                                         String commodityName,
                                         String commodityGroupCode,
                                         String createdBy,
                                         String createdDate);

}
