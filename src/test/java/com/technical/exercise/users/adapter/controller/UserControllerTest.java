package com.technical.exercise.users.adapter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.google.gson.Gson;
import com.technical.exercise.users.domain.Phone;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @Test
  void getUsers() throws Exception {
    when(userService.getUsers()).thenReturn(Collections.emptyList());
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/v1/user").header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    verify(userService, times(1)).getUsers();
  }

  @Test
  void getUserByUuid() throws Exception {
    String uuid = "dummyUuid";
    when(userService.getUserByUuid(uuid)).thenReturn(new User());
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/{uuid}", uuid)
        .header(HttpHeaders.AUTHORIZATION, "Bearer token").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$").isMap());
    verify(userService, times(1)).getUserByUuid(uuid);
  }

  @Test
  void createUser() throws Exception {
    Gson gson = new Gson();
    UserRequest userRequest =
        UserRequest.builder().name("name").password("Password14").email("correo@correo.com")
            .phones(Collections.singletonList(Phone.builder().number("1234").build())).build();

    String body = gson.toJson(userRequest);
    when(userService.createUser(any(), anyString())).thenReturn(
        User.builder().name("name").password("Password14").email("correo@correo.com").build());
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/user")
            .header(HttpHeaders.AUTHORIZATION, "Bearer token").content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andExpect(jsonPath("$").isMap());
    verify(userService, times(1)).createUser(any(), anyString());
  }

  @Test
  void updateUser() throws Exception {
    String uuid = "dummyUuid";
    Gson gson = new Gson();
    UserRequest userRequest =
        UserRequest.builder().name("name").password("Password14").email("correo@correo.com")
            .phones(Collections.singletonList(Phone.builder().number("1234").build())).build();

    String body = gson.toJson(userRequest);
    when(userService.updateUser(any(), anyString())).thenReturn(
        User.builder().name("name").password("Password14").email("correo@correo.com").build());
    mockMvc
        .perform(MockMvcRequestBuilders.put("/v1/user/{uuid}", uuid)
            .header(HttpHeaders.AUTHORIZATION, "Bearer token").content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$").isMap());
    verify(userService, times(1)).updateUser(any(), anyString());
  }

  @Test
  void deleteUser() throws Exception {
    String uuid = "dummyUuid";
    when(userService.deleteUser(uuid)).thenReturn("User deleted successfully");
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/v1/user/{uuid}", uuid)
            .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
        .andExpect(status().isOk()).andExpect(content().string("User deleted successfully"));
    verify(userService, times(1)).deleteUser(uuid);
  }
}

