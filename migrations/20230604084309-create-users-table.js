'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
     await queryInterface.createTable('users', { 
      user_id: {
        type : Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
      },
      username : {
        type: Sequelize.STRING,
        allowNull: true
      }, 
      email: {
        type : Sequelize.STRING,
        allowNull: false
      },
      password : {
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
    await queryInterface.dropTable('users');
  }
};
