package com.coodesh.lab.fullstackchallenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class FullstackchallengeApplication

fun main(args: Array<String>) {
    runApplication<FullstackchallengeApplication>(*args)
}
