const { User, DetalleUsuario, Sexo } = require('../../models/User');

exports.Datos_pacientes = async (req, res) => {
    try {
        const { clave_unica } = req.params;

        const usuarios = await DetalleUsuario.findAll({
            where: { clave_unica: clave_unica },
            include: [
                {
                    model: User,
                    as: 'User',
                    attributes: ['id', 'nombre', 'apellido_paterno', 'fecha_nacimiento']
                },
                {
                    model: Sexo,
                    as: 'sexo',
                    attributes: ['sexo']
                }
            ]
        });

        if (!usuarios || usuarios.length === 0) {
            return res.status(404).json({ error: 'No se encontraron usuarios con la clave única especificada' });
        }

        const usuariosFormateados = usuarios.map(usuario => ({
            id: usuario.User.id,
            nombre_completo: `${usuario.User.nombre} ${usuario.User.apellido_paterno}`,
            edad: calcularEdad(usuario.User.fecha_nacimiento),
            sexo: usuario.sexo ? usuario.sexo.sexo : null
        }));

        res.status(200).json(usuariosFormateados);

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
