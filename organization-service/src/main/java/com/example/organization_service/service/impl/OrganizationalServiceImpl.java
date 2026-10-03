package com.example.organization_service.service.impl;

import com.example.organization_service.dto.OrganizationDto;
import com.example.organization_service.entity.Organization;
import com.example.organization_service.mapper.OrganizationMapper;
import com.example.organization_service.repository.OrganizationRepository;
import com.example.organization_service.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationalServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        OrganizationDto organizationDto1 = OrganizationMapper.mapToOrganizationDto(savedOrganization);

        return organizationDto1;
    }

    @Override
    public OrganizationDto getOrganization(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDto organizationDto =OrganizationMapper.mapToOrganizationDto(organization);
        return organizationDto;
    }
}
