package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.AppUserResponse;
import org.example.medsys.dto.auth.AppUserRequest;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.service.auth.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {

    private final AppUserService appUserService;

    public AdminUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppUserResponse> createUser(@Valid @RequestBody  AppUserRequest request) {

        AppUser appUserResponse = appUserService.createAppUser(
                request.getEgn(),
                request.getPassword(),
                request.getRoles()
        );



        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(appUserResponse));
    }

    @GetMapping("/{egn}")
    public ResponseEntity<AppUserResponse> getUserByEgn(@PathVariable String egn){
        AppUserResponse userResponse = appUserService.getUserByEgn(egn);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody AppUserRequest request){

        AppUser updatedUser = appUserService.updateUser(
                id,
                request.getEgn(),
                request.getPassword(),
                request.getRoles()
        );

        AppUserResponse appUserResponse = new AppUserResponse(
                updatedUser.getId(),
                updatedUser.getEgn(),
                updatedUser.getRoleList()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appUserResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        appUserService.deleteUserById(id);

        return ResponseEntity.noContent().build(); //204 no content
    }

    private AppUserResponse toResponse(AppUser appUser){

        return new AppUserResponse(
                appUser.getId(),
                appUser.getEgn(),
                appUser.getRoleList()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
    }
}
