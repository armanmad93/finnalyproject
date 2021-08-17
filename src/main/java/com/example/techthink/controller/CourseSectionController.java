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
import com.example.techthink.controller.model.*;
import com.example.techthink.facade.SectionFacade;
import com.example.techthink.persistence.CourseSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@RestController
public class CourseSectionController {

    private final SectionFacade facade;

    public CourseSectionController(SectionFacade facade) {
        this.facade = facade;
    }


    @PostMapping(value = "/createSection/{id}")
    public ResponseEntity<CourseSectionResponse> create(@PathVariable("id") Long id, @RequestBody CourseSectionRequest request) {
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.createSection(id, request));
        return body;
    }

    @PostMapping(value = "enrollIn/section/{sectionId}")
    public ResponseEntity<SectionParticipantsResponse> enrollIn(@PathVariable Long sectionId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) authentication.getCredentials();
        ResponseEntity<SectionParticipantsResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.enrollIn(user.getId(), sectionId));
        return body;
    }

    @GetMapping(value = "section/{id}")
    public ResponseEntity<CourseSectionResponse> readById(@PathVariable Long id){
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "sections")
    public ResponseEntity<List<CourseSectionResponse>> readAll(){
        ResponseEntity<List<CourseSectionResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "section/{id}")
    public ResponseEntity<CourseSectionResponse> update(@PathVariable Long id, @RequestBody CourseSectionRequest request){
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "section/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }

    //professor can delete students from the section
    ///MAYBE need to check if the professor own the section??
    @DeleteMapping(value = "section/deleteStudent/{studentId}/{sectionId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long studentId, @PathVariable Long sectionId){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.deleteStudentFromSection(sectionId, sectionId));
        return body;
    }


    @PostMapping(value = "setSectionPic/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CourseSectionResponse> uploadProfPic(@PathVariable Long id, @RequestPart MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();;


        AWSCredentials credentials= new BasicAWSCredentials(
                "AKIAWQHZ4UCXO2VY2VWT",
                "S7tGyJwrvJiGW2CSqBR0koV8eTCRMdWP/f02OJLv"
        );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();

        PutObjectRequest requestFile = new PutObjectRequest("techthink", file.getOriginalFilename(), inputStream, objectMetadata);
        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(requestFile);

        String pictureURL = s3Client.getUrl("techthink", file.getOriginalFilename()).toExternalForm();
        //System.out.println(pictureURL);

        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.setSectionPic(id, pictureURL));
        return body;
    }















    //TODO -- get the professor's id by security
    //
//    @PostMapping(value = "/createSection/{id}", consumes = { //MediaType.APPLICATION_JSON_VALUE,
//            MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<CourseSectionResponse> create(@PathVariable("id") Long id, @RequestPart CourseSectionRequest request, @RequestPart MultipartFile file) throws IOException {
//        InputStream inputStream = file.getInputStream();
//
//        AWSCredentials credentials= new BasicAWSCredentials(
//                "AKIAWQHZ4UCXO2VY2VWT",
//                "S7tGyJwrvJiGW2CSqBR0koV8eTCRMdWP/f02OJLv"
//        );
//
//        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
//
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(awsStaticCredentialsProvider)
//                .withRegion(Regions.EU_CENTRAL_1)
//                .build();
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//
//        PutObjectRequest requestFile = new PutObjectRequest("techthink", file.getOriginalFilename(), inputStream, objectMetadata);
//        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
//        PutObjectResult putObjectResult = s3Client.putObject(requestFile);
//
//        String pictureURL = s3Client.getUrl("techthink", file.getOriginalFilename()).toExternalForm();
//        System.out.println(pictureURL);
//        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.createSection(id, request));
//        return body;
//    }

}
