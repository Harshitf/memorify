package com.moclase.Memorify.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

private String Id;
private String firstName;
private String lastName;
private String email;
private LocalDateTime lastSeen;
private boolean isOnline;

}
