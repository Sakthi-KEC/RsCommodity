package net.microservices.RsCommodity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsCommodityDto
{

    private String id;
    private String commodityCode;
    private String commodityName;
    private String commodityDescription;
    private String commodityGroupCode;
    private Boolean status;
    private String createdBy;
    private String createdDate;
    private Boolean saved;

}
