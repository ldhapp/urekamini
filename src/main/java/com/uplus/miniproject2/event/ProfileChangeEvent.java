package com.uplus.miniproject2.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileChangeEvent {

    private Long profileId;
    private Long userId;
    private String eventType; //Register(등록) or Update(수정)
}
