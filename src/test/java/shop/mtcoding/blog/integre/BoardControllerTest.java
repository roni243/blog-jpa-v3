package shop.mtcoding.blog.integre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog.board.BoardRequest;
import shop.mtcoding.blog.user.User;

@AutoConfigureMockMvc //MockMvc 클래스가 IoC에 로드
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    private String accessToken;

    @BeforeEach
    public void setUp() {
        //테스트 시작 전에 실행할 코드
        System.out.println("setUp");
        User ssar = User.builder().id(1).username("ssar").build();
        accessToken = JwtUtil.create(ssar);
    }

    @AfterEach
    public void tearDown() {
        //테스트 후 정리할 코드
        System.out.println("tearDown");
    }

    @Test
    public void save_test() throws Exception {
        //given(가짜데이터)
        BoardRequest.SaveDTO reqDTO = new BoardRequest.SaveDTO();
        reqDTO.setTitle("제목21");
        reqDTO.setContent("내용21");
        reqDTO.setIsPublic(true);

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println(requestBody);

        //when(테스트 실행)


        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/s/api/board")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

        //eye(결과 눈으로 검증)
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        //System.out.println(responseBody);

//        //then(결과 코드로 검증)
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        //actions.andExpect(MockMvcResultMatchers.jsonPath("$.status.length()", is(50)).value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(21));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.content").value("내용21"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isPublic").value(true));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("haha"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value("haha@nate.com"));


    }
}
