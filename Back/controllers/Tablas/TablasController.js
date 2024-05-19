const { Role, Sexo} = require('../../models/User');
const {TiposPorciones, EmocionesActividades, TiposActividades, IntensidadesActividades, PastillasDormir, CalidadesSueño, sintomasAnsiedad, intensidadesAnsiedad, TiempoPastillas, PeriodoPastillas, EmocionesPresion } = require('../../models/Datosmain');



exports.TablaRoles = async (req, res) => {
    try {
        const roles = await Role.findAll({
            attributes: ['id', 'nombre']
        });
        res.json(roles);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaSexos = async (req, res) => {
    try {
        const sexos = await Sexo.findAll({
            attributes: ['id', 'sexo']
        });
        res.json(sexos);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaTiposPorciones = async (req, res) => {
    try {
        const tiposPorciones = await TiposPorciones.findAll({
            attributes: ['id', 'tipo']
        });
        res.json(tiposPorciones);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaEmocionesActividades = async (req, res) => {
    try {
        const emocionesActividades = await EmocionesActividades.findAll({
            attributes: ['id', 'emocion']
        });
        res.json(emocionesActividades);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaTiposActividades = async (req, res) => {
    try {
        const tiposActividades = await TiposActividades.findAll({
            attributes: ['id', 'tipo']
        });
        res.json(tiposActividades);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaIntensidadesActividades = async (req, res) => {
    try {
        const intensidadesActividades = await IntensidadesActividades.findAll({
            attributes: ['id', 'intensidad']
        });
        res.json(intensidadesActividades);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaPastillasDormir = async (req, res) => {
    try {
        const pastillasDormir = await PastillasDormir.findAll({
            attributes: ['id', 'pastilla']
        });
        res.json(pastillasDormir);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaCalidadesSueño = async (req, res) => {
    try {
        const calidadesSueño = await CalidadesSueño.findAll({
            attributes: ['id', 'emocion']
        });
        res.json(calidadesSueño);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaSintomasAnsiedad = async (req, res) => {
    try {
        const sintomas = await sintomasAnsiedad.findAll({
            attributes: ['id', 'sintoma']
        });
        res.json(sintomas);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaIntensidadesAnsiedad = async (req, res) => {
    try {
        const intensidades = await intensidadesAnsiedad.findAll({
            attributes: ['id', 'intensidad']
        });
        res.json(intensidades);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaTiempoPastillas = async (req, res) => {
    try {
        const tiempoPastillas = await TiempoPastillas.findAll({
            attributes: ['id', 'tiempo']
        });
        res.json(tiempoPastillas);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaPeriodoPastillas = async (req, res) => {
    try {
        const periodoPastillas = await PeriodoPastillas.findAll({
            attributes: ['id', 'periodo']
        });
        res.json(periodoPastillas);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}

exports.TablaEmocionesPresion = async (req, res) => {
    try {
        const emocionesPresion = await EmocionesPresion.findAll({
            attributes: ['id', 'emocion']
        });
        res.json(emocionesPresion);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
}
