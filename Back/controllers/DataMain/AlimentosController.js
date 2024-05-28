const { Alimentos, TiposPorciones} = require('../../models/Datosmain');

exports.AgregarAlimentos = async (req, res) => {
  try {
    const { usuario_id, nombre, tipo_porcion, fecha } = req.body;

    const tipoPorcion = await TiposPorciones.findOne({ where: { tipo: tipo_porcion } });

    if (!tipoPorcion) {
      return res.status(404).json({ error: 'El tipo de porción especificado no existe' });
    }

    const newAlimento = await Alimentos.create({
      usuario_id,
      nombre,
      tipo_porcion_id: tipoPorcion.id,
      fecha
    });

    res.status(201).json({message:'Registro de Alimentos exitoso'});
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.LeerAlimentos = async (req, res) => {
  try {
    const { Usuario_id } = req.params;

    const alimentos = await Alimentos.findAll({
      where: { usuario_id: Usuario_id },
      include: {
        model: TiposPorciones,
        attributes: ['tipo'], 
        as: 'tipo_porcion',
      },
      attributes: ['id', 'nombre', 'fecha'],
      order: [['fecha', 'DESC']],
    });

    if (!alimentos || alimentos.length === 0) {
      return res.status(404).json({ error: 'No hay alimentos para este Usuario' });
    }

    const alimentosFormateados = alimentos.map(alimento => ({
      id: alimento.id,
      nombre: alimento.nombre,
      fecha: alimento.fecha,
      tipo_porcion: alimento.tipo_porcion ? alimento.tipo_porcion.tipo : null,
    }));

    res.status(200).json(alimentosFormateados);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};



exports.ModificarAlimentos = async (req, res) => {
  try {
    const { id_alimento, nombre, tipo_porcion } = req.body;

    const alimentoExistente = await Alimentos.findByPk(id_alimento);

    if (!alimentoExistente) {
      return res.status(404).json({ error: 'El alimento no existe' });
    }

    const tipoPorcion = await TiposPorciones.findOne({ where: { tipo: tipo_porcion } });

    if (!tipoPorcion) {
      return res.status(404).json({ error: 'El tipo de porción especificado no existe' });
    }

    await alimentoExistente.update({
      nombre,
      tipo_porcion_id: tipoPorcion.id,
    });

    res.status(200).json({ message: 'Alimento modificado correctamente' });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};



exports.EliminarAlimentos = async (req, res) => {
  try {
    const { id_alimento } = req.params; 

    const alimento = await Alimentos.findByPk(id_alimento);

    if (!alimento) {
      return res.status(404).json({ error: 'El alimento no existe' });
    }

    await alimento.destroy();

    res.status(200).json({message:'Alimento eliminado correctamente'});
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.filtroporfechaAlimento = async (req, res) => {
  try {
    const { fecha, usuario_id } = req.body;

    const alimentos = await Alimentos.findAll({
      where: { fecha: fecha, usuario_id: usuario_id },
      include: {
        model: TiposPorciones,
        attributes: ['tipo'], 
        as: 'tipo_porcion',
      },
      attributes: ['id', 'nombre', 'fecha'], 
    });


    if (!alimentos || alimentos.length === 0) {
      return res.status(404).json({ error: 'No hay alimentos para esta fecha o Usuario' });
    }

    const alimentosFormateados = alimentos.map(alimento => ({
      id: alimento.id,
      nombre: alimento.nombre,
      fecha: alimento.fecha,
      tipo_porcion: alimento.tipo_porcion ? alimento.tipo_porcion.tipo : null,
    }));

    res.status(200).json(alimentosFormateados);
    
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

