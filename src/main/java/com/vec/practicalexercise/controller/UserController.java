package com.vec.practicalexercise.controller;

import com.vec.practicalexercise.entity.Error;
import com.vec.practicalexercise.entity.User;
import com.vec.practicalexercise.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.GetAppletMessage;

@OpenAPIDefinition(info = @Info(title = "user-service",
        description = "A service for creating, retrieving, updating, and deleting users.",
        version = "0.1.0"))
@Controller
@ResponseBody
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(operationId = "retrieve", summary = "Retrieve an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user response.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "404", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) }) })
    @GetMapping("/{email}")
    public ResponseEntity<Object> getUser(
            @Parameter(name = "email", description = "User email.", required = true,
            schema = @Schema(type = "string", format = "email")) @PathVariable String email){
        User user = null;
        if(email==null || email==""){
            Error error = new Error("400", "Bad Request!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        try{
            user = userService.getUser(email);
        }catch (Exception e){
            Error error = new Error("400", "Bad Request!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if(user==null){
            Error error = new Error("404", "Cannot find the user!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(operationId = "create", summary = "Creates a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user was created successfully."),
            @ApiResponse(responseCode = "400", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PostMapping("")
    public ResponseEntity<Object> addUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = User.class)) })
            @RequestBody User user){
        int result;
        Error error = new Error("400", "Bad Request!");
        try{
            result = userService.addUser(user);
        }catch (Exception e){
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        if(result != 1){
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(operationId = "update", summary = "Update an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The user was updated successfully."),
            @ApiResponse(responseCode = "400", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "404", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping("/{email}")
    public ResponseEntity<Object> updateUser(
            @Parameter(name = "email", description = "User email.", required = true,
            schema = @Schema(type = "string", format = "email")) @PathVariable String email,
                                             @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }) @RequestBody User user){
        int result;
        try{
            result = userService.updateUser(email, user);
        }catch (Exception e){
            Error error = new Error("400", "Bad Request!");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        if(result == 0){
            Error error = new Error("404", "Cannot find the user!");
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(operationId = "delete", summary = "Delete an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The user was deleted successfully."),
            @ApiResponse(responseCode = "400", description = "An unexpected error occurred.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteUser(
            @Parameter(name = "email", description = "User email.", required = true,
            schema = @Schema(type = "string", format = "email")) @PathVariable String email){
        int result;
        Error error = new Error("400", "Bad Request!");
        try{
            result = userService.deleteUser(email);
        }catch (Exception e){
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        if(result == 0){
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
