package com.pms.user.service.response;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
  private  String jwt;
  private  String message;
  private  String status;
}
