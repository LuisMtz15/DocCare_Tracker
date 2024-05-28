const express = require('express');
const router = express.Router();

const { AgregarAlimentos,EliminarAlimentos, LeerAlimentos,ModificarAlimentos,filtroporfechaAlimento} = require('../controllers/DataMain/AlimentosController');
const {AgregarActividad, LeerActividad,ModificarActividad,EliminarActividad, filtroporfechaActividad} = require('../controllers/DataMain/ActividadController');
const {AgregarSueño, LeerSueño,ModificarSueño,EliminarSueño, filtroporfechaSueño} = require('../controllers/DataMain/SueñoController');
const {AgregarAnsiedad, LeerAnsiedad,ModificarAnsiedad,EliminarAnsiedad, filtroporfechaAnsiedad } = require('../controllers/DataMain/AnsiedadController');
const {AgregarPastillas, LeerPastillas,ModificarPastillas,EliminarPastillas, filtroporfechaPastillas } = require('../controllers/DataMain/PastillasController');
const {AgregarPresion, LeerPresion,ModificarPresion,EliminarPresion, filtroporfechaPresion} = require('../controllers/DataMain/PresionController');

const {ObtenerPorciones, ObtenerFechas}= require('../controllers/Graphs/AlimentosGraphs');
const {ObtenerTipoActividad, ObtenerIntensidad}= require('../controllers/Graphs/ActividadesGraphs');
const {ObtenerIntensidadAnsiedad, ObtenerSintomaAnsiedad}= require('../controllers/Graphs/AnsiedadGraphs');
const {ObtenerTiempoPastillas, ObtenerMedicamento}= require('../controllers/Graphs/PastillasGraph');
const {ObtenerDiastolica, ObtenerSistolica}= require('../controllers/Graphs/PresionGraphs');
const {ObtenerPastillaDormir, ObtenerhorasSueno}= require('../controllers/Graphs/SuenoGraphs');



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

// Ruta para obtener las porciones de los alimentos
router.post('/alimentos/porciones', verificarToken("Usuario") , ObtenerPorciones);

// Ruta para obtener las fechas de los alimentos
router.post('/alimentos/fechas', verificarToken("Usuario") , ObtenerFechas);



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

// Ruta para obtener el tipo de actividad
router.post('/Actividad/tipo', verificarToken("Usuario") , ObtenerTipoActividad);

// Ruta para obtener la intensidad de la actividad
router.post('/Actividad/intensidad', verificarToken("Usuario") , ObtenerIntensidad);



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

// Ruta para obtener la pastilla de dormir
router.post('/sueno/pastilla', verificarToken("Usuario") , ObtenerPastillaDormir);

// Ruta para obtener las horas de sueño
router.post('/sueno/horas', verificarToken("Usuario") , ObtenerhorasSueno);




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

// Ruta para obtener la intensidad de la ansiedad
router.post('/ansiedad/intensidad', verificarToken("Usuario") , ObtenerIntensidadAnsiedad);

// Ruta para obtener el sintoma de la ansiedad
router.post('/ansiedad/sintoma', verificarToken("Usuario") , ObtenerSintomaAnsiedad);



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

// Ruta para obtener el tiempo de las pastillas
router.post('/pastillas/tiempo', verificarToken("Usuario") , ObtenerTiempoPastillas);

// Ruta para obtener el medicamento de las pastillas
router.post('/pastillas/medicamento', verificarToken("Usuario") , ObtenerMedicamento);



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

// Ruta para obtener la diastolica de la presion
router.post('/presion/diastolica', verificarToken("Usuario") , ObtenerDiastolica);

// Ruta para obtener la sistolica de la presion
router.post('/presion/sistolica', verificarToken("Usuario") , ObtenerSistolica);




module.exports = router;
