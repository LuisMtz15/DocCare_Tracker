const jwt = require("jsonwebtoken");


exports.verificarToken = (role) => (req,res,next) =>{

    const token = req.headers["auth"] || null

    if (!token){
        return res.status(401).json({ message: "Unauthorized" });
    }

    jwt.verify(token, "secretkey", (err, decoded) => {
        if (err) {
          return res.status(401).json({ message: "Invalid token" });
        }
        req.user = decoded;
        // Si es administrador tiene acceso a cualquier ruta. 
        /*if (decoded.role == "admin"){
            next()
        }*/
        
       if (decoded.role != role){
            return res.status(401).json({ message: "Unauthorized" });
        }
        else {
        next();
        }
      });

}

exports.verificarToken_CU = (role) => (req,res,next) =>{

    const token = req.headers["auth"] || null

    if (!token){
        return res.status(401).json({ message: "Unauthorized" });
    }

    jwt.verify(token, "secretkey", (err, decoded) => {
        if (err) {
          return res.status(401).json({ message: "Invalid token" });
        }
        req.user = decoded;
        // Si es administrador tiene acceso a cualquier ruta. 
        if (decoded.role == "Doctor"){
            next()
        }
       else if (decoded.role != role){
            return res.status(401).json({ message: "Unauthorized" });
        }
        else {
        next();
        }
      });

}