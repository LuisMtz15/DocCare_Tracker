package com.example.ejemplosapis.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.doccare_tracker.model.Actividad.AgregarActividad
import com.example.doccare_tracker.model.Actividad.AgregarActividadRespuesta
import com.example.doccare_tracker.model.Actividad.EliminarActividadRespuesta
import com.example.doccare_tracker.model.Actividad.FiltroActividad
import com.example.doccare_tracker.model.Actividad.LeerActividadRespuesta
import com.example.doccare_tracker.model.Actividad.ModificarActividad
import com.example.doccare_tracker.model.Actividad.ModificarActividadResultado
import com.example.doccare_tracker.model.Actividad.Tablas.EmocionesActividades
import com.example.doccare_tracker.model.Actividad.Tablas.IntensidadesActividad
import com.example.doccare_tracker.model.Actividad.Tablas.TiposActividades
import com.example.doccare_tracker.model.Alimentos.AgregarAlimentoRespuesta
import com.example.doccare_tracker.model.Alimentos.AgregarAlimentos
import com.example.doccare_tracker.model.Alimentos.EditarAlimento
import com.example.doccare_tracker.model.Alimentos.EditarAlimentoRespuesta
import com.example.doccare_tracker.model.Alimentos.EliminarAlimentoRespuesta
import com.example.doccare_tracker.model.Alimentos.FiltroAlimentos
import com.example.doccare_tracker.model.Alimentos.LeerAlimentosRespuesta
import com.example.doccare_tracker.model.Alimentos.Tablas.TablasTipoPorciones
import com.example.doccare_tracker.model.Ansiedad.AgregarAnsiedad
import com.example.doccare_tracker.model.Ansiedad.AgregarAnsiedadRespuesta
import com.example.doccare_tracker.model.Ansiedad.EliminarAnsiedadRespuesta
import com.example.doccare_tracker.model.Ansiedad.FiltroAnsiedad
import com.example.doccare_tracker.model.Ansiedad.LeerAnsiedadRespuesta
import com.example.doccare_tracker.model.Ansiedad.ModificarAnsiedad
import com.example.doccare_tracker.model.Ansiedad.ModificarAnsiedadRespuesta
import com.example.doccare_tracker.model.Ansiedad.Tablas.IntensidadAnsiedad
import com.example.doccare_tracker.model.Ansiedad.Tablas.SintomasAnsiedad
import com.example.doccare_tracker.model.Graphs.Actividades.IntensidadActividadesResult
import com.example.doccare_tracker.model.Graphs.Actividades.TipoActividadesResult
import com.example.doccare_tracker.model.Graphs.Alimentos.BarrasGraphResult
import com.example.doccare_tracker.model.Graphs.Alimentos.DonaGraphResult
import com.example.doccare_tracker.model.Graphs.Ansiedad.IntensidadAnsiedadesResult
import com.example.doccare_tracker.model.Graphs.Ansiedad.SintomasResult
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Graphs.Pastillas.MedicamentosResult
import com.example.doccare_tracker.model.Graphs.Pastillas.TiempoPastillasResult
import com.example.doccare_tracker.model.Graphs.Pesos.ModifcarPeso
import com.example.doccare_tracker.model.Graphs.Pesos.ModificarPesoResult
import com.example.doccare_tracker.model.Graphs.Pesos.PesosGraph
import com.example.doccare_tracker.model.Graphs.Pesos.PesosGraphResult
import com.example.doccare_tracker.model.Graphs.Presion.DiastolicasResult
import com.example.doccare_tracker.model.Graphs.Presion.SistolicasResult
import com.example.doccare_tracker.model.Graphs.Sueño.HorasResult
import com.example.doccare_tracker.model.Graphs.Sueño.PastillaSuenoResult
import com.example.doccare_tracker.model.Informacion_Personal.Contraseñas.ModificarContraseña
import com.example.doccare_tracker.model.Informacion_Personal.Contraseñas.ModificarContraseñaRespuesta
import com.example.doccare_tracker.model.Informacion_Personal.Doctor.ModificarDatosDoctor
import com.example.doccare_tracker.model.Informacion_Personal.Doctor.ModificarDatosDoctorRespuesta
import com.example.doccare_tracker.model.Informacion_Personal.JalarInfo.JalarInformacionRespuesta
import com.example.doccare_tracker.model.Informacion_Personal.JalarInfo.JalarUsuariosDocRespuesta
import com.example.doccare_tracker.model.Informacion_Personal.Usuario.ModificarDatosUsuario
import com.example.doccare_tracker.model.Informacion_Personal.Usuario.ModificarDatosUsuarioRespuesta
import com.example.doccare_tracker.model.Login.Login
import com.example.doccare_tracker.model.Login.LoginRespuesta
import com.example.doccare_tracker.model.Pastillas.AgregarPastillas
import com.example.doccare_tracker.model.Pastillas.AgregarPastillasRespuesta
import com.example.doccare_tracker.model.Pastillas.EliminarPastillaRespuesta
import com.example.doccare_tracker.model.Pastillas.FiltroPastillas
import com.example.doccare_tracker.model.Pastillas.LeerPastillasRespuesta
import com.example.doccare_tracker.model.Pastillas.ModificarPastillas
import com.example.doccare_tracker.model.Pastillas.ModificarPastillasRespuesta
import com.example.doccare_tracker.model.Pastillas.Tablas.PeriodoPastillas
import com.example.doccare_tracker.model.Presion.AgregarPresion
import com.example.doccare_tracker.model.Presion.AgregarPresionRespuesta
import com.example.doccare_tracker.model.Presion.EliminarPresionRespuesta
import com.example.doccare_tracker.model.Presion.FiltroPresion
import com.example.doccare_tracker.model.Presion.LeerPresionesRespuesta
import com.example.doccare_tracker.model.Presion.ModificarPresion
import com.example.doccare_tracker.model.Presion.ModificarPresionRespuesta
import com.example.doccare_tracker.model.Presion.Tabla.EmocionesPresion
import com.example.doccare_tracker.model.Registros.Doctor.RespuestaDetalleDoctor
import com.example.doccare_tracker.model.Registros.RegistrarUsuario
import com.example.doccare_tracker.model.Registros.RespuestaRegistro
import com.example.doccare_tracker.model.Registros.Usuario.RegistrarDetalleUsuario
import com.example.doccare_tracker.model.Registros.Usuario.RespuestaDetalleUsuario
import com.example.doccare_tracker.model.Sueño.AgregarSueño
import com.example.doccare_tracker.model.Sueño.AgregarSueñoRespuesta
import com.example.doccare_tracker.model.Sueño.EliminarSueñoRespuesta
import com.example.doccare_tracker.model.Sueño.FiltroSueño
import com.example.doccare_tracker.model.Sueño.LeerSueñoRespuesta
import com.example.doccare_tracker.model.Sueño.ModificarSueño
import com.example.doccare_tracker.model.Sueño.ModificarSueñoRespuesta
import com.example.doccare_tracker.model.Sueño.Tabla.CalidadesSueño
import com.example.doccare_tracker.model.Token.ChecarToken
import com.example.doccare_tracker.model.Token.TokenRespuesta
import com.example.ejemplosapis.service.UserServiceApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel (private val serviceApi: UserServiceApi, application: Application) : AndroidViewModel(application) {


    private val prefs = application.getSharedPreferences("Settings", Context.MODE_PRIVATE)


    //Para registrar usuario
    private val _registrarUsuarioResult = MutableStateFlow<Result<RespuestaRegistro>?>(null)
    val registrarUsuarioResult = _registrarUsuarioResult.asStateFlow()

    fun setRegistrarUsuarioResult(result: Result<RespuestaRegistro>?) {
        _registrarUsuarioResult.value = result
    }


    //Variables para el login
    private val _usuario_id = MutableStateFlow(prefs.getInt("idUsuario",0))
    val usuario_id = _usuario_id.asStateFlow()

    private fun setidUsuarioSession(id: Int) {
        viewModelScope.launch {
            prefs.edit().putInt("idUsuario",id).apply()
            _usuario_id.value = id
        }
    }


    //Para registrar detalleUsuario
    private val _registrarDetalleUsuarioResult = MutableStateFlow<Result<RespuestaDetalleUsuario>?>(null)
    val registrarDetalleUsuarioResult = _registrarDetalleUsuarioResult.asStateFlow()

    fun setRegistrarDetalleUsuarioResult(result: Result<RespuestaDetalleUsuario>?) {
        _registrarDetalleUsuarioResult.value = result
    }

    //Para registrar detalleDocotr
    private val _registrarDetalleDoctorResult = MutableStateFlow<Result<RespuestaDetalleDoctor>?>(null)
    val registrarDetalleDoctorResult = _registrarDetalleDoctorResult.asStateFlow()

    fun setRegistrarDetalleDoctorResult(result: Result<RespuestaDetalleDoctor>?) {
        _registrarDetalleDoctorResult.value = result
    }

    //Función para Registrar Usuario

    fun registrarUsuario(user: RegistrarUsuario) {

        viewModelScope.launch {

            try{
                val response = serviceApi.registrarUsuario(user)
                _registrarUsuarioResult.value = Result.success(response)
                response.usuario_id?.let { setidUsuarioSession(it) }

            } catch(e: Exception){
                _registrarUsuarioResult.value  = Result.failure(e)
            }
        }
    }

    //Función para Registrar Detalle Usuario

    fun registrardetalleUsuario(user: RegistrarDetalleUsuario) {
        viewModelScope.launch {
            try{
                val response = serviceApi.registrarDetalleUsuario(user)
                _registrarDetalleUsuarioResult.value = Result.success(response)
                //setJwtToken(response.token)

            } catch(e: Exception){
                _registrarDetalleUsuarioResult.value  =Result.failure(e)
            }
        }
    }

    //Función para Registrar Detalle Doctor

    fun registrardetalleDoctor(idDoctor: Int) {
        viewModelScope.launch {
            try{
                val response = serviceApi.registrarDetalleDoctor(idDoctor)
                _registrarDetalleDoctorResult.value = Result.success(response)

            } catch(e: Exception){
                _registrarDetalleDoctorResult.value  =Result.failure(e)
            }
        }
    }









    //Token
    private val _tokenResult = MutableStateFlow<Result<TokenRespuesta>?>(null)
    val tokenResult = _tokenResult.asStateFlow()
    fun setTokenResult(result: Result<TokenRespuesta>?) {
        _tokenResult.value = result
    }
    private val _isTokenValid = MutableStateFlow(false)
    val isTokenValid = _isTokenValid.asStateFlow()
    fun setIsTokenValid(isValid: Boolean) {
        _isTokenValid.value = isValid
    }

    fun checarToken(user: ChecarToken) {
        _tokenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.checarToken(user)
                _tokenResult.value = Result.success(response)
                _isTokenValid.value = true
            } catch(e: Exception){
                _tokenResult.value =Result.failure(e)
                println(e)
            }
        }
    }








    //Login

    private val _loginResult = MutableStateFlow<Result<LoginRespuesta>?>(null)
    val loginResult = _loginResult.asStateFlow()
    fun setLoginResult(result: Result<LoginRespuesta>?) {
        _loginResult.value = result
    }



    private val _role = MutableStateFlow(prefs.getBoolean("Usuario",true))
    val role = _role.asStateFlow()

    private fun setRole(role: Boolean) {
        viewModelScope.launch {
            prefs.edit().putBoolean("Usuario",role).apply()
            _role.value = role
        }
    }

    private val _jwtToken = MutableStateFlow(prefs.getString("jwtToken",""))
    val jwtToken = _jwtToken.asStateFlow()

    private fun setJwtToken(token: String?) {

        viewModelScope.launch {
            prefs.edit().putString("jwtToken",token).apply()
            _jwtToken.value = token
        }
    }

    private val _clave = MutableStateFlow(prefs.getString("clave",""))
    val clave = _clave.asStateFlow()

    fun setClave(clave: String?) {
        viewModelScope.launch {
            prefs.edit().putString("clave",clave).apply()
            _clave.value = clave
        }
    }

    private val _nombreUsuario = MutableStateFlow(prefs.getString("nombreUsuario",""))
    val nombreUsuario = _nombreUsuario.asStateFlow()
    fun setNombreUsuario(nombre: String?) {
        viewModelScope.launch {
            prefs.edit().putString("nombreUsuario", nombre).apply()
            _nombreUsuario.value = nombre
        }
    }

    private val _apellidoUsuario = MutableStateFlow(prefs.getString("apellidoUsuario",""))
    val apellidoUsuario = _apellidoUsuario.asStateFlow()
    fun setApellidoUsuario(apellido: String?) {
        viewModelScope.launch {
            prefs.edit().putString("apellidoUsuario", apellido).apply()
            _apellidoUsuario.value = apellido
            }
    }

    fun usuariologin(user: Login) {
        _loginResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.usuarioLogin(user)
                _loginResult.value = Result.success(response)
                setJwtToken(response.token)
                setidUsuarioSession(response.id)
                setClave(response.clave)
                setNombreUsuario(response.nombre)
                setApellidoUsuario(response.apellidos)
                setTablaUsuarioPesos(null)
                if(response.role == "Usuario") {
                    setRole(true)
                }
                else{
                    setRole(false)
                    _clave.value?.let { leerUsuariosDoc(it) }
                }

            } catch(e: Exception){
                _loginResult.value =Result.failure(e)
            }
        }
    }



    //LogOut

    fun cerrarSesion() {
        setJwtToken(null)
        _loginResult.value = null
        setidUsuarioSession(0)
        setRole(true)
        setClave(null)
        setAltura("")
        setApellidoMaterno("")
        setApellidoPaterno("")
        setCircunferencia("")
        setEdad(0)
        setPeso("")
        setEmail("")
        setNombre("")
        setSexo("")
        setExitodatos(false)
        setNombreUsuario(null)
        setApellidoUsuario(null)
    }






    //Pantalla Inicio Usuario

    //Leer Alimentos
    private val _leerAlimentosResult = MutableStateFlow<Result<LeerAlimentosRespuesta>?>(null)
    val leerAlimentosResult = _leerAlimentosResult.asStateFlow()
    fun setLeerAlimentosResult(result: Result<LeerAlimentosRespuesta>?) {
        _leerAlimentosResult.value = result
    }
    private val _alimentoslist = MutableStateFlow(LeerAlimentosRespuesta())
    val alimentoslist = _alimentoslist.asStateFlow()

    fun leeralimentos(usuario_id: Int) {
        _leerAlimentosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerAlimentos(jwtToken.value,usuario_id)
                _alimentoslist.value = response
                _leerAlimentosResult.value = Result.success(response)
            } catch(e: Exception){
                setNohayAlimentos(true)
                _leerAlimentosResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Alimentos
    private val _agregarAlimentosResult = MutableStateFlow<Result<AgregarAlimentoRespuesta>?>(null)
    val agregarAlimentosResult = _agregarAlimentosResult.asStateFlow()
    fun setAgregarAlimentosResult(result: Result<AgregarAlimentoRespuesta>?) {
        _agregarAlimentosResult.value = result
    }

    fun agregaralimentos(user: AgregarAlimentos) {
        _agregarAlimentosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarAlimentos(jwtToken.value,user)
                _agregarAlimentosResult.value = Result.success(response)
                leeralimentos(usuario_id.value)
            } catch(e: Exception){
                _agregarAlimentosResult.value =Result.failure(e)
            }
        }
    }


    //Editar Alimentos

    private val _editarAlimentosResult = MutableStateFlow<Result<EditarAlimentoRespuesta>?>(null)
    val editarAlimentosResult = _editarAlimentosResult.asStateFlow()
    fun setEditarAlimentosResult(result: Result<EditarAlimentoRespuesta>?) {
        _editarAlimentosResult.value = result
    }

    fun editaralimentos(user: EditarAlimento) {
        _editarAlimentosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarAlimentos(jwtToken.value,user)
                _editarAlimentosResult.value = Result.success(response)
                leeralimentos(usuario_id.value)
            } catch(e: Exception){
                _editarAlimentosResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Alimentos

    private val _eliminarAlimentosResult = MutableStateFlow<Result<EliminarAlimentoRespuesta>?>(null)
    val eliminarAlimentosResult = _eliminarAlimentosResult.asStateFlow()
    fun setEliminarAlimentosResult(result: Result<EliminarAlimentoRespuesta>?) {
        _eliminarAlimentosResult.value = result
    }

    fun eliminaralimentos(idalimento: Int) {
        _eliminarAlimentosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarAlimentos(jwtToken.value,idalimento)
                _eliminarAlimentosResult.value = Result.success(response)
                leeralimentos(usuario_id.value)
            } catch(e: Exception){
                _eliminarAlimentosResult.value =Result.failure(e)
            }
        }
    }


    //Filtro Alimentos

    private val _filtroAlimentosResult = MutableStateFlow<Result<LeerAlimentosRespuesta>?>(null)
    val filtroAlimentosResult = _filtroAlimentosResult.asStateFlow()

    fun setFiltroAlimentosResult(result: Result<LeerAlimentosRespuesta>?) {
        _filtroAlimentosResult.value = result
    }

    private val _filtroAlimentoslist = MutableStateFlow(LeerAlimentosRespuesta())
    val filtroAlimentoslist = _filtroAlimentoslist.asStateFlow()

    private val _idAlimento = MutableStateFlow(0)
    val idAlimento = _idAlimento.asStateFlow()

    private val _nombreAlimento = MutableStateFlow("")
    val nombreAlimento = _nombreAlimento.asStateFlow()

    private val _porcionAlimento = MutableStateFlow("")
    val porcionAlimento = _porcionAlimento.asStateFlow()

    fun setPorcionAlimento(porcion: String) {
        _porcionAlimento.value = porcion
    }

    fun setNombreAlimento(nombre: String) {
        _nombreAlimento.value = nombre
    }

    fun setIdAlimento(id: Int) {
        _idAlimento.value = id
    }

    fun filtraralimentos(user: FiltroAlimentos) {
        _filtroAlimentosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarAlimentos(jwtToken.value,user)
                _filtroAlimentoslist.value = response
                _filtroAlimentosResult.value = Result.success(response)
            } catch(e: Exception){
                _filtroAlimentosResult.value =Result.failure(e)
            }
        }
    }

    //Tablas Alimentos

    //Tablas Porciones
    private val _leerPorcionesResult = MutableStateFlow<Result<TablasTipoPorciones>?>(null)
    val leerPorcionesResult = _leerPorcionesResult.asStateFlow()

    private val _porcioneslist = MutableStateFlow(TablasTipoPorciones())
    val porcioneslist = _porcioneslist.asStateFlow()

    fun tablaporciones() {
        _leerPorcionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.tablasPorciones()
                _porcioneslist.value = response
                _leerPorcionesResult.value = Result.success(response)
            } catch(e: Exception){
                _leerPorcionesResult.value =Result.failure(e)
            }
        }
    }



    //Actividades


    //Leer Actividades

    private val _leerActividadesResult = MutableStateFlow<Result<LeerActividadRespuesta>?>(null)
    val leerActividadesResult = _leerActividadesResult.asStateFlow()
    fun setLeerActividadesResult(result: Result<LeerActividadRespuesta>?) {
        _leerActividadesResult.value = result
    }

    private val _actividadeslist = MutableStateFlow(LeerActividadRespuesta())
    val actividadeslist = _actividadeslist.asStateFlow()

    fun leeractividades(user: Int) {
        _leerActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerActividades(jwtToken.value,user)
                _actividadeslist.value = response
                _leerActividadesResult.value = Result.success(response)
            } catch(e: Exception){
                setNohayActividades(true)
                _leerActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Actividades

    private val _agregarActividadesResult = MutableStateFlow<Result<AgregarActividadRespuesta>?>(null)
    val agregarActividadesResult = _agregarActividadesResult.asStateFlow()
    fun setAgregarActividadesResult(result: Result<AgregarActividadRespuesta>?) {
        _agregarActividadesResult.value = result
    }

    fun agregaractividades(user: AgregarActividad) {
        _agregarActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarActividades(jwtToken.value,user)
                _agregarActividadesResult.value = Result.success(response)
                leeractividades(usuario_id.value)
            } catch(e: Exception){
                _agregarActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Editar Actividades

    private val _editarActividadesResult = MutableStateFlow<Result<ModificarActividadResultado>?>(null)
    val editarActividadesResult = _editarActividadesResult.asStateFlow()
    fun setEditarActividadesResult(result: Result<ModificarActividadResultado>?) {
        _editarActividadesResult.value = result
    }

    fun editaractividades(user: ModificarActividad) {
        _editarActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarActividades(jwtToken.value,user)
                _editarActividadesResult.value = Result.success(response)
                leeractividades(usuario_id.value)
            } catch(e: Exception){
                _editarActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Actividades

    private val _eliminarActividadesResult = MutableStateFlow<Result<EliminarActividadRespuesta>?>(null)
    val eliminarActividadesResult = _eliminarActividadesResult.asStateFlow()
    fun setEliminarActividadesResult(result: Result<EliminarActividadRespuesta>?) {
        _eliminarActividadesResult.value = result
    }

    fun eliminaractividades(user: Int) {
        _eliminarActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarActividades(jwtToken.value,user)
                _eliminarActividadesResult.value = Result.success(response)
                leeractividades(usuario_id.value)
            } catch(e: Exception){
                _eliminarActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Filtro Actividades

    private val _filtroActividadesResult = MutableStateFlow<Result<LeerActividadRespuesta>?>(null)
    val filtroActividadesResult = _filtroActividadesResult.asStateFlow()
    fun setFiltroActividadesResult(result: Result<LeerActividadRespuesta>?) {
        _filtroActividadesResult.value = result
    }

    private val _filtroActividadeslist = MutableStateFlow(LeerActividadRespuesta())
    val filtroActividadeslist = _filtroActividadeslist.asStateFlow()

    fun filtroactividades(user: FiltroActividad) {
        _filtroActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarActividades(jwtToken.value,user)
                _filtroActividadeslist.value = response
                _filtroActividadesResult.value = Result.success(response)
            } catch(e: Exception){
                _filtroActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Informacion Actividades

    private val _idActividad = MutableStateFlow(0)
    val idActividad = _idActividad.asStateFlow()
    fun setIdActividad(id: Int) {
        _idActividad.value = id
    }

    private val _duracionActividad = MutableStateFlow(0)
    val duracionActividad = _duracionActividad.asStateFlow()
    fun setDuracionActividad(duracion: Int) {
        _duracionActividad.value = duracion
    }

    private val _emocionActividad = MutableStateFlow("")
    val emocionActividad = _emocionActividad.asStateFlow()
    fun setEmocionActividad(emocion: String) {
        _emocionActividad.value = emocion
    }

    private val _intensidadActividad = MutableStateFlow("")
    val intensidadActividad = _intensidadActividad.asStateFlow()
    fun setIntensidadActividad(intensidad: String) {
        _intensidadActividad.value = intensidad
    }

    private val _tipoActividad = MutableStateFlow("")
    val tipoActividad = _tipoActividad.asStateFlow()
    fun setTipoActividad(tipo: String) {
        _tipoActividad.value = tipo
    }

    //Tablas Actividades

    //Tipos Actividades

    private val _leerTipoActividadesResult = MutableStateFlow<Result<TiposActividades>?>(null)
    val leerTipoActividadesResult = _leerTipoActividadesResult.asStateFlow()

    private val _tipoActividadeslist = MutableStateFlow(TiposActividades())
    val tipoActividadeslist = _tipoActividadeslist.asStateFlow()

    fun tiposactividades() {
        _leerTipoActividadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.TiposActividad()
                _tipoActividadeslist.value = response
                _leerTipoActividadesResult.value = Result.success(response)
            } catch(e: Exception){
                _leerTipoActividadesResult.value =Result.failure(e)
            }
        }
    }

    //Intensidades

    private val _leerIntensidadesResult = MutableStateFlow<Result<IntensidadesActividad>?>(null)
    val leerIntensidadesResult = _leerIntensidadesResult.asStateFlow()

    private val _intensidadeslist = MutableStateFlow(IntensidadesActividad())
    val intensidadeslist = _intensidadeslist.asStateFlow()

    fun intensidades() {
        _leerIntensidadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.IntensidadesAct()
                _intensidadeslist.value = response
                _leerIntensidadesResult.value = Result.success(response)
            } catch(e: Exception){
                _leerIntensidadesResult.value =Result.failure(e)
            }
        }
    }

    //Emociones

    private val _leerEmocionesResult = MutableStateFlow<Result<EmocionesActividades>?>(null)
    val leerEmocionesResult = _leerEmocionesResult.asStateFlow()

    private val _emocioneslist = MutableStateFlow(EmocionesActividades())
    val emocioneslist = _emocioneslist.asStateFlow()

    fun emocionesAct() {
        _leerEmocionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.EmocionesAct()
                _emocioneslist.value = response
                _leerEmocionesResult.value = Result.success(response)
            } catch(e: Exception){
                _leerEmocionesResult.value =Result.failure(e)
            }
        }
    }


    //Ansiedad

    //Leer Ansiedad

    private val _leerAnsiedadResult = MutableStateFlow<Result<LeerAnsiedadRespuesta>?>(null)
    val leerAnsiedadResult = _leerAnsiedadResult.asStateFlow()
    fun setLeerAnsiedadResult(result: Result<LeerAnsiedadRespuesta>?) {
        _leerAnsiedadResult.value = result
    }
    private val _ansiedadlist = MutableStateFlow(LeerAnsiedadRespuesta())
    val ansiedadlist = _ansiedadlist.asStateFlow()

    fun leeransiedades(user: Int) {
        _leerAnsiedadResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerAnsiedades(jwtToken.value,user)
                _ansiedadlist.value = response
                _leerAnsiedadResult.value = Result.success(response)
            } catch(e: Exception){
                setNohayAnsiedades(true)
                _leerAnsiedadResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Ansiedad

    private val _agregarAnsiedadResult = MutableStateFlow<Result<AgregarAnsiedadRespuesta>?>(null)
    val agregarAnsiedadResult = _agregarAnsiedadResult.asStateFlow()
    fun setAgregarAnsiedadResult(result: Result<AgregarAnsiedadRespuesta>?) {
        _agregarAnsiedadResult.value = result
    }

    fun agregaransiedades(user: AgregarAnsiedad) {
        _agregarAnsiedadResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarAnsiedades(jwtToken.value,user)
                _agregarAnsiedadResult.value = Result.success(response)
                leeransiedades(usuario_id.value)
            } catch(e: Exception){
                _agregarAnsiedadResult.value =Result.failure(e)
            }
        }
    }

    //Editar Ansiedad

    private val _editarAnsiedadResult = MutableStateFlow<Result<ModificarAnsiedadRespuesta>?>(null)
    val editarAnsiedadResult = _editarAnsiedadResult.asStateFlow()
    fun setEditarAnsiedadResult(result: Result<ModificarAnsiedadRespuesta>?) {
        _editarAnsiedadResult.value = result
    }

    fun editaransiedades(user: ModificarAnsiedad) {
        _editarAnsiedadResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarAnsiedades(jwtToken.value,user)
                _editarAnsiedadResult.value = Result.success(response)
                leeransiedades(usuario_id.value)
            } catch(e: Exception){
                _editarAnsiedadResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Ansiedad

    private val _eliminarAnsiedadResult = MutableStateFlow<Result<EliminarAnsiedadRespuesta>?>(null)
    val eliminarAnsiedadResult = _eliminarAnsiedadResult.asStateFlow()
    fun setEliminarAnsiedadResult(result: Result<EliminarAnsiedadRespuesta>?) {
        _eliminarAnsiedadResult.value = result
    }

    fun eliminaransiedades(user: Int) {
        _eliminarAnsiedadResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarAnsiedades(jwtToken.value,user)
                _eliminarAnsiedadResult.value = Result.success(response)
                leeransiedades(usuario_id.value)
            } catch(e: Exception){
                _eliminarAnsiedadResult.value =Result.failure(e)
            }
        }
    }

    //Filtro Ansiedad

    private val _filtroAnsiedadResult = MutableStateFlow<Result<LeerAnsiedadRespuesta>?>(null)
    val filtroAnsiedadResult = _filtroAnsiedadResult.asStateFlow()
    fun setFiltroAnsiedadResult(result: Result<LeerAnsiedadRespuesta>?) {
        _filtroAnsiedadResult.value = result
    }
    private val _filtroAnsiedadlist = MutableStateFlow(LeerAnsiedadRespuesta())
    val filtroAnsiedadlist = _filtroAnsiedadlist.asStateFlow()

    fun filtroansiedades(user: FiltroAnsiedad) {
        _filtroAnsiedadResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarAnsiedades(jwtToken.value,user)
                _filtroAnsiedadlist.value = response
                _filtroAnsiedadResult.value = Result.success(response)
            } catch(e: Exception){
                _filtroAnsiedadResult.value =Result.failure(e)
            }
        }
    }

    //Informacion Ansiedad

    private val _idAnsiedad = MutableStateFlow(0)
    val idAnsiedad = _idAnsiedad.asStateFlow()
    fun setIdAnsiedad(id: Int) {
        _idAnsiedad.value = id
    }

    private val _intensidadAnsiedad = MutableStateFlow("")
    val intensidadAnsiedad = _intensidadAnsiedad.asStateFlow()
    fun setIntensidadAnsiedad(intensidad: String) {
        _intensidadAnsiedad.value = intensidad
    }

    private val _sintomas = MutableStateFlow("")
    val sintomas = _sintomas.asStateFlow()
    fun setSintomas(tipo: String) {
        _sintomas.value = tipo
    }

    private val _nota = MutableStateFlow("")
    val nota = _nota.asStateFlow()
    fun setNota(descripcion: String) {
        _nota.value = descripcion
    }

    private val _hora = MutableStateFlow("") //Ansiedades
    val hora = _hora.asStateFlow()
    fun setHora(hora: String) {
        _hora.value = hora
    }

    //Tablas Ansiedad

    //Intensidades

    private val _leerIntensidadAnsiedad = MutableStateFlow<Result<IntensidadAnsiedad>?>(null)
    val leerIntensidad = _leerIntensidadAnsiedad.asStateFlow()
    private val _intensidadAnsiedadlist = MutableStateFlow(IntensidadAnsiedad())
    val intensidadlist = _intensidadAnsiedadlist.asStateFlow()

    fun intensidadAnsiedad() {
        _leerIntensidadAnsiedad.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.IntensidadesAns()
                _intensidadAnsiedadlist.value = response
                _leerIntensidadAnsiedad.value = Result.success(response)
            } catch(e: Exception){
                _leerIntensidadAnsiedad.value =Result.failure(e)
            }
        }
    }

    //Sintomas Ansiedad

    private val _leerSintomasAnsiedad = MutableStateFlow<Result<SintomasAnsiedad>?>(null)
    val leerSintomas = _leerSintomasAnsiedad.asStateFlow()
    private val _sintomasAnsiedadlist = MutableStateFlow(SintomasAnsiedad())
    val sintomaslist = _sintomasAnsiedadlist.asStateFlow()

    fun sintomasAnsiedad() {
        _leerSintomasAnsiedad.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.SintomasAns()
                _sintomasAnsiedadlist.value = response
                _leerSintomasAnsiedad.value = Result.success(response)
            } catch(e: Exception){
                _leerSintomasAnsiedad.value =Result.failure(e)
            }
        }
    }



    //Sueno

    //Leer Sueno

    private val _leersuenoResult = MutableStateFlow<Result<LeerSueñoRespuesta>?>(null)
    val leersuenoResult = _leersuenoResult.asStateFlow()
    fun setLeerSueñoResult(result: Result<LeerSueñoRespuesta>?) {
        _leersuenoResult.value = result
    }

    private val _suenolist = MutableStateFlow(LeerSueñoRespuesta())
    val suenolist = _suenolist.asStateFlow()

    fun leersueno(user : Int) {
        _leersuenoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerSuenos(jwtToken.value, user)
                _suenolist.value = response
                _leersuenoResult.value = Result.success(response)
            } catch(e: Exception){
                setNohaySuenos(true)
                _leersuenoResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Sueno

    private val _agregarsuenoResult = MutableStateFlow<Result<AgregarSueñoRespuesta>?>(null)
    val agregarsuenoResult = _agregarsuenoResult.asStateFlow()
    fun setAgregarSueñoResult(result: Result<AgregarSueñoRespuesta>?) {
        _agregarsuenoResult.value = result
    }

    fun agregarsueno(user : AgregarSueño) {
        _agregarsuenoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarSuenos(jwtToken.value, user)
                _agregarsuenoResult.value = Result.success(response)
                leersueno(usuario_id.value)
            } catch(e: Exception){
                _agregarsuenoResult.value =Result.failure(e)
            }
        }
    }

    //Editar Sueno

    private val _editarsuenoResult = MutableStateFlow<Result<ModificarSueñoRespuesta>?>(null)
    val editarsuenoResult = _editarsuenoResult.asStateFlow()
    fun setEditarSueñoResult(result: Result<ModificarSueñoRespuesta>?) {
        _editarsuenoResult.value = result
    }

    fun editarsueno(user : ModificarSueño) {
        _editarsuenoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarSuenos(jwtToken.value, user)
                _editarsuenoResult.value = Result.success(response)
                leersueno(usuario_id.value)
            } catch(e: Exception){
                _editarsuenoResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Sueno

    private val _eliminarsuenoResult = MutableStateFlow<Result<EliminarSueñoRespuesta>?>(null)
    val eliminarsuenoResult = _eliminarsuenoResult.asStateFlow()
    fun setEliminarSueñoResult(result: Result<EliminarSueñoRespuesta>?) {
        _eliminarsuenoResult.value = result
    }

    fun eliminarsueno(user : Int) {
        _eliminarsuenoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarSuenos(jwtToken.value, user)
                _eliminarsuenoResult.value = Result.success(response)
                leersueno(usuario_id.value)
            } catch(e: Exception){
                _eliminarsuenoResult.value =Result.failure(e)
            }
        }
    }

    //Filtro Sueno

    private val _filtrosuenoResult = MutableStateFlow<Result<LeerSueñoRespuesta>?>(null)
    val filtrosuenoResult = _filtrosuenoResult.asStateFlow()
    fun setFiltroSueñoResult(result: Result<LeerSueñoRespuesta>?) {
        _filtrosuenoResult.value = result
    }
    private val _filtrosuenolist = MutableStateFlow(LeerSueñoRespuesta())
    val filtrosuenolist = _filtrosuenolist.asStateFlow()

    fun filtrosueno(user : FiltroSueño) {
        _filtrosuenoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarSuenos(jwtToken.value, user)
                _filtrosuenolist.value = response
                _filtrosuenoResult.value = Result.success(response)
            } catch(e: Exception){
                _filtrosuenoResult.value =Result.failure(e)
            }
        }
    }

    //Informacion Sueno

    private val _idSueno = MutableStateFlow(0)
    val idSueno = _idSueno.asStateFlow()
    fun setIdSueno(id: Int) {
        _idSueno.value = id
    }

    private val _horas = MutableStateFlow(0) //Sueño
    val horas = _horas.asStateFlow()
    fun setHoras(horas: Int) {
        _horas.value = horas
    }

    private val _pastilla = MutableStateFlow("")
    val pastilla = _pastilla.asStateFlow()
    fun setPastilla(pastilla: String) {
        _pastilla.value = pastilla
    }

    private val _calidad = MutableStateFlow("")
    val calidad = _calidad.asStateFlow()
    fun setCalidad(calidad: String) {
        _calidad.value = calidad
    }

    private val _horadormir = MutableStateFlow("")
    val horadormir = _horadormir.asStateFlow()
    fun setHoradormir(horadormir: String) {
        _horadormir.value = horadormir
    }

    private val _horadespertar = MutableStateFlow("")
    val horadespertar = _horadespertar.asStateFlow()
    fun setHoradespertar(horadespertar: String) {
        _horadespertar.value = horadespertar
    }


    //Tablas Sueno

    //Calidades Sueno

    private val _leerCalidadesSueno = MutableStateFlow<Result<CalidadesSueño>?>(null)
    val leerCalidadesSueno = _leerCalidadesSueno.asStateFlow()
    private val _calidadeslist = MutableStateFlow(CalidadesSueño())
    val calidadeslist = _calidadeslist.asStateFlow()

    fun calidadesSueño() {
        _leerCalidadesSueno.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.Calidadessueno()
                _calidadeslist.value = response
                _leerCalidadesSueno.value = Result.success(response)
            } catch(e: Exception){
                _leerCalidadesSueno.value =Result.failure(e)
            }
        }
    }



    //Pastillas

    //Leer Pastillas

    private val _leerpastillasResult = MutableStateFlow<Result<LeerPastillasRespuesta>?>(null)
    val leerpastillasResult = _leerpastillasResult.asStateFlow()
    fun setLeerPastillasResult(result: Result<LeerPastillasRespuesta>?) {
        _leerpastillasResult.value = result
    }
    private val _pastillaslist = MutableStateFlow(LeerPastillasRespuesta())
    val pastillaslist = _pastillaslist.asStateFlow()

    fun leerpastillas(user: Int) {
        _leerpastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerPastillas(jwtToken.value,user)
                _pastillaslist.value = response
                _leerpastillasResult.value = Result.success(response)
            } catch(e: Exception){
                setNohayPastillas(true)
                _leerpastillasResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Pastillas

    private val _agregarpastillasResult = MutableStateFlow<Result<AgregarPastillasRespuesta>?>(null)
    val agregarpastillasResult = _agregarpastillasResult.asStateFlow()
    fun setAgregarPastillasResult(result: Result<AgregarPastillasRespuesta>?) {
        _agregarpastillasResult.value = result
    }

    fun agregarpastillas(user: AgregarPastillas) {
        _agregarpastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarPastillas(jwtToken.value,user)
                _agregarpastillasResult.value = Result.success(response)
                leerpastillas(usuario_id.value)
            } catch(e: Exception){
                _agregarpastillasResult.value =Result.failure(e)
            }
        }
    }

    //Editar Pastillas

    private val _editarpastillasResult = MutableStateFlow<Result<ModificarPastillasRespuesta>?>(null)
    val editarpastillasResult = _editarpastillasResult.asStateFlow()
    fun setEditarPastillasResult(result: Result<ModificarPastillasRespuesta>?) {
        _editarpastillasResult.value = result
    }

    fun editarpastillas(user: ModificarPastillas) {
        _editarpastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarPastillas(jwtToken.value,user)
                _editarpastillasResult.value = Result.success(response)
                leerpastillas(usuario_id.value)
            } catch(e: Exception){
                _editarpastillasResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Pastillas

    private val _eliminarpastillasResult = MutableStateFlow<Result<EliminarPastillaRespuesta>?>(null)
    val eliminarpastillasResult = _eliminarpastillasResult.asStateFlow()
    fun setEliminarPastillasResult(result: Result<EliminarPastillaRespuesta>?) {
        _eliminarpastillasResult.value = result
    }
    fun eliminarpastillas(user: Int) {
        _eliminarpastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarPastillas(jwtToken.value,user)
                _eliminarpastillasResult.value = Result.success(response)
                leerpastillas(usuario_id.value)
            } catch(e: Exception){
                _eliminarpastillasResult.value =Result.failure(e)
            }
        }
    }

    //Filtro Pastillas

    private val _filtropastillasResult = MutableStateFlow<Result<LeerPastillasRespuesta>?>(null)
    val filtropastillasResult = _filtropastillasResult.asStateFlow()
    private val _filtropastillaslist = MutableStateFlow(LeerPastillasRespuesta())
    val filtropastillaslist = _filtropastillaslist.asStateFlow()
    fun setFiltroPastillasResult(result: Result<LeerPastillasRespuesta>?) {
        _filtropastillasResult.value = result
    }

    fun filtropastillas(user: FiltroPastillas) {
        _filtropastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarPastillas(jwtToken.value,user)
                _filtropastillaslist.value = response
                _filtropastillasResult.value = Result.success(response)
            } catch(e: Exception){
                _filtropastillasResult.value =Result.failure(e)
            }
        }
    }

    //Informacion Pastillas

    private val _idPastilla = MutableStateFlow(0)
    val idPastilla = _idPastilla.asStateFlow()

    fun setIdPastilla(id: Int) {
        _idPastilla.value = id
    }

    private val _nombrepastilla = MutableStateFlow("")
    val nombrepastilla = _nombrepastilla.asStateFlow()
    fun setNombrePastilla(nombre: String) {
        _nombrepastilla.value = nombre
    }

    private val _dosispastilla = MutableStateFlow("")
    val dosispastilla = _dosispastilla.asStateFlow()
    fun setDosisPastilla(dosis: String) {
        _dosispastilla.value = dosis
    }

    private val _tiempopastilla = MutableStateFlow("")
    val tiempopastilla = _tiempopastilla.asStateFlow()
    fun setTiempoPastilla(tiempo: String) {
        _tiempopastilla.value = tiempo
    }

    private val _periodopastilla = MutableStateFlow(0)
    val periodopastilla = _periodopastilla.asStateFlow()
    fun setPeriodoPastilla(periodo: Int) {
        _periodopastilla.value = periodo
    }

    //Tablas Pastillas

    //Periodos

    private val _leerPeriodosPastillas = MutableStateFlow<Result<PeriodoPastillas>?>(null)
    val leerPeriodosPastillas = _leerPeriodosPastillas.asStateFlow()
    private val _periodoslist = MutableStateFlow(PeriodoPastillas())
    val periodoslist = _periodoslist.asStateFlow()

    fun leerperiodospastillas() {
        _leerPeriodosPastillas.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.PeriodoPastillas()
                _periodoslist.value = response
                _leerPeriodosPastillas.value = Result.success(response)
            } catch(e: Exception){
                _leerPeriodosPastillas.value =Result.failure(e)
            }
        }
    }


    //Presiones

    //Leer Presiones

    private val _leerpresionesResult = MutableStateFlow<Result<LeerPresionesRespuesta>?>(null)
    val leerpresionesResult =_leerpresionesResult.asStateFlow()
    fun setLeerPresionesResult(result: Result<LeerPresionesRespuesta>?) {
        _leerpresionesResult.value = result
    }
    private val _presioneslist = MutableStateFlow(LeerPresionesRespuesta())
    val presioneslist = _presioneslist.asStateFlow()

    fun leerpresiones(user: Int) {
        _leerpresionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerPresiones(jwtToken.value,user)
                _presioneslist.value = response
                _leerpresionesResult.value = Result.success(response)
            } catch(e: Exception){
                setNohayPresiones(true)
                _leerpresionesResult.value =Result.failure(e)
            }
        }
    }

    //Agregar Presiones

    private val _agregarpresionesResult = MutableStateFlow<Result<AgregarPresionRespuesta>?>(null)
    val agregarpresionesResult =_agregarpresionesResult.asStateFlow()
    fun setAgregarPresionesResult(result: Result<AgregarPresionRespuesta>?) {
        _agregarpresionesResult.value = result
    }

    fun agregarpresiones(user: AgregarPresion) {
        _agregarpresionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.agregarPresiones(jwtToken.value,user)
                _agregarpresionesResult.value = Result.success(response)
                leerpresiones(usuario_id.value)
            } catch(e: Exception){
                _agregarpresionesResult.value =Result.failure(e)
            }
        }
    }

    //Editar Presiones

    private val _editarpresionesResult = MutableStateFlow<Result<ModificarPresionRespuesta>?>(null)
    val editarpresionesResult =_editarpresionesResult.asStateFlow()
    fun setEditarPresionesResult(result: Result<ModificarPresionRespuesta>?) {
        _editarpresionesResult.value = result
    }

    fun editarpresiones(user: ModificarPresion) {
        _editarpresionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarPresiones(jwtToken.value,user)
                _editarpresionesResult.value = Result.success(response)
                leerpresiones(usuario_id.value)
            } catch(e: Exception){
                _editarpresionesResult.value =Result.failure(e)
            }
        }
    }

    //Eliminar Presiones

    private val _eliminarpresionesResult = MutableStateFlow<Result<EliminarPresionRespuesta>?>(null)
    val eliminarpresionesResult =_eliminarpresionesResult.asStateFlow()
    fun setEliminarPresionesResult(result: Result<EliminarPresionRespuesta>?) {
        _eliminarpresionesResult.value = result
    }

    fun eliminarpresiones(user: Int) {
        _eliminarpresionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.eliminarPresiones(jwtToken.value,user)
                _eliminarpresionesResult.value = Result.success(response)
                leerpresiones(usuario_id.value)
            } catch(e: Exception){
                _eliminarpresionesResult.value =Result.failure(e)
            }
        }
    }

    //Filtro Presiones

    private val _filtropresionesResult = MutableStateFlow<Result<LeerPresionesRespuesta>?>(null)
    val filtropresionesResult =_filtropresionesResult.asStateFlow()
    private val _filtropresioneslist = MutableStateFlow(LeerPresionesRespuesta())
    val filtropresioneslist = _filtropresioneslist.asStateFlow()
    fun setFiltroPresionesResult(result: Result<LeerPresionesRespuesta>?) {
        _filtropresionesResult.value = result
    }

    fun filtropresiones(user: FiltroPresion) {
        _filtropresionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.filtrarPresiones(jwtToken.value,user)
                _filtropresioneslist.value = response
                _filtropresionesResult.value = Result.success(response)
            } catch(e: Exception){
                _filtropresionesResult.value =Result.failure(e)
            }
        }
    }

    //Informacion Presiones

    private val _idPresion = MutableStateFlow(0)
    val idPresion = _idPresion.asStateFlow()

    fun setIdPresion(id: Int) {
        _idPresion.value = id
    }

    private val _horapresion = MutableStateFlow("")
    val horapresion = _horapresion.asStateFlow()

    fun setHoraPresion(hora: String) {
        _horapresion.value = hora
    }

    private val _sistolica = MutableStateFlow(0)
    val sistolica = _sistolica.asStateFlow()
    fun setSistolica(sistolica: Int) {
        _sistolica.value = sistolica
    }

    private val _diastolica = MutableStateFlow(0)
    val diastolica = _diastolica.asStateFlow()
    fun setDiastolica(diastolica: Int) {
        _diastolica.value = diastolica
    }

    private val _emocionpresion = MutableStateFlow("")
    val emocionpresion = _emocionpresion.asStateFlow()
    fun setEmocionPresion(emocion: String) {
        _emocionpresion.value = emocion
    }

    //Tablas Presiones

    //Emociones Presion

    private val _leerEmocionesPresion = MutableStateFlow<Result<EmocionesPresion>?>(null)
    val leerEmocionesPresion = _leerEmocionesPresion.asStateFlow()
    private val _emocionespresionlist = MutableStateFlow(EmocionesPresion())
    val emocioneslistpresion = _emocionespresionlist.asStateFlow()


    fun emocionespresion() {
        _leerEmocionesPresion.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.EmocionesPresion()
                _emocionespresionlist.value = response
                _leerEmocionesPresion.value = Result.success(response)
            } catch(e: Exception){
                _leerEmocionesPresion.value =Result.failure(e)
            }
        }
    }



    //Informacion Personal

    //Obtener Informacion Personal

    private val _leerUsuarioResult = MutableStateFlow<Result<JalarInformacionRespuesta>?>(null)
    val leerUsuarioResult = _leerUsuarioResult.asStateFlow()


    fun setLeerUsuarioResult(result: Result<JalarInformacionRespuesta>?) {
        _leerUsuarioResult.value = result
    }

    private val _altura = MutableStateFlow(prefs.getString("altura",""))
    val altura = _altura.asStateFlow()
    fun setAltura(altura: String?) {
        viewModelScope.launch {
            prefs.edit().putString("altura",altura).apply()
            _altura.value = altura
        }
    }

    private val _apellidomaterno = MutableStateFlow(prefs.getString("apellidomaterno",""))
    val apellidomaterno = _apellidomaterno.asStateFlow()
    fun setApellidoMaterno(apellidomaterno: String?) {
        viewModelScope.launch {
            prefs.edit().putString("apellidomaterno", apellidomaterno).apply()
            _apellidomaterno.value = apellidomaterno
        }
    }

    private val _apellidopaterno = MutableStateFlow(prefs.getString("apellidopaterno",""))
    val apellidopaterno = _apellidopaterno.asStateFlow()

    fun setApellidoPaterno(apellidopaterno: String?) {
        viewModelScope.launch {
            prefs.edit().putString("apellidopaterno", apellidopaterno).apply()
            _apellidopaterno.value = apellidopaterno
        }
    }

    private val _circunferencia = MutableStateFlow(prefs.getString("circunferencia",""))
    val circunferencia = _circunferencia.asStateFlow()

    fun setCircunferencia(circunferencia: String?) {
        viewModelScope.launch {
            prefs.edit().putString("circunferencia", circunferencia).apply()
            _circunferencia.value = circunferencia
        }
    }

    private val _edad = MutableStateFlow(prefs.getInt("edad",0))
    val edad = _edad.asStateFlow()
    fun setEdad(edad: Int) {
        viewModelScope.launch {
            prefs.edit().putInt("edad", edad).apply()
            _edad.value = edad
        }
    }

    private val _peso = MutableStateFlow(prefs.getString("peso",""))
    val peso = _peso.asStateFlow()

    fun setPeso(peso: String?) {
        viewModelScope.launch {
            prefs.edit().putString("peso", peso).apply()
            _peso.value = peso
        }
    }

    private val _email = MutableStateFlow(prefs.getString("email",""))
    val email = _email.asStateFlow()
    fun setEmail(email: String?) {
        viewModelScope.launch {
            prefs.edit().putString("email", email).apply()
            _email.value = email
        }
    }

    private val _nombre = MutableStateFlow(prefs.getString("nombre",""))
    val nombre = _nombre.asStateFlow()
    fun setNombre(nombre: String?) {
        viewModelScope.launch {
            prefs.edit().putString("nombre", nombre).apply()
            _nombre.value = nombre
        }
    }

    val _sexo = MutableStateFlow(prefs.getString("sexo",""))
    val sexo = _sexo.asStateFlow()
    fun setSexo(sexo: String?) {
        viewModelScope.launch {
            prefs.edit().putString("sexo", sexo).apply()
            _sexo.value = sexo
        }
    }

    fun jalarinformacionusuarios(user: Int) {
        _leerUsuarioResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.jalarDatosUsuario(jwtToken.value,user)
                setAltura(response.altura?.let { cambiar_valores(it) })
                setApellidoMaterno(response.apellido_materno)
                setApellidoPaterno(response.apellido_paterno)
                setCircunferencia(response.circunferencia_abdominal?.let { cambiar_valores(it) })
                setEdad(response.edad)
                setPeso(response.peso?.let { cambiar_valores(it)})
                setEmail(response.email)
                setNombre(response.nombre)
                setSexo(response.sexo)

                _leerUsuarioResult.value = Result.success(response)
            } catch(e: Exception){
                _leerUsuarioResult.value =Result.failure(e)
            }
        }
    }

    fun jalarinformacionusuarios2(user: Int) {
        _leerUsuarioResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.jalarDatosUsuario(jwtToken.value,user)
                setAltura(response.altura?.let { cambiar_valores(it) })
                setApellidoMaterno(response.apellido_materno)
                setApellidoPaterno(response.apellido_paterno)
                setCircunferencia(response.circunferencia_abdominal?.let { cambiar_valores(it) })
                setEdad(response.edad)
                setPeso(response.peso?.let { cambiar_valores(it)})
                setEmail(response.email)
                setNombre(response.nombre)
                setSexo(response.sexo)
            } catch(e: Exception){
                _leerUsuarioResult.value =Result.failure(e)
            }
        }
    }
fun cambiar_valores(datoString: String):String{
    val datoDouble = datoString.toDouble()
    val datoInt = datoDouble.toInt()
    return datoInt.toString()

}



    //Modificar Usuario

    private val _editarUsuarioResult = MutableStateFlow<Result<ModificarDatosUsuarioRespuesta>?>(null)
    val editarUsuarioResult = _editarUsuarioResult.asStateFlow()
    fun setEditarUsuarioResult(result: Result<ModificarDatosUsuarioRespuesta>?) {
        _editarUsuarioResult.value = result
    }

    fun editarDatosUsuario(user: ModificarDatosUsuario) {
        _editarUsuarioResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarUsuario(jwtToken.value,user)
                _editarUsuarioResult.value = Result.success(response)
                jalarinformacionusuarios(usuario_id.value)
            } catch(e: Exception){
                _editarUsuarioResult.value =Result.failure(e)
            }
        }
    }

    //Modificar Doctor

    private val _editarDoctorResult = MutableStateFlow<Result<ModificarDatosDoctorRespuesta>?>(null)
    val editarDoctorResult = _editarDoctorResult.asStateFlow()
    fun setEditarDoctorResult(result: Result<ModificarDatosDoctorRespuesta>?){
        _editarDoctorResult.value = result
    }
    fun editarDatosDoctor(user: ModificarDatosDoctor) {
        _editarDoctorResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarDoctor(jwtToken.value,user)
                _editarDoctorResult.value = Result.success(response)
                jalarinformacionusuarios(usuario_id.value)
            } catch(e: Exception){
                _editarDoctorResult.value =Result.failure(e)
            }
        }
    }

    //Modificar Password

    private val _editarPasswordResult = MutableStateFlow<Result<ModificarContraseñaRespuesta>?>(null)
    val editarPasswordResult = _editarPasswordResult.asStateFlow()

    fun setEditarPasswordResult(result: Result<ModificarContraseñaRespuesta>?) {
        _editarPasswordResult.value = result
    }

    fun editarPassword(user: ModificarContraseña) {
        _editarPasswordResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.editarContraseña(jwtToken.value,user)
                _editarPasswordResult.value = Result.success(response)
                jalarinformacionusuarios(usuario_id.value)
            } catch(e: Exception){
                _editarPasswordResult.value =Result.failure(e)
            }
        }
    }


    //Jalar Usuarios Doc

    private val _leerUsuariosDocResult = MutableStateFlow<Result<JalarUsuariosDocRespuesta>?>(null)
    val leerUsuariosDocResult = _leerUsuariosDocResult.asStateFlow()
    fun setLeerUsuariosDocResult(result: Result<JalarUsuariosDocRespuesta>?) {
        _leerUsuariosDocResult.value = result
    }
    private val _usuariosDoclist = MutableStateFlow(JalarUsuariosDocRespuesta())
    val usuariosDoclist = _usuariosDoclist.asStateFlow()

    private val _user_edad= MutableStateFlow(0)
    val user_edad = _user_edad.asStateFlow()
    fun setUserEdad(edad: Int) {
        _user_edad.value = edad
    }

    private val _user_nombre= MutableStateFlow("")
    val user_nombre = _user_nombre.asStateFlow()
    fun setUserNombre(nombre: String) {
        _user_nombre.value = nombre
    }

    private val _user_sexo= MutableStateFlow("")
    val user_sexo = _user_sexo.asStateFlow()
    fun setUserSexo(sexo: String) {
        _user_sexo.value = sexo
    }

    private val _user_id= MutableStateFlow(0)
    val user_id = _user_id.asStateFlow()
    fun setUser_id(id: Int) {
        _user_id.value = id
    }


    fun leerUsuariosDoc(user: String) {
        _leerUsuariosDocResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.jalarUsuariosDoc(jwtToken.value,user)
                _usuariosDoclist.value = response
                _leerUsuariosDocResult.value = Result.success(response)
            } catch(e: Exception){
                _leerUsuariosDocResult.value =Result.failure(e)
            }
        }
    }


    //Variables en caso de no tener datos en procesos

    private val _nohayAlimentoss = MutableStateFlow(false)
    val nohayAlimentoss = _nohayAlimentoss.asStateFlow()
    fun setNohayAlimentos(nohay: Boolean) {
        _nohayAlimentoss.value = nohay
    }

    private val _nohayActividades= MutableStateFlow(false)
    val nohayActividades = _nohayActividades.asStateFlow()
    fun setNohayActividades(nohay: Boolean) {
        _nohayActividades.value = nohay
    }

    private val _nohayAnsiedades = MutableStateFlow(false)
    val nohayAnsiedades = _nohayAnsiedades.asStateFlow()
    fun setNohayAnsiedades(nohay: Boolean) {
        _nohayAnsiedades.value = nohay
    }

    private val _nohaySuenos = MutableStateFlow(false)
    val nohaySuenos = _nohaySuenos.asStateFlow()
    fun setNohaySuenos(nohay: Boolean) {
        _nohaySuenos.value = nohay
    }

    private val _nohayPastillas = MutableStateFlow(false)
    val nohayPastillas = _nohayPastillas.asStateFlow()
    fun setNohayPastillas(nohay: Boolean) {
        _nohayPastillas.value = nohay
    }

    private val _nohayPresiones = MutableStateFlow(false)
    val nohayPresiones = _nohayPresiones.asStateFlow()
    fun setNohayPresiones(nohay: Boolean) {
        _nohayPresiones.value = nohay
    }

    //Jalar datos de todos los procesos para resumen


    //Leer Alimentos
    private val _leerAlimentosResumenResult = MutableStateFlow<Result<LeerAlimentosRespuesta>?>(null)
    val leerAlimentosResumenResult = _leerAlimentosResumenResult.asStateFlow()
    fun setLeerAlimentosResumenResult(result: Result<LeerAlimentosRespuesta>?) {
        _leerAlimentosResumenResult.value = result
    }
    private val _alimentosResumenlist = MutableStateFlow(LeerAlimentosRespuesta())
    val alimentosResumenlist = _alimentosResumenlist.asStateFlow()

    fun leeralimentosResumen(usuario_id: Int) {
        _leerAlimentosResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerAlimentos(jwtToken.value,usuario_id)
                _alimentosResumenlist.value = response
                _leerAlimentosResumenResult.value = Result.success(response)
                setNohayAlimentos(false)
            } catch(e: Exception){
                setNohayAlimentos(true)
                _leerAlimentosResumenResult.value =Result.failure(e)
            }
        }
    }

//Leer Actividades

    private val _leerActividadesResumenResult = MutableStateFlow<Result<LeerActividadRespuesta>?>(null)
    val leerActividadesResumenResult = _leerActividadesResumenResult.asStateFlow()
    fun setLeerActividadesResumenResult(result: Result<LeerActividadRespuesta>?) {
        _leerActividadesResumenResult.value = result
    }

    private val _actividadesResumenlist = MutableStateFlow(LeerActividadRespuesta())
    val actividadesResumenlist = _actividadesResumenlist.asStateFlow()

    fun leeractividadesResumen(user: Int) {
        _leerActividadesResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerActividades(jwtToken.value,user)
                _actividadesResumenlist.value = response
                _leerActividadesResumenResult.value = Result.success(response)
                setNohayActividades(false)
            } catch(e: Exception){
                setNohayActividades(true)
                _leerActividadesResumenResult.value =Result.failure(e)
            }
        }
    }


//Leer Ansiedad

    private val _leerAnsiedadResumenResult = MutableStateFlow<Result<LeerAnsiedadRespuesta>?>(null)
    val leerAnsiedadResumenResult = _leerAnsiedadResumenResult.asStateFlow()
    fun setLeerAnsiedadResumenResult(result: Result<LeerAnsiedadRespuesta>?) {
        _leerAnsiedadResumenResult.value = result
    }
    private val _ansiedadResumenlist = MutableStateFlow(LeerAnsiedadRespuesta())
    val ansiedadResumenlist = _ansiedadResumenlist.asStateFlow()

    fun leeransiedadesResumen(user: Int) {
        _leerAnsiedadResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerAnsiedades(jwtToken.value,user)
                _ansiedadResumenlist.value = response
                _leerAnsiedadResumenResult.value = Result.success(response)
                setNohayAnsiedades(false)
            } catch(e: Exception){
                setNohayAnsiedades(true)
                _leerAnsiedadResumenResult.value =Result.failure(e)
            }
        }
    }


//Leer Sueno

    private val _leersuenoResumenResult = MutableStateFlow<Result<LeerSueñoRespuesta>?>(null)
    val leersuenoResumenResult = _leersuenoResumenResult.asStateFlow()
    fun setLeerSueñoResumenResult(result: Result<LeerSueñoRespuesta>?) {
        _leersuenoResumenResult.value = result
    }

    private val _suenoResumenlist = MutableStateFlow(LeerSueñoRespuesta())
    val suenoResumenlist = _suenoResumenlist.asStateFlow()

    fun leersuenoResumen(user : Int) {
        _leersuenoResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerSuenos(jwtToken.value, user)
                _suenoResumenlist.value = response
                _leersuenoResumenResult.value = Result.success(response)
                setNohaySuenos(false)
            } catch(e: Exception){
                setNohaySuenos(true)
                _leersuenoResumenResult.value =Result.failure(e)
            }
        }
    }


//Leer Pastillas

    private val _leerpastillasResumenResult = MutableStateFlow<Result<LeerPastillasRespuesta>?>(null)
    val leerpastillasResumenResult = _leerpastillasResumenResult.asStateFlow()
    fun setLeerPastillasResumenResult(result: Result<LeerPastillasRespuesta>?) {
        _leerpastillasResumenResult.value = result
    }
    private val _pastillasResumenlist = MutableStateFlow(LeerPastillasRespuesta())
    val pastillasResumenlist = _pastillasResumenlist.asStateFlow()

    fun leerpastillasResumen(user: Int) {
        _leerpastillasResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerPastillas(jwtToken.value,user)
                _pastillasResumenlist.value = response
                _leerpastillasResumenResult.value = Result.success(response)
                setNohayPastillas(false)
            } catch(e: Exception){
                setNohayPastillas(true)
                _leerpastillasResumenResult.value =Result.failure(e)
            }
        }
    }

//Leer Presiones

    private val _leerpresionesResumenResult = MutableStateFlow<Result<LeerPresionesRespuesta>?>(null)
    val leerpresionesResumenResult =_leerpresionesResumenResult.asStateFlow()
    fun setLeerPresionesResumenResult(result: Result<LeerPresionesRespuesta>?) {
        _leerpresionesResumenResult.value = result
    }
    private val _presionesResumenlist = MutableStateFlow(LeerPresionesRespuesta())
    val presionesResumenlist = _presionesResumenlist.asStateFlow()


    fun leerpresionesResumen(user: Int) {
        _leerpresionesResumenResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.leerPresiones(jwtToken.value,user)
                _presionesResumenlist.value = response
                _leerpresionesResumenResult.value = Result.success(response)
                setNohayPresiones(false)
            } catch(e: Exception){
                setNohayPresiones(true)
                _leerpresionesResumenResult.value =Result.failure(e)
            }
        }
    }



    private val _exitodatos = MutableStateFlow(false)
    val exitodatos = _exitodatos.asStateFlow()
    fun setExitodatos(exitodatos: Boolean) {
        _exitodatos.value = exitodatos
    }

    fun leertodoslosdatos(user: Int) {
        leeralimentosResumen(user)
        leeractividadesResumen(user)
        leeransiedadesResumen(user)
        leersuenoResumen(user)
        leerpastillasResumen(user)
        leerpresionesResumen(user)
        _exitodatos.value = true
    }

    //Funciones para las graficas

    //Alimentos porciones

    private val _tablaAlimentosPorcionesResult = MutableStateFlow<Result<DonaGraphResult>?>(null)
    val tablaAlimentosPorciones = _tablaAlimentosPorcionesResult.asStateFlow()
    fun setTablaAlimentosPorciones(result: Result<DonaGraphResult>?) {
        _tablaAlimentosPorcionesResult.value = result
    }
    private val _tablaAlimentosPorcioneslist = MutableStateFlow<List<String>?>(null)
    val tablaAlimentosPorcioneslist = _tablaAlimentosPorcioneslist.asStateFlow()
    fun setTablaAlimentosPorcioneslist(list: List<String>?) {
        _tablaAlimentosPorcioneslist.value = list
    }

    fun leertablaAlimentosPorciones(user: DataGraph) {
        _tablaAlimentosPorcionesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.procionesAlimentos(jwtToken.value,user)
                _tablaAlimentosPorcioneslist.value=response.tipos_porcion
                _tablaAlimentosPorcionesResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaAlimentosPorcionesResult.value =Result.failure(e)
            }
        }
    }

    //Alimentos Fechas

    private val _tablaAlimentosFechasResult = MutableStateFlow<Result<BarrasGraphResult>?>(null)
    val tablaAlimentosFechas = _tablaAlimentosFechasResult.asStateFlow()
    fun setTablaAlimentosFechas(result: Result<BarrasGraphResult>?) {
        _tablaAlimentosFechasResult.value = result
    }

    private val _tablaAlimentosFechaslist = MutableStateFlow<List<String>?>(null)
    val tablaAlimentosFechaslist = _tablaAlimentosFechaslist.asStateFlow()
    fun setTablaAlimentosFechaslist(list: List<String>?) {
        _tablaAlimentosFechaslist.value = list
    }

    fun leertablaAlimentosfechas(user: DataGraph) {
        _tablaAlimentosFechasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.fechasAlimentos(jwtToken.value,user)
                _tablaAlimentosFechaslist.value=response.Fechas
                _tablaAlimentosFechasResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaAlimentosFechasResult.value =Result.failure(e)
            }
        }
    }

    //Actividades Intensidades

    private val _tablaActividadesIntensidadesResult = MutableStateFlow<Result<IntensidadActividadesResult>?>(null)
    val tablaActividadesIntensidades = _tablaActividadesIntensidadesResult.asStateFlow()
    fun setTablaActividadesIntensidades(result: Result<IntensidadActividadesResult>?) {
        _tablaActividadesIntensidadesResult.value = result
    }

    private val _tablaActividadesIntensidadeslist = MutableStateFlow<List<String>?>(null)
    val tablaActividadesIntensidadeslist = _tablaActividadesIntensidadeslist
    fun setTablaActividadesIntensidadeslist(list: List<String>?) {
        _tablaActividadesIntensidadeslist.value = list
    }

    fun leertablaActividadesIntensidades(user: DataGraph) {
        _tablaActividadesIntensidadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.intensidadActividades(jwtToken.value,user)
                _tablaActividadesIntensidadeslist.value=response.IntensidadesActividad
                _tablaActividadesIntensidadesResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaActividadesIntensidadesResult.value =Result.failure(e)
            }
        }
    }

    //Actividades Tipos

    private val _tablaActividadesTiposResult = MutableStateFlow<Result<TipoActividadesResult>?>(null)
    val tablaActividadesTipos = _tablaActividadesTiposResult.asStateFlow()
    fun setTablaActividadesTipos(result: Result<TipoActividadesResult>?) {
        _tablaActividadesTiposResult.value = result
    }

    private val _tablaActividadesTiposlist = MutableStateFlow<List<String>?>(null)
    val tablaActividadesTiposlist = _tablaActividadesTiposlist
    fun setTablaActividadesTiposlist(list: List<String>?) {
        _tablaActividadesTiposlist.value = list
    }

    fun leertablaActividadesTipos(user: DataGraph) {
        _tablaActividadesTiposResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.tipoActividades(jwtToken.value,user)
                _tablaActividadesTiposlist.value=response.tiposActividad
                _tablaActividadesTiposResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaActividadesTiposResult.value =Result.failure(e)
            }
        }
    }


    //Ansiedad Intensidad

    private val _tablaAnsiedadIntensidadesResult = MutableStateFlow<Result<IntensidadAnsiedadesResult>?>(null)
    val tablaAnsiedadIntensidades = _tablaAnsiedadIntensidadesResult.asStateFlow()
    fun setTablaAnsiedadIntensidades(result: Result<IntensidadAnsiedadesResult>?) {
        _tablaAnsiedadIntensidadesResult.value = result
    }

    private val _tablaAnsiedadIntensidadeslist = MutableStateFlow<List<String>?>(null)
    val tablaAnsiedadIntensidadeslist = _tablaAnsiedadIntensidadeslist
    fun setTablaAnsiedadIntensidadeslist(list: List<String>?) {
        _tablaAnsiedadIntensidadeslist.value = list
    }

    fun leertablaAnsiedadIntensidades(user: DataGraph) {
        _tablaAnsiedadIntensidadesResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.intensidadAnsiedad(jwtToken.value,user)
                _tablaAnsiedadIntensidadeslist.value=response.Intensidades
                _tablaAnsiedadIntensidadesResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaAnsiedadIntensidadesResult.value =Result.failure(e)
            }
        }
    }


    //Ansiedad Sintomas

    private val _tablaAnsiedadSintomasResult = MutableStateFlow<Result<SintomasResult>?>(null)
    val tablaAnsiedadSintomas = _tablaAnsiedadSintomasResult.asStateFlow()
    fun setTablaAnsiedadSintomas(result: Result<SintomasResult>?) {
        _tablaAnsiedadSintomasResult.value = result
    }

    private val _tablaAnsiedadSintomaslist = MutableStateFlow<List<String>?>(null)
    val tablaAnsiedadSintomaslist = _tablaAnsiedadSintomaslist
    fun setTablaAnsiedadSintomaslist(list: List<String>?) {
        _tablaAnsiedadSintomaslist.value = list
    }

    fun leertablaAnsiedadSintomas(user: DataGraph) {
        _tablaAnsiedadSintomasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.sintomasAnsiedad(jwtToken.value,user)
                _tablaAnsiedadSintomaslist.value=response.Sintomas
                _tablaAnsiedadSintomasResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaAnsiedadSintomasResult.value =Result.failure(e)
            }
        }
    }


    //Presion Diastolica

    private val _tablaPresionDiastolicaResult = MutableStateFlow<Result<DiastolicasResult>?>(null)
    val tablaPresionDiastolica = _tablaPresionDiastolicaResult.asStateFlow()
    fun setTablaPresionDiastolica(result: Result<DiastolicasResult>?) {
        _tablaPresionDiastolicaResult.value = result
    }

    private val _tablaPresionDiastolicalist = MutableStateFlow<List<String>?>(null)
    val tablaPresionDiastolicalist = _tablaPresionDiastolicalist
    fun setTablaPresionDiastolicalist(list: List<String>?) {
        _tablaPresionDiastolicalist.value = list
    }

    fun leertablaPresionDiastolica(user: DataGraph) {
        _tablaPresionDiastolicaResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.diastolicaPresion(jwtToken.value,user)
                _tablaPresionDiastolicalist.value=response.Diastolicas
                _tablaPresionDiastolicaResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaPresionDiastolicaResult.value =Result.failure(e)
            }
        }
    }


    //Presion Sistolica

    private val _tablaPresionSistolicaResult = MutableStateFlow<Result<SistolicasResult>?>(null)
    val tablaPresionSistolica = _tablaPresionSistolicaResult.asStateFlow()
    fun setTablaPresionSistolica(result: Result<SistolicasResult>?) {
        _tablaPresionSistolicaResult.value = result
    }

    private val _tablaPresionSistolicalist = MutableStateFlow<List<String>?>(null)
    val tablaPresionSistolicalist = _tablaPresionSistolicalist
    fun setTablaPresionSistolicalist(list: List<String>?) {
        _tablaPresionSistolicalist.value = list
    }

    fun leertablaPresionSistolica(user: DataGraph) {
        _tablaPresionSistolicaResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.sistolicaPresion(jwtToken.value,user)
                _tablaPresionSistolicalist.value=response.Sistolicas
                _tablaPresionSistolicaResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaPresionSistolicaResult.value =Result.failure(e)
            }
        }
    }


    //Pastillas Tiempo

    private val _tablaPastillasTiempoResult = MutableStateFlow<Result<TiempoPastillasResult>?>(null)
    val tablaPastillasTiempo = _tablaPastillasTiempoResult.asStateFlow()
    fun setTablaPastillasTiempo(result: Result<TiempoPastillasResult>?) {
        _tablaPastillasTiempoResult.value = result
    }

    private val _tablaPastillasTiempolist = MutableStateFlow<List<String>?>(null)
    val tablaPastillasTiempolist = _tablaPastillasTiempolist
    fun setTablaPastillasTiempolist(list: List<String>?) {
        _tablaPastillasTiempolist.value = list
    }

    fun leertablaPastillasTiempo(user: DataGraph) {
        _tablaPastillasTiempoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.tiempoPastillas(jwtToken.value,user)
                _tablaPastillasTiempolist.value=response.TiempoPastillas
                _tablaPastillasTiempoResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaPastillasTiempoResult.value =Result.failure(e)
            }
        }
    }


    //Pastillas Medicamento

    private val _tablaPastillasMedicamentoResult = MutableStateFlow<Result<MedicamentosResult>?>(null)
    val tablaPastillasMedicamento = _tablaPastillasMedicamentoResult.asStateFlow()
    fun setTablaPastillasMedicamento(result: Result<MedicamentosResult>?) {
        _tablaPastillasMedicamentoResult.value = result
    }

    private val _tablaPastillasMedicamentolist = MutableStateFlow<List<String>?>(null)
    val tablaPastillasMedicamentolist = _tablaPastillasMedicamentolist
    fun setTablaPastillasMedicamentolist(list: List<String>?) {
        _tablaPastillasMedicamentolist.value = list
    }

    fun leertablaPastillasMedicamento(user: DataGraph) {
        _tablaPastillasMedicamentoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.medicamentosPastillas(jwtToken.value,user)
                _tablaPastillasMedicamentolist.value=response.Medicamentos
                _tablaPastillasMedicamentoResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaPastillasMedicamentoResult.value =Result.failure(e)
            }
        }
    }


    //Sueno Pastilla

    private val _tablaSuenopastillasResult = MutableStateFlow<Result<PastillaSuenoResult>?>(null)
    val tablaSuenopastillas = _tablaSuenopastillasResult.asStateFlow()
    fun setTablaSuenopastillas(result: Result<PastillaSuenoResult>?) {
        _tablaSuenopastillasResult.value = result
    }

    private val _tablaSuenopastillaslist = MutableStateFlow<List<String>?>(null)
    val tablaSuenopastillaslist = _tablaSuenopastillaslist
    fun setTablaSuenopastillaslist(list: List<String>?) {
        _tablaSuenopastillaslist.value = list
    }

    fun leertablaSuenopastillas(user: DataGraph) {
        _tablaSuenopastillasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.pastillaSueno(jwtToken.value,user)
                _tablaSuenopastillaslist.value=response.PastillasSueno
                _tablaSuenopastillasResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaSuenopastillasResult.value =Result.failure(e)
            }
        }
    }


    //Sueno horas

    private val _tablaSuenohorasResult = MutableStateFlow<Result<HorasResult>?>(null)
    val tablaSuenohoras = _tablaSuenohorasResult.asStateFlow()
    fun setTablaSuenohoras(result: Result<HorasResult>?) {
        _tablaSuenohorasResult.value = result
    }

    private val _tablaSuenohoraslist = MutableStateFlow<List<String>?>(null)
    val tablaSuenohoraslist = _tablaSuenohoraslist
    fun setTablaSuenohoraslist(list: List<String>?) {
        _tablaSuenohoraslist.value = list
    }

    fun leertablaSuenohoras(user: DataGraph) {
        _tablaSuenohorasResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.horasSueno(jwtToken.value,user)
                _tablaSuenohoraslist.value=response.Horas
                _tablaSuenohorasResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaSuenohorasResult.value =Result.failure(e)
            }
        }
    }

    //Usuario Pesos
    private val _tablaUsuarioPesosResult = MutableStateFlow<Result<PesosGraphResult>?>(null)
    val tablaUsuarioPesos = _tablaUsuarioPesosResult.asStateFlow()
    fun setTablaUsuarioPesos(result: Result<PesosGraphResult>?) {
        _tablaUsuarioPesosResult.value = result
    }
    private val _tablaUsuarioPesoslist = MutableStateFlow<List<Float>?>(null)
    val tablaUsuarioPesoslist = _tablaUsuarioPesoslist
    fun setTablaUsuarioPesoslist(list: List<Float>?) {
        _tablaUsuarioPesoslist.value = list
    }

    fun leertablaPesos(user: PesosGraph) {
        _tablaUsuarioPesosResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.jalarpesos(jwtToken.value,user)
                _tablaUsuarioPesoslist.value=response.pesos
                _tablaUsuarioPesosResult.value = Result.success(response)
            } catch(e: Exception){
                _tablaUsuarioPesosResult.value =Result.failure(e)
            }
        }
    }

    //Modificar Pesos
    private val _editarPesoResult = MutableStateFlow<Result<ModificarPesoResult>?>(null)
    val editarPesoResult = _editarPesoResult.asStateFlow()
    fun setEditarPesoResult(result: Result<ModificarPesoResult>?) {
        _editarPesoResult.value = result
    }

    fun editarPeso(user: ModifcarPeso) {
        _editarPesoResult.value = null
        viewModelScope.launch {
            try{
                val response = serviceApi.modificarpeso(jwtToken.value,user)
                _editarPesoResult.value=Result.success(response)
            } catch(e: Exception){
                _editarPesoResult.value =Result.failure(e)
            }
        }
    }






    init {
        tiposactividades()
        intensidades()
        emocionesAct()
        intensidadAnsiedad()
        sintomasAnsiedad()
        calidadesSueño()
        leerperiodospastillas()
        emocionespresion()
        tablaporciones()
        setExitodatos(false)
    }


}