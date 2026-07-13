package projects.notification_services.dto;

import lombok.Builder;

@Builder
public record JWTUserData(
    Long id,
    String email

) {

}
