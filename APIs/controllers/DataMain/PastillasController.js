const {TiempoPastillas ,PeriodoPastillas ,Pastillas} = require('../../models/Datosmain');

exports.AgregarPastillas = async (req, res) => {
    try {
        const {usuario_id, nombre, dosis, tiempo, periodo,fecha} = req.body;

        const tiempoExistente = await TiempoPastillas.findOne({ where: { tiempo } });
        const periodoExistente = await PeriodoPastillas.findOne({ where: { periodo } });

        if (!tiempoExistente) {
            return res.status(404).json({ error: 'El tiempo especificado no existe' });
        }
        if (!periodoExistente) {
            return res.status(404).json({ error: 'El periodo especificado no existe' });
        }

        const newPastilla = await Pastillas.create({
            usuario_id,
            nombre,
            dosis,
            tiempo_id: tiempoExistente.id,
            periodo_id: periodoExistente.id,
            fecha
        });

        res.status(201).json({message: 'Registro de Pastilla exitoso'});
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.LeerPastillas = async (req, res) => {
    try {
        const { Usuario_id } = req.params;

        const pastilla = await Pastillas.findAll({
            where: { usuario_id: Usuario_id },
            include: [
                {
                    model: TiempoPastillas,
                    attributes: ['tiempo'],
                    as: 'tiempo'
                },
                {
                    model: PeriodoPastillas,
                    attributes: ['periodo'],
                    as: 'periodo'
                }
            ],
            attributes: ['id', 'nombre', 'dosis', 'fecha']
        });

        if (!pastilla || pastilla.length === 0) {
            return res.status(404).json({ error: 'No hay Pastillas para este Usuario' });
        }

        const PastillasFormateadas = pastilla.map(pastilla => ({
            id: pastilla.id,
            nombre: pastilla.nombre,
            dosis: pastilla.dosis,
            fecha: pastilla.fecha,
            tiempo_pastilla: pastilla.tiempo ? pastilla.tiempo.tiempo : null,
            periodo_pastilla: pastilla.periodo ? pastilla.periodo.periodo : null
        }));

        res.status(200).json(PastillasFormateadas);

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.ModificarPastillas = async (req, res) => {
    try {
        const { id_pastilla, nombre, dosis, tiempo, periodo } = req.body;

        const pastillaExistente = await Pastillas.findByPk(id_pastilla);

        if (!pastillaExistente) {
            return res.status(404).json({ error: 'La pastilla no existe' });
        }

        const tiempoExistente = await TiempoPastillas.findOne({ where: { tiempo } });
        const periodoExistente = await PeriodoPastillas.findOne({ where: { periodo } });

        if (!tiempoExistente) {
            return res.status(404).json({ error: 'El tiempo especificado no existe' });
        }
        if (!periodoExistente) {
            return res.status(404).json({ error: 'El periodo especificado no existe' });
        }

        await pastillaExistente.update({
            nombre,
            dosis,
            tiempo_id: tiempoExistente.id,
            periodo_id: periodoExistente.id
        });

        res.status(200).json({ message: 'Pastilla modificada correctamente' });

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
}

exports.EliminarPastillas = async (req, res) => {
    try {
        const { id_pastilla } = req.params;

        const pastilla = await Pastillas.findByPk(id_pastilla);

        if (!pastilla) {
            return res.status(404).json({ error: 'La pastilla no existe' });
        }

        await pastilla.destroy();

        res.status(200).json({message: 'Pastilla eliminada correctamente' });

    } catch (error) {
        res.status(400).json({ error: error.message });
    }
}

exports.filtroporfechaPastillas = async (req, res) => {
    try {
      const { fecha, usuario_id } = req.body;
  
      const pastillas = await Pastillas.findAll({
        where: { fecha: fecha, usuario_id: usuario_id },
        include: [
            {
                model: TiempoPastillas,
                attributes: ['tiempo'],
                as: 'tiempo'
            },
            {
                model: PeriodoPastillas,
                attributes: ['periodo'],
                as: 'periodo'
            }
        ],
        attributes: ['id', 'nombre', 'fecha', 'dosis'], 
      });
  
      if (!pastillas || pastillas.length === 0) {
        return res.status(404).json({ error: 'No hay pastillas para esta fecha o Usuario' });
      }
     
  
      const pastillasFormateadas = pastillas.map(pastilla => ({
        id: pastilla.id,
        nombre: pastilla.nombre,
        fecha: pastilla.fecha,
        dosis: pastilla.dosis,
        tiempo_pastilla: pastilla.tiempo ? pastilla.tiempo.tiempo : null,
        periodo_pastilla: pastilla.periodo ? pastilla.periodo.periodo : null
      }));

  
      res.status(200).json(pastillasFormateadas);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }




