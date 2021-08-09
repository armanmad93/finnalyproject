package com.example.techthink.converter;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.model.*;
import com.example.techthink.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class Converter {
    public UserResponse fromUserToResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirst_name(user.getFirst_name());
        userResponse.setLast_name(user.getLast_name());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public AddressResponse fromAddressToResponse(Address address){
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        return addressResponse;
    }

    public List<AddressResponse> fromAddressToResponseList(List<Address> addresses){
        return addresses.stream()
                .map(each -> fromAddressToResponse(each))
                .collect(Collectors.toList());
    }

    public CategoryResponse fromCategoryToResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public List<CategoryResponse> fromCategoryToResponseList(List<Category> categories){
        return categories.stream()
                .map(each -> fromCategoryToResponse(each))
                .collect(Collectors.toList());
    }

    public SubCategoryResponse fromSubCategoryToResponse(SubCategory subCategory){
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        subCategoryResponse.setId(subCategory.getId());
        subCategoryResponse.setName(subCategory.getName());
        CategoryResponse categoryResponse = fromCategoryToResponse(subCategory.getCategory());
        subCategoryResponse.setCategory(categoryResponse);
        return subCategoryResponse;
    }

    public List<SubCategoryResponse> fromSubCategoryToResponseList(List<SubCategory> subCategories){
        return subCategories.stream()
                .map(each -> fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
    }

    public CourseResponse fromCourseToResponse(Course course, List<SubCategory> subCategories){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setPrice(course.getPrice());

        List<SubCategoryResponse> subCategoryResponses = subCategories.stream()
                .map(each -> fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
        courseResponse.setSubCategoryResponses(subCategoryResponses);
        return courseResponse;
    }

    //public List<CourseResponse> fromCourseToResponseList(List<Course> courses, )
//List<Course> courses = courseService.readAll();
//        List<List<SubCategory>>

//    public List<Course> fromCourseToResponseList(List<Course> courses, List<List<SubCategory>> subcategories){
//        return courses.stream()
//                .map(each -> fromCourseToResponse(each, subcategories.get()))
//                .collect(Collectors.toList());
//    }




}
