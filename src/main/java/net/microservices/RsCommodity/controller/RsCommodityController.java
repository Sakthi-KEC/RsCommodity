package net.microservices.RsCommodity.controller;


import net.microservices.RsCommodity.dto.RsCommodityDto;
import net.microservices.RsCommodity.service.RsCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/commodity")

public class RsCommodityController
{

    @Autowired
    private RsCommodityService service;


    @PostMapping
    public Mono<RsCommodityDto> addCommodity(@RequestBody Mono<RsCommodityDto> commodity)
    {
        return service.addCommodity(commodity);
    }

    @PutMapping("/edit/{id}")
    public Mono<RsCommodityDto> editCommodity(@RequestBody Mono<RsCommodityDto> commodity, @PathVariable String id)
    {
        return service.editCommodity(commodity,id);
    }

    @PutMapping("/savedsearch")
    public Mono<RsCommodityDto> addsearchedcommodity(@RequestBody Mono<RsCommodityDto> commodity)
    {
        return service.addsearchedcommodity(commodity);
    }

    @GetMapping()
    public Flux<RsCommodityDto> getAllCommodityByStatus()
    {
        return service.getAllCommodityByStatus();
    }

    @GetMapping("/search")
    public Flux<RsCommodityDto> geByDynamicSearch(@RequestParam(required = false) String commodityCode,
                                                  @RequestParam(required = false) String commodityName,
                                                  @RequestParam(required = false) String commodityGroupCode,
                                                  @RequestParam(required = false) String createdBy,
                                                  @RequestParam(required = false) String createdDate)
    {
        return service.geByDynamicSearch(commodityCode,commodityName,commodityGroupCode,createdBy,createdDate);
    }

    @GetMapping("/savedsearch/codes")
    public Mono<List<String>> getAllCommoditySavedSearchCode()
    {
        return service.getAllCommoditySavedSearchCode();
    }

    @GetMapping("/saved/{code}")
    public Flux<RsCommodityDto> getSavedCommodityGroupCode(@PathVariable String code)
    {
        return service.getSavedCommodityGroupCode(code);
    }

}
