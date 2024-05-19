const {PastillasDormir ,CalidadesSueño ,Sueño} = require('../../models/Datosmain');


exports.AgregarSueño = async (req, res) => {
    try {
        const {usuario_id, pastilla, calidad_sueño, horas, fecha, horaDormir, horaDespertar} = req.body;

        const pastillaExistente = await PastillasDormir.findOne({ where: { pastilla } });
        const calidadExistente = await CalidadesSueño.findOne({ where: { emocion: calidad_sueño } });

        if (!pastillaExistente) {
            return res.status(404).json({ error: 'El dato de la pastilla no es válido' });
        }
        if (!calidadExistente) {
            return res.status(404).json({ error: 'La calidad de sueño especificada no existe' });
        }

        const newSueño = await Sueño.create({
            usuario_id,
            pastilla_id: pastillaExistente.id,
            calidad_sueño_id: calidadExistente.id,
            horas,
            fecha,
            horaDormir,
            horaDespertar
        });

        res.status(201).json({message: 'Registro de Sueño exitoso'});

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
};

exports.LeerSueño = async (req, res) => {
    try {
        const { Usuario_id } = req.params;

        const Sueños = await Sueño.findAll({
            where: { usuario_id: Usuario_id },
            include: [
                {
                    model: PastillasDormir,
                    attributes: ['pastilla'],
                    as: 'pastilla'
                },
                {
                    model: CalidadesSueño,
                    attributes: ['emocion'],
                    as: 'calidad'
                }
            ],
            attributes: ['id', 'fecha', 'horas', 'horaDormir', 'horaDespertar']
        });

        if (!Sueños || Sueños.length === 0) {
            return res.status(404).json({ error: 'No hay Sueños para este Usuario' });
        }

        const SueñosFormateados = Sueños.map(Sueño => ({
            id: Sueño.id,
            fecha: Sueño.fecha,
            horas: Sueño.horas,
            pastilla_sueño: Sueño.pastilla ? Sueño.pastilla.pastilla : null,
            calidad_sueño: Sueño.calidad ? Sueño.calidad.emocion : null,
            horaDormir: Sueño.horaDormir,
            horaDespertar: Sueño.horaDespertar
        }));

        res.status(200).json(SueñosFormateados);

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
};


exports.ModificarSueño = async (req, res) => {
    try {
        const { id_sueño, horas, calidad_sueño, pastilla, horaDormir, horaDespertar } = req.body;

        const sueñoExistente = await Sueño.findByPk(id_sueño);

        if (!sueñoExistente) {
            return res.status(404).json({ error: 'El sueño no existe' });
        }

        const pastillaExistente = await PastillasDormir.findOne({ where: { pastilla } });
        const calidadExistente = await CalidadesSueño.findOne({ where: { emocion: calidad_sueño } });

        if (!pastillaExistente) {
            return res.status(404).json({ error: 'El dato de la pastilla no es válido' });
        }
        if (!calidadExistente) {
            return res.status(404).json({ error: 'La calidad de sueño especificada no existe' });
        }

        await sueñoExistente.update({
            pastilla_id: pastillaExistente.id,
            calidad_sueño_id: calidadExistente.id,
            horas,
            horaDormir,
            horaDespertar
        });

        res.status(200).json({ message: 'Sueño modificado correctamente' });

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
};

exports.EliminarSueño = async (req, res) => {
    try {
        const { id_sueno } = req.params;
    
        const sueño = await Sueño.findByPk(id_sueno);
    
        if (!sueño) {
            return res.status(404).json({ error: 'El sueño no existe' });
        }
    
        await sueño.destroy();
    
        res.status(200).json({message:'Sueño eliminado correctamente'});
    
        } catch (error) {
        res.status(400).json({ error: error.message });
        }
    };


exports.filtroporfechaSueño = async (req, res) => {
    try {
        const { fecha, usuario_id } = req.body;


        const sueños = await Sueño.findAll({
            where: { fecha: fecha, usuario_id: usuario_id },
            include: [
                {
                    model: PastillasDormir,
                    attributes: ['pastilla'],
                    as: 'pastilla'
                },
                {
                    model: CalidadesSueño,
                    attributes: ['emocion'],
                    as: 'calidad'
                }
            ],
            attributes: ['id', 'fecha', 'horas', 'horaDormir', 'horaDespertar']
        });

        if (!sueños || sueños.length === 0) {
            return res.status(404).json({ error: 'No hay sueños para esta fecha o Usuario' });
        }

        const sueñosFormateados = sueños.map(sueño => ({
            id: sueño.id,
            fecha: sueño.fecha,
            horas: sueño.horas,
            pastilla_sueño: sueño.pastilla ? sueño.pastilla.pastilla : null,
            calidad_sueño: sueño.calidad ? sueño.calidad.emocion : null,
            horaDormir: sueño.horaDormir,
            horaDespertar: sueño.horaDespertar
        }));

        res.status(200).json(sueñosFormateados);

        } catch (error) {
        res.status(400).json({ error: error.message });
        }
    };


