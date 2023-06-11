module.exports = (sequelize, DataTypes) => {
    const Article = sequelize.define('Article', {
        article_id: {
            type : DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false
        },
        title : {
          type: DataTypes.STRING,
          allowNull: false
        },
        author : {
          type: DataTypes.STRING,
          allowNull: false
        },
        date : {
          type: DataTypes.DATE,
          allowNull: false
        },
        article : {
          type: DataTypes.TEXT,
          allowNull: false
        },
        source : {
          type: DataTypes.STRING,
          allowNull: false
        },
        img_url : {
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
        tableName : 'articles',
        timezone: '+07:00' // Set waktu ke GMT+7
    });

    return Article;
}