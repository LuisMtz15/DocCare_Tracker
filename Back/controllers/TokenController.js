const jwt = require("jsonwebtoken");



exports.verificarTokenparaLogin = async (req, res) => {
  try {
    const { token } = req.body;

    jwt.verify(token, "secretkey", (err, decoded) => {
      if (err) {
        return res.status(401).json({ message: "Invalid token" });
      }
      
      res.status(201).json({ message: 'Llave válida'});
    });
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};


