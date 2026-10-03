package com.example.employee_service.service;

import com.example.employee_service.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {

    @GetMapping("api/organization/{code}")
    OrganizationDto getOrganization(@PathVariable("code") String organizationCode);
}