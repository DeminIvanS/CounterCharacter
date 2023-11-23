package ru.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.example.controller.MainController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MainController controller;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform((RequestBuilder) post("/api/v1/counter/get_count_char_from_string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"string\" : \"abbccc\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"c\": 3, \"b\": 2, \"a\": 1")));

        this.mockMvc.perform((RequestBuilder) post("/api/v1/counter/get_count_char_from_string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"string\" : \"aaaaabcccc\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"a\": 5, \"c\": 4, \"b\": 1")));

        this.mockMvc.perform((RequestBuilder) post("/api/v1/counter/get_count_char_from_string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"string\" : \"\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The line is empty! Write something.")));

        this.mockMvc.perform((RequestBuilder) post("/api/v1/counter/get_count_char_from_string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"string\" : \"dfhilj;dlfkghj;lxfkgjh;klxjbh/lkjfl/hknjdfl./gkhndfnh.jnv.bkjndjfghesw;oiuhrgituhdsilufhgvliaygelysdlfighlncsisduybgvtasdybldgvnhlfughydilsucmdfhgbvyergteryiugtvidzfhgbdkzhjfbgiudghiyauegrhlaiguyiluyfdsglifugliusdfghlsduifghlsuidfhgldfsuhgiushdflgiuher7ghasldfkjngjkdnvbkjnlbknfghmndflkgjhjfithurtshurgsyeghsgdchvbdkcbnvdkfjnbifgjkhfo;suhsdurhjgbbjnjkfghngksnggjk.nfsdg\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The line is too long. Maximum number of characters 255.")));
    }
}
