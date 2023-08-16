package com.kinghk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    /**
     * @api {GET} /hello hello
     * @apiVersion 1.0.0
     * @apiGroup IndexController
     * @apiName hello
     * @apiParam (请求参数) {String} name
     * @apiParamExample 请求参数示例
     * name=eBvz
     * @apiSuccess (响应结果) {Object} response
     * @apiSuccessExample 响应结果示例
     */
    @GetMapping("hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }
}
