package com.kurdi.authorizationserver.vm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResponse {
    Object data;
    String message;
}
