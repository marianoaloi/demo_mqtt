package br.com.aloi.demo_mqtt.controller;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
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
