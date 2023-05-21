package com.kurdi.authorizationserver.vm.actions;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ActionVM {
    private String name;
    private String description;

}
