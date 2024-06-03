const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');
const bcrypt = require('bcrypt');


const Pesos = sequelize.define('Pesos', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  usuario_id: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  peso: {
    type: DataTypes.DECIMAL(5, 2),
    allowNull: false
  }
});


const Role = sequelize.define('Roles', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true, 
    autoIncrement: true
  },
  nombre: {
    type: DataTypes.STRING(20),
    unique: true,
    allowNull: false
  }
});

const Sexo = sequelize.define('Sexos', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true, 
    autoIncrement: true
  },
  sexo: {
    type: DataTypes.STRING(20),
    unique: true,
    allowNull: false
  }
});

const User = sequelize.define('Usuarios', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true, 
    autoIncrement: true
  },
  nombre: {
    type: DataTypes.STRING(50),
    allowNull: false
  },
  apellido_paterno: {
    type: DataTypes.STRING(50),
    allowNull: false
  },
  apellido_materno: {
    type: DataTypes.STRING(50),
    allowNull: false
  },
  email: {
    type: DataTypes.STRING(100),
    allowNull: false,
    unique: true
  },
  password: {
    type: DataTypes.STRING(100),
    allowNull: false
  },
  fecha_nacimiento: {
    type: DataTypes.DATE,
    allowNull: false
  },
  rol_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'Roles',
      key: 'id'
    }
  }
},{
    hooks: {
      beforeCreate: async (user) => {
        const salt = await bcrypt.genSalt(10);
        user.password = await bcrypt.hash(user.password, salt);
      }
    }
});

const DetalleUsuario = sequelize.define('DetallesUsuario', {
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
  altura: {
    type: DataTypes.DECIMAL(5, 2),
    allowNull: true
  },
  peso: {
    type: DataTypes.DECIMAL(5, 2),
    allowNull: true
  },
  circunferencia_abdominal: {
    type: DataTypes.DECIMAL(5, 2),
    allowNull: true
  },
  clave_unica: {
    type: DataTypes.STRING(50),
    defaultValue: null
  },
  sexo_id: {
    type: DataTypes.INTEGER,
    allowNull: true,
    references: {
      model: 'Sexos',
      key: 'id'
    }
  }
});

const DetalleDoctor = sequelize.define('DetallesDoctor', {
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
  clave_unica: {
    type: DataTypes.STRING(50),
    allowNull: false,
    unique: true
  }
},{freezeTableName: true});

//Asociaciones
User.belongsTo(Role, { foreignKey: 'rol_id', as: 'rol' });
DetalleUsuario.belongsTo(Sexo, { foreignKey: 'sexo_id', as: 'sexo' });

/*User.hasOne(DetalleUsuario, { foreignKey: 'usuario_id', as: 'detalle_usuario' });

User.hasOne(DetalleDoctor, { foreignKey: 'usuario_id', as: 'detalle_doctor' });*/

DetalleUsuario.belongsTo(User, { foreignKey: 'usuario_id', as: 'User' });

DetalleDoctor.belongsTo(User, { foreignKey: 'usuario_id', as: 'UserDoc' });

module.exports = { User, Role, Sexo, DetalleUsuario, DetalleDoctor, Pesos};
