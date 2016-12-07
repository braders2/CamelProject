package com.camel.transform.impl;

import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.GenericTransformer;

public class ProjectTransformerImpl implements GenericTransformer<ProjectDTO, ProjectRecord> {

    @Override
    public ProjectDTO convertToDto(ProjectRecord projectRecord) {
        return ProjectDTO.builder().idProject(projectRecord.getIdProject())
                                    .name(projectRecord.getName())
                                    .timeFrom(projectRecord.getTimeFrom())
                                    .timeTo(projectRecord.getTimeTo())
                                    .idCustomer(projectRecord.getIdCustomer())
                                    .build();
    }

    @Override
    public ProjectRecord convertToEntity(ProjectDTO projectDTO) {
        ProjectRecord projectRecord = new ProjectRecord();
        projectRecord.setIdProject(projectDTO.getIdProject());
        projectRecord.setName(projectDTO.getName());
        projectRecord.setTimeFrom(projectDTO.getTimeFrom());
        projectRecord.setTimeTo(projectDTO.getTimeTo());
        projectRecord.setIdCustomer(projectDTO.getIdProject());
        return projectRecord;
    }
}
