module.exports = (sequelize, DataTypes) => {
    const User = sequelize.define('User', {
        user_id: {
            type : DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false
          },
        username : {
            type: DataTypes.STRING,
            allowNull: true
          }, 
        email: {
            type : DataTypes.STRING,
            allowNull: false
          },
        password : {
            type: DataTypes.STRING,
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
        tableName : 'Users',
        timezone: '+07:00' // Set waktu ke GMT+7
    });

    return User;
}