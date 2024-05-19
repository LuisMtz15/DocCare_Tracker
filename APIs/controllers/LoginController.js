const {User, Role, DetalleDoctor, DetalleUsuario} = require('../models/User');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

exports.LoginController = async (req, res) => {
  try {
    const { mail, password } = req.body;
    let clave;
    
    // Verificar si el usuario existe
    const usuario = await User.findOne({ where: { email: mail } });
    if (!usuario) {
      return res.status(404).json({ error: 'El usuario especificado no existe' });
    }

    const match = await bcrypt.compare(password, usuario.password);
    if (!match) {
      return res.status(401).json({ message: 'Authentication failed' });
    }

    const nombreRol = await Role.findByPk(usuario.rol_id, { attributes: ['nombre'] });
  
    //usuario.rol_id = nombreRol.nombre;
    const token = jwt.sign({ id: usuario, role: nombreRol.nombre}, 'secretkey', {expiresIn: '1h' });

    if (nombreRol.nombre === 'Usuario') {
      
      const detalleusuario = await DetalleUsuario.findOne({ where: { usuario_id: usuario.id } });
      clave = detalleusuario.clave_unica;
    }
    else{
      const detalledoctor = await DetalleDoctor.findOne({ where: { usuario_id: usuario.id } });
      clave = detalledoctor.clave_unica;
    }
    

    const responseData = {
      status: 'Succesful',
      id: usuario.id,
      role: nombreRol.nombre,
      token: token,
      clave: clave,
      nombre: usuario.nombre,
      apellidos: usuario.apellido_paterno,
    };

    res.status(201).json(responseData);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
