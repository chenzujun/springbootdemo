package com.raine.springboot.demo.domain.base;

import com.raine.springboot.demo.common.enums.EnumSystemStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {
	
	private static final long serialVersionUID = 7285065610386199394L;

	private String code;
	private String message;
	private Object obj;
	
	public ResponseResult() {
		this.code = EnumSystemStatus.SUCCESS.getCode();
		this.message = EnumSystemStatus.SUCCESS.getMessage();
	}
	
	public ResponseResult(EnumSystemStatus statusMessage){
		this.code = statusMessage.getCode();
		this.message = statusMessage.getMessage();
		
	}

}
