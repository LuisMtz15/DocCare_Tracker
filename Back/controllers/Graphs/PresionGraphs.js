const {PresionArterial} = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerDiastolica = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    const fechaObj = new Date(fecha);
    
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    const usuarioPresion = await PresionArterial.findAll({
      where: {
        usuario_id: usuario_id,
        fecha: {
          [Op.between]: [fechaInicio, fechaObj]
        }
      },
      attributes: ['diastolica'],
    });

    if (!usuarioPresion || usuarioPresion.length === 0) {
      return res.status(404).json({ error: 'No hay Presiones registrados para este Usuario en los últimos 5 días' });
    }

    const diastolica = usuarioPresion.map(presion => presion.diastolica.toString());
    res.status(200).json({ Diastolicas: diastolica });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
exports.ObtenerSistolica = async (req, res) => {
    try {
      const { usuario_id, fecha } = req.body;
  
      const fechaObj = new Date(fecha);
      
      const fechaInicio = new Date(fechaObj);
      fechaInicio.setDate(fechaInicio.getDate() - 5);
      
      const usuarioPresion = await PresionArterial.findAll({
        where: {
          usuario_id: usuario_id,
          fecha: {
            [Op.between]: [fechaInicio, fechaObj]
          }
        },
        attributes: ['sistolica'],
      });
  
      if (!usuarioPresion || usuarioPresion.length === 0) {
        return res.status(404).json({ error: 'No hay Presiones registrados para este Usuario en los últimos 5 días' });
      }
  
      const sistolica = usuarioPresion.map(presion => presion.sistolica.toString());
      res.status(200).json({ Sistolicas: sistolica });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  };