package rooftophero.io.toyp2p.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rooftophero.io.toyp2p.config.SwaggerConfig;
import rooftophero.io.toyp2p.repository.AccountRepository;
import rooftophero.io.toyp2p.service.AccountService;
import rooftophero.io.toyp2p.service.dto.response.AccountResponseDto;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {AccountApiController.class, SwaggerConfig.class})
@AutoConfigureMockMvc
class AccountApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    WebApplicationContext context;
//
    @MockBean
    private AccountService accountService;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .alwaysDo(print())
//                .build();
//    }

    @Test
    public void getAccountList() throws Exception {
        // Given
        AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                .id(1L)
                .bankName("KB Bank")
                .accountNumber("2535-235325")
                .build();
        given(accountService.getAccountList())
                .willReturn(Collections.singletonList(accountResponseDto));
        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"));
        // Then
        result.andExpect(status().isOk());
    }

}