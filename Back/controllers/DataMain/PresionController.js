const {EmocionesPresion ,PresionArterial} = require('../../models/Datosmain');

exports.AgregarPresion = async (req, res) => {
    try {
        const {usuario_id, sistolica, diastolica, hora, emocion, fecha} = req.body;

        const emocionExistente = await EmocionesPresion.findOne({ where: { emocion } });

        if (!emocionExistente) {
            return res.status(404).json({ error: 'La emocion especificada no existe' });
        }

        const newPresion = await PresionArterial.create({
            usuario_id,
            sistolica,
            diastolica,
            hora,
            emocion_id: emocionExistente.id,
            fecha
        });

        res.status(201).json({message: 'Registro de Presion exitoso'});
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};


exports.LeerPresion = async (req, res) => {
    try {
        const { Usuario_id } = req.params;

        const presion = await PresionArterial.findAll({
            where: { usuario_id: Usuario_id },
            include: [
                {
                    model: EmocionesPresion,
                    attributes: ['emocion'],
                    as: 'emocion'
                }
            ],
            attributes: ['id', 'sistolica', 'diastolica', 'hora', 'fecha'],
            order: [['fecha', 'DESC']]
        });

        if (!presion || presion.length === 0) {
            return res.status(404).json({ error: 'No hay Presiones para este Usuario' });
        }

        const PresionesFormateadas = presion.map(presion => ({
            id: presion.id,
            sistolica: presion.sistolica,
            diastolica: presion.diastolica,
            hora: presion.hora,
            fecha: presion.fecha,
            emocion_presion: presion.emocion ? presion.emocion.emocion : null
        }));

        res.status(200).json(PresionesFormateadas);

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};


exports.ModificarPresion = async (req, res) => {
    try {
        const { id_presion, sistolica, diastolica, hora, emocion } = req.body;

        const presionExistente = await PresionArterial.findByPk(id_presion);

        if (!presionExistente) {
            return res.status(404).json({ error: 'La presion no existe' });
        }

        const emocionExistente = await EmocionesPresion.findOne({ where: { emocion } });

        if (!emocionExistente) {
            return res.status(404).json({ error: 'La emocion especificada no existe' });
        }

        await presionExistente.update({
            sistolica,
            diastolica,
            hora,
            emocion_id: emocionExistente.id
        });

        res.status(200).json({ message: 'Presion modificada correctamente' });

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.EliminarPresion = async (req, res) => {
    try {
        const { id_presion } = req.params;

        const presion = await PresionArterial.findByPk(id_presion);

        if (!presion) {
            return res.status(404).json({ error: 'La presion no existe' });
        }

        await presion.destroy();

        res.status(200).json({message: 'Presion eliminada correctamente' });

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.filtroporfechaPresion = async (req, res) => {
    try {
      const { fecha, usuario_id } = req.body;
  
      const presiones = await PresionArterial.findAll({
        where: { fecha: fecha, usuario_id: usuario_id },
        include: {
          model: EmocionesPresion,
          attributes: ['emocion'], 
          as: 'emocion',
        },
        attributes: ['id', 'sistolica', 'diastolica', 'hora', 'fecha'], 
      });
  
      if (!presiones || presiones.length === 0) {
        return res.status(404).json({ error: 'No hay presiones para esta fecha o Usuario' });
      }
  
      const presionesFormateadas = presiones.map(presion => ({
        id: presion.id,
        sistolica: presion.sistolica,
        diastolica: presion.diastolica,
        hora: presion.hora,
        fecha: presion.fecha,
        emocion_presion: presion.emocion ? presion.emocion.emocion : null,
      }));
  
      res.status(200).json(presionesFormateadas);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }
