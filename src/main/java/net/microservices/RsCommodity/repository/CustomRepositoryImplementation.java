package net.microservices.RsCommodity.repository;

import net.microservices.RsCommodity.model.RsCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomRepositoryImplementation implements CustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

   @Override
    public Flux<RsCommodity> findProperties(String commodityCode,
                                            String commodityName,
                                            String commodityGroupCode,
                                            String createdBy,
                                            String createdDate)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (commodityCode != null && !commodityCode.isEmpty())
            criteria.add(Criteria.where("commodityCode").is(commodityCode));

        if (commodityName != null && !commodityName.isEmpty())
            criteria.add(Criteria.where("commodityName").is(commodityName));

        if (commodityGroupCode != null && !commodityGroupCode.isEmpty())
            criteria.add(Criteria.where("commodityGroupCode").is(commodityGroupCode));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (createdDate != null && !createdDate.isEmpty())
            criteria.add(Criteria.where("createdDate").is(createdDate));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

    return template.find(query, RsCommodity.class);
    }
}
