const {sintomasAnsiedad ,intensidadesAnsiedad ,Ansiedad} = require('../../models/Datosmain');


exports.AgregarAnsiedad = async (req, res) => {
    try {
        const {usuario_id, sintoma, intensidad, hora, fecha, nota} = req.body;

        const sintomaExistente = await sintomasAnsiedad.findOne({ where: { sintoma } });
        const intensidadExistente = await intensidadesAnsiedad.findOne({ where: { intensidad } });

        if (!sintomaExistente) {
            return res.status(404).json({ error: 'El dato del sintoma no es válido' });
        }
        if (!intensidadExistente) {
            return res.status(404).json({ error: 'La intensidad de la ansiedad especificada no existe' });
        }

        const newAnsiedad = await Ansiedad.create({
            usuario_id,
            sintoma_id: sintomaExistente.id,
            intensidad_id: intensidadExistente.id,
            hora,
            fecha,
            nota
        });

        res.status(201).json({message: 'Registro de Ansiedad exitoso'});


        } catch (error) {
        res.status(400).json({ error: error.message });
        }
};

exports.LeerAnsiedad = async (req, res) => {
    try {
        const { Usuario_id } = req.params;

        const Ansiedades = await Ansiedad.findAll({
            where: { usuario_id: Usuario_id },
            include: [
                {
                    model: sintomasAnsiedad,
                    attributes: ['sintoma'],
                    as: 'sintoma'
                },
                {
                    model: intensidadesAnsiedad,
                    attributes: ['intensidad'],
                    as: 'intensidad'
                }
            ],
            attributes: ['id', 'fecha', 'hora', 'nota'],
            order: [['fecha', 'DESC']]
        });

        if (!Ansiedades || Ansiedades.length === 0) {
            return res.status(404).json({ error: 'No hay Ansiedades para este Usuario' });
        }

        const AnsiedadesFormateadas = Ansiedades.map(Ansiedad => ({
            id: Ansiedad.id,
            fecha: Ansiedad.fecha,
            hora: Ansiedad.hora,
            nota: Ansiedad.nota,
            sintoma_ansiedad: Ansiedad.sintoma ? Ansiedad.sintoma.sintoma : null,
            intensidad_ansiedad: Ansiedad.intensidad ? Ansiedad.intensidad.intensidad : null
        }));
        
        res.status(200).json(AnsiedadesFormateadas);

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
};


exports.ModificarAnsiedad = async (req, res) => {
    try {
        const { id_ansiedad, sintoma, intensidad, hora, nota } = req.body;

        const ansiedadExistente = await Ansiedad.findByPk(id_ansiedad);

        if (!ansiedadExistente) {
            return res.status(404).json({ error: 'La ansiedad no existe' });
        }

        const sintomaExistente = await sintomasAnsiedad.findOne({ where: { sintoma } });
        const intensidadExistente = await intensidadesAnsiedad.findOne({ where: { intensidad } });

        if (!sintomaExistente) {
            return res.status(404).json({ error: 'El dato del sintoma no es válido' });
        }
        if (!intensidadExistente) {
            return res.status(404).json({ error: 'La intensidad de la ansiedad especificada no existe' });
        }

        await ansiedadExistente.update({
            sintoma_id: sintomaExistente.id,
            intensidad_id: intensidadExistente.id,
            hora,
            nota
        });

        res.status(200).json({ message: 'Ansiedad modificada correctamente' });

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
}

exports.EliminarAnsiedad = async (req, res) => {
    try {
        const { id_ansiedad } = req.params;
    
        const ansiedad = await Ansiedad.findByPk(id_ansiedad);
    
        if (!ansiedad) {
            return res.status(404).json({ error: 'La ansiedad no existe' });
        }
    
        await ansiedad.destroy();
    
        res.status(200).json({message: 'Ansiedad eliminada correctamente' });
    
        } catch (error) {
        res.status(400).json({ error: error.message });
        }
    }

    exports.filtroporfechaAnsiedad = async (req, res) => {
        try {
          const { fecha, usuario_id } = req.body;
      
          const ansiedades = await Ansiedad.findAll({
            where: { fecha: fecha, usuario_id: usuario_id },
            include: [
              {
                model: sintomasAnsiedad,
                attributes: ['sintoma'],
                as: 'sintoma'
              },
              {
                model: intensidadesAnsiedad,
                attributes: ['intensidad'],
                as: 'intensidad'
              }
            ],
            attributes: ['id', 'fecha', 'hora', 'nota']
          });
      
          if (!ansiedades || ansiedades.length === 0) {
            return res.status(404).json({ error: 'No hay ansiedades para esta fecha o Usuario' });
          }
      
          const ansiedadesFormateadas = ansiedades.map(ansiedad => ({
            id: ansiedad.id,
            fecha: ansiedad.fecha,
            hora: ansiedad.hora,
            nota: ansiedad.nota,
            sintoma_ansiedad: ansiedad.sintoma ? ansiedad.sintoma.sintoma : null,
            intensidad_ansiedad: ansiedad.intensidad ? ansiedad.intensidad.intensidad : null
          }));
      
          res.status(200).json(ansiedadesFormateadas);
        } catch (error) {
          res.status(400).json({ error: error.message });
        }
    };
