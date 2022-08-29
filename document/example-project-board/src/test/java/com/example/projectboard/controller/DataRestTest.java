package com.example.projectboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest //WebMvc는 slice테스트 controller 밖에 가져오지않음(불필요한 내용들은 load하지 않음-> data rest의 auto configuration을 읽지않음)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional //기본동작은 rollback
@DisplayName("Data Rest - API 테스트")
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc){
        this.mvc=mvc;
    }

    @Test
    @DisplayName("[api] 게시글 리스트 조회")
    void test() throws Exception {
     //given

     //when& then
     mvc.perform(get("/api/articles"))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
             .andDo(print());
    }
}
