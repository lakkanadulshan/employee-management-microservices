package com.example.organization_service.service;

import com.example.organization_service.dto.OrganizationDto;
import org.springframework.stereotype.Service;


public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganization(String organizationCode);
}
