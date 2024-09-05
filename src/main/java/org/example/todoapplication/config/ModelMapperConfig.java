package org.example.todoapplication.config;

import org.example.todoapplication.dto.request.TaskRequestDto;
import org.example.todoapplication.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.createTypeMap(TaskRequestDto.class, Task.class)
                .addMapping(src->null,Task::setId);
        return mapper;
    }

}
