package com.example.projectboard.domain.ccontroller;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("view 컨트롤러 - 인증")
@WebMvcTest
public class AuthControllerTest {

    private final MockMvc mvc;

    //원래는 Autowired 생략이 가능하지만 test파일에서는 불가능함 붙여줘야함
    public AuthControllerTest(@Autowired MockMvc mvc){
        this.mvc=mvc;
    }

    @Disabled("구현중")
    @DisplayName("[view][Get] 로그링ㄴ - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticlesView() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

}
