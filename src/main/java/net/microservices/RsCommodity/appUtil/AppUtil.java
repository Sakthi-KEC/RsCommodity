package net.microservices.RsCommodity.appUtil;

import net.microservices.RsCommodity.dto.RsCommodityDto;
import net.microservices.RsCommodity.model.RsCommodity;
import org.springframework.beans.BeanUtils;


public class AppUtil
{

    public static RsCommodity DtoToEntity(RsCommodityDto dto)
    {
        RsCommodity entity=new RsCommodity();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static RsCommodityDto EntityToDto(RsCommodity entity)
    {
        RsCommodityDto dto=new RsCommodityDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

}
