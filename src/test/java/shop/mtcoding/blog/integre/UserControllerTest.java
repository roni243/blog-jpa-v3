package shop.mtcoding.blog.integre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shop.mtcoding.blog.user.UserRequest;

@AutoConfigureMockMvc //MockMvc 클래스가 IoC에 로드
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    // 나머지 메서드도 전부 테스트

    @Test
    public void login_test() throws Exception {
        // given

        // when

        // eye

        // then

    }

    @Rollback(false)
    @Test
    public void join_test() throws Exception {
        //given(가짜데이터)
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setUsername("haha");
        reqDTO.setPassword("1234");
        reqDTO.setEmail("haha@nate.com");

        String requestBody = om.writeValueAsString(reqDTO);
        //System.out.println(requestBody);

        //when(테스트 실행)
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/join").content(requestBody).contentType(MediaType.APPLICATION_JSON)
        );

        //eye(결과 눈으로 검증)
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        //then(결과 코드로 검증)
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        //actions.andExpect(MockMvcResultMatchers.jsonPath("$.status.length()", is(50)).value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(4));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("haha"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value("haha@nate.com"));

    }
}
