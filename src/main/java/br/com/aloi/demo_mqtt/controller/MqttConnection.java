package br.com.aloi.demo_mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aloi.demo_mqtt.service.MqttService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MqttConnection {

    @Autowired
    MqttService mqttservice;

    @RequestMapping("/find")
    public String getConnection(@RequestParam(name="msg", required =  true) final String msg)

    {
        return mqttservice.createMessage(msg);
    }
}
