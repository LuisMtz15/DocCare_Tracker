const express = require('express');
const router = express.Router();

const { registrarUsuario } = require('../controllers/RegistrarUsuarioC');
const { registrarDetalleUsuario } = require('../controllers/DetalleUsuarioC');
const { registrarDetalleDoctor } = require('../controllers/DetalleDoctorC');
const { obtenerUsuarioConDetalle } = require('../controllers/DatosController');
const { LoginController } = require('../controllers/LoginController');
const { modificarDatosUsuario } = require('../controllers/ModificarD_C');
const { modificarDatosDoctor } = require('../controllers/ModificarD_C');
const { modificarPassword } = require('../controllers/ModificarD_C');
const {verificarToken} = require('../middleware/verificarToken');
const {verificarToken_CU} = require('../middleware/verificarToken');

const { verificarTokenparaLogin } = require('../controllers/TokenController');


// Ruta para registrar un usuario
router.post('/usuarios', registrarUsuario);

// Ruta para registrar los detalles de un usuario
router.post('/usuarios/detalles', registrarDetalleUsuario);

// Ruta para registrar los detalles de un doctor
router.post('/usuarios/detallesdoc/:usuario_id', registrarDetalleDoctor);

// Ruta para desplegar los datos de un usuario
router.post('/usuarios/datos/:usuario_id', verificarToken_CU("Usuario"), obtenerUsuarioConDetalle); // O Doctor


// Ruta para hacer un login
router.post('/usuarios/login', LoginController);

// Ruta para modificar datos de un usuario
router.post('/usuarios/modificar',verificarToken("Usuario"), modificarDatosUsuario);

// Ruta para modificar datos de un Doctor
router.post('/usuarios/modificardoc',verificarToken("Doctor"), modificarDatosDoctor);

router.post('/usuarios/token',verificarTokenparaLogin);

// Ruta para modificar contraseña de un user
router.post('/usuarios/modificar/pswd',verificarToken_CU("Usuario"), modificarPassword);


module.exports = router;
