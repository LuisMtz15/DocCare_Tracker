const {Pastillas, TiempoPastillas} = require('../../models/Datosmain');
const { Op } = require('sequelize');

exports.ObtenerTiempoPastillas = async (req, res) => {
  try {
    const { usuario_id, fecha } = req.body;

    const fechaObj = new Date(fecha);
    
    const fechaInicio = new Date(fechaObj);
    fechaInicio.setDate(fechaInicio.getDate() - 5);
    
    const usuarioPastillas = await Pastillas.findAll({
      where: {
        usuario_id: usuario_id
      },
      include:
        {
            model: TiempoPastillas,
            attributes: ['tiempo'],
            as: 'tiempo'
        }
    });

    if (!usuarioPastillas || usuarioPastillas.length === 0) {
      return res.status(404).json({ error: 'No hay Pastillas registradas para este Usuario en los últimos 5 días' });
    }

    const timepoPastillas = usuarioPastillas.map(pastilla => pastilla.tiempo.tiempo);
    res.status(200).json({ TiempoPastillas: timepoPastillas });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
exports.ObtenerMedicamento = async (req, res) => {
    try {
      const { usuario_id, fecha } = req.body;
  
      const fechaObj = new Date(fecha);
      
      const fechaInicio = new Date(fechaObj);
      fechaInicio.setDate(fechaInicio.getDate() - 5);
      
      const usuarioPastillas = await Pastillas.findAll({
        where: {
          usuario_id: usuario_id
        },
        attributes: ['nombre'],
      });
  
      if (!usuarioPastillas || usuarioPastillas.length === 0) {
        return res.status(404).json({ error: 'No hay Pastillas registradas para este Usuario en los últimos 5 días' });
      }
  
      const Medicamentos = usuarioPastillas.map(pastilla => pastilla.nombre);
      res.status(200).json({ Medicamentos: Medicamentos });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  };