module.exports = (sequelize, DataTypes) => {
    const Project = sequelize.define('Project', {
      project_id: {
        type : DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
      },
      email: {
        type : DataTypes.STRING,
        allowNull: false
      },
      project_name : {
        type: DataTypes.STRING,
        allowNull: false
      },
      description : {
        type: DataTypes.STRING,
        allowNull: false
      },
      date : {
        type: DataTypes.DATE,
        allowNull: false
      },
      note : {
        type: DataTypes.TEXT,
        allowNull: false
      },
      status : {
        type: DataTypes.BOOLEAN,
        allowNull: false
      },
      createdAt : {
        type: DataTypes.DATE,
        allowNull: false
      },
      updatedAt : {
        type: DataTypes.DATE,
        allowNull: false
      }
    },{ 
        tableName : 'projects',
        timezone: '+07:00' // Set waktu ke GMT+7
    });

    return Project;
}