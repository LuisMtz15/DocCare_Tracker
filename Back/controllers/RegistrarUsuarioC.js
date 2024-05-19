const {User,Role} = require('../models/User');

exports.registrarUsuario = async (req, res) => {
  try {
    const { nombre, apellido_paterno, apellido_materno, email, password, fecha_nacimiento, rol } = req.body;
    
    // Verificar si el rol existe antes de crear el usuario
    const rolExistente = await Role.findOne({ where: { nombre: rol } });

    if (!rolExistente) {
      return res.status(404).json({ message: 'El rol especificado no existe' });
    }

    const mailExistente = await User.findOne({ where: { email } });

    if (mailExistente) {
      return res.status(409).json({ message: 'El correo ya está registrado' });
    }

    const newUser = await User.create({
      nombre,
      apellido_paterno,
      apellido_materno,
      email,
      password,
      fecha_nacimiento,
      rol_id : rolExistente.id
    });

    res.status(201).json({ message: 'Registro inicial exitoso', usuario_id: newUser.id});
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};
