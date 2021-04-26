package com.dm.controller;

import com.dm.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *                  ,;,,;
  *                ,;;'(    
  *      __      ,;;' ' \   
  *   /'  '\'~~'~' \ /'\.)  
  * ,;(      )    /  |.     
  *,;' \    /-.,,(   ) \    
  *     ) /       ) / )|    
  *     ||        ||  \)     
  *    (_\       (_\
  *@className DemoServiceImpl
  *@cescription TODO
  *@author dm
  *@date 2021/4/25 17:00
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@RestController
@Slf4j
public class DemoServiceController {

    @Reference(version = "default")
    private DemoService demoService;

    @RequestMapping("/default")
    public String test(){
        return demoService.called("默认服务");
    }
}
