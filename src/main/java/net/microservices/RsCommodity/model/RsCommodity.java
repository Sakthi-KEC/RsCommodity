package net.microservices.RsCommodity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "commodity")
public class RsCommodity
{
	@Id
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
