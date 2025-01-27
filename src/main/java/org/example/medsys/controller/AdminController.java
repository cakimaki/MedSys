package org.example.medsys.controller;

//@RestController
//@RequestMapping("/api/admin")
////@PreAuthorize("hasRole('ADMIN')")
//public class AdminController {
//
//	private final AppUserService appUserService;
//
//	@Autowired
//	public AdminController(AppUserService appUserService) {
//		this.appUserService = appUserService;
//	}
//
//	@PostMapping("/create-user")
//	public ResponseEntity<AppUser> createUser(@RequestBody AppUserRequest request) {
//		AppUser appUser = appUserService.createAppUser(request.getEgn(), request.getPassword(), request.getRoles());
//		return ResponseEntity.status(HttpStatus.CREATED).body(appUser);
//	}
//}