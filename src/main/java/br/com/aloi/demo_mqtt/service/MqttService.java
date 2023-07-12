package br.com.aloi.demo_mqtt.service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
public class MqttService {

    private static final String TOPIC = "home/front/garden";
    IMqttClient publisher;
    private String username = "maloi";
    private String password = "maloi";

    public MqttService() {
        String publisherId = UUID.randomUUID().toString();
        try {
            publisher = new MqttClient("tcp://server2:1883", publisherId);
            MqttConnectOptions options = new MqttConnectOptions();

            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());

            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startMethod();
    }

    private void startMethod() {
        CountDownLatch receivedSignal = new CountDownLatch(10);
        try {
            publisher.subscribe(TOPIC, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                // ... payload handling omitted
                System.out.println(new String(payload));
                receivedSignal.countDown();
            });
            receivedSignal.await(1, TimeUnit.MINUTES);
        } catch (MqttException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String createMessage(String str) {
        if (!publisher.isConnected()) {
            return null;
        }
        // MqttMessage msg = readEngineTemp();
        MqttMessage msg = new MqttMessage(str .getBytes());
        msg.setQos(0);
        msg.setRetained(true);
        try {
            publisher.publish(TOPIC, msg);
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    Random rnd = new Random();

    private MqttMessage readEngineTemp() {
        double temp = 80 + rnd.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temp)
                .getBytes();
        return new MqttMessage(payload);
    }
}
