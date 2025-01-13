//package org.example.medsys.security;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class AdminControllerTest {
//
//	@Mock
//	private AdminService adminService;
//
//	@InjectMocks
//	private AdminController adminController;
//
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
//	}
//
//	@Test
//	@WithMockUser(roles = "ADMIN")
//	void testAdminAccess() throws Exception {
//		when(adminService.getDashboardData()).thenReturn("Dashboard data");
//
//		mockMvc.perform(get("/api/admin/dashboard"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	@WithMockUser(roles = "DOCTOR")
//	void testDoctorCannotAccessAdmin() throws Exception {
//		mockMvc.perform(get("/api/admin/dashboard"))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	void testUnauthorizedAccess() throws Exception {
//		mockMvc.perform(get("/api/admin/dashboard"))
//				.andExpect(status().isUnauthorized());
//	}
//}