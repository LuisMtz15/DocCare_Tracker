package com.example.ejemplosapis.service

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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserServiceApi {

    companion object {

        private val okHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()


        val instance: UserServiceApi = Retrofit.Builder().baseUrl("https://apis-bmzj.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(UserServiceApi::class.java)
    }

    //Registrar cualquier usuario

    @POST("users/usuarios/")
    suspend fun registrarUsuario(@Body user: RegistrarUsuario) : RespuestaRegistro

    //Registrar Detalle Usuario
    @POST("users/usuarios/detalles/")
    suspend fun registrarDetalleUsuario(@Body user: RegistrarDetalleUsuario) : RespuestaDetalleUsuario

    //Registrar Detalle Doctor
    @POST("users/usuarios/detallesdoc/{idDoctor}")
    suspend fun registrarDetalleDoctor(@Path("idDoctor") idDoctor: Int) : RespuestaDetalleDoctor


    //Login

    @POST("users/usuarios/login/")
    suspend fun usuarioLogin(@Body user: Login) : LoginRespuesta

    //ChecarToken

    @POST("users/usuarios/token/")
    suspend fun checarToken(@Body user: ChecarToken) : TokenRespuesta

    //Alimentos

    //Leer Alimentos
    @POST("data/alimentos/leer/{userId}")
    suspend fun leerAlimentos(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerAlimentosRespuesta

    //Agregar Alimentos
    @POST("data/alimentos/agregar")
    suspend fun agregarAlimentos(@Header("auth") token: String?, @Body user: AgregarAlimentos) : AgregarAlimentoRespuesta

    //Editar Alimentos
    @POST("data/alimentos/modificar")
    suspend fun editarAlimentos(@Header("auth") token: String?, @Body user: EditarAlimento) : EditarAlimentoRespuesta

    //Eliminar Alimentos

    @POST("data/alimentos/eliminar/{alimento_id}")
    suspend fun eliminarAlimentos(@Header("auth") token: String?, @Path("alimento_id") idAlimento: Int) : EliminarAlimentoRespuesta

    //Filtrar Alimentos
    @POST("data/alimentos/filtrar")
    suspend fun filtrarAlimentos(@Header("auth") token: String?, @Body user: FiltroAlimentos) : LeerAlimentosRespuesta


    //Actividades

    //Leer Actividades
    @POST("data/Actividad/leer/{userId}")
    suspend fun leerActividades(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerActividadRespuesta

    //Agregar Actividades
    @POST("data/Actividad/agregar")
    suspend fun agregarActividades(@Header("auth") token: String?, @Body user: AgregarActividad) : AgregarActividadRespuesta

    //Editar Actividades
    @POST("data/Actividad/modificar")
    suspend fun editarActividades(@Header("auth") token: String?, @Body user: ModificarActividad) : ModificarActividadResultado

    //Eliminar Actividades

    @POST("data/Actividad/eliminar/{actividad_id}")
    suspend fun eliminarActividades(@Header("auth") token: String?, @Path("actividad_id") idActividad: Int) : EliminarActividadRespuesta

    //Filtrar Actividades
    @POST("data/Actividad/filtrar")
    suspend fun filtrarActividades(@Header("auth") token: String?, @Body user: FiltroActividad) : LeerActividadRespuesta


    //Ansiedad

    //Leer Ansiedades
    @POST("data/ansiedad/leer/{userId}")
    suspend fun leerAnsiedades(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerAnsiedadRespuesta

    //Agregar Ansiedades
    @POST("data/ansiedad/agregar")
    suspend fun agregarAnsiedades(@Header("auth") token: String?, @Body user: AgregarAnsiedad) : AgregarAnsiedadRespuesta

    //Editar Ansiedades
    @POST("data/ansiedad/modificar")
    suspend fun editarAnsiedades(@Header("auth") token: String?, @Body user: ModificarAnsiedad) : ModificarAnsiedadRespuesta

    //Eliminar Ansiedades

    @POST("data/ansiedad/eliminar/{ansiedad_id}")
    suspend fun eliminarAnsiedades(@Header("auth") token: String?, @Path("ansiedad_id") idAnsiedad: Int) : EliminarAnsiedadRespuesta

    //Filtrar Ansiedades
    @POST("data/ansiedad/filtrar")
    suspend fun filtrarAnsiedades(@Header("auth") token: String?, @Body user: FiltroAnsiedad) : LeerAnsiedadRespuesta




    //Sueño

    //Leer Sueños
    @POST("data/sueno/leer/{userId}")
    suspend fun leerSuenos(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerSueñoRespuesta

    //Agregar Sueños
    @POST("data/sueno/agregar")
    suspend fun agregarSuenos(@Header("auth") token: String?, @Body user: AgregarSueño) : AgregarSueñoRespuesta

    //Editar Sueños
    @POST("data/sueno/modificar")
    suspend fun editarSuenos(@Header("auth") token: String?, @Body user: ModificarSueño) : ModificarSueñoRespuesta

    //Eliminar Sueños

    @POST("data/sueno/eliminar/{sueno_id}")
    suspend fun eliminarSuenos(@Header("auth") token: String?, @Path("sueno_id") idSueno: Int) : EliminarSueñoRespuesta

    //Filtrar Sueños
    @POST("data/sueno/filtrar")
    suspend fun filtrarSuenos(@Header("auth") token: String?, @Body user: FiltroSueño) : LeerSueñoRespuesta



    //Pastillas

    //Leer Pastillas
    @POST("data/pastillas/leer/{userId}")
    suspend fun leerPastillas(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerPastillasRespuesta

    //Agregar Pastillas
    @POST("data/pastillas/agregar")
    suspend fun agregarPastillas(@Header("auth") token: String?, @Body user: AgregarPastillas) : AgregarPastillasRespuesta

    //Editar Pastillas
    @POST("data/pastillas/modificar")
    suspend fun editarPastillas(@Header("auth") token: String?, @Body user: ModificarPastillas) : ModificarPastillasRespuesta

    //Eliminar Pastillas

    @POST("data/pastillas/eliminar/{pastillas_id}")
    suspend fun eliminarPastillas(@Header("auth") token: String?, @Path("pastillas_id") idPastillas: Int) : EliminarPastillaRespuesta

    //Filtrar Pastillas
    @POST("data/pastillas/filtrar")
    suspend fun filtrarPastillas(@Header("auth") token: String?, @Body user: FiltroPastillas) : LeerPastillasRespuesta



    //Presion

    //Leer Presiones
    @POST("data/presion/leer/{userId}")
    suspend fun leerPresiones(@Header("auth") token: String?, @Path("userId") idUsuario: Int)  : LeerPresionesRespuesta

    //Agregar Presiones
    @POST("data/presion/agregar")
    suspend fun agregarPresiones(@Header("auth") token: String?, @Body user: AgregarPresion) : AgregarPresionRespuesta

    //Editar Presiones
    @POST("data/presion/modificar")
    suspend fun editarPresiones(@Header("auth") token: String?, @Body user: ModificarPresion) : ModificarPresionRespuesta

    //Eliminar Presiones

    @POST("data/presion/eliminar/{presion_id}")
    suspend fun eliminarPresiones(@Header("auth") token: String?, @Path("presion_id") idPresion: Int) : EliminarPresionRespuesta

    //Filtrar Presiones
    @POST("data/presion/filtrar")
    suspend fun filtrarPresiones(@Header("auth") token: String?, @Body user: FiltroPresion) : LeerPresionesRespuesta




    //Modificar Informacion

    //Modificar Usuario
    @POST("users/usuarios/modificar")
    suspend fun editarUsuario(@Header("auth") token: String?, @Body user: ModificarDatosUsuario) : ModificarDatosUsuarioRespuesta

    //Modificar Doctor

    @POST("users/usuarios/modificardoc/")
    suspend fun editarDoctor(@Header("auth") token: String?, @Body user: ModificarDatosDoctor) : ModificarDatosDoctorRespuesta

    //Modificar Contraseña
    @POST("users/usuarios/modificar/pswd")
    suspend fun editarContraseña(@Header("auth") token: String?, @Body user: ModificarContraseña) : ModificarContraseñaRespuesta

    //Jalar Datos del Usuario
    @POST("users/usuarios/datos/{idUsuario}")
    suspend fun jalarDatosUsuario(@Header("auth") token: String?, @Path("idUsuario") idUsuario: Int) : JalarInformacionRespuesta

    //Jalar Datos del Usuario
    @POST("datadoc/datos_pacientes/{clave}")
    suspend fun jalarUsuariosDoc(@Header("auth") token: String?, @Path("clave") clave: String) : JalarUsuariosDocRespuesta



    //Tablas

    //Porciones
    @GET("/tablas/tiposporciones")
    suspend fun tablasPorciones() : TablasTipoPorciones

    //Intensidades Actividades
    @GET("/tablas/intensidadesactividades")
    suspend fun IntensidadesAct() : IntensidadesActividad

    //Tipos de Actividades
    @GET("/tablas/tiposactividades")
    suspend fun TiposActividad() : TiposActividades

    //Emociones Actividades
    @GET("/tablas/emocionesactividades")
    suspend fun EmocionesAct() : EmocionesActividades

    //Sintomas Ansiedad
    @GET("/tablas/sintomasansiedad")
    suspend fun SintomasAns() : SintomasAnsiedad

    //Intensidades Ansiedad
    @GET("/tablas/intensidadesansiedad")
    suspend fun IntensidadesAns() : IntensidadAnsiedad

    // Calidades Sueño
    @GET("/tablas/calidadessueno")
    suspend fun Calidadessueno() : CalidadesSueño

    // Periodo Pastillas
    @GET("/tablas/periodopastillas")
    suspend fun PeriodoPastillas() : PeriodoPastillas

    // Emociones Presion
    @GET("/tablas/emocionespresion")
    suspend fun EmocionesPresion() : EmocionesPresion




}




















