import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejemplosapis.viewModel.AppViewModel


@Composable
fun AppNavigation(viewModel: AppViewModel, startDestination: String = AppScreens.InicioSesion.route, ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =startDestination) {
        composable(route = AppScreens.InicioSesion.route) {
            screen_inicio(navController,viewModel)
        }
        composable(route = AppScreens.PersonalUser.route) {
            screen_personalUser(navController,viewModel)
        }
        composable(route = AppScreens.PersonalDoc.route) {
            Screen_personalDoc(navController,viewModel)
        }

        composable(route = AppScreens.RegistroUser.route) {
            Registro_prin(navController,viewModel)
        }

        composable(route = AppScreens.RegistroDoc.route) {
            Registro_prin_doc(navController,viewModel)
        }

        composable(route = AppScreens.clave.route) {
            clave(navController,viewModel)
        }

        //Inicios de Usuarios
        composable(route = AppScreens.iniciodoc.route) {
            Inicio_doc(navController,viewModel)
        }

        composable(route = AppScreens.iniciouser.route) {
            Inicio_usuario(navController,viewModel)
        }

        //Modificar Contraseña

        composable(route = AppScreens.ContraseñaDoc.route) {
            ContraseñaDoc(navController,viewModel)
        }

        composable(route = AppScreens.ContraseñaUser.route) {
            ContraseñaUser(navController,viewModel)
        }


        //Interfaz de Doctor

        composable(route = AppScreens.InfoPersonalDoc.route) {
            Info_doc(navController,viewModel)
        }
        composable(route = AppScreens.InfoUsuaroDocSeleccion.route) {
            Info_usuario_doc(navController,viewModel)
        }
        composable(route = AppScreens.ModfiicarInfo.route) {
            Mod_info_doc(navController,viewModel)
        }

        //Interfaz de Usuario

        composable(route = AppScreens.InfoPersonalUser.route) {
            Info_Usuario(navController,viewModel)
        }
        composable(route = AppScreens.resumenUser.route) {
            Resumen_usuario(navController,viewModel)
        }
        composable(route = AppScreens.ModfiicarInfoUser.route) {
            Modi_personal_usuario(navController,viewModel)
        }

        //Procesos de Usuario

        composable(route = AppScreens.Actividad.route) {
            Inicio_Actividad(navController,viewModel)
        }
        composable(route = AppScreens.Alimentos.route) {
            Inicio_Alimentos(navController,viewModel)
        }
        composable(route = AppScreens.Sueño.route) {
            Inicio_Sueño(navController,viewModel)
        }
        composable(route = AppScreens.Presion.route) {
            Inicio_Presion(navController,viewModel)
        }
        composable(route = AppScreens.Pastillas.route) {
            Inicio_Pastillas(navController,viewModel)
        }
        composable(route = AppScreens.Ansiedad.route) {
            Inicio_Ansiedad(navController,viewModel)
        }



        //Procesos Individuales Alimentos

        composable(route = AppScreens.AlimentosAgregar.route) {
            Agregar_Alimentos(navController,viewModel)
        }
        composable(route = AppScreens.AlimentosEditar.route) {
            Editar_Alimentos_Especifico(navController,viewModel)
        }
        composable(route = AppScreens.AlimentosModificar.route) {
            Modificar_alimentos(navController,viewModel)
        }

        //Procesos Individuales Actividad

        composable(route = AppScreens.ActividadAgregar.route) {
            Agregar_Actividad(navController,viewModel)
        }
        composable(route = AppScreens.ActividadEditar.route) {
            Editar_Actividad_especifica(navController,viewModel)
        }
        composable(route = AppScreens.ActividadModificar.route) {
            Modificar_actividad(navController,viewModel)
        }

        //Procesos Individuales Ansiedad

        composable(route = AppScreens.AnsiedadAgregar.route) {
            Agregar_Ansiedad(navController,viewModel)
        }
        composable(route = AppScreens.AnsiedadEditar.route) {
            Editar_sintoma_especifico(navController,viewModel)
        }
        composable(route = AppScreens.AnsiedadModificar.route) {
            Modificar_sintomas(navController,viewModel)
        }

        //Procesos Individuales Presion

        composable(route = AppScreens.PresionAgregar.route) {
            Agregar_Presion(navController,viewModel)
        }
        composable(route = AppScreens.PresionEditar.route) {
            Editar_Presion_especifica(navController,viewModel)
        }
        composable(route = AppScreens.PresionModificar.route) {
            Modificar_presion(navController,viewModel)
        }

        //Procesos Individuales Sueño

        composable(route = AppScreens.SueñoAgregar.route) {
            Agregar_Sueño(navController,viewModel)
        }
        composable(route = AppScreens.SueñoEditar.route) {
            Editar_sueño_especifico(navController,viewModel)
        }
        composable(route = AppScreens.SueñoModificar.route) {
            Modificar_Sueño(navController,viewModel)
        }

        //Procesos Individuales Pastillas

        composable(route = AppScreens.PastillasAgregar.route) {
            Agregar_Pastillas(navController,viewModel)
        }
        composable(route = AppScreens.PastillasEditar.route) {
            Editar_pastilla_especifica(navController,viewModel)
        }
        composable(route = AppScreens.PastillasModificar.route) {
            Modificar_pastillas(navController,viewModel)
        }



    }
}