package org.now.mas.controller;

import org.now.mas.dto.BaseResponseDto;
import org.now.mas.dto.ExecuteCommandRequestDto;
import org.now.mas.service.HikvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康威视API控制器
 *
 * @author Administrator
 * @since 2021.08.14
 */
@RestController
@RequestMapping("/hikvision")
public class HikvisionController {

    private final HikvisionService hikvisionService;

    public HikvisionController(final HikvisionService hikvisionService) {
        this.hikvisionService = hikvisionService;
    }


    @PostMapping("/execute")
    public ResponseEntity<BaseResponseDto> executeCommand(@RequestBody final ExecuteCommandRequestDto executeCommandRequestDto) {
        return hikvisionService.executeForController(executeCommandRequestDto);
    }
}
