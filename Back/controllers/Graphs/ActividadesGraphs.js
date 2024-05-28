const { ActividadesFisicas, TiposActividades, IntensidadesActividades} = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerTipoActividad = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    // Consulta para obtener los alimentos de los últimos 5 días
    const usuarioActividad = await ActividadesFisicas.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
        }
      },
      include: {
        model: TiposActividades,
        attributes: ['tipo'], 
        as: 'tipo_actividad',
      }
    });

    if (!usuarioActividad || usuarioActividad.length === 0) {
      return res.status(404).json({ error: 'No hay Actividades registrados para este Usuario en los últimos 5 días' });
    }

    // Devolver los tipos de porción encontrados
    const tiposActividad = usuarioActividad.map(actividad => actividad.tipo_actividad.tipo);
    res.status(200).json({ tiposActividad: tiposActividad });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.ObtenerIntensidad = async (req, res) => {
  try {
    const { usuario_id,fecha} = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);

    // Consulta para obtener los alimentos de los últimos 5 días
    const usuarioActividad = await ActividadesFisicas.findAll({
        where: {
          usuario_id: usuario_id,
          fecha: {
            [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
          }
        },
        include: {
            model: IntensidadesActividades,
            attributes: ['intensidad'],
            as: 'intensidad_actividad'
        }
    });

    if (!usuarioActividad || usuarioActividad.length === 0) {
    return res.status(404).json({ error: 'No hay Actividades registrados para este Usuario en los últimos 5 días' });
    }
    // Devolver los tipos de porción encontrados
    const intensidadesActividad = usuarioActividad.map(actividad => actividad.intensidad_actividad.intensidad);
    res.status(200).json({ IntensidadesActividad: intensidadesActividad });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}
