package com.newera.marathon.dto.system.maintenance;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceGenearteCaptchaResponseDTO extends GenericResponseDTO {

	String captchaCode;

   
	String pic;

   
	String captchaId;


}