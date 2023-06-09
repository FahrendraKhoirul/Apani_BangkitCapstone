'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    await queryInterface.createTable('articles', {
      article_id: {
          type : Sequelize.INTEGER,
          primaryKey: true,
          autoIncrement: true,
          allowNull: false
      },
      title : {
        type: Sequelize.STRING,
        allowNull: false
      },
      author : {
        type: Sequelize.STRING,
        allowNull: false
      },
      date : {
        type: Sequelize.DATE,
        allowNull: false
      },
      article : {
        type: Sequelize.TEXT,
        allowNull: false
      },
      source : {
        type: Sequelize.STRING,
        allowNull: false
      },
      img_url : {
        type: Sequelize.STRING,
        allowNull: false
      },
      createdAt : {
        type: Sequelize.DATE,
        allowNull: false
      },
      updatedAt : {
        type: Sequelize.DATE,
        allowNull: false
      }
    });
  },

  async down (queryInterface, Sequelize) {
    await queryInterface.dropTable('articles');
  }
};