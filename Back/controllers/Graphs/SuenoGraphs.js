const { Sueño, PastillasDormir} = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerPastillaDormir = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    const fechaObj = new Date(fecha);
    
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    const usuarioSueno = await Sueño.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj]
        }
      },
      include: {
        model: PastillasDormir,
        attributes: ['pastilla'],
        as: 'pastilla'
      }
    });

    if (!usuarioSueno || usuarioSueno.length === 0) {
      return res.status(404).json({ error: 'No hay Sueños registrados para este Usuario en los últimos 5 días' });
    }

    const pastillaSueno = usuarioSueno.map(sueno => sueno.pastilla.pastilla);
    res.status(200).json({ PastillasSueno: pastillaSueno });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

exports.ObtenerhorasSueno = async (req, res) => {
  try {
    const { usuario_id,fecha} = req.body;

    const fechaObj = new Date(fecha);
    
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);

    const usuarioSueno = await Sueño.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj] // Entre fechaInicio y fechaObj
        }
      },
      attributes: ['horas'],
    });

    if (!usuarioSueno || usuarioSueno.length === 0) {
      return res.status(404).json({ error: 'No hay Sueños registrados para este Usuario en los últimos 5 días' });
    }

    const horas = usuarioSueno.map(sueno => sueno.horas.toString());
    res.status(200).json({ Horas: horas });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}
