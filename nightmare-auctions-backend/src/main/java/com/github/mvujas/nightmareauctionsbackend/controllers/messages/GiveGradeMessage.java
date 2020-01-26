package com.github.mvujas.nightmareauctionsbackend.controllers.messages;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GiveGradeMessage {

	@NotNull(message = "Grade value cannot be null")
	@Min(value = 1, message = "Minimum grade value is 1")
	@Max(value = 3, message = "Maximum grade value is 3")
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
