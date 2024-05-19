const { User, DetalleUsuario, DetalleDoctor, Role, Sexo } = require('../models/User');

exports.obtenerUsuarioConDetalle = async (req, res) => {
    try {
        const usuario_id = req.params.usuario_id;
        const user = await User.findByPk(usuario_id, {
            attributes: ['id', 'nombre','apellido_paterno','apellido_materno', 'email','password', 'fecha_nacimiento', 'rol_id']
        });
        if (!user) {
            return res.status(404).json({ error: 'Usuario no encontrado' });
        }
    
        let detalles = null;

        if (user.rol_id === 1) {
            detalles = await DetalleUsuario.findOne({
                where: { usuario_id },
                attributes: ['usuario_id', 'altura', 'peso','circunferencia_abdominal','clave_unica','sexo_id']
            });
            const nombreSexo = await Sexo.findByPk(detalles.sexo_id, { attributes: ['sexo'] });
            detalles.sexo_id = nombreSexo.sexo;
        } else if (user.rol_id === 2) {
            detalles = await DetalleDoctor.findOne({
                where: { usuario_id },
                attributes: ['usuario_id', 'clave_unica']
            });
        }

        const nombreRol = await Role.findByPk(user.rol_id, { attributes: ['nombre'] });
        user.rol_id = nombreRol.nombre;

        // Crear un objeto personalizado con todos los campos combinados
        const mergedData = {
            id: user.id,
            nombre: user.nombre,
            apellido_paterno: user.apellido_paterno,
            apellido_materno: user.apellido_materno,
            email: user.email,
            edad: calcularEdad(user.fecha_nacimiento),
            rol: user.rol_id,
            altura: detalles ? detalles.altura : null,
            peso: detalles ? detalles.peso : null,
            circunferencia_abdominal: detalles ? detalles.circunferencia_abdominal : null,
            sexo: detalles ? detalles.sexo_id : null
        };

        res.status(201).json(mergedData);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};


const calcularEdad = (fechaNacimiento) => {
    let fechaNaci = '';
    if (typeof fechaNacimiento === 'string') {
        fechaNaci = fechaNacimiento.split(' ')[0];
    } else if (fechaNacimiento instanceof Date) {
        fechaNaci = fechaNacimiento.toISOString().split('T')[0];
    } else {
        throw new Error('Formato de fecha inválido');
    }

    const hoy = new Date();
    const fechaNac = new Date(fechaNaci);
    let edad = hoy.getFullYear() - fechaNac.getFullYear();
    const mes = hoy.getMonth() - fechaNac.getMonth();

    if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNac.getDate())) {
        edad--;
    }

    return edad;
};
