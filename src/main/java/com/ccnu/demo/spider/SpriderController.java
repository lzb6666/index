package com.ccnu.demo.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpriderController {
    @Autowired
    private Spider spider;
    @RequestMapping("/spider")
    public void spider(){
        spider.movieSpider();
    }
}
