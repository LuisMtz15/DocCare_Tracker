const { Alimentos, TiposPorciones} = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerPorciones = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    // Consulta para obtener los alimentos de los últimos 5 días
    const usuarioAlimentos = await Alimentos.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
        }
      },
      include: {
        model: TiposPorciones,
        attributes: ['tipo'], 
        as: 'tipo_porcion',
      }
    });

    if (!usuarioAlimentos || usuarioAlimentos.length === 0) {
      return res.status(404).json({ error: 'No hay Alimentos registrados para este Usuario en los últimos 5 días' });
    }

    // Devolver los tipos de porción encontrados
    const tiposPorcion = usuarioAlimentos.map(alimento => alimento.tipo_porcion.tipo);
    res.status(200).json({ tipos_porcion: tiposPorcion });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.ObtenerFechas = async (req, res) => {
  try {
    const { usuario_id,fecha} = req.body;

    // Convertir la fecha recibida a formato de objeto Date
    const fechaObj = new Date(fecha);
    
    // Obtener la fecha 5 días antes
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);

    // Consulta para obtener lass fechas de los últimos 5 días
    const usuarioAlimentos = await Alimentos.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
        }
      },
      attributes: ['fecha'],
    });

    if (!usuarioAlimentos || usuarioAlimentos.length === 0) {
      return res.status(404).json({ error: 'No hay Alimentos registrados para este Usuario en los últimos 5 días' });
    }

    // Devolver los tipos de porción encontrados
    const fechas = usuarioAlimentos.map(alimento => alimento.fecha);
    res.status(200).json({ Fechas: fechas });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}
