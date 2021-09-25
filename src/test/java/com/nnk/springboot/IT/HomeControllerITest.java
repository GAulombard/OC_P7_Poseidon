package com.nnk.springboot.IT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HomeControllerITest extends AllTestITest {

    @DisplayName("Home")
    @Nested //allows to have an inner test class and group several test classes under the same parent
    class Home {

        @Test
        void getHome_withNoAuth() throws Exception {
            mockMvc.perform(get("/").with(anonymous()))
                    .andExpect(status().isOk());
        }

        @Test
        void getHome_withAdmin() throws Exception {
            mockMvc.perform(get("/").with(httpBasic("admin","admin")))
                    .andExpect(status().isOk());
        }

        @Test
        void getHome_withUser() throws Exception {
            mockMvc.perform(get("/").with(httpBasic("user","user")))
                    .andExpect(status().isOk());
        }

    }

    @DisplayName("Admin home")
    @Nested
    class AdminHome {

        @Test
        void adminHome_withNoAuth() throws Exception {
            mockMvc.perform(get("/admin/home").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void adminHome_withAdmin() throws Exception {
            mockMvc.perform(get("/admin/home").with(httpBasic("admin","admin")))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        void adminHome_withUser() throws Exception {
            mockMvc.perform(get("/admin/home").with(httpBasic("user","user")))
                    .andExpect(status().isForbidden());
        }

    }

}
