const { User, DetalleDoctor } = require('../models/User');

const generarClaveUnica = () => {
  const longitud = 8;
  let clave = '';
  const caracteres = '0123456789';

  for (let i = 0; i < longitud; i++) {
    const indice = Math.floor(Math.random() * caracteres.length);
    clave += caracteres.charAt(indice);
  }

  return clave;
};

exports.registrarDetalleDoctor = async (req, res) => {
  try {
    const { usuario_id } = req.params;

    // Verificar si el usuario es un doctor
    const usuarioDoctor = await User.findByPk(usuario_id);
    if (!usuarioDoctor || usuarioDoctor.rol_id !== 2) {
      return res.status(404).json({ error: 'El usuario especificado no es un doctor' });
    }

    // Verificar si ya existe un detalle de doctor para el usuario_id especificado

    const detalleExistenteDoc = await DetalleDoctor.findOne({ where: { usuario_id } });
    if (detalleExistenteDoc) {
      return res.status(400).json({ error: 'Ya existe un detalle de doctor registrado para este usuario' });
    }

    // Generar clave única
    let clave_unica = generarClaveUnica();

    // Verificar si la clave única ya existe en la tabla DetalleDoctor
    let detalleExistente = await DetalleDoctor.findOne({ where: { clave_unica } });
    while (detalleExistente) {
      clave_unica = generarClaveUnica();
      detalleExistente = await DetalleDoctor.findOne({ where: { clave_unica } });
    }

    const newDetalleDoctor = await DetalleDoctor.create({
      clave_unica,
      usuario_id
    });

    res.status(201).json({ message: 'Registro Completo', clave_unica });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
