const {Ansiedad, intensidadesAnsiedad,sintomasAnsiedad } = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerIntensidadAnsiedad = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    // Consulta para obtener los alimentos de los últimos 5 días
    const usuarioAnsiedad = await Ansiedad.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
        }
      },
      include: {
        model: intensidadesAnsiedad,
        attributes: ['intensidad'],
        as: 'intensidad'
      }
    });

    if (!usuarioAnsiedad || usuarioAnsiedad.length === 0) {
      return res.status(404).json({ error: 'No hay Actividades registrados para este Usuario en los últimos 5 días' });
    }

    // Devolver los tipos de porción encontrados
    const intensidadAnsiedad = usuarioAnsiedad.map(ansiedad => ansiedad.intensidad.intensidad);
    res.status(200).json({ Intensidades: intensidadAnsiedad });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.ObtenerSintomaAnsiedad = async (req, res) => {
  try {
    const { usuario_id,fecha} = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);

    // Consulta para obtener los alimentos de los últimos 5 días
    const usuarioAnsiedad = await Ansiedad.findAll({
        where: {
          usuario_id: usuario_id,
          fecha: {
            [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
          }
        },
        include: {
            model: sintomasAnsiedad,
            attributes: ['sintoma'],
            as: 'sintoma'
        }
    });

    if (!usuarioAnsiedad || usuarioAnsiedad.length === 0) {
    return res.status(404).json({ error: 'No hay Actividades registrados para este Usuario en los últimos 5 días' });
    }

    // Devolver los tipos de porción encontrados
    const sintomas_Ansiedad = usuarioAnsiedad.map(ansiedad => ansiedad.sintoma.sintoma);
    res.status(200).json({ Sintomas: sintomas_Ansiedad });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}
