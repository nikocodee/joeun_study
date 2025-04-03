import React, { useEffect, useState } from 'react';

function App() {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    // WebSocket 연결
    const ws = new WebSocket('ws://localhost:8000/ws');

    ws.onopen = () => {
      console.log('✅ WebSocket 연결됨');
      ws.send('hello'); // ping 용도
    };

    ws.onmessage = (event) => {
      const data = JSON.parse(event.data);
      setMessages((prev) => [...prev, data]);
    };

    ws.onclose = () => {
      console.log('❌ WebSocket 연결 종료');
    };

    return () => {
      ws.close();
    };
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h2>📡 Kafka 실시간 메시지</h2>
      <ul>
        {messages.map((msg, idx) => (
          <li key={idx}>
            📨 user_id: <strong>{msg.user_id}</strong>, action: <strong>{msg.action}</strong>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;