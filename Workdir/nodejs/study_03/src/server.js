const WebSocket = require("ws");

const server = new WebSocket.Server({ port: 8081 });

server.on("connection", (ws) => {
    console.log("Client connected");

    // 클라이언트가 보낸 메시지 처리
    ws.on("message", (message) => {
        console.log(`Received message: ${message}`);
    });

    // 주기적으로 클라이언트에 메시지 전송 (1초마다)
    setInterval(() => {
        ws.send(`Message from server at ${new Date().toLocaleTimeString()}`);
    }, 1000);
});

console.log("WebSocket server is running on ws://localhost:8081");
