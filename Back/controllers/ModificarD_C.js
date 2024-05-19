const { User, DetalleUsuario, Role} = require('../models/User');
const bcrypt = require('bcrypt');

exports.modificarDatosUsuario = async (req, res) => {
    try {
        const { usuario_id, nombre, apellidop, apellidom, correo,peso, altura, cintura, clave } = req.body;

        // Verificar si el usuario existe
        const usuario = await User.findByPk(usuario_id);
        if (!usuario) {
        return res.status(404).json({ error: 'El usuario especificado no existe' });
        }


        const nombreRol = await Role.findByPk(usuario.rol_id, { attributes: ['nombre'] });


        if (nombreRol.nombre !== "Usuario") {
            return res.status(404).json({ error: 'No se puede modificar el usuario Doctor' });
        }


        const detalleUsuario = await DetalleUsuario.findOne({ where: { usuario_id } });

        await detalleUsuario.update({
            peso: peso,
            altura: altura,
            circunferencia_abdominal: cintura,
            clave_unica: clave
        });

        await usuario.update({
            nombre: nombre,
            apellido_paterno: apellidop,
            apellido_materno: apellidom,
            email: correo
        });

        res.status(200).json({ message: 'Datos modificados correctamente'});
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.modificarDatosDoctor = async (req, res) => {
    try {
        const { usuario_id, nombre, apellidop, apellidom, correo } = req.body;

        // Verificar si el usuario existe
        const usuario = await User.findByPk(usuario_id);
        if (!usuario) {
        return res.status(404).json({ error: 'El usuario especificado no existe' });
        }

        const nombreRol = await Role.findByPk(usuario.rol_id, { attributes: ['nombre'] });

        if (nombreRol.nombre !== "Doctor") {
            return res.status(404).json({ error: 'No se puede modificar el usuario Usuario' });
        }

        await usuario.update({
            nombre: nombre,
            apellido_paterno: apellidop,
            apellido_materno: apellidom,
            email: correo
        });

        res.status(200).json({ message: 'Datos modificados correctamente'});
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

exports.modificarPassword = async (req, res) => {
    try {
        const { usuario_id, password, Oldpassword} = req.body;
        // Verificar si el usuario existe
        const usuario = await User.findByPk(usuario_id);
        if (!usuario) {
            return res.status(404).json({ error: 'El usuario especificado no existe' });
        }

        // Verificar si la contraseña actual es correcta
        const match = await bcrypt.compare(Oldpassword, usuario.password);
        if (!match) {
            return res.status(400).json({ error: 'La contraseña actual es incorrecta' });
        }

        // Cifrar la nueva contraseña
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        await usuario.update({
            password: hashedPassword
        });
    
        res.status(200).json({ message: 'Contraseña modificada correctamente' });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};