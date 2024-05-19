require('dotenv').config();
const express = require('express');
const sequelize = require('./config/database');
const userRoutes = require('./routes/User_routes');
const dataRoutes = require('./routes/Data_routes');
const dataDocRoutes = require('./routes/Doc_routes');
const tablasRoutes = require('./routes/Tablas_routes');
const { Role, Sexo } = require('./models/User');
const { TiposPorciones, EmocionesActividades,TiposActividades,IntensidadesActividades, PastillasDormir, CalidadesSueño,sintomasAnsiedad,intensidadesAnsiedad, TiempoPastillas,PeriodoPastillas, EmocionesPresion} = require('./models/Datosmain');
const app = express();
app.use(express.json());

sequelize.authenticate()
  .then(async () => {
    console.log('Conexion establecida.');
    // Sincroniza modelos y fuerza la creación de tablas (borra y recrea todas las tablas)
    //await sequelize.sync({ force: true });

    // Función para agregar roles y sexos a la base de datos
    async function agregarDatosIniciales() {
      try {
        // Agregar roles si no existen
        await Role.findOrCreate({ where: { nombre: 'Usuario' } });
        await Role.findOrCreate({ where: { nombre: 'Doctor' } });
        await Role.findOrCreate({ where: { nombre: 'Admin' } });

        // Agregar sexos si no existen
        await Sexo.findOrCreate({ where: { sexo: 'Masculino' } });
        await Sexo.findOrCreate({ where: { sexo: 'Femenino' } });

        
        // Agregar datos a TiposPorciones
        
        await TiposPorciones.findOrCreate({ where: { tipo: 'Chico' }});
        await TiposPorciones.findOrCreate({ where: { tipo: 'Mediano' }});
        await TiposPorciones.findOrCreate({ where: { tipo: 'Grande' }});
        await TiposPorciones.findOrCreate({ where: { tipo: 'Jumbo' }});
        
        
        // Agregar datos a EmocionesActividad
        
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Triste' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Normal' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Feliz' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Decaido' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Emocionado' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Tranquilo' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Disgustado' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Cansado' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Intranquilo' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Enojado' }});
        await EmocionesActividades.findOrCreate({ where: { emocion: 'Desesperado' }});
        

        // Agregar datos a TiposActividades
        
        await TiposActividades.findOrCreate({ where: { tipo: 'Aeróbicos' }});
        await TiposActividades.findOrCreate({ where: { tipo: 'Aneaeróbicos' }});
        await TiposActividades.findOrCreate({ where: { tipo: 'Equilibrado' }});
        

        // Agregar datos a IntensidadesActividades
        
        await IntensidadesActividades.findOrCreate({ where: { intensidad: 'Baja' }});
        await IntensidadesActividades.findOrCreate({ where: { intensidad: 'Moderada' }});
        await IntensidadesActividades.findOrCreate({ where: { intensidad: 'Alta' }});
        

        // Agregar datos a PastillasDormir
        
        await PastillasDormir.findOrCreate({ where: { pastilla: 'SI' }});
        await PastillasDormir.findOrCreate({ where: { pastilla: 'NO' }});
        

        // Agregar datos a CalidadesSueño
        
        await CalidadesSueño.findOrCreate({ where: { emocion: 'Molesto' }});
        await CalidadesSueño.findOrCreate({ where: { emocion: 'Normal' }});
        await CalidadesSueño.findOrCreate({ where: { emocion: 'Tranquilo' }});
        await CalidadesSueño.findOrCreate({ where: { emocion: 'No sentí la noche' }});
        await CalidadesSueño.findOrCreate({ where: { emocion: 'Incomodo' }});
      

        // Agregar datos a sintomasAnsiedad
        
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Dolor de cabeza' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Náuseas' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Palpitaciones' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Sudoración' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Mareos' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Fatiga' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Dificultad para respirar' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Tensión muscular' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Problemas gastrointestinales' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Problemas de sueño' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Irritabilidad' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Inquietud' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Dificultad para concentrarse' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Preocupación excesiva' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Miedo intenso' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Evitación de situaciones temidas' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Pensamientos intrusivos' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Compulsiones' }});
        await sintomasAnsiedad.findOrCreate({ where: { sintoma: 'Obsesiones' }});

        // Agregar datos a intensidadesAnsiedad
        await intensidadesAnsiedad.findOrCreate({ where: { intensidad: 'Leve' }});
        await intensidadesAnsiedad.findOrCreate({ where: { intensidad: 'Moderada' }});
        await intensidadesAnsiedad.findOrCreate({ where: { intensidad: 'Severa' }});
        await intensidadesAnsiedad.findOrCreate({ where: { intensidad: 'Muy severa' }});

        // Agregar datos a TiempoPastillas
        await TiempoPastillas.findOrCreate({ where: { tiempo: 'Temporal' }});
        await TiempoPastillas.findOrCreate({ where: { tiempo: 'Cronico' }});

        // Agregar datos a PeriodoPastillas
        
        await PeriodoPastillas.findOrCreate({ where: { periodo: 1 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 2 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 3 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 4 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 5 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 6 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 8 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 12 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 16 }});
        await PeriodoPastillas.findOrCreate({ where: { periodo: 24 }});
      

        // Agregar datos a EmocionesPresion
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Agitado' }});
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Normal' }});
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Tranquilo' }});
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Mareado' }});
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Incomodo' }});
        await EmocionesPresion.findOrCreate({ where: { emocion: 'Presionado' }});

        console.log('\n \n \n Datos iniciales agregados correctamente. \n \n \n');
      } catch (error) {
        console.error('Error al agregar datos iniciales:', error);
      }
    }
    // Llama a la función para agregar datos iniciales después de sincronizar modelos
    await agregarDatosIniciales();
    return sequelize.sync();
  })
  .catch(err => {
    console.error('Unable to connect to the database:', err);
  });

app.use("/users", userRoutes);
app.use("/data", dataRoutes);
app.use("/datadoc", dataDocRoutes);
app.use("/tablas", tablasRoutes);

app.listen(process.env.PORT || 3000, () => {
  console.log('\n \n \n Server is running \n \n \n');
});
