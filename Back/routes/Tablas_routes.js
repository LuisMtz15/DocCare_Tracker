const express = require('express');
const router = express.Router();

const { TablaRoles, TablaSexos,TablaTiposPorciones,TablaEmocionesActividades,TablaTiposActividades,TablaIntensidadesActividades,TablaPastillasDormir,TablaCalidadesSueño,TablaSintomasAnsiedad,TablaIntensidadesAnsiedad,TablaTiempoPastillas,TablaPeriodoPastillas,TablaEmocionesPresion} = require('../controllers/Tablas/TablasController');

// Ruta para jalar los datos de la tabla Roles
router.get('/roles', TablaRoles);

// Ruta para jalar los datos de la tabla Sexos
router.get('/sexos', TablaSexos);

// Ruta para jalar los datos de la tabla TiposPorciones
router.get('/tiposporciones', TablaTiposPorciones);

// Ruta para jalar los datos de la tabla EmocionesActividades
router.get('/emocionesactividades', TablaEmocionesActividades);

// Ruta para jalar los datos de la tabla TiposActividades
router.get('/tiposactividades', TablaTiposActividades);

// Ruta para jalar los datos de la tabla IntensidadesActividades
router.get('/intensidadesactividades', TablaIntensidadesActividades);

// Ruta para jalar los datos de la tabla PastillasDormir
router.get('/pastillasdormir', TablaPastillasDormir);

// Ruta para jalar los datos de la tabla CalidadesSueño
router.get('/calidadessueno', TablaCalidadesSueño);

// Ruta para jalar los datos de la tabla SintomasAnsiedad
router.get('/sintomasansiedad', TablaSintomasAnsiedad);

// Ruta para jalar los datos de la tabla IntensidadesAnsiedad
router.get('/intensidadesansiedad', TablaIntensidadesAnsiedad);

// Ruta para jalar los datos de la tabla TiempoPastillas
router.get('/tiempopastillas', TablaTiempoPastillas);

// Ruta para jalar los datos de la tabla PeriodoPastillas
router.get('/periodopastillas', TablaPeriodoPastillas);

// Ruta para jalar los datos de la tabla EmocionesPresion
router.get('/emocionespresion', TablaEmocionesPresion);


module.exports = router;
