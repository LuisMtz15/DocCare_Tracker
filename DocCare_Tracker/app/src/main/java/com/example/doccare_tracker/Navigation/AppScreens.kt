sealed class AppScreens(val route:String){

    //Registros sin cuenta

    object InicioSesion:AppScreens("Inicio_Sesion")
    object PersonalUser:AppScreens("Personal_User")
    object PersonalDoc:AppScreens("Personal_Doc")
    object RegistroUser:AppScreens("Registro_User")
    object RegistroDoc:AppScreens("Registro_Doc")
    object clave:AppScreens("User_Doc")
    object iniciodoc:AppScreens("Inicio_Doc")
    object iniciouser:AppScreens("Inicio_User")

    //Contraseña
    object ContraseñaDoc:AppScreens("Contraseña_Doc")
    object ContraseñaUser:AppScreens("Contraseña_User")

    //Inicio Doctor
    object InfoPersonalDoc:AppScreens("info_personal_doc")
    object InfoUsuaroDocSeleccion:AppScreens("info_usuario_doc_seleccion")
    object ModfiicarInfo:AppScreens("modificar_info")

    //Inicio Usuario
    object InfoPersonalUser:AppScreens("info_personal_user")
    object resumenUser:AppScreens("resumen_user")
    object ModfiicarInfoUser:AppScreens("modificar_info_user")

    //Procesos Usuario

    object Actividad:AppScreens("Actividad_user")
    object Alimentos:AppScreens("Alimentos_user")
    object Ansiedad:AppScreens("Ansiedad_user")
    object Sueño:AppScreens("Sueño_user")
    object Pastillas:AppScreens("Pastillas_user")
    object Presion:AppScreens("Presion_user")

    //Procesos Individuales Alimentos

    object AlimentosAgregar:AppScreens("Alimentos_agregar")
    object AlimentosModificar:AppScreens("Alimentos_modificar")
    object AlimentosEditar:AppScreens("Alimentos_editar")

    //Procesos Individuales Actividad

    object ActividadAgregar:AppScreens("Actividad_agregar")
    object ActividadModificar:AppScreens("Actividad_modificar")
    object ActividadEditar:AppScreens("Actividad_editar")
    object DudasActividad:AppScreens("Dudas")

    //Procesos Individuales Ansiedad

    object AnsiedadAgregar:AppScreens("Ansiedad_agregar")
    object AnsiedadModificar:AppScreens("Ansiedad_modificar")
    object AnsiedadEditar:AppScreens("Ansiedad_editar")

    //Procesos Individuales Presion

    object PresionAgregar:AppScreens("Presion_agregar")
    object PresionModificar:AppScreens("Presion_modificar")
    object PresionEditar:AppScreens("Presion_editar")

    //Procesos Individuales Sueño

    object SueñoAgregar:AppScreens("Sueño_agregar")
    object SueñoModificar:AppScreens("Sueño_modificar")
    object SueñoEditar:AppScreens("Sueño_editar")

    //Procesos Individuales Pastillas

    object PastillasAgregar:AppScreens("Pastillas_agregar")
    object PastillasModificar:AppScreens("Pastillas_modificar")
    object PastillasEditar:AppScreens("Pastillas_editar")








}
