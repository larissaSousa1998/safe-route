const express = require("express");
const app = express(); // create express app
const path = require("path");

app.use(express.static(path.join(__dirname, "..", "public")));
app.use(express.static("pages"));
app.use((req, res, next) => {
  res.sendFile(path.join(__dirname, "..", "public","pages", "index.html"));
});


// start express server on port 80
app.listen(80,"172.31.18.116" ,() => {
  console.log("server started on port 80");
});
// app.listen(80,() => {
//   console.log("server started on port 80");
// });
