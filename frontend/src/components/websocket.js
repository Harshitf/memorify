import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let client = null;

export const connect = (onMessageReceived) => {
    client = new Client({
        webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
        onConnect: () => {
            console.log('✅ Connected to WebSocket');
            client.subscribe('/topic/messages', (message) => {
                if (message.body) onMessageReceived(message.body);
            });
        },
        debug: (str) => console.log(str),
    });
    client.activate();
};

export const sendMessage = (message) => {
    if (client && client.connected) {
        client.publish({ destination: '/app/sendMessage', body: message });
    }
};
