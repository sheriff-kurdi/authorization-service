package com.kurdi.authorizationserver.vm.modules;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ModuleVM {
    private String name;
    private String description;
    private String projectName;

}
