package com.example.demo

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class IndexController {
    private val usermap : MutableMap<String,Userinfo> = mutableMapOf()

    @PostConstruct
    fun init(){
        usermap["1"] = Userinfo("1","1")
        usermap["2"] = Userinfo("2","1")
        usermap["3"] = Userinfo("3","1")
    }

    @GetMapping(path = ["/user/{id}"])
    fun getUserInfo(@PathVariable("id") id : String) = usermap[id]

    @GetMapping(path = ["user/all"])
    fun getUserInfoAll() = ArrayList<Userinfo>(usermap.values)

    @PostMapping(path = ["/user/{id}"])
    fun postUserinfo(@PathVariable("id") id : String, @RequestParam("state") state: String) {
        val userinfo = usermap[id]
        userinfo?.state = state
    }
}