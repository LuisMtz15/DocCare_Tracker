const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');
//const {User} = require('./User');


//Alimentos
const TiposPorciones = sequelize.define('TiposPorciones', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  tipo: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
});


const Alimentos = sequelize.define('Alimentos', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  nombre: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: false
  },
  tipo_porcion_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'TiposPorciones',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false
  }
});

//Actividades Fisicas

const EmocionesActividades = sequelize.define('EmocionesActividades', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  emocion: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
});

const TiposActividades = sequelize.define('TiposActividades', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  tipo: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
});

const IntensidadesActividades = sequelize.define('IntensidadesActividades', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  intensidad: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
});

const ActividadesFisicas = sequelize.define('ActividadesFisicas', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  tipo_actividad_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'TiposActividades',
      key: 'id'
    }
  },
  emocion_actividad_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'EmocionesActividades',
      key: 'id'
    }
  },
  intensidad_actividad_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'IntensidadesActividades',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false
  },
  duracion: {
    type: DataTypes.INTEGER,
    allowNull: false
  }
});

//Sueño

const PastillasDormir = sequelize.define('PastillasDormir', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  pastilla: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
}, {freezeTableName: true});

const CalidadesSueño = sequelize.define('CalidadesSueño', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  emocion: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
}, {freezeTableName: true});

const Sueño = sequelize.define('Sueño', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  pastilla_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'PastillasDormir',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false
  },
  horas: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  calidad_sueño_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'CalidadesSueño',
      key: 'id'
    }
  },
  horaDormir:{
    type: DataTypes.TIME,
    allowNull: false,
  },
  horaDespertar:{
    type: DataTypes.TIME,
    allowNull: false,
  }
}, {freezeTableName: true});

//Ansiedad

const sintomasAnsiedad = sequelize.define('SintomasAnsiedad', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  sintoma: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
},{freezeTableName: true});

const intensidadesAnsiedad = sequelize.define('IntensidadesAnsiedad', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  intensidad: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
},{freezeTableName: true});

const Ansiedad = sequelize.define('Ansiedad', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false
  },
  hora: {
    type: DataTypes.TIME,
    allowNull: false
  },
  sintoma_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'SintomasAnsiedad',
      key: 'id'
    }
  },
  intensidad_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'IntensidadesAnsiedad',
      key: 'id'
    }
  },
  nota: {
    type: DataTypes.STRING(500),
    allowNull: true
  }
},{freezeTableName: true});

//Pastillas

const TiempoPastillas = sequelize.define('TiempoPastillas', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  tiempo: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
});

const PeriodoPastillas = sequelize.define('PeriodoPastillas', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  periodo: {
    type: DataTypes.INTEGER,
    allowNull: false,
    unique: true
  }
});

const Pastillas = sequelize.define('Pastillas', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  nombre: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: false
  },
  dosis: {
    type: DataTypes.STRING(50),
    allowNull: false
  },
  tiempo_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'TiempoPastillas',
      key: 'id'
    }
  },
  periodo_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'PeriodoPastillas',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: true
  }
});

//PresionArterial

const EmocionesPresion = sequelize.define('EmocionesPresion', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  emocion: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
},{freezeTableName: true});

const PresionArterial = sequelize.define('PresionArterial', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Usuarios',
      key: 'id'
    }
  },
  fecha: {
    type: DataTypes.DATEONLY,
    allowNull: false
  },
  hora: {
    type: DataTypes.TIME,
    allowNull: false
  },
  sistolica: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  diastolica: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  emocion_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'EmocionesPresion',
      key: 'id'
    }
  }
},{freezeTableName: true});

//Asociaciones

Alimentos.belongsTo(TiposPorciones, {
  foreignKey: 'tipo_porcion_id',
  as: 'tipo_porcion',
});

ActividadesFisicas.belongsTo(TiposActividades, {
  foreignKey: 'tipo_actividad_id',
  as: 'tipo_actividad',
});

ActividadesFisicas.belongsTo(EmocionesActividades, {
  foreignKey: 'emocion_actividad_id',
  as: 'emocion_actividad',
});

ActividadesFisicas.belongsTo(IntensidadesActividades, {
  foreignKey: 'intensidad_actividad_id',
  as: 'intensidad_actividad',
});

Sueño.belongsTo(PastillasDormir, {
  foreignKey: 'pastilla_id',
  as: 'pastilla'
});

Sueño.belongsTo(CalidadesSueño, {
  foreignKey: 'calidad_sueño_id',
  as: 'calidad'
});

Ansiedad.belongsTo(sintomasAnsiedad, {
  foreignKey: 'sintoma_id',
  as: 'sintoma'
});

Ansiedad.belongsTo(intensidadesAnsiedad, {
  foreignKey: 'intensidad_id',
  as: 'intensidad'
});

Pastillas.belongsTo(TiempoPastillas, {
  foreignKey: 'tiempo_id',
  as: 'tiempo'
});

Pastillas.belongsTo(PeriodoPastillas, {
  foreignKey: 'periodo_id',
  as: 'periodo'
});

PresionArterial.belongsTo(EmocionesPresion, {
  foreignKey: 'emocion_id',
  as: 'emocion'
});



module.exports = {TiposPorciones, Alimentos, EmocionesActividades, TiposActividades, IntensidadesActividades, ActividadesFisicas, PastillasDormir, CalidadesSueño, Sueño, sintomasAnsiedad, intensidadesAnsiedad, Ansiedad, TiempoPastillas, PeriodoPastillas, Pastillas, EmocionesPresion, PresionArterial};