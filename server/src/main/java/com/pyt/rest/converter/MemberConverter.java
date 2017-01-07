package com.pyt.rest.converter;

import com.pyt.model.Member;
import com.pyt.rest.dto.MemberDto;

public class MemberConverter {
	public static MemberDto to(Member entity) {
		MemberDto dto = new MemberDto();
		if (entity != null) {
			dto.id = entity.getId().intValue();
			dto.name = entity.getName();
			dto.email = entity.getEmail();
			dto.quarterId = entity.getQuarter().getId().intValue();
		}
		return dto;
	}

}
