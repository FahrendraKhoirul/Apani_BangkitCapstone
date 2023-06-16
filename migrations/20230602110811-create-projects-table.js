'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    await queryInterface.createTable('projects', {
    project_id: {
      type : Sequelize.INTEGER,
      primaryKey: true,
      autoIncrement: true,
      allowNull: false
    },
    email: {
      type : Sequelize.STRING,
      allowNull: false
    },
    project_name : {
      type: Sequelize.STRING,
      allowNull: false
    },
    description : {
      type: Sequelize.STRING,
      allowNull: false
    },
    date : {
      type: Sequelize.STRING,
      allowNull: false
    },
    note : {
      type: Sequelize.TEXT,
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
  })
  },

  async down (queryInterface, Sequelize) {
    await queryInterface.dropTable('projects');
  }
};