const express = require('express');
const router = express.Router();

const { Datos_pacientes } = require('../controllers/DocMain/DatosPacientesController');
const {verificarToken} = require('../middleware/verificarToken');

// Ruta para jalar los datos de los pacientes

router.post('/datos_pacientes/:clave_unica', verificarToken("Doctor"),Datos_pacientes);


module.exports = router;
