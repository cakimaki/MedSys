package org.example.medsys.security;

import org.example.medsys.service.auth.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppUserService appUserService;

    // ========== ADMIN AREA ==========

    @Test
    @WithMockUser(roles = "ADMIN")
    void admin_can_access_admin_endpoint() throws Exception {
        mockMvc.perform(get("/api/admin/home"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void doctor_cannot_access_admin_endpoint() throws Exception {
        mockMvc.perform(get("/api/admin/home"))
                .andExpect(status().isForbidden()); // 403
    }

    @Test
    void anonymous_cannot_access_admin_endpoint() throws Exception {
        mockMvc.perform(get("/api/admin/home"))
                .andExpect(status().isUnauthorized()); // 401
    }

    // ========== DOCTOR AREA ==========

    @Test
    @WithMockUser(roles = "DOCTOR")
    void doctor_can_access_doctor_endpoint() throws Exception {
        mockMvc.perform(get("/api/doctor/home"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void admin_cannot_access_doctor_endpoint_with_current_config() throws Exception {
        mockMvc.perform(get("/api/doctor/home"))
                .andExpect(status().isForbidden()); // 403 with your current rules
    }

    @Test
    void anonymous_cannot_access_doctor_endpoint() throws Exception {
        mockMvc.perform(get("/api/doctor/home"))
                .andExpect(status().isUnauthorized()); // 401
    }

    // ========== PATIENT AREA ==========

    @Test
    @WithMockUser(roles = "PATIENT")
    void patient_can_access_patient_endpoint() throws Exception {
        mockMvc.perform(get("/api/patient/home"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void doctor_cannot_access_patient_endpoint_with_current_config() throws Exception {
        mockMvc.perform(get("/api/patient/home"))
                .andExpect(status().isForbidden()); // 403
    }

    @Test
    void anonymous_cannot_access_patient_endpoint() throws Exception {
        mockMvc.perform(get("/api/patient/home"))
                .andExpect(status().isUnauthorized()); // 401
    }

    // ========== PUBLIC ENDPOINTS ==========

    @Test
    void anonymous_can_access_home() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isAccepted()); // 202 from controller
    }

    @Test
    @WithMockUser(roles = "PATIENT")
    void authenticated_user_can_access_home() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isAccepted()); // also 202
    }
}
