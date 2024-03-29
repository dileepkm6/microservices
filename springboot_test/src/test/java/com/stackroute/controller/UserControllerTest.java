package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User user;
    @MockBean
    private UserService userService;
    @InjectMocks
    private UserController userController;

    private List<User> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setAge(26);
        user.setFirstName("Jonny");
        user.setId(101);
        user.setLastName("Jenny");
        user.setUserName("Jonny123");
        list = new ArrayList();
        list.add(user);
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
      @Test
     public void saveUserFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUser() throws Exception {
        when(userService.getAllUser()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllUser")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

//    @Test
//    public void deleteTrack() throws TrackNotFoundException,Exception
//    {
//        trackService.saveTrack(new Track(111,"dileep","maurya"));
//        when(trackService.deleteTrack(111)).thenReturn(track);
//        trackService.saveTrack(new Track(111,"dileep","maurya"));
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().is(200));
//    }
//    @Test
//    public void deleteTrackFailure() throws TrackNotFoundException,Exception
//    {
//        when(trackService.deleteTrack(102)).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict());
//    }
//
//    @Test
//    public void updateTrack() throws TrackNotFoundException,Exception
//    {
//        when(trackService.updateComment(101,"abc")).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateComment")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().is(200));
//    }
//    @Test
//    public void updateTrackFailure() throws TrackNotFoundException,Exception
//    {
//        when(trackService.updateComment(102,"abc")).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateComment")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(false)))
//                .andExpect(MockMvcResultMatchers.status().isConflict());
//    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}
