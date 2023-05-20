package com.kurdi.authorizationserver.vm.actions;

import java.util.List;

import lombok.Data;

@Data
public class AddActionsVM {
    private List<AddActionVM> actions;
    private String moduleName;

}