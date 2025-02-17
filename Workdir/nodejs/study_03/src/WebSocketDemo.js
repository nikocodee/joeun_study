import React, { useState, useEffect } from "react";

function WebSocketDemo() {
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        // const socket = new WebSocket("wss://example.com/socket");
        const socket = new WebSocket("ws://localhost:8081"); // 로컬 환경 비보안 연결

        socket.onmessage = (event) => {
            setMessages((prevMessages) => [...prevMessages, event.data]);
        };

        return () => {
            socket.close();
        }; // 컴포넌트 언마운트 시 WebSocket 연결 종료
    }, []);

    return (
        <div>
            <h2>Messages:</h2>
            <ul>
                {messages.map((msg, index) => (
                    <li key={index}>{msg}</li>
                ))}
            </ul>
        </div>
    );
}

export default WebSocketDemo;
