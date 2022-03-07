package net.microservices.RsCommodity.service;

import net.microservices.RsCommodity.appUtil.AppUtil;
import net.microservices.RsCommodity.dto.RsCommodityDto;
import net.microservices.RsCommodity.exception.CustomException;
import net.microservices.RsCommodity.repository.CustomRepository;
import net.microservices.RsCommodity.repository.RsCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RsCommodityService
{

    @Autowired
    private RsCommodityRepository commodityrepo;

    @Autowired
    private CustomRepository customRepo;


    // Adding RsCommodity to the Repository
    public Mono<RsCommodityDto> addCommodity(Mono<RsCommodityDto> commodity)
    {
        return  commodity.map(AppUtil::DtoToEntity)
                .flatMap(commodityrepo::insert)
                .map(AppUtil::EntityToDto);
    }

    // Editing RsCommodity by updating the old record and inserting the new record
    public Mono<RsCommodityDto> editCommodity(Mono<RsCommodityDto> commodity, String id)
    {
        Mono<RsCommodityDto> r = commodityrepo.findById(id).map(AppUtil::EntityToDto);

        commodityrepo.findById(id)
                .flatMap(p -> r.map(AppUtil::DtoToEntity)
                        .doOnNext(e -> e.setCommodityCode(id)))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(commodityrepo::save)
                .map(AppUtil::EntityToDto).subscribe();

        return commodity.map(AppUtil::DtoToEntity)
                .flatMap(commodityrepo::insert)
                .map(AppUtil::EntityToDto);

    }

    // Updating the Saved Status for the searched RsCommodity
    public Mono<RsCommodityDto> addsearchedcommodity(Mono<RsCommodityDto> commodity)
    {
        return  commodity.map(AppUtil::DtoToEntity)
                .flatMap(commodityrepo::save)
                .map(AppUtil::EntityToDto);
    }

    // Getting all RsCommodity from the Repository
    public Flux<RsCommodityDto> getAllCommodity()
    {
        return commodityrepo.findAll().map(AppUtil::EntityToDto);
    }

    // Getting all Active RsCommodity from the Repository
    public Flux<RsCommodityDto> getAllCommodityByStatus()
    {
        return commodityrepo.findByStatus(true).map(AppUtil::EntityToDto);
    }

    // Getting All RsCommodity by dynamically giving any of the fields
    public Flux<RsCommodityDto> geByDynamicSearch(String commodityCode, String commodityName, String commodityGroupCode, String createdBy, String createdDate)
    {
        return customRepo.findProperties(commodityCode,commodityName,commodityGroupCode,createdBy,createdDate)
                .filter(a->a.getStatus()==true)
                .map(AppUtil::EntityToDto);
    }

    // Getting all the groupCodes of Searched RsCommodity
    public Mono<List<String>> getAllCommoditySavedSearchCode()
    {
        return commodityrepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(AppUtil::EntityToDto)
                .map(a->a.getCommodityGroupCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched RsCommodity which saved status is updated
    public Flux<RsCommodityDto> getSavedCommodityGroupCode(String code)
    {
        return commodityrepo.findBycommodityGroupCodeIgnoreCase(code)
                .filter(a->a.getStatus()==true)
                .filter(a->a.getSaved()==true)
                .map(AppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Commodity Group Code"))));
    }

}
