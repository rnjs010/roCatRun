package com.ssafy.roCatRun.domain.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerReconnectedResponse {
    private String userId;
    private String nickName;
}
