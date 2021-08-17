package com.example.techthink.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.techthink.config.UserPrincipal;
import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class UserController {

    //TODO -- log in
    //Done httpBasic login page

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

//    @GetMapping("/login")
//    public ModelAndView login() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login.html");
//        return modelAndView;
//    }

    @PostMapping(value = "/registerStudent")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.registerStudent(request));
        return body;
    }

    //TODO -- security only admin can add a professor
    //we need HTML page for this task (Web config)
    @PostMapping(value = "/admin/registerProfessor")
    public ResponseEntity<UserResponse> addProfessor(@RequestBody RegisterRequest request) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.addProfessor(request));
        return body;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<UserResponse> readById(@PathVariable Long id) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readById(id));
        return body;
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<UserResponse>> readAll() {
        ResponseEntity<List<UserResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readAll());
        return body;
    }

    @PutMapping(value = "/editProfile")
    public ResponseEntity<UserResponse> editProfile(@RequestBody RegisterRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) authentication.getCredentials();
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.editProfile(user.getId(), request));
        return body;
    }

    @DeleteMapping(value = "deleteAccount")
    public ResponseEntity<Boolean> deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) authentication.getCredentials();
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.delete(user.getId()));
        return body;
    }

    @PostMapping(value = "uploadProfilePicture", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserResponse> uploadProfPic(@RequestPart MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) authentication.getCredentials();

        InputStream inputStream = file.getInputStream();

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAWQHZ4UCXO2VY2VWT",
                "S7tGyJwrvJiGW2CSqBR0koV8eTCRMdWP/f02OJLv"
        );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();

        PutObjectRequest requestFile = new PutObjectRequest("techthinkproject", file.getOriginalFilename(), inputStream, objectMetadata);
        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(requestFile);

        String pictureURL = s3Client.getUrl("techthinkproject", file.getOriginalFilename()).toExternalForm();
        //System.out.println(pictureURL);

        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.uploadPicture(user.getId(), pictureURL));
        return body;
    }


}
