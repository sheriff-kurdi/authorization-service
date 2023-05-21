package com.kurdi.authorizationserver.vm.projects;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProjectVM {
    private String name;
    private String description;
}
