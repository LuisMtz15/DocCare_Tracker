const { User, Sexo, DetalleUsuario} = require('../models/User');

exports.registrarDetalleUsuario = async (req, res) => {
  try {
    const { usuario_id, altura, peso, circunferencia_abdominal, clave_unica, sexo } = req.body;

    // Verificar si el usuario y sexo existen antes de crear los detalles
    const usuarioExistente = await User.findByPk(usuario_id);

    const sexoExistente = await Sexo.findOne({ where: { sexo: sexo } });

    if (!usuarioExistente) {
      return res.status(404).json({ error: 'El usuario especificado no existe' });
    }

    if (!sexoExistente) {
      return res.status(404).json({ error: 'El sexo especificado no existe' });
    }

    if (usuarioExistente.rol_id !== 1) {
      return res.status(404).json({ error: 'Este registro no corresponde a un usuario normal' });
    }
    
    // Verificar si ya existe un detalle de usuario para el usuario_id especificado
    const detalleExistente = await DetalleUsuario.findOne({ where: { usuario_id } });
    if (detalleExistente) {
      return res.status(400).json({ error: 'Ya existe un detalle de usuario registrado para este usuario' });
    }

    const newDetalleUsuario = await DetalleUsuario.create({
      usuario_id,
      altura,
      peso,
      circunferencia_abdominal,
      clave_unica,
      sexo_id: sexoExistente.id
    });

    // Enviar respuesta de "Registro Completo"
    res.status(201).json({ message: 'Registro Completo'});
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
