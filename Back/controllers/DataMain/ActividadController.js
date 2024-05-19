const { EmocionesActividades, TiposActividades,IntensidadesActividades,ActividadesFisicas} = require('../../models/Datosmain');


exports.AgregarActividad = async (req, res) => {
  try {
    const {usuario_id, tipo_actividad, emocion_actividad, intensidad_actividad, fecha, duracion} = req.body;

    // Verificar si el tipo de porción especificado existe
    const actividad = await TiposActividades.findOne({ where: { tipo: tipo_actividad } });
    const emocion = await EmocionesActividades.findOne({ where: { emocion: emocion_actividad } });
    const intensidad = await IntensidadesActividades.findOne({ where: { intensidad: intensidad_actividad } });

    if (!actividad) {
        return res.status(404).json({ error: 'El tipo de actividad especificado no existe' });
    }
    if (!emocion) {
        return res.status(404).json({ error: 'La emocion especificada no existe' });
    }
    if (!intensidad) {
        return res.status(404).json({ error: 'La intensidad especificada no existe' });
    }

    const newActividad = await ActividadesFisicas.create({
        usuario_id,
        tipo_actividad_id: actividad.id,
        emocion_actividad_id: emocion.id,
        intensidad_actividad_id: intensidad.id,
        fecha,
        duracion
    });

    res.status(201).json({message:'Registro de Actividad exitoso'});
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.LeerActividad = async (req, res) => {
  try {
    const { Usuario_id } = req.params;

    const Actividades = await ActividadesFisicas.findAll({
        where: { usuario_id: Usuario_id },
        include: [
            {
                model: TiposActividades,
                attributes: ['tipo'],
                as: 'tipo_actividad'
            },
            {
                model: EmocionesActividades,
                attributes: ['emocion'],
                as: 'emocion_actividad'
            },
            {
                model: IntensidadesActividades,
                attributes: ['intensidad'],
                as: 'intensidad_actividad'
            }
        ],
        attributes: ['id', 'fecha', 'duracion']
    });

    if (!Actividades || Actividades.length === 0) {
        return res.status(404).json({ error: 'No hay Actividades para este Usuario' });
    }

    const ActividadesFormateadas = Actividades.map(Actividad => ({
        id: Actividad.id,
        fecha: Actividad.fecha,
        duracion: Actividad.duracion,
        tipo_actividad: Actividad.tipo_actividad ? Actividad.tipo_actividad.tipo : null,
        emocion_actividad: Actividad.emocion_actividad ? Actividad.emocion_actividad.emocion : null,
        intensidad_actividad: Actividad.intensidad_actividad ? Actividad.intensidad_actividad.intensidad : null
    }));

    res.status(200).json(ActividadesFormateadas);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};


exports.ModificarActividad = async (req, res) => {
  try {
    const { id_actividad, tipo_actividad, emocion_actividad, intensidad_actividad, duracion } = req.body;

    const actividadExistente = await ActividadesFisicas.findByPk(id_actividad);

    if (!actividadExistente) {
        return res.status(404).json({ error: 'La actividad no existe' });
    }

    const actividad = await TiposActividades.findOne({ where: { tipo: tipo_actividad } });
    const emocion = await EmocionesActividades.findOne({ where: { emocion: emocion_actividad } });
    const intensidad = await IntensidadesActividades.findOne({ where: { intensidad: intensidad_actividad } });

    if (!actividad) {
        return res.status(404).json({ error: 'El tipo de actividad especificado no existe' });
    }
    if (!emocion) {
        return res.status(404).json({ error: 'La emocion especificada no existe' });
    }
    if (!intensidad) {
        return res.status(404).json({ error: 'La intensidad especificada no existe' });
    }

    await actividadExistente.update({
        tipo_actividad_id: actividad.id,
        emocion_actividad_id: emocion.id,
        intensidad_actividad_id: intensidad.id,
        duracion
    });

    res.status(200).json({ message: 'Actividad modificada correctamente' });

  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};


exports.EliminarActividad = async (req, res) => {
  try {
    const { id_actividad } = req.params;

    const actividad = await ActividadesFisicas.findByPk(id_actividad);

    if (!actividad) {
        return res.status(404).json({ error: 'La actividad no existe' });
    }

    await actividad.destroy();

    res.status(200).json({message:'Actividad eliminada correctamente'});
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.filtroporfechaActividad = async (req, res) => {
  try {
    const { fecha, usuario_id } = req.body;

    const actividades = await ActividadesFisicas.findAll({
      where: { fecha: fecha, usuario_id: usuario_id },
      include: [
        {
          model: TiposActividades,
          attributes: ['tipo'],
          as: 'tipo_actividad'
        },
        {
          model: EmocionesActividades,
          attributes: ['emocion'],
          as: 'emocion_actividad'
        },
        {
          model: IntensidadesActividades,
          attributes: ['intensidad'],
          as: 'intensidad_actividad'
        }
      ],
      attributes: ['id', 'fecha', 'duracion']
    });

    if (!actividades || actividades.length === 0) {
      return res.status(404).json({ error: 'No hay actividades para esta fecha o Usuario' });
    }

    const actividadesFormateadas = actividades.map(actividad => ({
      id: actividad.id,
      fecha: actividad.fecha,
      duracion: actividad.duracion,
      tipo_actividad: actividad.tipo_actividad ? actividad.tipo_actividad.tipo : null,
      emocion_actividad: actividad.emocion_actividad ? actividad.emocion_actividad.emocion : null,
      intensidad_actividad: actividad.intensidad_actividad ? actividad.intensidad_actividad.intensidad : null
    }));

    res.status(200).json(actividadesFormateadas);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}


