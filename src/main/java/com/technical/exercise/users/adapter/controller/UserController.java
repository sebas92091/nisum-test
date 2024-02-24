package com.technical.exercise.users.adapter.controller;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.service.UserService;
import com.technical.exercise.users.utils.Constants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@Validated
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{uuid}")
  public User getUsers(@PathVariable String uuid) {
    return userService.getUserByUuid(uuid);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @SecurityRequirement(name = "Authorization")
  @Parameter(name = "Authorization", description = "Bearer token", required = true,
      in = ParameterIn.HEADER, example = "Bearer " + Constants.TOKEN_DUMMY)
  @ApiResponses(
      value = {@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
          schema = @Schema(implementation = MethodArgumentNotValidException.class)))})
  public User createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @Valid @RequestBody final UserRequest user) {
    return userService.createUser(user, token);
  }

  @PutMapping("/{uuid}")
  public User updateUser(@PathVariable String uuid, @Valid @RequestBody final UserRequest user) {
    return userService.updateUser(user, uuid);
  }

  @DeleteMapping("/{uuid}")
  public String deleteUser(@PathVariable String uuid) {
    return userService.deleteUser(uuid);
  }


}
