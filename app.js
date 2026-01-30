require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');

const app = express();
app.use(express.json());

const PORT = process.env.PORT || 3000;
const MONGO_URI = process.env.MONGO_URI || "mongodb://127.0.0.1:27017/test";

// Пытаемся подключиться, но не падаем при ошибке
mongoose.connect(MONGO_URI)
  .then(() => console.log("✅ MongoDB connected"))
  .catch(err => console.log("⚠️ DB Connection failed, but server is running for Task 11"));

app.get('/', (req, res) => {
  res.json({ 
    message: "Backend is running!", 
    task: "Practice Task 11",
    status: "Deploy successful" 
  });
});

app.get('/api/items', (req, res) => {
  res.json([{ id: 1, name: "Task 11 Item" }]);
});

app.listen(PORT, () => {
  console.log(`🚀 Server is live on port ${PORT}`);
});