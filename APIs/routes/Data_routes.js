const express = require('express');
const router = express.Router();

const { AgregarAlimentos,EliminarAlimentos, LeerAlimentos,ModificarAlimentos,filtroporfechaAlimento} = require('../controllers/DataMain/AlimentosController');
const {AgregarActividad, LeerActividad,ModificarActividad,EliminarActividad, filtroporfechaActividad} = require('../controllers/DataMain/ActividadController');
const {AgregarSueño, LeerSueño,ModificarSueño,EliminarSueño, filtroporfechaSueño} = require('../controllers/DataMain/SueñoController');
const {AgregarAnsiedad, LeerAnsiedad,ModificarAnsiedad,EliminarAnsiedad, filtroporfechaAnsiedad } = require('../controllers/DataMain/AnsiedadController');
const {AgregarPastillas, LeerPastillas,ModificarPastillas,EliminarPastillas, filtroporfechaPastillas } = require('../controllers/DataMain/PastillasController');
const {AgregarPresion, LeerPresion,ModificarPresion,EliminarPresion, filtroporfechaPresion} = require('../controllers/DataMain/PresionController');
const {verificarToken} = require('../middleware/verificarToken');
const {verificarToken_CU} = require('../middleware/verificarToken');



// Ruta para agregar un alimento
router.post('/alimentos/agregar',verificarToken("Usuario"), AgregarAlimentos);

// Ruta para eliminar un alimento
router.post('/alimentos/eliminar/:id_alimento',verificarToken("Usuario"), EliminarAlimentos);

// Ruta para leer un alimento
router.post('/alimentos/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerAlimentos);

// Ruta para modificar un alimento
router.post('/alimentos/modificar',verificarToken("Usuario"), ModificarAlimentos);

// Ruta para filtrar por fecha
router.post('/alimentos/filtrar/',verificarToken("Usuario"), filtroporfechaAlimento);



// Ruta para agregar un Actividad
router.post('/Actividad/agregar',verificarToken("Usuario"), AgregarActividad);

// Ruta para eliminar un Actividad
router.post('/Actividad/eliminar/:id_actividad',verificarToken("Usuario"), EliminarActividad);

// Ruta para leer un Actividad
router.post('/Actividad/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerActividad);

// Ruta para modificar un Actividad
router.post('/Actividad/modificar',verificarToken("Usuario"), ModificarActividad);

// Ruta para filtrar por fecha
router.post('/Actividad/filtrar',verificarToken("Usuario"), filtroporfechaActividad);



// Ruta para agregar un Sueño
router.post('/sueno/agregar',verificarToken("Usuario"), AgregarSueño);

// Ruta para eliminar un Sueño
router.post('/sueno/eliminar/:id_sueno',verificarToken("Usuario"), EliminarSueño);

// Ruta para leer un Sueño
router.post('/sueno/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerSueño);

// Ruta para modificar un Sueño
router.post('/sueno/modificar',verificarToken("Usuario"), ModificarSueño);

// Ruta para filtrar por fecha
router.post('/sueno/filtrar',verificarToken("Usuario"), filtroporfechaSueño);




// Ruta para agregar una Ansiedad
router.post('/ansiedad/agregar',verificarToken("Usuario"), AgregarAnsiedad);

// Ruta para leer una Ansiedad
router.post('/ansiedad/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerAnsiedad);

// Ruta para modificar una Ansiedad
router.post('/ansiedad/modificar',verificarToken("Usuario"), ModificarAnsiedad);

// Ruta para eliminar una Ansiedad
router.post('/ansiedad/eliminar/:id_ansiedad',verificarToken("Usuario"), EliminarAnsiedad);

// Ruta para filtrar por fecha
router.post('/ansiedad/filtrar',verificarToken("Usuario"), filtroporfechaAnsiedad);



// Ruta para agregar una Pastilla
router.post('/pastillas/agregar',verificarToken("Usuario"), AgregarPastillas);

// Ruta para leer una Pastilla
router.post('/pastillas/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerPastillas);

// Ruta para modificar una Pastilla
router.post('/pastillas/modificar',verificarToken("Usuario"), ModificarPastillas);

// Ruta para eliminar una Pastilla
router.post('/pastillas/eliminar/:id_pastilla',verificarToken("Usuario"), EliminarPastillas);

// Ruta para filtrar por fecha
router.post('/pastillas/filtrar',verificarToken("Usuario"), filtroporfechaPastillas);



// Ruta para agregar una Presion
router.post('/presion/agregar',verificarToken("Usuario"), AgregarPresion);

// Ruta para leer una Presion
router.post('/presion/leer/:Usuario_id',verificarToken_CU("Usuario"), LeerPresion);

// Ruta para modificar una Presion
router.post('/presion/modificar',verificarToken("Usuario"), ModificarPresion);

// Ruta para eliminar una Presion
router.post('/presion/eliminar/:id_presion',verificarToken("Usuario"), EliminarPresion);

// Ruta para filtrar por fecha
router.post('/presion/filtrar',verificarToken("Usuario"), filtroporfechaPresion);




module.exports = router;
